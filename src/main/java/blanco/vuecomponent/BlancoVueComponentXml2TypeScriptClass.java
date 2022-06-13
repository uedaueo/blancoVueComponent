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
import blanco.cg.valueobject.BlancoCgClass;
import blanco.cg.valueobject.BlancoCgField;
import blanco.cg.valueobject.BlancoCgInterface;
import blanco.cg.valueobject.BlancoCgSourceFile;
import blanco.commons.util.BlancoNameAdjuster;
import blanco.commons.util.BlancoStringUtil;
import blanco.commons.util.BlancoXmlUtil;
import blanco.vuecomponent.message.BlancoVueComponentMessage;
import blanco.vuecomponent.resourcebundle.BlancoVueComponentResourceBundle;
import blanco.vuecomponent.valueobject.BlancoVueComponentClassStructure;
import blanco.vuecomponent.valueobject.BlancoVueComponentEmitsStructure;
import blanco.vuecomponent.valueobject.BlancoVueComponentPropsStructure;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This is a class to auto-generate TypeScript source code from intermediate XML files for value objects.
 *
 * This is one of the main classes of BlancoValueObjectTs.
 *
 * @author IGA Tosiki
 * @author tueda
 */
public class BlancoVueComponentXml2TypeScriptClass {
    /**
     * A message.
     */
    private final BlancoVueComponentMessage fMsg = new BlancoVueComponentMessage();

    /**
     * A resource bundle object for blancoValueObject.
     */
    private final BlancoVueComponentResourceBundle fBundle = new BlancoVueComponentResourceBundle();

    /**
     * A programming language expected for the input sheet.
     */
    private int fSheetLang = BlancoCgSupportedLang.PHP;

    public void setSheetLang(final int argSheetLang) {
        fSheetLang = argSheetLang;
    }

    /**
     * Style of the source code generation destination directory
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

    private String fListClass = "";
    public String getListClass() {
        return this.fListClass;
    }
    public void setListClass(String listClass) {
        this.fListClass = listClass;
    }

    /**
     * A factory for blancoCg to be used internally.
     */
    private BlancoCgObjectFactory fCgFactory = null;

    /**
     * Source file information for blancoCg to be used internally.
     */
    private BlancoCgSourceFile fCgSourceFile = null;

    /**
     * Class information for blancoCg to be used internally.
     */
    private BlancoCgClass fCgClass = null;

    /**
     * Interface information for blancoCg to be used internally.
     */
    private BlancoCgInterface fCgInterface = null;

    /**
     * Character encoding of auto-generated source files.
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
     * Auto-generates TypeScript source code from an intermediate XML file representing a value object.
     *
     * @param argMetaXmlSourceFile
     *            An XML file containing meta-information about the ValueObject.
     * @param argDirectoryTarget
     *            Source code generation destination directory.
     * @throws IOException
     *             If an I/O exception occurs.
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
            /* First, creates a .vue file. */
            generateComponent(classStructure, argDirectoryTarget);

            /* Saves a property with a query string */
            Map<String, String> queryProps = new HashMap<>();

            /* Generate defineComponent calling. */
            generateDefineComponent(classStructure, argDirectoryTarget);

            /* Generate props options */
            generatePropsOptioins(classStructure, argDirectoryTarget, queryProps);

            /* Generate emits options */
            if (classStructure.getEmitsList() != null && classStructure.getEmitsList().size() != 0) {
                generateEmitsOptioins(classStructure, argDirectoryTarget);
            }

            /* In the case of the screen, creates a RouterConfig. */
            if ("screen".equalsIgnoreCase(classStructure.getComponentKind())) {
                generateRouteRecord(classStructure, argDirectoryTarget, queryProps);
            }

        }
        return structures;
    }

    /**
     * After all meta files are parsed and generated, generate listClass
     * for RouteSettings of vue-router.
     *
     * @param argClassStructures
     * @param argDirectoryTarget
     * @throws IOException
     */
    public void processListClass(
            final List<BlancoVueComponentClassStructure> argClassStructures,
            final File argDirectoryTarget) throws IOException {
        /*
         * The output directory will be in the format specified by the targetStyle argument of the ant task.
         * For compatibility, the output directory will be blanco/main if it is not specified.
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

        String simpleClassName = BlancoVueComponentUtil.getSimpleClassName(this.getListClass());
        String constName = BlancoNameAdjuster.toParameterName(simpleClassName);
        String packageName = BlancoVueComponentUtil.getPackageName(this.getListClass());

        // Gets an instance of the BlancoCgObjectFactory class.
        fCgFactory = BlancoCgObjectFactory.getInstance();
        fCgSourceFile = fCgFactory.createSourceFile(packageName, null);
        fCgSourceFile.setEncoding(fEncoding);
        fCgSourceFile.setTabs(this.getTabs());

        // Creates a class.
        fCgClass = fCgFactory.createClass(simpleClassName, fBundle.getXml2sourceFileRouteconfigList());
        fCgSourceFile.getClassList().add(fCgClass);
        fCgClass.setAccess("");
        fCgClass.setNoClassDeclare(true);

        final BlancoCgField field = fCgFactory.createField(constName,
                "dummy", fBundle.getXml2sourceFileRouteSettingsConstDescription());
        fCgClass.getFieldList().add(field);

        field.setNotnull(true);
        field.setAccess("export const");
        field.setTypeInference(true);

        StringBuffer defaultValue = new StringBuffer();
        defaultValue.append("() => {" + this.getLineSeparator());
        defaultValue.append(this.getTabSpace() + "return [" + this.getLineSeparator());

        String suffix = "RouteRecord";
        int i = 0;
        for (BlancoVueComponentClassStructure structure : argClassStructures) {
            String className = structure.getName() + suffix;
            String classConstName = BlancoNameAdjuster.toParameterName(className);
            String classPackageName = structure.getPackage();
            if (classPackageName == null) {
                classPackageName = "";
            }
            if (i != 0) {
                defaultValue.append("," + this.getLineSeparator());
            }
            i++;

            defaultValue.append(this.getTabSpace() + this.getTabSpace() + classConstName);

            /*
             * Creates an import list.
             * Since duplicates are not allowed, the strategy is rather not to check for duplicates, but rather to generate errors at compiling.
             */
            String importHeader = "import { " + classConstName + " } from \"" + structure.getBasedir() + "/" + classPackageName.replace(".", "/") + "/" + className + "\"";
            fCgSourceFile.getHeaderList().add(importHeader);
        }
        defaultValue.append(this.getLineSeparator() + this.getTabSpace() + "]");
        defaultValue.append(this.getLineSeparator() + "}");

        field.setDefault(defaultValue.toString());

        // Auto-generates the actual source code based on the collected information.
        BlancoCgTransformerFactory.getTsSourceTransformer().transform(
                fCgSourceFile, fileBlancoMain);
    }

    /**
     * Creates a .vue file from the given component definition information.
     *
     * @param argClassStructure
     * @param argDirectoryTarget
     */
    private void generateComponent(
            final BlancoVueComponentClassStructure argClassStructure,
            final File argDirectoryTarget
    ) {
        /*
         * The output directory will be in the format specified by the targetStyle argument of the ant task.
         * For compatibility, the output directory will be blanco/main if it is not specified.
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

        /*
         * Matches the package (placement position) to the implementation class.
         */
        String packageName = argClassStructure.getPackage();
        if (packageName == null) {
            packageName = "";
        }
        String packageDir = packageName.replace(".", "/");
        impleDir += "/" + packageDir;

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
     * Generate options for defineComponent argument.
     *
     * @param argClassStructure
     *            Class information.
     * @param argDirectoryTarget
     *            Output directory for TypeScript source code.
     * @throws IOException
     *             If an I/O exception occurs.
     */
    public void generateDefineComponent(
            final BlancoVueComponentClassStructure argClassStructure,
            final File argDirectoryTarget) throws IOException {
        /*
         * The output directory will be in the format specified by the targetStyle argument of
         the ant task.
         * For compatibility, the output directory will be blanco/main if it is not specified.
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
            System.out.println("/* tueda */ generateDefineComponent argDirectoryTarget : " + argDirectoryTarget.getAbsolutePath());
        }

        boolean useTemplate = argClassStructure.getUseTemplate();
        boolean useSetup = argClassStructure.getUseSetup();
        boolean useData = argClassStructure.getUseData();
        boolean existsComponents = argClassStructure.getComponentList().size() != 0;
        boolean existsEmits = argClassStructure.getEmitsList().size() != 0;

        /*
         * Props always have subject and alias properties at least.
         * if useTemplate is false, render function should be implemented.
         */
        String propsType = argClassStructure.getName() + "Props";
        String propsConst = BlancoNameAdjuster.toParameterName(propsType);

        // Gets an instance of the BlancoCgObjectFactory class.
        fCgFactory = BlancoCgObjectFactory.getInstance();

        fCgSourceFile = fCgFactory.createSourceFile(argClassStructure
                .getPackage(), null);
        fCgSourceFile.setEncoding(fEncoding);
        fCgSourceFile.setTabs(this.getTabs());

        // Creates a class.
        fCgClass = fCgFactory.createClass(argClassStructure.getName(), "");
        fCgSourceFile.getClassList().add(fCgClass);
        // Do not declare class for defineComponent calling.
        fCgClass.setNoClassDeclare(true);

        List<String> plainTextList = fCgClass.getPlainTextList();
        // defineComponent call
        // Sets the JavaDoc for the class.
        plainTextList.add("/**");
        plainTextList.add(" * " + argClassStructure.getDescription());
        for (String line : argClassStructure.getDescriptionList()) {
            plainTextList.add(" * " + line);
        }
        plainTextList.add(" */");
        plainTextList.add("export default defineComponent({");
        plainTextList.add("name: \"" + argClassStructure.getName() + "\",");
        plainTextList.add("props: " + propsConst);
        if (existsComponents) {
            addCommaToListString(plainTextList);
            plainTextList.add("components: {");
            int loop = 0;
            for (String comp : argClassStructure.getComponentList()) {
                if (loop > 0) {
                    addCommaToListString(plainTextList);
                }
                plainTextList.add(this.getTabSpace() + this.getTabSpace() + comp);
                loop++;
            }
            plainTextList.add(this.getTabSpace() + "}");
        }
        if (existsEmits) {
            addCommaToListString(plainTextList);
            plainTextList.add("emits: " + BlancoNameAdjuster.toParameterName(argClassStructure.getName()) + "Emits");
        }
        if (useSetup) {
            addCommaToListString(plainTextList);
            plainTextList.add("setup: (props, context) => {");
            plainTextList.add(this.getTabSpace() + this.getTabSpace() + "return " + BlancoNameAdjuster.toParameterName(argClassStructure.getName()) + "Setup(props as " + propsType + ", context);");
            plainTextList.add(this.getTabSpace() + "}");
        }
        if (useData) {
            addCommaToListString(plainTextList);
            plainTextList.add("data: () => {");
            plainTextList.add(this.getTabSpace() + this.getTabSpace() + "return " + BlancoNameAdjuster.toParameterName(argClassStructure.getName()) + "Data();");
            plainTextList.add(this.getTabSpace() + "}");
        }
        if (!useTemplate) {
            addCommaToListString(plainTextList);
            plainTextList.add("render: () => {");
            plainTextList.add(this.getTabSpace() + this.getTabSpace() + "return " + BlancoNameAdjuster.toParameterName(argClassStructure.getName()) + "Render();");
            plainTextList.add(this.getTabSpace() + "}");
        }
        plainTextList.add("});");

        /* In TypeScript, sets the header instead of import. */
        for (int index = 0; index < argClassStructure.getComponentHeaderList()
                .size(); index++) {
            final String header = (String) argClassStructure.getComponentHeaderList()
                    .get(index);
            fCgSourceFile.getHeaderList().add(header);
        }

        // Auto-generates the actual source code based on the collected information.
        BlancoCgTransformerFactory.getTsSourceTransformer().transform(
                fCgSourceFile, fileBlancoMain);
    }

    /**
     * Generate props options file.
     *
     * @param argClassStructure
     * @param argDirectoryTarget
     * @throws IOException
     */
    public void generatePropsOptioins(
            final BlancoVueComponentClassStructure argClassStructure,
            final File argDirectoryTarget,
            final Map<String, String> argQueryProps) throws IOException {
        /*
         * The output directory will be in the format specified by the targetStyle argument of
         the ant task.
         * For compatibility, the output directory will be blanco/main if it is not specified.
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
            System.out.println("/* tueda */ generatePropsOptions argDirectoryTarget : " + argDirectoryTarget.getAbsolutePath());
        }

        /*
         * Props always have subject and alias properties at least.
         * if useTemplate is false, render function should be implemented.
         */
        String propsType = argClassStructure.getName() + "Props";
        String propsInterface = argClassStructure.getName() + "Interface";
        String propsConst = BlancoNameAdjuster.toParameterName(propsType);

        // Gets an instance of the BlancoCgObjectFactory class.
        fCgFactory = BlancoCgObjectFactory.getInstance();

        fCgSourceFile = fCgFactory.createSourceFile(argClassStructure
                .getPackage(), null);
        fCgSourceFile.setEncoding(fEncoding);
        fCgSourceFile.setTabs(this.getTabs());

        // Create interface first.
        fCgInterface = fCgFactory.createInterface(propsInterface, fBundle.getXml2sourceFilePropsInterfaceDescription());
        fCgSourceFile.getInterfaceList().add(fCgInterface);
        fCgInterface.setAccess("public");

        for (BlancoVueComponentPropsStructure propsStructure : argClassStructure.getPropsList()) {
            BlancoCgField fieldInf = fCgFactory.createField(
                    propsStructure.getName(),
                    propsStructure.getType(),
                    propsStructure.getDescription()
            );
            fCgInterface.getFieldList().add(fieldInf);

            for (String line : propsStructure.getDescriptionList()) {
                fieldInf.getLangDoc().getDescriptionList().add(line);
            }
            String generics = propsStructure.getGeneric();
            if (BlancoStringUtil.null2Blank(generics).length() != 0) {
                fieldInf.getType().setGenerics(generics);
            }
            fieldInf.setNotnull(!propsStructure.getNullable());
            fieldInf.setAccess("");
            // Stores the query string.
            String queryParam = propsStructure.getQueryParam();
            if (argQueryProps != null && queryParam != null && queryParam.length() > 0) {
                argQueryProps.put(propsStructure.getName(), queryParam);
            }
        }

        // Create non-named class.
        fCgClass = fCgFactory.createClass(propsType, null);
        fCgSourceFile.getClassList().add(fCgClass);
        // Do not declare class for defineComponent calling.
        fCgClass.setNoClassDeclare(true);

        List<String> plainTextList = fCgClass.getPlainTextList();
        plainTextList.add("");
        // declare props type
        plainTextList.add("/** " + fBundle.getXml2sourceFilePropsTypeDescription() + " */");
        plainTextList.add("export declare type " + propsType + " = Readonly<LooseRequired<" + propsInterface + ">>;");
        plainTextList.add("");

        // props definition
        BlancoCgField propsField = fCgFactory.createField(propsConst, "ComponentPropsOptions", fBundle.getXml2sourceFilePropsDescription());
        fCgClass.getFieldList().add(propsField);
        propsField.getType().setGenerics(propsInterface);
        propsField.setAccess("export const");
        propsField.setNotnull(true);

        StringBuffer propsBuf = new StringBuffer();
        int loopCount = 0;
        boolean written = false;
        propsBuf.append("{\n");
        for (BlancoVueComponentPropsStructure propsStructure : argClassStructure.getPropsList()) {
            boolean isRequired = propsStructure.getRequired();
            boolean isDefault = BlancoStringUtil.null2Blank(propsStructure.getDefault()).length() != 0;
            if (isRequired || isDefault) {
                if (loopCount > 0 && written) {
                    propsBuf.append("," + this.getLineSeparator());
                }
                written = true;
                propsBuf.append(this.getTabSpace() + propsStructure.getName() + ": {" + this.getLineSeparator());
                if (isRequired) {
                    propsBuf.append(this.getTabSpace() + this.getTabSpace() + "required: true");
                }
                if (isDefault) {
                    if (propsStructure.getRequired()) {
                        propsBuf.append("," + this.getLineSeparator());
                    }
                    propsBuf.append(this.getTabSpace() + this.getTabSpace() + "default: ");
                    propsBuf.append(propsStructure.getDefault());
                }
                propsBuf.append(this.getLineSeparator() + this.getTabSpace() + "}");
            } else {
                written = false;
            }
            loopCount++;
        }
        propsBuf.append(this.getLineSeparator() + "}");
        propsField.setDefault(propsBuf.toString());

        /* In TypeScript, sets the header instead of import. */
        for (int index = 0; index < argClassStructure.getPropsHeaderList()
                .size(); index++) {
            final String header = argClassStructure.getPropsHeaderList()
                    .get(index);
            fCgSourceFile.getHeaderList().add(header);
        }

        // Auto-generates the actual source code based on the collected information.
        BlancoCgTransformerFactory.getTsSourceTransformer().transform(
                fCgSourceFile, fileBlancoMain);
    }

    /**
     * Generate emits options
     *
     * @param argClassStructure
     * @param argDirectoryTarget
     * @throws IOException
     */
    public void generateEmitsOptioins(
            final BlancoVueComponentClassStructure argClassStructure,
            final File argDirectoryTarget) throws IOException {
        /*
         * The output directory will be in the format specified by the targetStyle argument of
         the ant task.
         * For compatibility, the output directory will be blanco/main if it is not specified.
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
            System.out.println("/* tueda */ generateEmitsOptions argDirectoryTarget : " + argDirectoryTarget.getAbsolutePath());
        }

        String emitsType = argClassStructure.getName() + "Emits";
        String emitsConst = BlancoNameAdjuster.toParameterName(emitsType);

        fCgFactory = BlancoCgObjectFactory.getInstance();

        fCgSourceFile = fCgFactory.createSourceFile(argClassStructure
                .getPackage(), null);
        fCgSourceFile.setEncoding(fEncoding);
        fCgSourceFile.setTabs(this.getTabs());

        // Create non-named class.
        fCgClass = fCgFactory.createClass(emitsType, null);
        fCgSourceFile.getClassList().add(fCgClass);
        // Do not declare class for defineComponent calling.
        fCgClass.setNoClassDeclare(true);

        List<String> plainTextList = fCgClass.getPlainTextList();
        plainTextList.add("");
        // declare emits type
        plainTextList.add("/** " + fBundle.getXml2sourceFileEmitsTypeDescription() + " */");
        plainTextList.add("export declare type " + emitsType + " = {");
        int emitsLoopCount = 0;
        for (BlancoVueComponentEmitsStructure emitsStructure : argClassStructure.getEmitsList()) {
            if (emitsLoopCount > 0) {
                this.addCommaToListString(plainTextList);
            }
            String type = emitsStructure.getType();
            if (BlancoStringUtil.null2Blank(emitsStructure.getGeneric()).length() != 0) {
                String simpleGeneric = BlancoVueComponentUtil.getSimpleClassName(emitsStructure.getGeneric());
                type = type + "<" + simpleGeneric + ">";
            }
            plainTextList.add(this.getTabSpace() + "\"" + emitsStructure.getName() + "\": (value: " + type + ") => boolean");
            emitsLoopCount++;
        }
        plainTextList.add("} | ObjectEmitsOptions;");

        // props definition
        BlancoCgField emitsField = fCgFactory.createField(emitsConst, emitsType, fBundle.getXml2sourceFileEmitsDescription());
        fCgClass.getFieldList().add(emitsField);
        emitsField.setAccess("export const");
        emitsField.setNotnull(true);

        StringBuffer emitsBuf = new StringBuffer();
        int loopCount = 0;
        emitsBuf.append("{" + this.getLineSeparator());
        for (BlancoVueComponentEmitsStructure emitsStructure : argClassStructure.getEmitsList()) {
            if (loopCount > 0) {
                emitsBuf.append("," + this.getLineSeparator());
            }
            String type = emitsStructure.getType();
            if (BlancoStringUtil.null2Blank(emitsStructure.getGeneric()).length() != 0) {
                String simpleGeneric = BlancoVueComponentUtil.getSimpleClassName(emitsStructure.getGeneric());
                type = type + "<" + simpleGeneric + ">";
            }
            emitsBuf.append(this.getTabSpace() + "\"" + emitsStructure.getName() + "\": (value: " + type + ") => true");
            loopCount++;
        }
        emitsBuf.append(this.getLineSeparator() + "}");
        emitsField.setDefault(emitsBuf.toString());

        /* In TypeScript, sets the header instead of import. */
        for (int index = 0; index < argClassStructure.getEmitsHeaderList()
                .size(); index++) {
            final String header = (String) argClassStructure.getEmitsHeaderList()
                    .get(index);
            fCgSourceFile.getHeaderList().add(header);
        }

        // Auto-generates the actual source code based on the collected information.
        BlancoCgTransformerFactory.getTsSourceTransformer().transform(
                fCgSourceFile, fileBlancoMain);
    }

    /**
     * For the screen component, creates a RouteConfig for vue-router.
     *  @param argClassStructure
     * @param argDirectoryTarget
     * @param argQueryProps
     */
    private void generateRouteRecord(
            final BlancoVueComponentClassStructure argClassStructure,
            final File argDirectoryTarget,
            final Map<String, String> argQueryProps) {
        /*
         * The output directory will be in the format specified by the targetStyle argument of the ant task.
         * For compatibility, the output directory will be blanco/main if it is not specified.
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
            System.out.println("/* tueda */ generateRouteConfig argDirectoryTarget : " + argDirectoryTarget.getAbsolutePath());
        }

        /*
         * Prepares the necessary information.
         */
        String suffix = "RouteRecord";
        String className = argClassStructure.getName() + suffix;
        String constName = BlancoNameAdjuster.toParameterName(className);
        String strImport = "import { RouteRecordRaw } from \"vue-router\"";
//        String strImport2 = "import { RoutePropsFunction } from \"vue-router/types/router\"";
        String strPackage = argClassStructure.getPackage();

        String propPathValue = "\"" + argClassStructure.getRouterPath() + "\"";
        String propNameValue = "\"" + argClassStructure.getRouterName() + "\"";
        String propMetaValue = "{" + this.getLineSeparator() +
                this.getTabSpace() + this.getTabSpace() + "reload: " + (argClassStructure.getForceReload() ? "true" : "false") + "," + this.getLineSeparator() +
                this.getTabSpace() + this.getTabSpace() + "authRequired: " + (argClassStructure.getAuthRequired() ? "true" : "false") + this.getLineSeparator() +
                this.getTabSpace() + "}";
        String propComponentValue = "() => import(\"" +
                argClassStructure.getBasedir() + "/" +
                strPackage.replace(".", "/") + "/" +
                argClassStructure.getName() + ".vue\")";

        String propPropsValue = "";
        if (argQueryProps != null && argQueryProps.size() > 0) {
            StringBuffer sb = new StringBuffer();
            Set<String> keySet = argQueryProps.keySet();

            sb.append("route => ({" + this.getLineSeparator());
            int i = 0;
            for (String key : keySet) {
                if (i > 0) {
                    sb.append("," + this.getLineSeparator());
                }
                sb.append(this.getTabSpace() + this.getTabSpace() + key + ": route.query." + argQueryProps.get(key));
                i++;
            }
            sb.append(this.getLineSeparator());
            sb.append(this.getTabSpace() + "})");
            propPropsValue = sb.toString();
        }

        // Gets an instance of the BlancoCgObjectFactory class.
        fCgFactory = BlancoCgObjectFactory.getInstance();

        fCgSourceFile = fCgFactory.createSourceFile(argClassStructure
                .getPackage(), null);
        fCgSourceFile.setEncoding(fEncoding);
        fCgSourceFile.setTabs(this.getTabs());

        // Creates a class.
        fCgClass = fCgFactory.createClass(className, null);
        fCgSourceFile.getClassList().add(fCgClass);
        fCgClass.setAccess("");
        fCgClass.setNoClassDeclare(true);

        // Imports the RouteConfig.
        fCgSourceFile.getHeaderList().add(strImport);

        BlancoCgField cgRouteRecord = fCgFactory.createField(constName, "RouteRecordRaw", fBundle.getXml2sourceFileRouteconfigClass(argClassStructure.getName()));
        fCgClass.getFieldList().add(cgRouteRecord);
        cgRouteRecord.setAccess("export const");
        cgRouteRecord.setNotnull(true);
        cgRouteRecord.setNotnull(true);

        StringBuffer recordBuf = new StringBuffer();
        recordBuf.append("{" + this.getLineSeparator());

        // Creates the field as public. It doesn't create a Getter/Setter.
        recordBuf.append(this.buildRouteRecordField("path", "string", propPathValue));
        recordBuf.append("," + this.getLineSeparator());
        recordBuf.append(this.buildRouteRecordField("name", "string", propNameValue));
        recordBuf.append("," + this.getLineSeparator());
        recordBuf.append(this.buildRouteRecordField("component", "object", propComponentValue));
        recordBuf.append("," + this.getLineSeparator());
        recordBuf.append(this.buildRouteRecordField("meta", "any", propMetaValue));
        if (propPropsValue != null && propPropsValue.length() > 0) {
            recordBuf.append("," + this.getLineSeparator());
            recordBuf.append(this.buildRouteRecordField("props", "RoutePropsFunction", propPropsValue));
        }
        recordBuf.append(this.getLineSeparator() + "}");
        cgRouteRecord.setDefault(recordBuf.toString());

        // Auto-generates the actual source code based on the collected information.
        BlancoCgTransformerFactory.getTsSourceTransformer().transform(
                fCgSourceFile, fileBlancoMain);
    }

    /**
     * Generates the field of RouteConfig.
     * @param fieldName
     * @param fieldType
     * @param defaultValue
     */
    private String buildRouteRecordField(
            String fieldName,
            String fieldType,
            String defaultValue
    ) {
        boolean isString = false;
        if ("string".equalsIgnoreCase(fieldType)) {
            isString = true;
        }
        StringBuffer fieldBuf = new StringBuffer();
        fieldBuf.append(this.getTabSpace() + fieldName + ": ");
        fieldBuf.append(defaultValue);
        return fieldBuf.toString();
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

    private void addCommaToListString(List<String> list) {
        if (list.size() > 0) {
            String lastline = list.remove(list.size() - 1);
            list.add(lastline + ",");
        }
    }
}
