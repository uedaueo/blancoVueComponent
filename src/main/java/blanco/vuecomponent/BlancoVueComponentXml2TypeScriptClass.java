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
import java.util.*;

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

            /* Next, creates an interface. */
            /*
             * In mixins, it is more convenient to declare property in duplicate, so it is deprecated.
             * 2020/07/01 by tueda
             */
//            generateInterface(classStructure, argDirectoryTarget);

            /* Saves a property with a query string */
            Map<String, String> queryProps = new HashMap<>();

            /* Then, creates a class. */
            generateClass(classStructure, argDirectoryTarget, queryProps);

            /* In the case of the screen, creates a RouterConfig. */
            if ("screen".equalsIgnoreCase(classStructure.getComponentKind())) {
                generateRouteConfig(classStructure, argDirectoryTarget, queryProps);
            }

        }
        return structures;
    }

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
        String packageName = BlancoVueComponentUtil.getPackageName(this.getListClass());

        // Gets an instance of the BlancoCgObjectFactory class.
        fCgFactory = BlancoCgObjectFactory.getInstance();
        fCgSourceFile = fCgFactory.createSourceFile(packageName, null);
        fCgSourceFile.setEncoding(fEncoding);
        fCgSourceFile.setTabs(this.getTabs());

        // Creates a class.
        fCgClass = fCgFactory.createClass(simpleClassName, fBundle.getXml2sourceFileRouteconfigList());
        fCgSourceFile.getClassList().add(fCgClass);
        fCgClass.setAccess("public");
        fCgSourceFile.getHeaderList().add("import { RouteConfig } from \"vue-router\"");

        final BlancoCgField field = fCgFactory.createField(BlancoVueComponentConstants.ROUTE_PAGEDEFS,
                "Array", "The Array of RouteConfig.");
        fCgClass.getFieldList().add(field);

        field.getType().setGenerics("RouteConfig");
        field.setStatic(true);
        field.setNotnull(true);
        field.setAccess("public");

        StringBuffer defaultValue = new StringBuffer();
        defaultValue.append("[" + this.getLineSeparator());

        String suffix = "RouteConfig";
        int i = 0;
        for (BlancoVueComponentClassStructure structure : argClassStructures) {
            String className = structure.getName() + suffix;
            String classPackageName = structure.getPackage();
            if (classPackageName == null) {
                classPackageName = "";
            }
            if (i != 0) {
                defaultValue.append("," + this.getLineSeparator());
            }
            i++;

            defaultValue.append(this.getTabSpace() + this.getTabSpace() + "new " + className + "()");

            /*
             * Creates an import list.
             * Since duplicates are not allowed, the strategy is rather not to check for duplicates, but rather to generate errors at compiling.
             */
            String importHeader = "import { " + className + " } from \"" + structure.getBasedir() + "/" + classPackageName.replace(".", "/") + "/" + className + "\"";
            fCgSourceFile.getHeaderList().add(importHeader);
        }
        defaultValue.append(this.getLineSeparator() + this.getTabSpace() + "]");

        field.setDefault(defaultValue.toString());

        // Auto-generates the actual source code based on the collected information.
        BlancoCgTransformerFactory.getTsSourceTransformer().transform(
                fCgSourceFile, fileBlancoMain);
    }

    /**
     * Auto-generates source code from a given class information value object.
     *
     * @param argClassStructure
     *            Class information.
     * @param argDirectoryTarget
     *            Output directory for TypeScript source code.
     * @param argQueryProps
     * @throws IOException
     *             If an I/O exception occurs.
     */
    public void generateClass(
            final BlancoVueComponentClassStructure argClassStructure,
            final File argDirectoryTarget,
            final Map<String, String> argQueryProps) throws IOException {
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

        // Gets an instance of the BlancoCgObjectFactory class.
        fCgFactory = BlancoCgObjectFactory.getInstance();

        fCgSourceFile = fCgFactory.createSourceFile(argClassStructure
                .getPackage(), null);
        fCgSourceFile.setEncoding(fEncoding);
        fCgSourceFile.setTabs(this.getTabs());

        // Creates a class.
        fCgClass = fCgFactory.createClass(argClassStructure.getName(), "");
        fCgSourceFile.getClassList().add(fCgClass);
        fCgClass.setAccess("default");

        // Inheritance
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

        // Sets the JavaDoc for the class.
        fCgClass.setDescription(argClassStructure.getDescription());
        for (String line : argClassStructure.getDescriptionList()) {
            fCgClass.getLangDoc().getDescriptionList().add(line);
        }

        /* In TypeScript, sets the header instead of import. */
        for (int index = 0; index < argClassStructure.getComponentHeaderList()
                .size(); index++) {
            final String header = (String) argClassStructure.getComponentHeaderList()
                    .get(index);
            fCgSourceFile.getHeaderList().add(header);
        }

        /* Sets the Component decorator. */
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

        /* Sets componentId, caption, routerPath and routerName. */
        buildMetaGet("componentId", argClassStructure.getName(), false);
        buildMetaGet("caption", argClassStructure.getSubject(), false);
        buildMetaGet("routerPath", argClassStructure.getRouterPath(), true);
        buildMetaGet("routerName", argClassStructure.getRouterName(), true);
        buildBooleanGet("expectConsistentAfterTransition", argClassStructure.getExpectConsistentAfterTransition(), false);

        /* Sets the property. */
        for (int indexField = 0; indexField < argClassStructure.getFieldList()
                .size(); indexField++) {
            final BlancoVueComponentFieldStructure fieldStructure = (BlancoVueComponentFieldStructure) argClassStructure
                    .getFieldList().get(indexField);

            // If a required field is not set, exception processing will be performed.
            if (fieldStructure.getName() == null) {
                throw new IllegalArgumentException(fMsg
                        .getMbvoji03(argClassStructure.getName()));
            }
            if (fieldStructure.getType() == null) {
                throw new IllegalArgumentException(fMsg.getMbvoji04(
                        argClassStructure.getName(), fieldStructure.getName()));
            }

            // Generates a field.
            buildProp(argClassStructure, fieldStructure, false, argQueryProps);

            // Getter/Setter is not generated.
            // Instead, generates a method that notifies the implementing class of property changes.
            /*
             * In mixins, it is more convenient to declare properties in duplicate, so it is deprecated.
             * 2020/07/01 by tueda
             */
//            buildOnChange(argClassStructure, fieldStructure);

        }

        // TODO: Consider how to generate the toString method.
//        if (argClassStructure.getGenerateToString()) {
//            // Generates the toString method.
//            buildMethodToString(argClassStructure);
//        }

        // TODO: Considers whether to externally flag whether to generate copyTo method.
//        BlancoBeanUtils.generateCopyToMethod(fCgSourceFile, fCgClass);

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
     * For the screen component, creates a RouteConfig for vue-router.
     *  @param argClassStructure
     * @param argDirectoryTarget
     * @param argQueryProps
     */
    private void generateRouteConfig(
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
        String suffix = "RouteConfig";
        String className = argClassStructure.getName() + suffix;
        String strImport = "import { RouteConfig } from \"vue-router\"";
        String strImport2 = "import { RoutePropsFunction } from \"vue-router/types/router\"";
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
        fCgClass = fCgFactory.createClass(className, fBundle.getXml2sourceFileRouteconfigClass(argClassStructure.getName()));
        fCgSourceFile.getClassList().add(fCgClass);
        fCgClass.setAccess("public");

        // Implements the RouteConfig.
        fCgClass.getImplementInterfaceList().add(
                fCgFactory.createType(suffix));

        // Imports the RouteConfig.
        fCgSourceFile.getHeaderList().add(strImport);

        // Creates the field as public. It doesn't create a Getter/Setter.
        this.buildRouteConfigField("path", "string", propPathValue);
        this.buildRouteConfigField("name", "string", propNameValue);
        this.buildRouteConfigField("component", "object", propComponentValue);
        this.buildRouteConfigField("meta", "any", propMetaValue);
        if (propPropsValue != null && propPropsValue.length() > 0) {
            fCgSourceFile.getHeaderList().add(strImport2);
            this.buildRouteConfigField("props", "RoutePropsFunction", propPropsValue);
        }

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
    private void buildRouteConfigField(
            String fieldName,
            String fieldType,
            String defaultValue
    ) {
        final BlancoCgField field = fCgFactory.createField(fieldName,
                fieldType, fBundle.getXml2sourceFileRouteconfigParameter(fieldName));
        fCgClass.getFieldList().add(field);

        field.setAccess("public");
        field.setNotnull(true);
        field.setDefault(defaultValue);
    }

    /**
     * Auto-generates the interface source code to force the implementation class to implement the property from the given property information.
     *
     * @param argClassStructure
     *            Class information.
     * @param argDirectoryTarget
     *            Output directory for TypeScript source code.
     * @throws IOException
     *             If an I/O exception occurs.
     */
    private void generateInterface(
            final BlancoVueComponentClassStructure argClassStructure,
            final File argDirectoryTarget,
            final Map<String, String> argQueryProps) throws IOException {
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
            System.out.println("/* tueda */ generateInterface argDirectoryTarget : " + argDirectoryTarget.getAbsolutePath());
        }

        // Gets an instance of the BlancoCgObjectFactory class.
        fCgFactory = BlancoCgObjectFactory.getInstance();

        fCgSourceFile = fCgFactory.createSourceFile(argClassStructure
                .getPackage(), null);
        fCgSourceFile.setEncoding(fEncoding);
        fCgSourceFile.setTabs(this.getTabs());

        // Creates an interface.
        fCgInterface = fCgFactory.createInterface(argClassStructure.getName() + "Interface", "");
        fCgSourceFile.getInterfaceList().add(fCgInterface);

        // In the case of an interface, ignores the access specification (always public).
        fCgInterface.setAccess("public");

        // Sets the JavaDoc for the class.
        fCgInterface.setDescription(argClassStructure.getDescription());
        for (String line : argClassStructure.getDescriptionList()) {
            fCgInterface.getLangDoc().getDescriptionList().add(line);
        }

        /* In TypeScript, sets the header instead of import. */
        for (int index = 0; index < argClassStructure.getInterfaceHeaderList()
                .size(); index++) {
            final String header = (String) argClassStructure.getInterfaceHeaderList()
                    .get(index);
            fCgSourceFile.getHeaderList().add(header);
        }

        for (int indexField = 0; indexField < argClassStructure.getFieldList()
                .size(); indexField++) {
            // Processes each field.
            final BlancoVueComponentFieldStructure fieldStructure = (BlancoVueComponentFieldStructure) argClassStructure
                    .getFieldList().get(indexField);

            // If a required field is not set, exception processing will be performed.
            if (fieldStructure.getName() == null) {
                throw new IllegalArgumentException(fMsg
                        .getMbvoji03(argClassStructure.getName()));
            }
            if (fieldStructure.getType() == null) {
                throw new IllegalArgumentException(fMsg.getMbvoji04(
                        argClassStructure.getName(), fieldStructure.getName()));
            }

            // Generates a field.
            buildProp(argClassStructure, fieldStructure, true, argQueryProps);

            // The interface does not have a Getter/Setter.
        }

        // TODO: Does the interface need a toString method?
//        if (argInterfaceStructure.getGenerateToString()) {
//            // Generates the toString method.
//            buildMethodToString(argInterfaceStructure);
//        }

        // TODO: Considers whether to externally flag whether to generate copyTo method.
//        BlancoBeanUtils.generateCopyToMethod(fCgSourceFile, fCgClass);

        // Auto-generates the actual source code based on the collected information.
        BlancoCgTransformerFactory.getTsSourceTransformer().transform(
                fCgSourceFile, fileBlancoMain);
    }

    /**
     * Generates the properties of the component.
     * @param argClassStructure
     *            Class information.
     * @param argFieldStructure
     * @param isInterface
     * @param argQueryProps
     */
    private void buildProp(
            final BlancoVueComponentClassStructure argClassStructure,
            final BlancoVueComponentFieldStructure argFieldStructure,
            boolean isInterface,
            final Map<String, String> argQueryProps) {

//        System.out.println("%%% " + argFieldStructure.toString());

        /*
         * In interface, prefixes the property name with "p".
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
         * Supports Generic. Since blancoCg assumes that <> is attached and trims the package part, it will not be set correctly if it is not set here.
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
         * Since Getter / Setter is not created, it will make them all public.
         */
        field.setAccess("public");
        /*
         * For the time being, final will not be supported.
         */
        field.setFinal(false);

        /*
         * Supports const (val in Kotlin).
         */
        field.setConst(argFieldStructure.getValue());

        // Supports nullable.
        if (!argFieldStructure.getNullable()) {
            field.setNotnull(true);
        }

        // Stores the query string.
        String queryParam = argFieldStructure.getQueryParam();
        if (argQueryProps != null && queryParam != null && queryParam.length() > 0) {
            argQueryProps.put(fieldName, queryParam);
        }

        // Sets the JavaDoc for the field.
        field.setDescription(argFieldStructure.getDescription());
        for (String line : argFieldStructure.getDescriptionList()) {
            field.getLangDoc().getDescriptionList().add(line);
        }
        field.getLangDoc().getDescriptionList().add(
                fBundle.getXml2javaclassFieldName(argFieldStructure.getName()));

        /*
         * In TypeScript, the default value of a property is mandatory in principle.
         * However, it cannot be set for interface.
         */
        if (isInterface != true) {
            final String type = field.getType().getName();
            String defaultRawValue = argFieldStructure.getDefault();
            boolean isNullable = argFieldStructure.getNullable();
            if (!isNullable && (defaultRawValue == null || defaultRawValue.length() <= 0)) {
                System.err.println("/* tueda */ The field does not have a default value. If it is not an interface, be sure to set the default value or set it to Nullable.");
                throw new IllegalArgumentException(fMsg
                        .getMbvoji08(argClassStructure.getName(), argFieldStructure.getName()));
            }

            // Sets the default value for the field.
            field.getLangDoc().getDescriptionList().add(
                    BlancoCgSourceUtil.escapeStringAsLangDoc(BlancoCgSupportedLang.KOTLIN, fBundle.getXml2javaclassFieldDefault(argFieldStructure
                            .getDefault())));
            if (argClassStructure.getAdjustDefaultValue() == false) {
                // If the variant of the default value is off, the value on the definition sheet is adopted as it is.
                field.setDefault(argFieldStructure.getDefault());
            } else {

                if (type.equals("string")) {
                    // Adds double-quotes.
                    field.setDefault("\""
                            + BlancoJavaSourceUtil
                                    .escapeStringAsJavaSource(argFieldStructure
                                            .getDefault()) + "\"");
                } else {
                    /*
                     * For other types, for the time being, the given value is written as is.
                     */
                    field.setDefault(argFieldStructure.getDefault());
                }
            }

            /* Sets the Prop decorator. */
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
     * Generates a method to notify the implementing class of property changes.
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

        // Supports Notnull.
        if (!argFieldStructure.getNullable()) {
            method.setNotnull(true);
        }

        // Sets the JavaDoc for the method.
        // TODO: It would better to make it resource bundle and support internationalization.
        List<String> lines = new ArrayList<>();
        lines.add("Watch " + argFieldStructure
                .getName() + " and notify changes to implements.");
        lines.add("immedeate: act on initialized.");
        lines.add("deep: follow inside of objects.");
        method.getLangDoc().getDescriptionList().addAll(lines);

        // Sets the decorations.
        List<String> decorates = new ArrayList<>();
        decorates.add("Watch(\"" +
                argFieldStructure.getName() +
                "\", {immediate: true, deep: true})"
                );
        method.getAnnotationList().addAll(decorates);

        // Implements parameters.
        method.getParameterList().add(this.createParameter(argClassStructure, argFieldStructure, "new"));
        method.getParameterList().add(this.createParameter(argClassStructure, argFieldStructure, "old"));

        // Implements methods.
        method.getLineList().add(
                "this.p"
                        + getFieldNameAdjustered(argClassStructure, argFieldStructure)
                        + " = "
                        + "new"
                        + getFieldNameAdjustered(argClassStructure,
                        argFieldStructure) + ";");
    }

    /**
     * Generates parameters.
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
        // Supports generic if available.
        String generic = argFieldStructure.getGeneric();
        if (generic != null && generic.length() > 0) {
            param.getType().setGenerics(generic);
        }
        return param;
    }

    private void buildMetaGet(
            String name,
            String meta,
            boolean isStatic) {
        if (meta == null) {
            return;
        }

        final BlancoCgMethod method = fCgFactory.createMethod(name,
                fBundle.getXml2javaclassGetJavadoc01(name));
        fCgClass.getMethodList().add(method);

        method.setNotnull(true);
        method.setAccess("get");
        method.setStatic(isStatic);

        BlancoCgReturn cgReturn = fCgFactory.createReturn("string",
                fBundle.getXml2javaclassGetReturnJavadoc(name));
        method.setReturn(cgReturn);

        // Implements methods.
        method.getLineList().add("return \"" + meta + "\";");
    }

    private void buildBooleanGet(
            String name,
            boolean meta,
            boolean isStatic) {

        final BlancoCgMethod method = fCgFactory.createMethod(name,
                fBundle.getXml2javaclassGetJavadoc01(name));
        fCgClass.getMethodList().add(method);

        method.setNotnull(true);
        method.setAccess("get");
        method.setStatic(isStatic);

        BlancoCgReturn cgReturn = fCgFactory.createReturn("boolean",
                fBundle.getXml2javaclassGetReturnJavadoc(name));
        method.setReturn(cgReturn);

        // Implements methods.
        String strMeta = meta ? "true" : "false";
        method.getLineList().add("return " + strMeta + ";");
    }

    /**
     * Gets the adjusted field name.
     *
     * @param argFieldStructure
     *            Field information.
     * @return Adjusted field name.
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
