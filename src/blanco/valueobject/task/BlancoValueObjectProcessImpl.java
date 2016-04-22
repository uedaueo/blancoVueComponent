/*
 * blanco Framework
 * Copyright (C) 2004-2008 IGA Tosiki
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
package blanco.valueobject.task;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.TransformerException;

import blanco.cg.BlancoCgSupportedLang;
import blanco.valueobject.BlancoValueObjectConstants;
import blanco.valueobject.BlancoValueObjectMeta2Xml;
import blanco.valueobject.BlancoValueObjectXml2JavaClass;
import blanco.valueobject.BlancoValueObjectXmlParser;
import blanco.valueobject.message.BlancoValueObjectMessage;
import blanco.valueobject.task.valueobject.BlancoValueObjectProcessInput;
import blanco.xml.bind.BlancoXmlBindingUtil;
import blanco.xml.bind.valueobject.BlancoXmlElement;

public class BlancoValueObjectProcessImpl implements BlancoValueObjectProcess {
    /**
     * メッセージ。
     */
    private final BlancoValueObjectMessage fMsg = new BlancoValueObjectMessage();

    /**
     * {@inheritDoc}
     */
    public int execute(final BlancoValueObjectProcessInput input)
            throws IOException, IllegalArgumentException {
        System.out.println("- " + BlancoValueObjectConstants.PRODUCT_NAME
                + " (" + BlancoValueObjectConstants.VERSION + ")");
        try {
            final File fileMetadir = new File(input.getMetadir());
            if (fileMetadir.exists() == false) {
                throw new IllegalArgumentException(fMsg.getMbvoja01(input
                        .getMetadir()));
            }

            // テンポラリディレクトリを作成。
            new File(input.getTmpdir()
                    + BlancoValueObjectConstants.TARGET_SUBDIRECTORY).mkdirs();

            new BlancoValueObjectMeta2Xml().processDirectory(fileMetadir, input
                    .getTmpdir()
                    + BlancoValueObjectConstants.TARGET_SUBDIRECTORY);

            // XML化されたメタファイルからValueObjectを生成
            // 最初にテンポラリフォルダを走査
            final File[] fileMeta2 = new File(input.getTmpdir()
                    + BlancoValueObjectConstants.TARGET_SUBDIRECTORY)
                    .listFiles();

        /*
         * まず始めにすべてのシートを検索して，クラス名とpackage名のリストを作ります．
         * php形式の定義書では，クラスを指定する際にpackage名が指定されていないからです．
         */
            BlancoValueObjectXmlParser.classList =
                    BlancoValueObjectXmlParser.createClassListFromSheets(fileMeta2);

            for (int index = 0; index < fileMeta2.length; index++) {
                if (fileMeta2[index].getName().endsWith(".xml") == false) {
                    continue;
                }

                final BlancoValueObjectXml2JavaClass xml2JavaClass = new BlancoValueObjectXml2JavaClass();
                xml2JavaClass.setEncoding(input.getEncoding());
                xml2JavaClass.setXmlRootElement(input.getXmlrootelement());
                xml2JavaClass.setSheetLang(new BlancoCgSupportedLang().convertToInt(input.getSheetType()));
                xml2JavaClass.process(fileMeta2[index], new File(input
                        .getTargetdir()));

                // 単体試験コードの自動生成機能は 0.9.1以降では削除されました。
            }

            // 次にメタディレクトリとして指定されているディレクトリを走査
            final File[] fileMeta3 = fileMetadir.listFiles();
            for (int index = 0; index < fileMeta3.length; index++) {
                if (fileMeta3[index].getName().endsWith(".xml") == false) {
                    continue;
                }

                final BlancoValueObjectXml2JavaClass xml2JavaClass = new BlancoValueObjectXml2JavaClass();
                xml2JavaClass.setEncoding(input.getEncoding());
                xml2JavaClass.setXmlRootElement(input.getXmlrootelement());
                xml2JavaClass.setSheetLang(new BlancoCgSupportedLang().convertToInt(input.getSheetType()));
                xml2JavaClass.process(fileMeta3[index], new File(input
                        .getTargetdir()));

                // 単体試験コードの自動生成機能は 0.9.1以降では削除されました。
            }

            return BlancoValueObjectBatchProcess.END_SUCCESS;
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
