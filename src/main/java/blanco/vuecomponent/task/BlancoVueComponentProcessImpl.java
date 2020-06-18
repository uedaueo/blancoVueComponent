/*
 * blanco Framework
 * Copyright (C) 2004-2008 IGA Tosiki
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
package blanco.vuecomponent.task;

import blanco.cg.BlancoCgSupportedLang;
import blanco.vuecomponent.*;
import blanco.vuecomponent.message.BlancoVueComponentMessage;
import blanco.vuecomponent.task.valueobject.BlancoVueComponentProcessInput;
import blanco.vuecomponent.valueobject.BlancoVueComponentClassStructure;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BlancoVueComponentProcessImpl implements BlancoVueComponentProcess {

    /**
     * メッセージ。
     */
    private final BlancoVueComponentMessage fMsg = new BlancoVueComponentMessage();

    /**
     * {@inheritDoc}
     */
    public int execute(final BlancoVueComponentProcessInput input)
            throws IOException, IllegalArgumentException {
        System.out.println("- " + BlancoVueComponentConstants.PRODUCT_NAME
                + " (" + BlancoVueComponentConstants.VERSION + ")");
        try {
            final File fileMetadir = new File(input.getMetadir());
            if (fileMetadir.exists() == false) {
                throw new IllegalArgumentException(fMsg.getMbvoja01(input
                        .getMetadir()));
            }

            /*
             * 改行コードを決定します。
             */
            String LF = "\n";
            String CR = "\r";
            String CRLF = CR + LF;
            String lineSeparatorMark = input.getLineSeparator();
            String lineSeparator = "";
            if ("LF".equals(lineSeparatorMark)) {
                lineSeparator = LF;
            } else if ("CR".equals(lineSeparatorMark)) {
                lineSeparator = CR;
            } else if ("CRLF".equals(lineSeparatorMark)) {
                lineSeparator = CRLF;
            }
            if (lineSeparator.length() != 0) {
                System.setProperty("line.separator", lineSeparator);
                if (input.getVerbose()) {
                    System.out.println("lineSeparator try to change to " + lineSeparatorMark);
                    String newProp = System.getProperty("line.separator");
                    String newMark = "other";
                    if (LF.equals(newProp)) {
                        newMark = "LF";
                    } else if (CR.equals(newProp)) {
                        newMark = "CR";
                    } else if (CRLF.equals(newProp)) {
                        newMark = "CRLF";
                    }
                    System.out.println("New System Props = " + newMark);
                }
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
            if (style != null && BlancoVueComponentConstants.TARGET_STYLE_MAVEN.equals(style)) {
                strTarget = strTarget + "/" + BlancoVueComponentConstants.TARGET_DIR_SUFFIX_MAVEN;
            } else if (style == null ||
                    !BlancoVueComponentConstants.TARGET_STYLE_FREE.equals(style)) {
                strTarget = strTarget + "/" + BlancoVueComponentConstants.TARGET_DIR_SUFFIX_BLANCO;
            }
            /* style が free だったらtargetdirをそのまま使う */
            if (input.getVerbose()) {
                System.out.println("/* tueda */ TARGETDIR = " + strTarget);
            }

            // テンポラリディレクトリを作成。
            new File(input.getTmpdir()
                    + BlancoVueComponentConstants.TARGET_SUBDIRECTORY).mkdirs();

            new BlancoVueComponentMeta2Xml().processDirectory(fileMetadir, input
                    .getTmpdir()
                    + BlancoVueComponentConstants.TARGET_SUBDIRECTORY);

            // XML化されたメタファイルからValueObjectを生成
            // 最初にテンポラリフォルダを走査
            final File[] fileMeta2 = new File(input.getTmpdir()
                    + BlancoVueComponentConstants.TARGET_SUBDIRECTORY)
                    .listFiles();

        /*
         * まず始めにすべてのシートを検索して，クラス名とpackage名のリストを作ります．
         * php形式の定義書では，クラスを指定する際にpackage名が指定されていないからです．
         */
            BlancoVueComponentUtil.isVerbose = input.getVerbose();
            BlancoVueComponentUtil.processValueObjects(input);

            /*
             * listClass が指定されている場合は、RouterConfig クラスと
             * RouterConfig クラスの一覧（配列）を保持するクラスを生成します。
             * RouterConfigは各コンポーネントクラスと同じ場所に保持され、
             * 一覧を保持するクラスは、targetDir/listClassに置かれます。
             * listClassはJavaのパッケージと同じ書式で指定することで、
             * Sub directory を指定することができます。
             */
            boolean createClassList = false;
            String listClassName = input.getListClass();
            BlancoVueComponentClassStructure listClassStructure = null;
            List<BlancoVueComponentClassStructure> listClassStructures = new ArrayList<>();
            if (listClassName != null && listClassName.length() > 0) {
                createClassList = true;
            }

            // 次にメタディレクトリとして指定されているディレクトリを走査
            for (int index = 0; index < fileMeta2.length; index++) {
                if (fileMeta2[index].getName().endsWith(".xml") == false) {
                    continue;
                }

                final BlancoVueComponentXml2TypeScriptClass xml2Class = new BlancoVueComponentXml2TypeScriptClass();
                xml2Class.setEncoding(input.getEncoding());
                xml2Class.setVerbose(input.getVerbose());
                xml2Class.setTargetStyleAdvanced(isTargetStyleAdvanced);
                xml2Class.setXmlRootElement(input.getXmlrootelement());
                xml2Class.setSheetLang(new BlancoCgSupportedLang().convertToInt(input.getSheetType()));
                xml2Class.setTabs(input.getTabs());
                BlancoVueComponentClassStructure [] structures = xml2Class.process(fileMeta2[index], new File(strTarget));

                /*
                 * listClass が指定されている場合は、自動生成した
                 * クラスの一覧を収集します。
                 */
                for (int index2 = 0; createClassList && index2 < structures.length; index2++) {
                    BlancoVueComponentClassStructure classStructure = structures[index2];
                    if (listClassName.equals(classStructure.getName())) {
                        listClassStructure = classStructure;
                    } else {
                        listClassStructures.add(classStructure);
                    }
                }
                // 単体試験コードの自動生成機能は 0.9.1以降では削除されました。
            }

            /*
             * listClass が指定されている場合は、自動生成したクラスの一覧を
             * 保持するValueObjectを作成します。
             */
            if (createClassList) {
                if (listClassStructure == null) {
                    System.out.println("[WARN] listClass is specified but no meta file. : " + listClassName);
                    return BlancoVueComponentBatchProcess.END_SUCCESS;
                }
                final BlancoVueComponentXml2TypeScriptClass xml2Class = new BlancoVueComponentXml2TypeScriptClass();
                xml2Class.setEncoding(input.getEncoding());
                xml2Class.setVerbose(input.getVerbose());
                xml2Class.setTargetStyleAdvanced(isTargetStyleAdvanced);
                xml2Class.setXmlRootElement(input.getXmlrootelement());
                xml2Class.setSheetLang(new BlancoCgSupportedLang().convertToInt(input.getSheetType()));
                xml2Class.setTabs(input.getTabs());
                xml2Class.processListClass(listClassStructures, listClassStructure, new File(strTarget));
            }

            return BlancoVueComponentBatchProcess.END_SUCCESS;
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
