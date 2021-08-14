/*
 * blanco Framework
 * Copyright (C) 2004-2008 IGA Tosiki
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
package blanco.vuecomponent;

import blanco.cg.BlancoCgSupportedLang;
import blanco.commons.util.BlancoNameUtil;
import blanco.commons.util.BlancoStringUtil;
import blanco.valueobjectts.valueobject.BlancoValueObjectTsClassStructure;
import blanco.vuecomponent.message.BlancoVueComponentMessage;
import blanco.vuecomponent.resourcebundle.BlancoVueComponentResourceBundle;
import blanco.vuecomponent.valueobject.BlancoVueComponentClassStructure;
import blanco.vuecomponent.valueobject.BlancoVueComponentFieldStructure;
import blanco.xml.bind.BlancoXmlBindingUtil;
import blanco.xml.bind.BlancoXmlUnmarshaller;
import blanco.xml.bind.valueobject.BlancoXmlAttribute;
import blanco.xml.bind.valueobject.BlancoXmlDocument;
import blanco.xml.bind.valueobject.BlancoXmlElement;

import java.io.File;
import java.util.*;

/**
 * A class that parses (reads and writes) the intermediate XML file format of blancoValueObject.
 *
 * @author IGA Tosiki
 */
public class BlancoVueComponentXmlParser {
    /**
     * A message.
     */
    private final BlancoVueComponentMessage fMsg = new BlancoVueComponentMessage();

    private boolean fVerbose = false;

    public void setVerbose(boolean argVerbose) {
        this.fVerbose = argVerbose;
    }

    public boolean isVerbose() {
        return fVerbose;
    }

    /**
     * A resource bundle object for blancoValueObject.
     */
    private final static BlancoVueComponentResourceBundle fBundle = new BlancoVueComponentResourceBundle();

    public static Map<String, Integer> mapCommons = new HashMap<String, Integer>() {
        {
            put(fBundle.getMeta2xmlElementCommon(), BlancoCgSupportedLang.PHP);
        } // Supports PHP type sheets only.

        {
            put(fBundle.getMeta2xmlElementCommonCs(), BlancoCgSupportedLang.CS);
        }

        {
            put(fBundle.getMeta2xmlElementCommonJs(), BlancoCgSupportedLang.JS);
        }

        {
            put(fBundle.getMeta2xmlElementCommonVb(), BlancoCgSupportedLang.VB);
        }

        {
            put(fBundle.getMeta2xmlElementCommonPhp(), BlancoCgSupportedLang.PHP);
        }

        {
            put(fBundle.getMeta2xmlElementCommonRuby(), BlancoCgSupportedLang.RUBY);
        }

        {
            put(fBundle.getMeta2xmlElementCommonPython(), BlancoCgSupportedLang.PYTHON);
        }

        {
            put(fBundle.getMeta2xmlElementCommonKt(), BlancoCgSupportedLang.KOTLIN);
        }

        {
            put(fBundle.getMeta2xmlElementCommonTs(), BlancoCgSupportedLang.TS);
        }
    };

    /**
     * Parses an XML document in an intermediate XML file to get an array of information.
     *
     * @param argMetaXmlSourceFile An intermediate XML file.
     * @return An array of information obtained as a result of parsing.
     */
    public BlancoVueComponentClassStructure[] parse(
            final File argMetaXmlSourceFile) {
        final BlancoXmlDocument documentMeta = new BlancoXmlUnmarshaller()
                .unmarshal(argMetaXmlSourceFile);
        if (documentMeta == null) {
            return null;
        }

        return parse(documentMeta);

    }

    /**
     * Parses an XML document in an intermediate XML file to get an array of value object information.
     *
     * @param argXmlDocument XML document of an intermediate XML file.
     * @return An array of value object information obtained as a result of parsing.
     */
    public BlancoVueComponentClassStructure[] parse(
            final BlancoXmlDocument argXmlDocument) {
        final List<BlancoVueComponentClassStructure> listStructure = new ArrayList<BlancoVueComponentClassStructure>();

        // Gets the root element.
        final BlancoXmlElement elementRoot = BlancoXmlBindingUtil
                .getDocumentElement(argXmlDocument);
        if (elementRoot == null) {
            // The process is aborted if there is no root element.
            return null;
        }

        // Gets a list of sheets (Excel sheets).
        final List<BlancoXmlElement> listSheet = BlancoXmlBindingUtil
                .getElementsByTagName(elementRoot, "sheet");

        final int sizeListSheet = listSheet.size();
        for (int index = 0; index < sizeListSheet; index++) {
            final BlancoXmlElement elementSheet = listSheet.get(index);

            /*
             * Supports sheets written for languages other than Java.
             */
            List<BlancoXmlElement> listCommon = null;
            int sheetLang = BlancoCgSupportedLang.JAVA;
            for (String common : mapCommons.keySet()) {
                listCommon = BlancoXmlBindingUtil
                        .getElementsByTagName(elementSheet,
                                common);
                if (listCommon.size() != 0) {
                    BlancoXmlAttribute attr = new BlancoXmlAttribute();
                    attr.setType("CDATA");
                    attr.setQName("style");
                    attr.setLocalName("style");

                    sheetLang = mapCommons.get(common);
                    attr.setValue(new BlancoCgSupportedLang().convertToString(sheetLang));

                    elementSheet.getAtts().add(attr);

                    /* tueda DEBUG */
                    if (this.isVerbose()) {
                        System.out.println("/* tueda */ style = " + BlancoXmlBindingUtil.getAttribute(elementSheet, "style"));
                    }

                    break;
                }
            }

            if (listCommon == null || listCommon.size() == 0) {
                // Skips if there is no common.
                continue;
            }

            // Processes only the first item.
            final BlancoXmlElement elementCommon = listCommon.get(0);
            final String name = BlancoXmlBindingUtil.getTextContent(
                    elementCommon, "name");
            if (BlancoStringUtil.null2Blank(name).trim().length() == 0) {
                continue;
            }

            if (sheetLang != BlancoCgSupportedLang.PHP) {
                System.out.println("### ERROR Just PHP SheetLang is permitted.");
                throw new IllegalArgumentException("### ERROR: Invalid SheetLang : " + sheetLang);
            }

            BlancoVueComponentClassStructure objClassStructure = parseElementSheetPhp(elementSheet);
            ;

            if (objClassStructure != null) {
                // Saves the obtained information.
                listStructure.add(objClassStructure);
            }
        }

        final BlancoVueComponentClassStructure[] result = new BlancoVueComponentClassStructure[listStructure
                .size()];
        listStructure.toArray(result);
        return result;
    }

    /**
     * Parses the "sheet" XML element (PHP format) in the intermediate XML file to get the value object information.
     *
     * @param argElementSheet "sheet" XML element in the intermediate XML file.
     * @return Value object information obtained as a result of parsing. Null is returned if "name" is not found.
     */
    public BlancoVueComponentClassStructure parseElementSheetPhp(
            final BlancoXmlElement argElementSheet
    ) {

        final BlancoVueComponentClassStructure objClassStructure = new BlancoVueComponentClassStructure();
        /*
         * Used to generate a component. Stores it  in objClassStructure#headerList.
         */
        final Map<String, List<String>> componentHeaderList = new HashMap<>();
        /*
         * Used to import a component.
         * Since the component is default exported, it should not be wrapped in "{ }" when import.
         */
        final Map<String, List<String>> defaultExportedHeaderList = new HashMap<>();        /*
         * Used to generate an interface. Stores it in objClassStructure#importList.
         */
        final Map<String, List<String>> interfaceHeaderList = new HashMap<>();

        // Gets the common information.
        final BlancoXmlElement elementCommon = BlancoXmlBindingUtil
                .getElement(argElementSheet, fBundle
                        .getMeta2xmlElementCommon());
        if (elementCommon == null) {
            // Skips the processing of this sheet if there is no common.
            return null;
        }

        final String name = BlancoXmlBindingUtil.getTextContent(
                elementCommon, "name");
        if (BlancoStringUtil.null2Blank(name).trim().length() == 0) {
            // Skips if name is empty.
            return null;
        }

        if (this.isVerbose()) {
            System.out.println("BlancoVueComponent#parseElementPhp name = " + name);
        }

        // Vue component definition common.
        this.parseVueComponentCommon(elementCommon, objClassStructure);

        // Vue component definition inheritance.
        this.parseVueComponentExtends(componentHeaderList, defaultExportedHeaderList, objClassStructure);

        // Vue component definition using component.
        final List<BlancoXmlElement> componentList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet,
                        fBundle.getMeta2xmlElementComponents());
        if (componentList != null && componentList.size() != 0) {
            final BlancoXmlElement elementComponentRoot = componentList.get(0);
            this.parseVueComponentComponents(elementComponentRoot, componentHeaderList, objClassStructure);
        }

        // Vue component definition property.
        final List<BlancoXmlElement> listList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet, fBundle.getMeta2xmlElementList());
        if (listList != null && listList.size() != 0) {
            final BlancoXmlElement elementListRoot = listList.get(0);
            this.parseVueComponentProperties(elementListRoot, componentHeaderList, interfaceHeaderList, objClassStructure);
        }

        // Gets the header information.
        List<BlancoXmlElement> headerElementList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet,
                        fBundle.getMeta2xmlElementHeader());
        List<String> headerList = this.parseHeaderList(headerElementList, componentHeaderList, defaultExportedHeaderList);
        if (headerList != null && headerList.size() > 0) {
            objClassStructure.getComponentHeaderList().addAll(headerList);
        }
        headerElementList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet,
                        fBundle.getMeta2xmlElementHeaderInterface());
        headerList = this.parseHeaderList(headerElementList, interfaceHeaderList, null);
        if (headerList != null && headerList.size() > 0) {
            objClassStructure.getInterfaceHeaderList().addAll(headerList);
        }

        return objClassStructure;
    }

    public static Map<String, String> createClassListFromSheets(final File[] argFileMeta) {
        Map<String, String> classList = new HashMap<String, String>();

        for (int index = 0; index < argFileMeta.length; index++) {
            File metaXmlSourceFile = argFileMeta[index];

            if (metaXmlSourceFile.getName().endsWith(".xml") == false) {
                continue;
            }

            final BlancoXmlDocument documentMeta = new BlancoXmlUnmarshaller()
                    .unmarshal(metaXmlSourceFile);
            if (documentMeta == null) {
                continue;
            }

            // Gets the root element.
            final BlancoXmlElement elementRoot = BlancoXmlBindingUtil
                    .getDocumentElement(documentMeta);
            if (elementRoot == null) {
                // The process is aborted if there is no root element.
                continue;
            }

            // Gets a list of sheets (Excel sheets).
            final List<BlancoXmlElement> listSheet = BlancoXmlBindingUtil
                    .getElementsByTagName(elementRoot, "sheet");


            for (BlancoXmlElement elementSheet : listSheet) {
                /*
                 * Supports sheets written for languages other than Java.
                 */
                List<BlancoXmlElement> listCommon = null;
                for (String common : mapCommons.keySet()) {
                    listCommon = BlancoXmlBindingUtil
                            .getElementsByTagName(elementSheet,
                                    common);
                    if (listCommon.size() != 0) {
                        BlancoXmlElement elementCommon = listCommon.get(0);
                        classList.put(
                                BlancoXmlBindingUtil.getTextContent(elementCommon, "name"),
                                BlancoXmlBindingUtil.getTextContent(elementCommon, "package")
                        );

//                        System.out.println("/* tueda */ createClassList = " +
//                                BlancoXmlBindingUtil.getTextContent(elementCommon, "name") + " : " +
//                                BlancoXmlBindingUtil.getTextContent(elementCommon, "package"));
                        break;
                    }
                }
            }
        }

        return classList;
    }

    /**
     * Parses the intermediate XML file to get "Vue component definition common".
     *
     * @param argElementCommon
     * @param argObjClassStructure
     */
    private void parseVueComponentCommon(
            final BlancoXmlElement argElementCommon,
            final BlancoVueComponentClassStructure argObjClassStructure
    ) {
        argObjClassStructure.setSubject(BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "subject"));
        argObjClassStructure.setName(BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "name"));
        argObjClassStructure.setPackage(BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "package"));
        argObjClassStructure.setClassAlias(BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "classAlias"));
        argObjClassStructure.setRouterName(BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "router-name"));
        argObjClassStructure.setRouterPath(BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "router-path"));
        argObjClassStructure.setBasedir(BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "basedir"));
        argObjClassStructure.setImpledir(BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "impledir"));

        argObjClassStructure.setDescription(BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "description"));
        if (BlancoStringUtil.null2Blank(argObjClassStructure.getDescription())
                .length() > 0) {
            final String[] lines = BlancoNameUtil.splitString(argObjClassStructure
                    .getDescription(), '\n');
            for (int index = 0; index < lines.length; index++) {
                if (index == 0) {
                    argObjClassStructure.setDescription(lines[index]);
                } else {
                    // For a multi-line description, it will be split and stored.
                    // From the second line, assumes that character reference encoding has been properly implemented. 
                    argObjClassStructure.getDescriptionList().add(lines[index]);
                }
            }
        }

        /*
         * Component type: screen or part
         */
        argObjClassStructure.setComponentKind(BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "componentKind"));

        argObjClassStructure.setAuthRequired("true".equals(BlancoXmlBindingUtil
                .getTextContent(argElementCommon, "authRequired")));
        argObjClassStructure.setForceReload("true".equals(BlancoXmlBindingUtil
                .getTextContent(argElementCommon, "forceReload")));
        argObjClassStructure.setUseTemplate("true".equals(BlancoXmlBindingUtil
                .getTextContent(argElementCommon, "useTemplate")));
        argObjClassStructure.setUseScript("true".equals(BlancoXmlBindingUtil
                .getTextContent(argElementCommon, "useScript")));
        argObjClassStructure.setUseStyle("true".equals(BlancoXmlBindingUtil
                .getTextContent(argElementCommon, "useStyle")));

        argObjClassStructure.setAdjustFieldName("true".equals(BlancoXmlBindingUtil
                .getTextContent(argElementCommon, "adjustFieldName")));
        argObjClassStructure.setAdjustDefaultValue("true"
                .equals(BlancoXmlBindingUtil.getTextContent(argElementCommon,
                        "adjustDefaultValue")));
        argObjClassStructure.setCreateImportList("true"
                .equals(BlancoXmlBindingUtil.getTextContent(argElementCommon,
                        "createImportList")));
        argObjClassStructure.setExpectConsistentAfterTransition("true"
                .equals(BlancoXmlBindingUtil.getTextContent(argElementCommon,
                        "expectConsistentAfterTransition")));
        argObjClassStructure
                .setFieldList(new ArrayList<blanco.vuecomponent.valueobject.BlancoVueComponentFieldStructure>());
    }

    /**
     * In VueComponent, the "Mixins (implementation components)" function is automatically extended.
     *
     * @param argImportHeaderList
     * @param argObjClassStructure
     */
    private void parseVueComponentExtends(
            final Map<String, List<String>> argImportHeaderList,
            final Map<String, List<String>> argDefaultExportedHeaderList,
            final BlancoVueComponentClassStructure argObjClassStructure
    ) {

        final String impleClassName = argObjClassStructure.getName() + BlancoVueComponentConstants.IMPLECLASS_SUFFIX;
        String mixins = "Mixins(" + impleClassName + ")";
        argObjClassStructure.setExtends(mixins);

        /*
         * import { Component, Mixins, Prop, Vue, Watch } from "vue-property-decorator";
         */
        BlancoVueComponentUtil.addImportHeaderList("Component", "vue-property-decorator", argImportHeaderList);
        BlancoVueComponentUtil.addImportHeaderList("Mixins", "vue-property-decorator", argImportHeaderList);
        BlancoVueComponentUtil.addImportHeaderList("Prop", "vue-property-decorator", argImportHeaderList);
        BlancoVueComponentUtil.addImportHeaderList("Watch", "vue-property-decorator", argImportHeaderList);

        /*
         * import HogeImple from "HogeImple";
         * Component is default exported, so it should not be wrapped in "{ }".
         */
        BlancoVueComponentUtil.makeImportHeaderList(
                argObjClassStructure.getPackage(),
                impleClassName,
                argDefaultExportedHeaderList,
                argObjClassStructure.getImpledir(),
                ""
        );
    }

    /**
     * Creates a list of components to be used.
     * Since the import statement is not generated, it should be described in the definition document.
     *
     * @param argElementComponent
     * @param argImportHeaderList
     * @param argObjClassStructure
     */
    private void parseVueComponentComponents(
            final BlancoXmlElement argElementComponent,
            final Map<String, List<String>> argImportHeaderList,
            final BlancoVueComponentClassStructure argObjClassStructure
    ) {
        /* A list of components to be used. */
        final List<BlancoXmlElement> listComponentsChildNodes = BlancoXmlBindingUtil
                .getElementsByTagName(argElementComponent, "components");
        for (int index = 0;
             listComponentsChildNodes != null &&
                     index < listComponentsChildNodes.size();
             index++) {
            final BlancoXmlElement elementList = listComponentsChildNodes
                    .get(index);

            final String componentName = BlancoXmlBindingUtil
                    .getTextContent(elementList, "name");
            if (componentName == null || componentName.trim().length() == 0) {
                continue;
            }

            argObjClassStructure.getComponentList().add(
                    BlancoXmlBindingUtil
                            .getTextContent(elementList, "name"));
        }
    }

    private void parseVueComponentProperties(
            final BlancoXmlElement argElementProperties,
            final Map<String, List<String>> argComponentHeaderList,
            final Map<String, List<String>> argInterfaceHeaderList,
            final BlancoVueComponentClassStructure argObjClassStructure
    ) {
        final List<BlancoXmlElement> listChildNodes = BlancoXmlBindingUtil
                .getElementsByTagName(argElementProperties, "field");
        for (int index = 0; index < listChildNodes.size(); index++) {
            final BlancoXmlElement elementList = listChildNodes.get(index);
            final BlancoVueComponentFieldStructure fieldStructure = new BlancoVueComponentFieldStructure();

            fieldStructure.setNo(BlancoXmlBindingUtil.getTextContent(
                    elementList, "no"));
            fieldStructure.setName(BlancoXmlBindingUtil.getTextContent(
                    elementList, "name"));
            if (fieldStructure.getName() == null
                    || fieldStructure.getName().trim().length() == 0) {
                continue;
            }

            /*
             * Gets the type. Changes the type name to TypeScript style.
             */
            String phpType = BlancoXmlBindingUtil.getTextContent(elementList, "type");
            String targetType = phpType;
            if ("boolean".equalsIgnoreCase(phpType)) {
                targetType = "boolean";
            } else if ("integer".equalsIgnoreCase(phpType)) {
                targetType = "number";
            } else if ("double".equalsIgnoreCase(phpType)) {
                targetType = "number";
            } else if ("float".equalsIgnoreCase(phpType)) {
                targetType = "number";
            } else if ("string".equalsIgnoreCase(phpType)) {
                targetType = "string";
            } else if ("array".equalsIgnoreCase(phpType)) {
                    targetType = "Array";
            } else if ("object".equalsIgnoreCase(phpType)) {
                targetType = "any";
            } else {
                /* Searches for a package name with this name. */
                BlancoValueObjectTsClassStructure voStructure = BlancoVueComponentUtil.objects.get(phpType);

                String packageName = null;
                if (voStructure != null) {
                    packageName = voStructure.getPackage();
                    if (packageName == null) {
                        // Tries to isolate the package name.
                        String simpleName = BlancoVueComponentUtil.getSimpleClassName(phpType);
                        if (simpleName != null && !simpleName.equals(phpType)) {
                            packageName = BlancoVueComponentUtil.getPackageName(phpType);
                            phpType = simpleName;
                        }
                    }
                    if (packageName != null) {
                        targetType = packageName + "." + phpType;
                    }

                    /* Others are written as is. */

                    /*
                     * Creates import information for TypeScript.
                     * Note that the package may be the same as the component, but the basedir may be different.
                     */
                    if (argObjClassStructure.getCreateImportList()) {
                        BlancoVueComponentUtil.makeImportHeaderList(packageName, phpType, argComponentHeaderList, voStructure.getBasedir(), "");
                        BlancoVueComponentUtil.makeImportHeaderList(packageName, phpType, argInterfaceHeaderList, voStructure.getBasedir(), "");
                    }
                }
            }

            fieldStructure.setType(targetType);

            /* Supports Generic. */
            String phpGeneric = BlancoXmlBindingUtil.getTextContent(elementList, "generic");
            if (BlancoStringUtil.null2Blank(phpGeneric).length() != 0) {
                String targetGeneric = phpGeneric;
                if ("boolean".equalsIgnoreCase(phpGeneric)) {
                    targetGeneric = "boolean";
                } else if ("integer".equalsIgnoreCase(phpGeneric)) {
                    targetGeneric = "number";
                } else if ("double".equalsIgnoreCase(phpGeneric)) {
                    targetGeneric = "number";
                } else if ("float".equalsIgnoreCase(phpGeneric)) {
                    targetGeneric = "number";
                } else if ("string".equalsIgnoreCase(phpGeneric)) {
                    targetGeneric = "string";
                } else if ("array".equalsIgnoreCase(phpGeneric)) {
                    throw new IllegalArgumentException(fMsg.getMbvoji06(
                            argObjClassStructure.getName(),
                            fieldStructure.getName(),
                            phpGeneric,
                            phpGeneric
                    ));
                } else if ("object".equalsIgnoreCase(phpGeneric)) {
                    targetGeneric = "any";
                } else {
                    /* Searches for a package with this name. */
                    BlancoValueObjectTsClassStructure voStructure = BlancoVueComponentUtil.objects.get(phpGeneric);

                    String packageName = null;
                    if (voStructure != null) {
                        packageName = voStructure.getPackage();
                        if (packageName == null) {
                            // Tries to isolate package name.
                            String simpleName = BlancoVueComponentUtil.getSimpleClassName(phpGeneric);
                            if (simpleName != null && !simpleName.equals(phpGeneric)) {
                                packageName = BlancoVueComponentUtil.getPackageName(phpGeneric);
                                phpGeneric = simpleName;
                            }
                        }
                        if (packageName != null) {
                            targetGeneric = packageName + "." + phpGeneric;
                        }

                        /* Others are written as is. */

                        /*
                         * Creates import information for TypeScript.
                         * Note that the package may be the same as the component, but the basedir may be different.
                         */
                        if (argObjClassStructure.getCreateImportList()) {
                            BlancoVueComponentUtil.makeImportHeaderList(packageName, phpGeneric, argComponentHeaderList, voStructure.getBasedir(), "");
                            BlancoVueComponentUtil.makeImportHeaderList(packageName, phpGeneric, argInterfaceHeaderList, voStructure.getBasedir(), "");
                        }
                    }
                }
                fieldStructure.setGeneric(targetGeneric);
            }

            // Supports Nullable.
            fieldStructure.setNullable("true".equals(BlancoXmlBindingUtil
                    .getTextContent(elementList, "nullable")));

            // Supports query string.
            fieldStructure.setQueryParam(BlancoXmlBindingUtil.getTextContent(
                    elementList, "queryParam"));

            // Supports description.
            fieldStructure.setDescription(BlancoXmlBindingUtil
                    .getTextContent(elementList, "description"));
            final String[] lines = BlancoNameUtil.splitString(
                    fieldStructure.getDescription(), '\n');
            for (int indexLine = 0; indexLine < lines.length; indexLine++) {
                if (indexLine == 0) {
                    fieldStructure.setDescription(lines[indexLine]);
                } else {
                    // For a multi-line description, it will be split and stored.
                    // From the second line, assumes that character reference encoding has been properly implemented. 
                    fieldStructure.getDescriptionList().add(
                            lines[indexLine]);
                }
            }

            fieldStructure.setDefault(BlancoXmlBindingUtil.getTextContent(
                    elementList, "default"));

            argObjClassStructure.getFieldList().add(fieldStructure);
        }
    }

    /**
     *
     *
     * @param argHeaderElementList
     * @param argImportHeaderList
     * @return
     */
    private List<String> parseHeaderList(
            final List<BlancoXmlElement> argHeaderElementList,
            final Map<String, List<String>> argImportHeaderList,
            final Map<String, List<String>> argDefaultExportedHeaderList
    ) {
        if (this.isVerbose()) {
            System.out.println("BlancoVueComponent#parseHeaderList: Start parseHeaderList.");
        }

        List<String> headerList = new ArrayList<>();

        /*
         * Creates a list of header.
         * First, outputs what is written in the definition document as is.
         */
        if (argHeaderElementList != null && argHeaderElementList.size() > 0) {
            final BlancoXmlElement elementHeaderRoot = argHeaderElementList.get(0);
            final List<BlancoXmlElement> listHeaderChildNodes = BlancoXmlBindingUtil
                    .getElementsByTagName(elementHeaderRoot, "header");
            for (int index = 0; index < listHeaderChildNodes.size(); index++) {
                final BlancoXmlElement elementList = listHeaderChildNodes
                        .get(index);

                final String headerName = BlancoXmlBindingUtil
                        .getTextContent(elementList, "name");
                if (this.isVerbose()) {
                    System.out.println("BlancoVueComponent#parseHeaderList header = " + headerName);
                }
                if (headerName == null || headerName.trim().length() == 0) {
                    continue;
                }
                headerList.add(
                        BlancoXmlBindingUtil
                                .getTextContent(elementList, "name"));
            }
        }

        /*
         * Next, outputs the auto-generated one.
         * The current method requires the following assumptions.
         *  * One class definition per file
         *  * Represents directories with Java/Kotlin style package notation in the definition sheet
         * TODO: Should it be possible to define the directory where the files are located on the definition sheet?
         */
        this.parseGeneratedHeaderList(headerList, argImportHeaderList, false);

        /*
         * Next, outputs the default exported one.
         * The current method requires the following assumptions.
         *  * One class definition per file
         *  * Represents directories with Java/Kotlin style package notation in the definition sheet
         * TODO: Should it be possible to define the directory where the files are located on the definition sheet?
         */
        if (argDefaultExportedHeaderList != null && argDefaultExportedHeaderList.size() > 0) {
            this.parseGeneratedHeaderList(headerList, argDefaultExportedHeaderList, true);
        }

        return headerList;
    }

    private void parseGeneratedHeaderList(
            List<String> argHeaderList,
            final Map<String, List<String>> argImportHeaderList,
            boolean isDefaultExported
    ) {
        if (argImportHeaderList != null && argImportHeaderList.size() > 0) {
            Set<String> fromList = argImportHeaderList.keySet();
            for (String strFrom : fromList) {
                StringBuffer sb = new StringBuffer();
                if (isDefaultExported) {
                    sb.append("import ");
                } else {
                    sb.append("import { ");
                }
                List<String> classNameList = argImportHeaderList.get(strFrom);
                int count = 0;
                for (String className : classNameList) {
                    if (count > 0) {
                        sb.append(", ");
                    }
                    sb.append(className);
                    count++;
                }
                if (count > 0) {
                    if (isDefaultExported) {
                        sb.append(" from \"" + strFrom + "\"");
                    } else {
                        sb.append(" } from \"" + strFrom + "\"");
                    }
                    if (this.isVerbose()) {
                        System.out.println("BlancoRestGeneratorTsXmlParser#parseGeneratedHeaderList import = " + sb.toString());
                    }
                    argHeaderList.add(sb.toString());
                }
            }
        }
    }
}
