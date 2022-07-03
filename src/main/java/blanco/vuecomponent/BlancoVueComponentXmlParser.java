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
import blanco.commons.util.BlancoNameAdjuster;
import blanco.commons.util.BlancoNameUtil;
import blanco.commons.util.BlancoStringUtil;
import blanco.vuecomponent.message.BlancoVueComponentMessage;
import blanco.vuecomponent.resourcebundle.BlancoVueComponentResourceBundle;
import blanco.vuecomponent.valueobject.BlancoVueComponentApiStructure;
import blanco.vuecomponent.valueobject.BlancoVueComponentClassStructure;
import blanco.vuecomponent.valueobject.BlancoVueComponentEmitsStructure;
import blanco.vuecomponent.valueobject.BlancoVueComponentPropsStructure;
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
         * import named objects into defineComponent file with file names as key.
         */
        final Map<String, List<String>> namedExportecHeaderList = new HashMap<>();

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

        /*
         * Parse defineComponent file informations.
         */

        // Vue component definition common.
        this.parseCommon(elementCommon, objClassStructure);

        // Vue component definition using component.
        final List<BlancoXmlElement> componentList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet,
                        fBundle.getMeta2xmlElementListComponents());
        if (componentList != null && componentList.size() != 0) {
            final BlancoXmlElement elementComponentRoot = componentList.get(0);
            this.parseVueComponentComponents(elementComponentRoot, objClassStructure);
        }

        /*
         * Parse API list file informations
         */
        final List<BlancoXmlElement> apiElementList = BlancoXmlBindingUtil.getElementsByTagName(argElementSheet, fBundle.getMeta2xmlElementListApi());
        if (apiElementList != null && apiElementList.size() != 0) {
            final BlancoXmlElement elementApiRoot = apiElementList.get(0);
            this.parseApiList(elementApiRoot, objClassStructure);
        }

        /*
         * Parse header information for API
         */
        List<BlancoXmlElement> apiHeaderElementList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet,
                        fBundle.getMeta2xmlElementHeaderApi());
        List<String> apiHeaderList = this.parseHeaderList(apiHeaderElementList, null, null);
        if (apiHeaderList != null && apiHeaderList.size() > 0) {
            objClassStructure.getApiHeaderList().addAll(apiHeaderList);
        }

        /*
         * Parse Props file informations
         */
        namedExportecHeaderList.clear();
        final List<BlancoXmlElement> propsList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet, fBundle.getMeta2xmlElementListProps());
        if (propsList != null && propsList.size() != 0) {
            final BlancoXmlElement elementListRoot = propsList.get(0);
            /* share function with emits */
            this.parseVueComponentProps(elementListRoot, namedExportecHeaderList, objClassStructure);
        }
        /*
         * subject and alias properties are automatically generated.
         */
        objClassStructure.getPropsList().add(0, this.generatePropsStructure(
                "alias",
                "string",
                true,
                "\"" + objClassStructure.getAlias() + "\"",
                fBundle.getXml2sourceFilePropsAliasDescription()
        ));
        objClassStructure.getPropsList().add(0, this.generatePropsStructure(
                "subject",
                "string",
                true,
                "\"" + objClassStructure.getSubject() + "\"",
                fBundle.getXml2sourceFilePropsSubjectDescription()
        ));

        /*
         * Import LooseRequired and ComponentPropsOptions
         */
        BlancoVueComponentUtil.addImportHeaderList("LooseRequired", "@vue/shared", namedExportecHeaderList);
        BlancoVueComponentUtil.addImportHeaderList("ComponentPropsOptions", "vue", namedExportecHeaderList);
        // Get headers user defined.
        List<BlancoXmlElement> propsHeaderElementList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet,
                        fBundle.getMeta2xmlElementHeaderProps());
        List<String> propsHeaderList = this.parseHeaderList(propsHeaderElementList, namedExportecHeaderList, null);
        if (propsHeaderList != null && propsHeaderList.size() > 0) {
            objClassStructure.getPropsHeaderList().addAll(propsHeaderList);
        }

        /*
         * Parse Emits file informations
         */
        namedExportecHeaderList.clear();
        final List<BlancoXmlElement> emitsList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet, fBundle.getMeta2xmlElementListEmits());
        if (emitsList != null && emitsList.size() != 0) {
            namedExportecHeaderList.clear();
            final BlancoXmlElement elementListRoot = emitsList.get(0);
            this.parseVueComponentEmits(elementListRoot, namedExportecHeaderList, objClassStructure);
        }
        /*
         * Import ObjectEmitsOptions
         */
        BlancoVueComponentUtil.addImportHeaderList("ObjectEmitsOptions", "vue", namedExportecHeaderList);
        // Get headers user defined.
        List<BlancoXmlElement> emitsHeaderElementList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet,
                        fBundle.getMeta2xmlElementHeaderEmits());
        List<String> emitsHeaderList = this.parseHeaderList(emitsHeaderElementList, namedExportecHeaderList, null);
        if (emitsHeaderList != null && emitsHeaderList.size() > 0) {
            objClassStructure.getEmitsHeaderList().addAll(emitsHeaderList);
        }

        // At last, generate impoorts for Vue component definition.
        namedExportecHeaderList.clear();
        this.generateImports4Component(namedExportecHeaderList, objClassStructure);

        // Get the header information for Vue component definition.
        List<BlancoXmlElement> componentHeaderElementList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet,
                        fBundle.getMeta2xmlElementHeaderComponents());
        List<String> headerList = this.parseHeaderList(componentHeaderElementList, namedExportecHeaderList, null);
        if (headerList != null && headerList.size() > 0) {
            objClassStructure.getComponentHeaderList().addAll(headerList);
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
    private void parseCommon(
            final BlancoXmlElement argElementCommon,
            final BlancoVueComponentClassStructure argObjClassStructure
    ) {
        argObjClassStructure.setSubject(BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "subject"));
        argObjClassStructure.setName(BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "name"));
        argObjClassStructure.setAlias(BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "alias"));
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
        argObjClassStructure.setUseSetup("true".equals(BlancoXmlBindingUtil
                .getTextContent(argElementCommon, "useSetup")));
        argObjClassStructure.setUseData("true".equals(BlancoXmlBindingUtil
                .getTextContent(argElementCommon, "useData")));

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
                .setComponentList(new ArrayList<>());
        argObjClassStructure
                .setPropsList(new ArrayList<>());
        argObjClassStructure
                .setApiList(new ArrayList<>());
        argObjClassStructure
                .setEmitsList(new ArrayList<>());
    }

    /**
     * Parse import headers for Component definition.
     *
     * @param argNamedExportedHeaderList
     * @param argObjClassStructure
     */
    private void generateImports4Component(
            final Map<String, List<String>> argNamedExportedHeaderList,
            final BlancoVueComponentClassStructure argObjClassStructure
    ) {
        // make "import info for defineComponent
        BlancoVueComponentUtil.addImportHeaderList("defineComponent", "vue", argNamedExportedHeaderList);

        String componentSuffix = BlancoNameAdjuster.toParameterName(argObjClassStructure.getName());

        // make import info for props. props is always required.
        BlancoVueComponentUtil.addImportHeaderList(argObjClassStructure.getName() + "Props", "./" + argObjClassStructure.getName() + "Props", argNamedExportedHeaderList);
        BlancoVueComponentUtil.addImportHeaderList(componentSuffix + "Props", "./" + argObjClassStructure.getName() + "Props", argNamedExportedHeaderList);

        // make import info for emits.
        if (argObjClassStructure.getEmitsList() != null && argObjClassStructure.getEmitsList().size() != 0) {
            BlancoVueComponentUtil.addImportHeaderList(componentSuffix + "Emits", "./" + argObjClassStructure.getName() + "Emits", argNamedExportedHeaderList);
        }

        String impleDir = argObjClassStructure.getImpledir() + "/";
        String classNameCannon = impleDir + argObjClassStructure.getPackage().replace('.', '/') + "/" + argObjClassStructure.getName();

        // make import info for setup.
        if (argObjClassStructure.getUseSetup()) {
            BlancoVueComponentUtil.addImportHeaderList(componentSuffix + "Setup",  classNameCannon + "Setup", argNamedExportedHeaderList);
        }

        // make import info for data
        if (argObjClassStructure.getUseData()) {
            BlancoVueComponentUtil.addImportHeaderList(componentSuffix + "Data", classNameCannon + "Data", argNamedExportedHeaderList);
        }

        // make import info for render
        if (!argObjClassStructure.getUseTemplate()) {
            BlancoVueComponentUtil.addImportHeaderList(componentSuffix + "Render", classNameCannon + "Render", argNamedExportedHeaderList);
        }
    }

    /**
     * Creates a list of components to be used.
     * Since the import statement is not generated, it should be described in the definition document.
     *
     * @param argElementComponent
     * @param argObjClassStructure
     */
    private void parseVueComponentComponents(
            final BlancoXmlElement argElementComponent,
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

    /**
     * Parse API LIST definition.
     *
     * @param argElementApi
     * @param argObjClassStructure
     */
    private void parseApiList(
            final BlancoXmlElement argElementApi,
            final BlancoVueComponentClassStructure argObjClassStructure
    ) {
        /* A list of API to be used. */
        final List<BlancoXmlElement> listApiChildNodes = BlancoXmlBindingUtil
                .getElementsByTagName(argElementApi, "api");
        for (int index = 0;
             listApiChildNodes != null &&
                     index < listApiChildNodes.size();
             index++) {
            final BlancoXmlElement elementList = listApiChildNodes
                    .get(index);
            final BlancoVueComponentApiStructure apiStructure = new BlancoVueComponentApiStructure();

            final String apiName = BlancoXmlBindingUtil
                    .getTextContent(elementList, "name");
            if (apiName == null || apiName.trim().length() == 0) {
                continue;
            }
            apiStructure.setName(apiName);

            final String apiNo = BlancoXmlBindingUtil
                    .getTextContent(elementList, "no");
            if (apiNo != null && apiNo.trim().length() != 0) {
                apiStructure.setNo(apiNo);
            }

            final String apiMethod = BlancoXmlBindingUtil
                    .getTextContent(elementList, "method");
            if (apiMethod == null || apiMethod.trim().length() == 0) {
                continue;
            }
            apiStructure.setMethod(apiMethod);

            final String apiDescription = BlancoXmlBindingUtil
                    .getTextContent(elementList, "description");
            if (apiDescription == null || apiDescription.trim().length() == 0) {
                continue;
            }
            apiStructure.setDescription(apiDescription);
            final String[] lines = BlancoNameUtil.splitString(
                    apiStructure.getDescription(), '\n');
            for (int indexLine = 0; indexLine < lines.length; indexLine++) {
                if (indexLine == 0) {
                    apiStructure.setDescription(lines[indexLine]);
                } else {
                    // For a multi-line description, it will be split and stored.
                    // From the second line, assumes that character reference encoding has been properly implemented.
                    apiStructure.getDescriptionList().add(
                            lines[indexLine]);
                }
            }

            argObjClassStructure.getApiList().add(apiStructure);
        }
    }

    private void parseVueComponentProps(
            final BlancoXmlElement argElementProperties,
            final Map<String, List<String>> argHeaderList,
            final BlancoVueComponentClassStructure argObjClassStructure
    ) {
        final List<BlancoXmlElement> listChildNodes = BlancoXmlBindingUtil
                .getElementsByTagName(argElementProperties, "props");
        for (int index = 0; index < listChildNodes.size(); index++) {
            final BlancoXmlElement elementList = listChildNodes.get(index);
            final BlancoVueComponentPropsStructure propsStructure = new BlancoVueComponentPropsStructure();

            propsStructure.setNo(BlancoXmlBindingUtil.getTextContent(
                    elementList, "no"));
            propsStructure.setName(BlancoXmlBindingUtil.getTextContent(
                    elementList, "name"));
            if (propsStructure.getName() == null
                    || propsStructure.getName().trim().length() == 0) {
                continue;
            }

            /*
             * Gets the type. Changes the type name to TypeScript style.
             */
            String phpType = BlancoXmlBindingUtil.getTextContent(elementList, "type");
            String targetType = BlancoVueComponentUtil.convertPhpType2Ts(phpType, argObjClassStructure, argHeaderList, false);
            propsStructure.setType(targetType);

            /* Supports Generic. */
            String phpGeneric = BlancoXmlBindingUtil.getTextContent(elementList, "generic");
            if (BlancoStringUtil.null2Blank(phpGeneric).length() != 0) {
                String targetGeneric = "";
                try {
                    targetGeneric = BlancoVueComponentUtil.convertPhpType2Ts(phpGeneric, argObjClassStructure, argHeaderList, true);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(fMsg.getMbvoji06(
                            argObjClassStructure.getName(),
                            propsStructure.getName(),
                            phpGeneric,
                            phpGeneric
                    ));
                }
                propsStructure.setGeneric(targetGeneric);
            }

            // Supports required.
            if ("true".equalsIgnoreCase(BlancoXmlBindingUtil.getTextContent(
                    elementList, "required"))) {
                propsStructure.setRequired(true);
            }

            // Supports Nullable.
            propsStructure.setNullable("true".equals(BlancoXmlBindingUtil
                    .getTextContent(elementList, "nullable")));

            // Supports query string.
            propsStructure.setQueryParam(BlancoXmlBindingUtil.getTextContent(
                    elementList, "queryParam"));

            // Supports description.
            propsStructure.setDescription(BlancoXmlBindingUtil
                    .getTextContent(elementList, "description"));
            final String[] lines = BlancoNameUtil.splitString(
                    propsStructure.getDescription(), '\n');
            for (int indexLine = 0; indexLine < lines.length; indexLine++) {
                if (indexLine == 0) {
                    propsStructure.setDescription(lines[indexLine]);
                } else {
                    // For a multi-line description, it will be split and stored.
                    // From the second line, assumes that character reference encoding has been properly implemented.
                    propsStructure.getDescriptionList().add(
                            lines[indexLine]);
                }
            }

            propsStructure.setDefault(BlancoXmlBindingUtil.getTextContent(
                    elementList, "default"));

            argObjClassStructure.getPropsList().add(propsStructure);
        }
    }

    private void parseVueComponentEmits(
            final BlancoXmlElement argElementEmits,
            final Map<String, List<String>> argHeaderList,
            final BlancoVueComponentClassStructure argObjClassStructure
    ) {
        final List<BlancoXmlElement> listChildNodes = BlancoXmlBindingUtil
                .getElementsByTagName(argElementEmits, "emits");
        for (int index = 0; index < listChildNodes.size(); index++) {
            final BlancoXmlElement elementList = listChildNodes.get(index);
            final BlancoVueComponentEmitsStructure emitsStructure = new BlancoVueComponentEmitsStructure();

            emitsStructure.setNo(BlancoXmlBindingUtil.getTextContent(
                    elementList, "no"));
            emitsStructure.setName(BlancoXmlBindingUtil.getTextContent(
                    elementList, "name"));
            if (emitsStructure.getName() == null
                    || emitsStructure.getName().trim().length() == 0) {
                continue;
            }

            /*
             * Gets the type. Changes the type name to TypeScript style.
             */
            String phpType = BlancoXmlBindingUtil.getTextContent(elementList, "type");
            String targetType = BlancoVueComponentUtil.convertPhpType2Ts(phpType, argObjClassStructure, argHeaderList, false);
            emitsStructure.setType(targetType);

            /* Supports Generic. */
            String phpGeneric = BlancoXmlBindingUtil.getTextContent(elementList, "generic");
            if (BlancoStringUtil.null2Blank(phpGeneric).length() != 0) {
                String targetGeneric = "";
                try {
                    targetGeneric = BlancoVueComponentUtil.convertPhpType2Ts(phpGeneric, argObjClassStructure, argHeaderList, true);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(fMsg.getMbvoji06(
                            argObjClassStructure.getName(),
                            emitsStructure.getName(),
                            phpGeneric,
                            phpGeneric
                    ));
                }
                emitsStructure.setGeneric(targetGeneric);
            }

            // Supports description.
            emitsStructure.setDescription(BlancoXmlBindingUtil
                    .getTextContent(elementList, "description"));
            final String[] lines = BlancoNameUtil.splitString(
                    emitsStructure.getDescription(), '\n');
            for (int indexLine = 0; indexLine < lines.length; indexLine++) {
                if (indexLine == 0) {
                    emitsStructure.setDescription(lines[indexLine]);
                } else {
                    // For a multi-line description, it will be split and stored.
                    // From the second line, assumes that character reference encoding has been properly implemented.
                    emitsStructure.getDescriptionList().add(
                            lines[indexLine]);
                }
            }

            argObjClassStructure.getEmitsList().add(emitsStructure);
        }
    }

    /**
     * to add subject and alias properties
     *
     * @param argPropName
     * @param argType
     * @param argNullable
     * @param argDefaultValue
     * @param argDescription
     * @return
     */
    private BlancoVueComponentPropsStructure generatePropsStructure(
            String argPropName,
            String argType,
            Boolean argNullable,
            String argDefaultValue,
            String argDescription) {
        BlancoVueComponentPropsStructure propsStructure = new BlancoVueComponentPropsStructure();
        propsStructure.setName(argPropName);
        propsStructure.setType(argType);
        propsStructure.setDefault(argDefaultValue);
        propsStructure.setNullable(argNullable);
        propsStructure.setDescription(argDescription);

        return propsStructure;
    }

    /**
     * Create headerList with auto-generated imports and user defined imports.
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
