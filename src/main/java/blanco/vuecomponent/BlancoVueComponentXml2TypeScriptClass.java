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
import blanco.cg.valueobject.*;
import blanco.commons.util.BlancoNameAdjuster;
import blanco.commons.util.BlancoStringUtil;
import blanco.commons.util.BlancoXmlUtil;
import blanco.vuecomponent.message.BlancoVueComponentMessage;
import blanco.vuecomponent.resourcebundle.BlancoVueComponentResourceBundle;
import blanco.vuecomponent.valueobject.BlancoVueComponentApiStructure;
import blanco.vuecomponent.valueobject.BlancoVueComponentClassStructure;
import blanco.vuecomponent.valueobject.BlancoVueComponentEmitsStructure;
import blanco.vuecomponent.valueobject.BlancoVueComponentPropsStructure;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private String fRouteRecordMap = "";
    public String getRouteRecordMap() {
        return this.fRouteRecordMap;
    }
    public void setRouteRecordMap(String routeRecordMap) {
        this.fRouteRecordMap = routeRecordMap;
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
        parser.setXml2Class(this);
        final BlancoVueComponentClassStructure[] structures = parser.parse(argMetaXmlSourceFile);

        for (int index = 0; index < structures.length; index++) {
            BlancoVueComponentClassStructure classStructure = structures[index];
            /* First, creates a .vue file. */
            generateComponent(classStructure, argDirectoryTarget);

            /* Saves a property with a query string */
            Map<String, String> queryProps = new HashMap<>();
            /* Saves a property with a path string */
            Map<String, String> pathProps = new HashMap<>();

            /* Generate defineComponent calling. */
            generateDefineComponent(classStructure, argDirectoryTarget);

            /* Generate props options */
            generatePropsOptioins(classStructure, argDirectoryTarget, queryProps, pathProps);

            /* Generate emits options */
            if (classStructure.getEmitsList() != null && classStructure.getEmitsList().size() != 0) {
                generateEmitsOptioins(classStructure, argDirectoryTarget);
            }

            /* Generate RequestFactory  */
            if (classStructure.getApiList() != null && classStructure.getApiList().size() != 0) {
                generateRequestFactory(classStructure, argDirectoryTarget);
            }

            /* In the case of the screen, creates a RouterConfig. */
            if ("screen".equalsIgnoreCase(classStructure.getComponentKind())) {
                generateRouteRecord(classStructure, argDirectoryTarget, queryProps, pathProps);
                if (!BlancoStringUtil.null2Blank(BlancoVueComponentUtil.menuItemInterface).trim().isEmpty() &&
                        !BlancoStringUtil.null2Blank(classStructure.getMenuLabel()).trim().isEmpty()) {
                    generateMenuItem(classStructure, argDirectoryTarget);
                }
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
     * Generate BreadCrumbInterface for bread crumb default value.
     * @param argDirectoryTarget
     */
    public void processBreadCrumbInterface(
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
            System.out.println("/* tueda */ generateClass argDirectoryTarget : " + argDirectoryTarget.getAbsolutePath());
        }

        String interfaceName = BlancoVueComponentUtil.breadCrumbInterface;
        String simpleClassName = BlancoVueComponentUtil.getSimpleClassName(interfaceName);
        String packageName = BlancoVueComponentUtil.getPackageName(interfaceName);

        // Gets an instance of the BlancoCgObjectFactory class.
        fCgFactory = BlancoCgObjectFactory.getInstance();
        fCgSourceFile = fCgFactory.createSourceFile(packageName, null);
        fCgSourceFile.setEncoding(fEncoding);
        fCgSourceFile.setTabs(this.getTabs());

        // Creates a interface.
        fCgInterface = fCgFactory.createInterface(simpleClassName, fBundle.getXml2sourceFileBreadCrumbInterface());
        fCgSourceFile.getInterfaceList().add(fCgInterface);
        fCgInterface.setAccess("public");

        BlancoCgField name = fCgFactory.createField("name", "string", fBundle.getXml2sourceFileBreadCrumbName());
        BlancoCgField nolink = fCgFactory.createField("nolink", "boolean", fBundle.getXml2sourceFileBreadCrumbNolink());
        fCgInterface.getFieldList().add(name);
        fCgInterface.getFieldList().add(nolink);
        name.setAccess("public");
        name.setNotnull(true);
        nolink.setAccess("public");
        nolink.setNotnull(true);

        // Auto-generates the actual source code based on the collected information.
        BlancoCgTransformerFactory.getTsSourceTransformer().transform(
                fCgSourceFile, fileBlancoMain);
    }

    /**
     * create RouteRecordMapInterface.<br>
     * <br>
     * import {RouteRecordRaw} from "vue-router";<br>
     * export interface RouteRecordMapInterface {<br>
     *   [key: string]: RouteRecordRaw;<br>
     * }<br>
     * <br>
     * @param argDirectoryTarget
     */
    public void processRouteRecordMapInterface(
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
            System.out.println("/* tueda */ generateClass argDirectoryTarget : " + argDirectoryTarget.getAbsolutePath());
        }

        String interfaceName = this.getRouteRecordMap() + "Interface";
        String simpleClassName = BlancoVueComponentUtil.getSimpleClassName(interfaceName);
        String packageName = BlancoVueComponentUtil.getPackageName(interfaceName);

        // Gets an instance of the BlancoCgObjectFactory class.
        fCgFactory = BlancoCgObjectFactory.getInstance();
        fCgSourceFile = fCgFactory.createSourceFile(packageName, null);
        fCgSourceFile.setEncoding(fEncoding);
        fCgSourceFile.setTabs(this.getTabs());

        // Creates a interface.
        fCgInterface = fCgFactory.createInterface(simpleClassName, fBundle.getXml2sourceFileRouterecordMapInterface());
        fCgSourceFile.getInterfaceList().add(fCgInterface);
        fCgInterface.setAccess("public");
        /*
         * Creates an import.
         */
        String importHeader = "import { RouteRecordRaw } from \"vue-router\"";
        fCgSourceFile.getHeaderList().add(importHeader);

        List<String> plainTextList = fCgInterface.getPlainTextList();
        plainTextList.add(this.getTabSpace() + "[key: string]: RouteRecordRaw;");

        // Auto-generates the actual source code based on the collected information.
        BlancoCgTransformerFactory.getTsSourceTransformer().transform(
                fCgSourceFile, fileBlancoMain);
    }

    /**
     * After all meta files are parsed and generated, generate routeRecordMap
     * mainly using for bread crumbs .
     *
     * @param argClassStructures
     * @param argDirectoryTarget
     * @throws IOException
     */
    public void processRouteRecordMap(
            final List<BlancoVueComponentClassStructure> argClassStructures,
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
            System.out.println("/* tueda */ generateClass argDirectoryTarget : " + argDirectoryTarget.getAbsolutePath());
        }

        String simpleClassName = BlancoVueComponentUtil.getSimpleClassName(this.getRouteRecordMap());
        String constName = BlancoNameAdjuster.toParameterName(simpleClassName);
        String packageName = BlancoVueComponentUtil.getPackageName(this.getRouteRecordMap());
        String simpleInterfaceName = simpleClassName + "Interface";

        /* At least one structure exists. */
        BlancoVueComponentClassStructure delegateStructure = argClassStructures.get(0);
        String baseDir = delegateStructure.getBasedir();

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
//        field.setTypeInference(true);
        BlancoCgType myType = fCgFactory.createType(simpleInterfaceName);
        field.setType(myType);

        StringBuffer defaultValue = new StringBuffer();
        defaultValue.append("{" + this.getLineSeparator());

        /* Import interface */
        String interfaceHeader = "import { " + simpleInterfaceName + " } from \"" + baseDir + "/" + packageName.replace(".", "/") + "/" + simpleInterfaceName + "\"";
        fCgSourceFile.getHeaderList().add(interfaceHeader);

        /* "alias" is defined as default value. */
        String keyGetter = "get" + BlancoNameAdjuster.toClassName(BlancoVueComponentUtil.routeRecordMapKey);

        String routeRecordSuffix = "RouteRecord";
        int i = 0;
        for (BlancoVueComponentClassStructure structure : argClassStructures) {
            /* get key value first. */
            String keyValue = null;
            Class<? extends BlancoVueComponentClassStructure> clazz = structure.getClass();
            try {
                Method getter = clazz.getMethod(keyGetter);
                keyValue = (String) getter.invoke(structure);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error!!! next structure.");
            }
            if (keyValue == null || keyValue.isEmpty()) {
                System.out.println("No Such Item: " + keyGetter);
                continue;
            }

            /* get routeRecord const name. */
            String className = structure.getName() + routeRecordSuffix;
            String classConstName = BlancoNameAdjuster.toParameterName(className);
            String classPackageName = structure.getPackage();
            if (classPackageName == null) {
                classPackageName = "";
            }

            if (i != 0) {
                defaultValue.append("," + this.getLineSeparator());
            }
            i++;

            defaultValue.append(this.getTabSpace() + keyValue + ": " + classConstName);

            /*
             * Creates an import list.
             * Since duplicates are not allowed, the strategy is rather not to check for duplicates, but rather to generate errors at compiling.
             */
            String importHeader = "import { " + classConstName + " } from \"" + structure.getBasedir() + "/" + classPackageName.replace(".", "/") + "/" + className + "\"";
            fCgSourceFile.getHeaderList().add(importHeader);
        }
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

            /* cast props as the ComponentProps type. */
            plainTextList.add("const myProps = props as " + propsType + ";");

            /* Implement RequestFactory */
            Boolean isRequestFactory = false;
            if (argClassStructure.getApiList() != null && argClassStructure.getApiList().size() != 0) {
                isRequestFactory = true;
                String factoryClass = argClassStructure.getName() + "RequestFactory";
                plainTextList.add("const factory: " + factoryClass + " = {");
                int apiCount = 0;
                for (BlancoVueComponentApiStructure apiStructure : argClassStructure.getApiList()) {
                    if (apiCount > 0) {
                        addCommaToListString(plainTextList);
                    }
                    String requestClass = BlancoNameAdjuster.toClassName(apiStructure.getName()) + BlancoNameAdjuster.toClassName(apiStructure.getMethod()) + "Request";
                    String creator = "create" + requestClass;
                    plainTextList.add(creator + "(): " + requestClass + " {");
                    plainTextList.add("return new " + requestClass + "();");
                    plainTextList.add("}");
                    apiCount++;
                }
                plainTextList.add("};");

                /* Add headers */
                if (argClassStructure.getApiHeaderList() != null && argClassStructure.getApiHeaderList().size() > 0) {
                    argClassStructure.getComponentHeaderList().addAll(argClassStructure.getApiHeaderList());
                }
            }

            /* Implement onBeforeRouteLeave */
            if (BlancoStringUtil.null2Blank(argClassStructure.getBeforeRouteLeave()).length() > 0 && "screen".equals(argClassStructure.getComponentKind())) {
                plainTextList.add("const noAuthPath = inject<string>('noAuthPath');");
                plainTextList.add ("onBeforeRouteLeave((to, from, next) => {");
                plainTextList.add(argClassStructure.getBeforeRouteLeave() + "(useRouter(), to, from, next, myProps.componentId, noAuthPath!);");
                plainTextList.add("});");
                /* Add headers */
                argClassStructure.getComponentHeaderList().add("import { onBeforeRouteLeave, useRouter } from \"vue-router\"");
            }

            plainTextList.add(this.getTabSpace() + this.getTabSpace() + "return " + BlancoNameAdjuster.toParameterName(argClassStructure.getName()) + "Setup(myProps, context" + (isRequestFactory ? ", factory" : "") + ");");
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
            final Map<String, String> argQueryProps,
            final Map<String, String> argPathProps
    ) throws IOException {
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
            if (argQueryProps != null && queryParam != null && !queryParam.isEmpty()) {
                argQueryProps.put(propsStructure.getName(), queryParam);
            }
            // Store the path string
            String pathParam = propsStructure.getPathParam();
            if (argPathProps != null && pathParam != null && !pathParam.isEmpty()) {
                argPathProps.put(propsStructure.getName(), pathParam);
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
            }
//            else {
//                written = false;
//            }
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
     * Generate RequestFactory class
     *
     * @param argClassStructure
     * @param argDirectoryTarget
     * @throws IOException
     */
    public void generateRequestFactory(
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
            System.out.println("/* tueda */ generateRequestFactory argDirectoryTarget : " + argDirectoryTarget.getAbsolutePath());
        }

        String factoryClass = argClassStructure.getName() + "RequestFactory";

        fCgFactory = BlancoCgObjectFactory.getInstance();

        fCgSourceFile = fCgFactory.createSourceFile(argClassStructure
                .getPackage(), null);
        fCgSourceFile.setEncoding(fEncoding);
        fCgSourceFile.setTabs(this.getTabs());

        // Create interface.
        fCgInterface = fCgFactory.createInterface(factoryClass, fBundle.getXml2sourceFileRequestFactoryDescription(factoryClass));
        fCgSourceFile.getInterfaceList().add(fCgInterface);
        fCgInterface.setAccess("public");

        for (BlancoVueComponentApiStructure apiStructure : argClassStructure.getApiList()) {
            String requestClass = BlancoNameAdjuster.toClassName(apiStructure.getName()) + BlancoNameAdjuster.toClassName(apiStructure.getMethod()) + "Request";
            String creator = "create" + requestClass;
            BlancoCgMethod apiMethod = fCgFactory.createMethod(creator, fBundle.getXml2sourceFileRequestFactoryMethodDescription(requestClass));
            fCgInterface.getMethodList().add(apiMethod);
            apiMethod.setAccess("public");
            apiMethod.setNotnull(true);

            BlancoCgReturn apiReturn = fCgFactory.createReturn(requestClass, fBundle.getXml2sourceFileRequestFactoryReturnDescription(requestClass));
            apiReturn.setNullable(false);
            apiMethod.setReturn(apiReturn);
        }

        /* In TypeScript, sets the header instead of import. */
        for (int index = 0; index < argClassStructure.getApiHeaderList()
                .size(); index++) {
            final String header = (String) argClassStructure.getApiHeaderList()
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
     * @param argPathProps
     */
    private void generateRouteRecord(
            final BlancoVueComponentClassStructure argClassStructure,
            final File argDirectoryTarget,
            final Map<String, String> argQueryProps,
            final Map<String, String> argPathProps
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

        String metaBreadCrumbName = null;
        Class<? extends BlancoVueComponentClassStructure> clazz = argClassStructure.getClass();
        try {
            String getterKey = "get" + BlancoNameAdjuster.toClassName(BlancoVueComponentUtil.routeRecordBreadCrumbName);
            Method getter = clazz.getMethod(getterKey);
            metaBreadCrumbName = (String) getter.invoke(argClassStructure);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!!! next structure.");
        }

        String propMetaValue = "{" + this.getLineSeparator() +
                (BlancoStringUtil.null2Blank(argClassStructure.getLayoutType()).trim().length() > 0 ? this.getTabSpace() + this.getTabSpace() + "layoutType: \"" + argClassStructure.getLayoutType() + "\"," + this.getLineSeparator() : "") +
                (BlancoStringUtil.null2Blank(metaBreadCrumbName).trim().length() > 0 ? this.getTabSpace() + this.getTabSpace() + "breadCrumb: \"" + metaBreadCrumbName + "\"," + this.getLineSeparator() : "") +
                this.getTabSpace() + this.getTabSpace() + "componentId: \"" + argClassStructure.getName() + "\"," + this.getLineSeparator() +
                this.getTabSpace() + this.getTabSpace() + "reload: " + (argClassStructure.getForceReload() ? "true" : "false") + "," + this.getLineSeparator() +
                this.getTabSpace() + this.getTabSpace() + "authRequired: " + (argClassStructure.getAuthRequired() ? "true" : "false") + this.getLineSeparator() +
                this.getTabSpace() + "}";
        String propComponentValue = "() => import(\"" +
                argClassStructure.getBasedir() + "/" +
                strPackage.replace(".", "/") + "/" +
                argClassStructure.getName() + ".vue\")";

        String propPropsValue = "";
        boolean pathPropsExist = (argPathProps != null && !argPathProps.isEmpty());
        boolean queryPropsExist = (argQueryProps != null && !argQueryProps.isEmpty());
        if (pathPropsExist || queryPropsExist) {
            assert argPathProps != null;
            assert argQueryProps != null;

            StringBuffer sb = new StringBuffer();
            sb.append("route => ({" + this.getLineSeparator());
            List<BlancoVueComponentPropsStructure> propsStructures = argClassStructure.getPropsList();
            int i = 0;
            for (BlancoVueComponentPropsStructure propsStructure : propsStructures) {
                String propName = propsStructure.getName();
                boolean isPathParam = argPathProps.containsKey(propName);
                boolean isQueryParam = argQueryProps.containsKey(propName);
                if (!isPathParam && !isQueryParam) {
                    continue;
                }
                if (i > 0) {
                    sb.append("," + this.getLineSeparator());
                }
                i++;

                sb.append(this.getTabSpace() + this.getTabSpace() + propName + ": route.");

                if (isPathParam) {
                    sb.append("params." + argPathProps.get(propName));
                    if (isQueryParam) {
                        sb.append(" || route.");
                    }
                }
                if (isQueryParam) {
                    sb.append("query." + argQueryProps.get(propName));
                }
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
        recordBuf.append(this.buildMapField("path", "string", propPathValue));
        recordBuf.append("," + this.getLineSeparator());
        recordBuf.append(this.buildMapField("name", "string", propNameValue));
        recordBuf.append("," + this.getLineSeparator());
        recordBuf.append(this.buildMapField("component", "object", propComponentValue));
        recordBuf.append("," + this.getLineSeparator());
        recordBuf.append(this.buildMapField("meta", "any", propMetaValue));
        if (!propPropsValue.isEmpty()) {
            recordBuf.append("," + this.getLineSeparator());
            recordBuf.append(this.buildMapField("props", "RoutePropsFunction", propPropsValue));
        }
        recordBuf.append(this.getLineSeparator() + "}");
        cgRouteRecord.setDefault(recordBuf.toString());

        // Auto-generates the actual source code based on the collected information.
        BlancoCgTransformerFactory.getTsSourceTransformer().transform(
                fCgSourceFile, fileBlancoMain);
    }

    /**
     * Generate MenuInfoInterface
     * @param argDirectoryTarget
     */
    public void processMenuInfoInterface(
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
            System.out.println("/* tueda */ generateMenuInfoInterface argDirectoryTarget : " + argDirectoryTarget.getAbsolutePath());
        }

        String interfaceName = BlancoVueComponentUtil.menuItemInterface;
        String simpleClassName = BlancoVueComponentUtil.getSimpleClassName(interfaceName);
        String packageName = BlancoVueComponentUtil.getPackageName(interfaceName);

        // Gets an instance of the BlancoCgObjectFactory class.
        fCgFactory = BlancoCgObjectFactory.getInstance();
        fCgSourceFile = fCgFactory.createSourceFile(packageName, null);
        fCgSourceFile.setEncoding(fEncoding);
        fCgSourceFile.setTabs(this.getTabs());

        // Creates a interface.
        fCgInterface = fCgFactory.createInterface(simpleClassName, fBundle.getXml2sourceFileMenuItemInterface());
        fCgSourceFile.getInterfaceList().add(fCgInterface);
        fCgInterface.setAccess("public");

        BlancoCgField name = fCgFactory.createField("name", "string", fBundle.getXml2sourceFileMenuItemName());
        BlancoCgField label = fCgFactory.createField("label", "string", fBundle.getXml2sourceFileMenuItemLabel());
        BlancoCgField description = fCgFactory.createField("description", "string", fBundle.getXml2sourceFileMenuItemDescriptioin());
        fCgInterface.getFieldList().add(name);
        fCgInterface.getFieldList().add(label);
        fCgInterface.getFieldList().add(description);
        name.setAccess("public");
        name.setNotnull(true);
        label.setAccess("public");
        label.setNotnull(true);
        description.setAccess("public");
        description.setNotnull(false);

        // Auto-generates the actual source code based on the collected information.
        BlancoCgTransformerFactory.getTsSourceTransformer().transform(
                fCgSourceFile, fileBlancoMain);
    }

    private void generateMenuItem(
            final BlancoVueComponentClassStructure argClassStructure,
            final File argDirectoryTarget
    ) {
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
            System.out.println("/* tueda */ generateMenuInfo argDirectoryTarget : " + argDirectoryTarget.getAbsolutePath());
        }

        /*
         * Prepares the necessary information.
         */
        String interfaceName = BlancoVueComponentUtil.menuItemInterface;
        String simpleInterfaceName = BlancoVueComponentUtil.getSimpleClassName(interfaceName);
        String interfacePackageName = BlancoVueComponentUtil.getPackageName(interfaceName);
        final Map<String, List<String>> importHeaderList = new HashMap<>();
        BlancoVueComponentUtil.makeImportHeaderList(interfacePackageName, simpleInterfaceName, importHeaderList, argClassStructure.getBasedir(), argClassStructure.getPackage());
        List<String> headerList = new ArrayList<String>();
        BlancoVueComponentUtil.transformGeneratedHeaderList(headerList, importHeaderList, false);

        String suffix = "MenuItem";
        String className = argClassStructure.getName() + suffix;
        String constName = BlancoNameAdjuster.toParameterName(className);
        String strPackage = argClassStructure.getPackage();

        String propNameValue = "\"" + argClassStructure.getName() + "\"";
        String propLabelValue = "\"" + argClassStructure.getMenuLabel() + "\"";
        String description = argClassStructure.getDescription();

        Class<? extends BlancoVueComponentClassStructure> clazz = argClassStructure.getClass();
        try {
            String getterKey = "get" + BlancoNameAdjuster.toClassName(BlancoVueComponentUtil.menuItemDescription);
            Method getter = clazz.getMethod(getterKey);
            description = (String) getter.invoke(argClassStructure);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!!! next structure.");
        }
        String propDescriptionValue = "\"" + description + "\"";

        fCgFactory = BlancoCgObjectFactory.getInstance();

        fCgSourceFile = fCgFactory.createSourceFile(strPackage, null);
        fCgSourceFile.setEncoding(fEncoding);
        fCgSourceFile.setTabs(this.getTabs());

        // Creates a class.
        fCgClass = fCgFactory.createClass(className, null);
        fCgSourceFile.getClassList().add(fCgClass);
        fCgClass.setAccess("");
        fCgClass.setNoClassDeclare(true);

        /* In TypeScript, sets the header instead of import. */
        fCgSourceFile.getHeaderList().addAll(headerList);

        BlancoCgField cgMenuItem = fCgFactory.createField(constName, simpleInterfaceName, fBundle.getXml2sourceFileMenuItemClassDescription(argClassStructure.getName()));
        fCgClass.getFieldList().add(cgMenuItem);
        cgMenuItem.setAccess("export const");
        cgMenuItem.setNotnull(true);
        cgMenuItem.setNotnull(true);

        StringBuffer menuItemBuf = new StringBuffer();
        menuItemBuf.append("{" + this.getLineSeparator());

        // Creates the field as public. It doesn't create a Getter/Setter.
        menuItemBuf.append(this.buildMapField("name", "string", propNameValue));
        menuItemBuf.append("," + this.getLineSeparator());
        menuItemBuf.append(this.buildMapField("label", "string", propLabelValue));
        menuItemBuf.append("," + this.getLineSeparator());
        if (!BlancoStringUtil.null2Blank(propDescriptionValue).trim().isEmpty()) {
            menuItemBuf.append(this.buildMapField("description", "string", propDescriptionValue));
        }

        menuItemBuf.append(this.getLineSeparator() + "}");
        cgMenuItem.setDefault(menuItemBuf.toString());

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
    private String buildMapField(
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

    public String getTabSpace() {
        StringBuffer spc = new StringBuffer();
        for (int i = this.getTabs(); i > 0; i--) {
            spc.append(" ");
        }
        return spc.toString();
    }

    public String getLineSeparator() {
        return System.getProperty("line.separator", "\n");
    }

    private void addCommaToListString(List<String> list) {
        if (list.size() > 0) {
            String lastline = list.remove(list.size() - 1);
            list.add(lastline + ",");
        }
    }
}
