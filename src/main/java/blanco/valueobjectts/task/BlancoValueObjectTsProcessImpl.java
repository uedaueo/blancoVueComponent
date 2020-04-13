/*
 * blanco Framework
 * Copyright (C) 2004-2008 IGA Tosiki
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
package blanco.valueobjectts.task;

import blanco.cg.BlancoCgSupportedLang;
import blanco.valueobjectts.BlancoValueObjectTsConstants;
import blanco.valueobjectts.BlancoValueObjectTsMeta2Xml;
import blanco.valueobjectts.BlancoValueObjectTsXml2TypeScriptClass;
import blanco.valueobjectts.BlancoValueObjectTsXmlParser;
import blanco.valueobjectts.message.BlancoValueObjectTsMessage;
import blanco.valueobjectts.task.valueobject.BlancoValueObjectTsProcessInput;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class BlancoValueObjectTsProcessImpl implements BlancoValueObjectTsProcess {

    /**
     * メッセージ。
     */
    private final BlancoValueObjectTsMessage fMsg = new BlancoValueObjectTsMessage();

    /**
     * {@inheritDoc}
     */
    public int execute(final BlancoValueObjectTsProcessInput input)
            throws IOException, IllegalArgumentException {
        System.out.println("- " + BlancoValueObjectTsConstants.PRODUCT_NAME
                + " (" + BlancoValueObjectTsConstants.VERSION + ")");
        try {
            final File fileMetadir = new File(input.getMetadir());
            if (fileMetadir.exists() == false) {
                throw new IllegalArgumentException(fMsg.getMbvoja01(input
                        .getMetadir()));
            }

            /*
             * targetdir, targetStyleの処理。
             * 生成されたコードの保管場所を設定する。
             * targetstyle = blanco:
             *  targetdirの下に main ディレクトリを作成
             * targetstyle = maven:
             *  targetdirの下に main/java ディレクトリを作成
             * targetstyle = free:
             *  targetdirをそのまま使用してディレクトリを作成。
             *  ただしtargetdirがからの場合はデフォルト文字列(blanco)使用する。
             * by tueda, 2019/08/30
             */
            String strTarget = input.getTargetdir();
            String style = input.getTargetStyle();
            // ここを通ったら常にtrue
            boolean isTargetStyleAdvanced = true;
            if (style != null && BlancoValueObjectTsConstants.TARGET_STYLE_MAVEN.equals(style)) {
                strTarget = strTarget + "/" + BlancoValueObjectTsConstants.TARGET_DIR_SUFFIX_MAVEN;
            } else if (style == null ||
                    !BlancoValueObjectTsConstants.TARGET_STYLE_FREE.equals(style)) {
                strTarget = strTarget + "/" + BlancoValueObjectTsConstants.TARGET_DIR_SUFFIX_BLANCO;
            }
            /* style が free だったらtargetdirをそのまま使う */
            if (input.getVerbose()) {
                System.out.println("/* tueda */ TARGETDIR = " + strTarget);
            }

            // テンポラリディレクトリを作成。
            new File(input.getTmpdir()
                    + BlancoValueObjectTsConstants.TARGET_SUBDIRECTORY).mkdirs();

            new BlancoValueObjectTsMeta2Xml().processDirectory(fileMetadir, input
                    .getTmpdir()
                    + BlancoValueObjectTsConstants.TARGET_SUBDIRECTORY);

            // XML化されたメタファイルからValueObjectを生成
            // 最初にテンポラリフォルダを走査
            final File[] fileMeta2 = new File(input.getTmpdir()
                    + BlancoValueObjectTsConstants.TARGET_SUBDIRECTORY)
                    .listFiles();

        /*
         * まず始めにすべてのシートを検索して，クラス名とpackage名のリストを作ります．
         * php形式の定義書では，クラスを指定する際にpackage名が指定されていないからです．
         */
            BlancoValueObjectTsXmlParser.classList =
                    BlancoValueObjectTsXmlParser.createClassListFromSheets(fileMeta2);

            // 次にメタディレクトリとして指定されているディレクトリを走査
            for (int index = 0; index < fileMeta2.length; index++) {
                if (fileMeta2[index].getName().endsWith(".xml") == false) {
                    continue;
                }

                final BlancoValueObjectTsXml2TypeScriptClass xml2KotlinClass = new BlancoValueObjectTsXml2TypeScriptClass();
                xml2KotlinClass.setEncoding(input.getEncoding());
                xml2KotlinClass.setVerbose(input.getVerbose());
                xml2KotlinClass.setTargetStyleAdvanced(isTargetStyleAdvanced);
                xml2KotlinClass.setXmlRootElement(input.getXmlrootelement());
                xml2KotlinClass.setSheetLang(new BlancoCgSupportedLang().convertToInt(input.getSheetType()));
                xml2KotlinClass.process(fileMeta2[index], new File(strTarget));

                // 単体試験コードの自動生成機能は 0.9.1以降では削除されました。
            }

//            // 次にメタディレクトリとして指定されているディレクトリを走査
//            final File[] fileMeta3 = fileMetadir.listFiles();
//            for (int index = 0; index < fileMeta3.length; index++) {
//                if (fileMeta3[index].getName().endsWith(".xml") == false) {
//                    continue;
//                }
//
//                final BlancoValueObjectTsXml2TypeScriptClass xml2JavaClass = new BlancoValueObjectTsXml2TypeScriptClass();
//                xml2JavaClass.setEncoding(input.getEncoding());
//                xml2JavaClass.setVerbose(input.getVerbose());
//                xml2JavaClass.setTargetStyleAdvanced(isTargetStyleAdvanced);
//                xml2JavaClass.setXmlRootElement(input.getXmlrootelement());
//                xml2JavaClass.setSheetLang(new BlancoCgSupportedLang().convertToInt(input.getSheetType()));
//                xml2JavaClass.process(fileMeta3[index], new File(strTarget));
//
//                // 単体試験コードの自動生成機能は 0.9.1以降では削除されました。
//            }

            return BlancoValueObjectTsBatchProcess.END_SUCCESS;
        } catch (TransformerException e) {
            throw new IOException("XML変換の過程で例外が発生しました: " + e.toString());
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean progress(final String argProgressMessage) {
        System.out.println(argProgressMessage);
        return false;
    }
}
