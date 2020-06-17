/*
 * blanco Framework
 * Copyright (C) 2004-2010 IGA Tosiki
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
package blanco.vuecomponent;

import blanco.cg.BlancoCgObjectFactory;
import blanco.cg.BlancoCgSupportedLang;
import blanco.cg.transformer.BlancoCgTransformerFactory;
import blanco.cg.util.BlancoCgSourceUtil;
import blanco.cg.valueobject.*;
import blanco.commons.util.BlancoJavaSourceUtil;
import blanco.commons.util.BlancoNameAdjuster;
import blanco.commons.util.BlancoStringUtil;
import blanco.commons.util.BlancoXmlUtil;
import blanco.vuecomponent.message.BlancoVueComponentMessage;
import blanco.vuecomponent.resourcebundle.BlancoVueComponentResourceBundle;
import blanco.vuecomponent.valueobject.BlancoVueComponentClassStructure;
import blanco.vuecomponent.valueobject.BlancoVueComponentFieldStructure;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * バリューオブジェクト用中間XMLファイルから TypeScript ソースコードを自動生成するクラス。
 *
 * BlancoValueObjectTsの主たるクラスのひとつです。
 *
 * @author IGA Tosiki
 * @author tueda
 */
public class BlancoVueComponentXml2TypeScriptClass {
    /**
     * メッセージ。
     */
    private final BlancoVueComponentMessage fMsg = new BlancoVueComponentMessage();

    /**
     * blancoValueObjectのリソースバンドルオブジェクト。
     */
    private final BlancoVueComponentResourceBundle fBundle = new BlancoVueComponentResourceBundle();

    /**
     * 入力シートに期待するプログラミング言語
     */
    private int fSheetLang = BlancoCgSupportedLang.PHP;

    public void setSheetLang(final int argSheetLang) {
        fSheetLang = argSheetLang;
    }

    /**
     * ソースコード生成先ディレクトリのスタイル
     */
    private boolean fTargetStyleAdvanced = false;
    public void setTargetStyleAdvanced(boolean argTargetStyleAdvanced) {
        this.fTargetStyleAdvanced = argTargetStyleAdvanced;
    }
    public boolean isTargetStyleAdvanced() {
        return this.fTargetStyleAdvanced;
    }

    private boolean fVerbose = false;
    public void setVerbose(boolean argVerbose) {
        this.fVerbose = argVerbose;
    }
    public boolean isVerbose() {
        return this.fVerbose;
    }

    private int fTabs = 4;
    public int getTabs() {
        return fTabs;
    }
    public void setTabs(int fTabs) {
        this.fTabs = fTabs;
    }

    /**
     * 内部的に利用するblancoCg用ファクトリ。
     */
    private BlancoCgObjectFactory fCgFactory = null;

    /**
     * 内部的に利用するblancoCg用ソースファイル情報。
     */
    private BlancoCgSourceFile fCgSourceFile = null;

    /**
     * 内部的に利用するblancoCg用クラス情報。
     */
    private BlancoCgClass fCgClass = null;

    /**
     * 内部的に利用するblancoCg用インタフェイス情報。
     */
    private BlancoCgInterface fCgInterface = null;

    /**
     * 自動生成するソースファイルの文字エンコーディング。
     */
    private String fEncoding = null;

    public void setEncoding(final String argEncoding) {
        fEncoding = argEncoding;
    }

    private boolean fIsXmlRootElement = false;

    public void setXmlRootElement(final boolean isXmlRootElement) {
        fIsXmlRootElement = isXmlRootElement;
    }

    /**
     * バリューオブジェクトを表現する中間XMLファイルから、Javaソースコードを自動生成します。
     *
     * @param argMetaXmlSourceFile
     *            ValueObjectに関するメタ情報が含まれているXMLファイル
     * @param argDirectoryTarget
     *            ソースコード生成先ディレクトリ
     * @throws IOException
     *             入出力例外が発生した場合
     * @return
     */
    public BlancoVueComponentClassStructure[] process(
            final File argMetaXmlSourceFile,
            final File argDirectoryTarget) throws IOException {
        BlancoVueComponentXmlParser parser = new BlancoVueComponentXmlParser();
        parser.setVerbose(this.isVerbose());
        final BlancoVueComponentClassStructure[] structures = parser.parse(argMetaXmlSourceFile);

        for (int index = 0; index < structures.length; index++) {
            BlancoVueComponentClassStructure classStructure = structures[index];
            /* まず、.vue ファイルを作成します */
            generateComponent(classStructure, argDirectoryTarget);

            /* 次に、interface を作成します */
            generateInterface(classStructure, argDirectoryTarget);

            /* 次に、class を作成します */
            generateClass(classStructure, argDirectoryTarget);

        }
        return structures;
    }

    public void processListClass(
            final List<BlancoVueComponentClassStructure> classStructures,
            final BlancoVueComponentClassStructure listClassStructure,
            final File argDirectoryTarget) throws IOException {
        List<BlancoVueComponentFieldStructure> fieldList = listClassStructure.getFieldList();

        BlancoVueComponentXmlParser parser = new BlancoVueComponentXmlParser();

        if (this.isVerbose()) {
            System.out.println("**** processListClass : " + listClassStructure.getName());
        }

        for (BlancoVueComponentClassStructure structure : classStructures) {
            String className = structure.getName();
            String classPackageName = structure.getPackage();
            String classDescription = structure.getDescription();
            String classType = className;
            if (classPackageName != null && classPackageName.length() > 0) {
                classType = classPackageName + "." + className;
            }

            BlancoVueComponentFieldStructure fieldStructure = new BlancoVueComponentFieldStructure();
            listClassStructure.getFieldList().add(fieldStructure);

            String propertyName = BlancoNameAdjuster.toLowerCaseTitle(className);
            String aliasName = structure.getClassAlias();
            if (aliasName != null && aliasName.length() > 0) {
                propertyName = aliasName;
            }
            fieldStructure.setName(propertyName);
            fieldStructure.setType(classType);
            fieldStructure.setDefault("new " + className + "()");
            fieldStructure.setDescription(classDescription);

            /*
             * import list の作成
             */
            if (listClassStructure.getCreateImportList()
                    && classPackageName != null
                    && classPackageName.length() > 0) {
//                BlancoVueComponentUtil.makeImportHeaderList(classPackageName, className, listClassStructure);
            }
        }

        /*
         * 自動生成されたものを出力します。
         * 現在の方式だと、以下の前提が必要。
         *  * 1ファイルに1クラスの定義
         *  * 定義シートでは Java/kotlin 式の package 表記でディレクトリを表現
         * TODO: 定義シート上にファイルの配置ディレクトリを定義できるようにすべし？
         */
//        if (listClassStructure.getCreateImportList()) {
//            Map<String, List<String>> importHeaderList = parser.importHeaderList;
//            Set<String> fromList = importHeaderList.keySet();
//            for (String strFrom : fromList) {
//                StringBuffer sb = new StringBuffer();
//                sb.append("import { ");
//                List<String> classNameList = importHeaderList.get(strFrom);
//                int count = 0;
//                for (String className : classNameList) {
//                    if (count > 0) {
//                        sb.append(", ");
//                    }
//                    sb.append(className);
//                    count++;
//                }
//                if (count > 0) {
//                    sb.append(" } from \"" + strFrom + "\"");
//                    listClassStructure.getHeaderList().add(sb.toString());
//                }
//            }
//        }
        /*
         * クラスファイルを（上書き）生成
         */
        generateClass(listClassStructure, argDirectoryTarget);
    }

    /**
     * 与えられたクラス情報バリューオブジェクトから、ソースコードを自動生成します。
     *
     * @param argClassStructure
     *            クラス情報
     * @param argDirectoryTarget
     *            TypeScript ソースコードの出力先ディレクトリ
     * @throws IOException
     *             入出力例外が発生した場合。
     */
    public void generateClass(
            final BlancoVueComponentClassStructure argClassStructure,
            final File argDirectoryTarget) throws IOException {
        /*
         * 出力ディレクトリはant taskのtargetStyel引数で
         * 指定された書式で出力されます。
         * 従来と互換性を保つために、指定がない場合は blanco/main
         * となります。
         * by tueda, 2019/08/30
         */
        String strTarget = argDirectoryTarget
                .getAbsolutePath(); // advanced
        if (!this.isTargetStyleAdvanced()) {
            strTarget += "/main"; // legacy
        }
        final File fileBlancoMain = new File(strTarget);

        /* tueda DEBUG */
        if (this.isVerbose()) {
            System.out.println("/* tueda */ generateClass argDirectoryTarget : " + argDirectoryTarget.getAbsolutePath());
        }

        // BlancoCgObjectFactoryクラスのインスタンスを取得します。
        fCgFactory = BlancoCgObjectFactory.getInstance();

        fCgSourceFile = fCgFactory.createSourceFile(argClassStructure
                .getPackage(), null);
        fCgSourceFile.setEncoding(fEncoding);
        fCgSourceFile.setTabs(this.getTabs());

        // クラスを作成します。
        fCgClass = fCgFactory.createClass(argClassStructure.getName(), "");
        fCgSourceFile.getClassList().add(fCgClass);
        fCgClass.setAccess("default");

        // 継承
        if (BlancoStringUtil.null2Blank(argClassStructure.getExtends())
                .length() > 0) {
            fCgClass.getExtendClassList().add(
                    fCgFactory.createType(argClassStructure.getExtends()));
        }
        if (fIsXmlRootElement) {
            fCgClass.getAnnotationList().add("XmlRootElement");
            fCgSourceFile.getImportList().add(
                    "javax.xml.bind.annotation.XmlRootElement");
        }

        // クラスのJavaDocを設定します。
        fCgClass.setDescription(argClassStructure.getDescription());
        for (String line : argClassStructure.getDescriptionList()) {
            fCgClass.getLangDoc().getDescriptionList().add(line);
        }

        /* TypeScript では import の代わりに header を設定します */
        for (int index = 0; index < argClassStructure.getComponentHeaderList()
                .size(); index++) {
            final String header = (String) argClassStructure.getComponentHeaderList()
                    .get(index);
            fCgSourceFile.getHeaderList().add(header);
        }

        /* Component decorator を設定します。 */
        List<String> decorators = new ArrayList<>();
        StringBuffer lineBuffer = new StringBuffer();
        lineBuffer.append("Component");
        List<String> subComponents = argClassStructure.getComponentList();
        if (subComponents != null && subComponents.size() > 0) {
            lineBuffer.append("({" + this.getLineSeparator());
            lineBuffer.append(this.getTabSpace() + "components: {" + this.getLineSeparator());
            int i = 0;
            for (String subComponent : subComponents) {
                if (i != 0) {
                    lineBuffer.append("," + this.getLineSeparator());
                }
                lineBuffer.append(this.getTabSpace() + this.getTabSpace() + subComponent);
                i++;
            }
            lineBuffer.append(this.getLineSeparator() + this.getTabSpace() + "}" + this.getLineSeparator());
            lineBuffer.append("})");
        }
        decorators.add(lineBuffer.toString());
        fCgClass.getAnnotationList().addAll(decorators);

        /* プロパティを設定します */
        for (int indexField = 0; indexField < argClassStructure.getFieldList()
                .size(); indexField++) {
            final BlancoVueComponentFieldStructure fieldStructure = (BlancoVueComponentFieldStructure) argClassStructure
                    .getFieldList().get(indexField);

            // 必須項目が未設定の場合には例外処理を実施します。
            if (fieldStructure.getName() == null) {
                throw new IllegalArgumentException(fMsg
                        .getMbvoji03(argClassStructure.getName()));
            }
            if (fieldStructure.getType() == null) {
                throw new IllegalArgumentException(fMsg.getMbvoji04(
                        argClassStructure.getName(), fieldStructure.getName()));
            }

            // フィールドの生成。
            buildProp(argClassStructure, fieldStructure, false);

            // Getter/Setterは生成しません。
            // 代わりに、実装クラスに property の変更を通知するメソッドを生成します。
            buildOnChange(argClassStructure, fieldStructure);

        }

        // TODO toString メソッドの生成方式を検討する
//        if (argClassStructure.getGenerateToString()) {
//            // toStringメソッドの生成。
//            buildMethodToString(argClassStructure);
//        }

        // TODO copyTo メソッドの生成有無を外部フラグ化するかどうか検討すること。
//        BlancoBeanUtils.generateCopyToMethod(fCgSourceFile, fCgClass);

        // 収集された情報を元に実際のソースコードを自動生成。
        BlancoCgTransformerFactory.getTsSourceTransformer().transform(
                fCgSourceFile, fileBlancoMain);
    }

    /**
     * 与えられたコンポーネント定義情報から、.vue ファイルを作成します。
     *
     * @param argClassStructure
     * @param argDirectoryTarget
     */
    private void generateComponent(
            final BlancoVueComponentClassStructure argClassStructure,
            final File argDirectoryTarget) {
        /*
         * 出力ディレクトリはant taskのtargetStyel引数で
         * 指定された書式で出力されます。
         * 従来と互換性を保つために、指定がない場合は blanco/main
         * となります。
         * by tueda, 2019/08/30
         */
        String strTarget = argDirectoryTarget
                .getAbsolutePath(); // advanced
        if (!this.isTargetStyleAdvanced()) {
            strTarget += "/main"; // legacy
        }
        final File fileBlancoMain = new File(strTarget);

        /* tueda DEBUG */
        if (this.isVerbose()) {
            System.out.println("/* tueda */ generateComponent argDirectoryTarget : " + argDirectoryTarget.getAbsolutePath());
        }

        boolean useTemplate = argClassStructure.getUseTemplate();
        boolean useScript = argClassStructure.getUseScript();
        boolean useStyle = argClassStructure.getUseStyle();

        if (useScript != true) {
            System.out.println("### ERROR: Script cannot be ignored.");
            throw new IllegalArgumentException("### ERROR: Script cannot be ignored for " + argClassStructure.getName());
        }

        BlancoVueComponentXmlTransformer transformer = new BlancoVueComponentXmlTransformer();

        transformer.preapreTransform(fileBlancoMain, argClassStructure);

        String impleDir = argClassStructure.getImpledir();
        if (impleDir == null || impleDir.length() == 0) {
            impleDir = ".";
        }

        if (useTemplate) {
            Document docTemplate = BlancoXmlUtil.newDocument();
            Element templateElement = docTemplate.createElement("template");
            docTemplate.appendChild(templateElement);
            templateElement.setAttribute("src", impleDir + "/" + argClassStructure.getName() + ".html");
            transformer.transform(docTemplate);
        }

        {
            Document docScript = BlancoXmlUtil.newDocument();
            Element scriptElement = docScript.createElement("script");
            docScript.appendChild(scriptElement);
            scriptElement.setAttribute("lang", "ts");
            scriptElement.setAttribute("src","./" + argClassStructure.getName() + ".ts");
            transformer.transform(docScript);
        }

        if (useStyle) {
            Document docStyle = BlancoXmlUtil.newDocument();
            Element styleElement = docStyle.createElement("style");
            docStyle.appendChild(styleElement);
            styleElement.setAttribute("lang", "scss");
            styleElement.setAttribute("src",impleDir + "/" + argClassStructure.getName() + ".scss");
            transformer.transform(docStyle);
        }

        transformer.closeTransform();
    }

    /**
     * 与えられたプロパティ情報から、実装クラスにプロパティの実装を強制するためのインタフェイスソースコードを自動生成します。
     *
     * @param argClassStructure
     *            クラス情報
     * @param argDirectoryTarget
     *            TypeScript ソースコードの出力先ディレクトリ
     * @throws IOException
     *             入出力例外が発生した場合。
     */
    private void generateInterface(
            final BlancoVueComponentClassStructure argClassStructure,
            final File argDirectoryTarget) throws IOException {
        /*
         * 出力ディレクトリはant taskのtargetStyel引数で
         * 指定された書式で出力されます。
         * 従来と互換性を保つために、指定がない場合は blanco/main
         * となります。
         * by tueda, 2019/08/30
         */
        String strTarget = argDirectoryTarget
                .getAbsolutePath(); // advanced
        if (!this.isTargetStyleAdvanced()) {
            strTarget += "/main"; // legacy
        }
        final File fileBlancoMain = new File(strTarget);

        /* tueda DEBUG */
        if (this.isVerbose()) {
            System.out.println("/* tueda */ generateInterface argDirectoryTarget : " + argDirectoryTarget.getAbsolutePath());
        }

        // BlancoCgObjectFactoryクラスのインスタンスを取得します。
        fCgFactory = BlancoCgObjectFactory.getInstance();

        fCgSourceFile = fCgFactory.createSourceFile(argClassStructure
                .getPackage(), null);
        fCgSourceFile.setEncoding(fEncoding);
        fCgSourceFile.setTabs(this.getTabs());

        // インタフェイスを作成します。
        fCgInterface = fCgFactory.createInterface(argClassStructure.getName() + "Interface", "");
        fCgSourceFile.getInterfaceList().add(fCgInterface);

        // インタフェイスではアクセス指定は無視します（常にpublic）。
        fCgInterface.setAccess("public");

        // クラスのJavaDocを設定します。
        fCgInterface.setDescription(argClassStructure.getDescription());
        for (String line : argClassStructure.getDescriptionList()) {
            fCgInterface.getLangDoc().getDescriptionList().add(line);
        }

        /* TypeScript では import の代わりに header を設定します */
        for (int index = 0; index < argClassStructure.getInterfaceHeaderList()
                .size(); index++) {
            final String header = (String) argClassStructure.getInterfaceHeaderList()
                    .get(index);
            fCgSourceFile.getHeaderList().add(header);
        }

        for (int indexField = 0; indexField < argClassStructure.getFieldList()
                .size(); indexField++) {
            // おのおののフィールドを処理します。
            final BlancoVueComponentFieldStructure fieldStructure = (BlancoVueComponentFieldStructure) argClassStructure
                    .getFieldList().get(indexField);

            // 必須項目が未設定の場合には例外処理を実施します。
            if (fieldStructure.getName() == null) {
                throw new IllegalArgumentException(fMsg
                        .getMbvoji03(argClassStructure.getName()));
            }
            if (fieldStructure.getType() == null) {
                throw new IllegalArgumentException(fMsg.getMbvoji04(
                        argClassStructure.getName(), fieldStructure.getName()));
            }

            // フィールドの生成。
            buildProp(argClassStructure, fieldStructure, true);

            // インタフェイスには Getter/Setter はありません。
        }

        // TODO インタフェイスに toString メソッドは必要か？
//        if (argInterfaceStructure.getGenerateToString()) {
//            // toStringメソッドの生成。
//            buildMethodToString(argInterfaceStructure);
//        }

        // TODO copyTo メソッドの生成有無を外部フラグ化するかどうか検討すること。
//        BlancoBeanUtils.generateCopyToMethod(fCgSourceFile, fCgClass);

        // 収集された情報を元に実際のソースコードを自動生成。
        BlancoCgTransformerFactory.getTsSourceTransformer().transform(
                fCgSourceFile, fileBlancoMain);
    }

    /**
     * コンポーネントのプロパティを生成します。
     *  @param argClassStructure
     *            クラス情報。
     * @param argFieldStructure
     * @param isInterface
     */
    private void buildProp(
            final BlancoVueComponentClassStructure argClassStructure,
            final BlancoVueComponentFieldStructure argFieldStructure,
            boolean isInterface) {

//        System.out.println("%%% " + argFieldStructure.toString());

        /*
         * Interface ではプロパティ名の前にpをつける。
         */
        String fieldName = argFieldStructure.getName();
        if (isInterface) {
            fieldName = "p" + this.getFieldNameAdjustered(argClassStructure, argFieldStructure);
        }
        final BlancoCgField field = fCgFactory.createField(fieldName,
                argFieldStructure.getType(), null);

        if (isInterface != true) {
            fCgClass.getFieldList().add(field);
        } else {
            fCgInterface.getFieldList().add(field);
        }

        /*
         * Generic に対応します。blancoCg 側では <> が付いている前提かつ
         * package部をtrimするので、ここで設定しないと正しく設定されません。
         */
        String generic = argFieldStructure.getGeneric();
        if (generic != null && generic.length() > 0) {
            field.getType().setGenerics(generic);
        }

        if (this.isVerbose()) {
            System.out.println("!!! type = " + argFieldStructure.getType());
            System.out.println("!!! generic = " + field.getType().getGenerics());
        }

        /*
         * Getter / Setter は作らないので、すべて public にします。
         */
        field.setAccess("public");
        /*
         * 当面、final には対応しません。
         */
        field.setFinal(false);

        /*
         * const (kotlin では val）に対応します。
         */
        field.setConst(argFieldStructure.getValue());

        // nullable に対応します。
        if (!argFieldStructure.getNullable()) {
            field.setNotnull(true);
        }

        // フィールドのJavaDocを設定します。
        field.setDescription(argFieldStructure.getDescription());
        for (String line : argFieldStructure.getDescriptionList()) {
            field.getLangDoc().getDescriptionList().add(line);
        }
        field.getLangDoc().getDescriptionList().add(
                fBundle.getXml2javaclassFieldName(argFieldStructure.getName()));

        /*
         * TypeScript ではプロパティのデフォルト値は原則必須
         * ただし、interface では設定不可。
         */
        if (isInterface != true) {
            final String type = field.getType().getName();
            String defaultRawValue = argFieldStructure.getDefault();
            boolean isNullable = argFieldStructure.getNullable();
            if (!isNullable && (defaultRawValue == null || defaultRawValue.length() <= 0)) {
                System.err.println("/* tueda */ フィールドにデフォルト値が設定されていません。interface でない場合は必ずデフォルト値を設定するか、Nullableを設定してください。");
                throw new IllegalArgumentException(fMsg
                        .getMbvoji08(argClassStructure.getName(), argFieldStructure.getName()));
            }

            // フィールドのデフォルト値を設定します。
            field.getLangDoc().getDescriptionList().add(
                    BlancoCgSourceUtil.escapeStringAsLangDoc(BlancoCgSupportedLang.KOTLIN, fBundle.getXml2javaclassFieldDefault(argFieldStructure
                            .getDefault())));
            if (argClassStructure.getAdjustDefaultValue() == false) {
                // デフォルト値の変形がoffの場合には、定義書上の値をそのまま採用。
                field.setDefault(argFieldStructure.getDefault());
            } else {

                if (type.equals("string")) {
                    // ダブルクオートを付与します。
                    field.setDefault("\""
                            + BlancoJavaSourceUtil
                                    .escapeStringAsJavaSource(argFieldStructure
                                            .getDefault()) + "\"");
                } else {
                    /*
                     * その他の型については当面、与えられた値をそのまま記述します。
                     */
                    field.setDefault(argFieldStructure.getDefault());
                }
            }

            /* Prop デコレータを設定します。 */
            boolean required = argFieldStructure.getRequired();
            List<String> decorators = new ArrayList<>();
            decorators.add("Prop(" +
                    (required ? "{ required: true }" : "") +
                    ")");
            field.getAnnotationList().addAll(decorators);
            if (this.isVerbose()) {
                System.out.println("/* tueda */ method annotation = " + field.getAnnotationList().get(0));
            }
        }
    }

    /**
     * プロパティの変更を実装クラスに通知するメソッドを生成します。
     *
     * @param argClassStructure
     * @param argFieldStructure
     */
    private void buildOnChange(
            final BlancoVueComponentClassStructure argClassStructure,
            final BlancoVueComponentFieldStructure argFieldStructure
    ) {
        String methodName = "onChange" + BlancoNameAdjuster.toClassName(argFieldStructure.getName());

        final BlancoCgMethod method = fCgFactory.createMethod(methodName,
                fBundle.getXml2javaclassGetJavadoc01(argFieldStructure
                        .getName()));
        fCgClass.getMethodList().add(method);

        // Notnull に対応
        if (!argFieldStructure.getNullable()) {
            method.setNotnull(true);
        }

        // メソッドの JavaDoc設定。
        // TODO: リソースバンドル化して、国際化対応した方がよい。
        List<String> lines = new ArrayList<>();
        lines.add("Watch " + argFieldStructure
                .getName() + " and notify changes to implements.");
        lines.add("immedeate: act on initialized.");
        lines.add("deep: follow inside of objects.");
        method.getLangDoc().getDescriptionList().addAll(lines);

        // デコレーションを設定
        List<String> decorates = new ArrayList<>();
        decorates.add("Watch(\"" +
                argFieldStructure.getName() +
                "\", {immediate: true, deep: true})"
                );
        method.getAnnotationList().addAll(decorates);

        // パラメータの実装
        method.getParameterList().add(this.createParameter(argClassStructure, argFieldStructure, "new"));
        method.getParameterList().add(this.createParameter(argClassStructure, argFieldStructure, "old"));

        // メソッドの実装
        method.getLineList().add(
                "this.p"
                        + getFieldNameAdjustered(argClassStructure, argFieldStructure)
                        + " = "
                        + "new"
                        + getFieldNameAdjustered(argClassStructure,
                        argFieldStructure) + ";");
    }

    /**
     * パラメータを生成します。
     *
     * @param argClassStructure
     * @param argFieldStructure
     * @param prefix
     * @return
     */
    private BlancoCgParameter createParameter(
            final BlancoVueComponentClassStructure argClassStructure,
            final BlancoVueComponentFieldStructure argFieldStructure,
            final String prefix
    ) {
        BlancoCgParameter param = fCgFactory.createParameter(prefix
                        + getFieldNameAdjustered(argClassStructure,
                argFieldStructure),
                argFieldStructure.getType(),
                fBundle.getXml2javaclassSetArgJavadoc(argFieldStructure
                        .getName()));
        if (!argFieldStructure.getNullable()) {
            param.setNotnull(true);
        }
        // generic があれば対応
        String generic = argFieldStructure.getGeneric();
        if (generic != null && generic.length() > 0) {
            param.getType().setGenerics(generic);
        }
        return param;
    }

    /**
     * 調整済みのフィールド名を取得します。
     *
     * @param argFieldStructure
     *            フィールド情報。
     * @return 調整後のフィールド名。
     */
    private String getFieldNameAdjustered(
            final BlancoVueComponentClassStructure argClassStructure,
            final BlancoVueComponentFieldStructure argFieldStructure) {
        return (argClassStructure.getAdjustFieldName() == false ? argFieldStructure
                .getName()
                : BlancoNameAdjuster.toClassName(argFieldStructure.getName()));
    }

    private String getTabSpace() {
        StringBuffer spc = new StringBuffer();
        for (int i = this.getTabs(); i > 0; i--) {
            spc.append(" ");
        }
        return spc.toString();
    }

    private String getLineSeparator() {
        return System.getProperty("line.separator", "\n");
    }
}
