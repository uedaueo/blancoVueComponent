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
import blanco.commons.util.BlancoStringUtil;
import blanco.vuecomponent.*;
import blanco.vuecomponent.message.BlancoVueComponentMessage;
import blanco.vuecomponent.task.valueobject.BlancoVueComponentProcessInput;
import blanco.vuecomponent.valueobject.BlancoVueComponentClassStructure;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BlancoVueComponentProcessImpl implements BlancoVueComponentProcess {

    /**
     * A message.
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
             * Determines the newline code.
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
             * Processes targetdir and targetStyle.
             * Sets the storage location for the generated code.
             * targetstyle = blanco:
             *  Creates a main directory under targetdir.
             * targetstyle = maven:
             *  Creates a main/java directory under targetdir.
             * targetstyle = free:
             *  Creates a directory using targetdir as is.
             *  However, if targetdir is empty, the default string (blanco) is used.
             * by tueda, 2019/08/30
             */
            String strTarget = input.getTargetdir();
            String style = input.getTargetStyle();
            // Always true when passing through here.
            boolean isTargetStyleAdvanced = true;
            if (style != null && BlancoVueComponentConstants.TARGET_STYLE_MAVEN.equals(style)) {
                strTarget = strTarget + "/" + BlancoVueComponentConstants.TARGET_DIR_SUFFIX_MAVEN;
            } else if (style == null ||
                    !BlancoVueComponentConstants.TARGET_STYLE_FREE.equals(style)) {
                strTarget = strTarget + "/" + BlancoVueComponentConstants.TARGET_DIR_SUFFIX_BLANCO;
            }
            /* If style is free, uses targetdir as is. */
            if (input.getVerbose()) {
                System.out.println("/* tueda */ TARGETDIR = " + strTarget);
            }

            // Creates a temporary directory.
            new File(input.getTmpdir()
                    + BlancoVueComponentConstants.TARGET_SUBDIRECTORY).mkdirs();

            new BlancoVueComponentMeta2Xml().processDirectory(fileMetadir, input
                    .getTmpdir()
                    + BlancoVueComponentConstants.TARGET_SUBDIRECTORY);

            // Generates ValueObject from XML-ized meta file.
            // Scans the temporary folder first.
            final File[] fileMeta2 = new File(input.getTmpdir()
                    + BlancoVueComponentConstants.TARGET_SUBDIRECTORY)
                    .listFiles();

        /*
         * First, searches all the sheets and makes a list of structures from the class names.
         * The reason is that in the PHP-style definitions, the package name is not specified when specifying a class.
         */
            BlancoVueComponentUtil.isVerbose = input.getVerbose();
            BlancoVueComponentUtil.routeRecordMapKey = input.getRouteRecordMapKey();
            BlancoVueComponentUtil.routeRecordBreadCrumbName = input.getRouteRecordBreadCrumbName();
            BlancoVueComponentUtil.breadCrumbInterface = input.getBreadCrumbInterface();
            BlancoVueComponentUtil.menuItemInterface = input.getMenuItemInterface();
            BlancoVueComponentUtil.menuItemDescription = input.getMenuItemDescription();

            BlancoVueComponentUtil.processValueObjects(input);

            /*
             * If the listClass is specified, generates the RouterConfig class and a class that holds a list (array) of RouterConfig classes.
             * RouterConfig is kept in the same location as each component class, and the class that holds the list is placed in targetDir/listClass.
             * The listClass can specify the sub directory with being specified in the same as a Java package.
             */
            boolean createClassList = false;
            String listClassName = input.getListClass();
            List<BlancoVueComponentClassStructure> screenComponentStructures = new ArrayList<>();
            List<BlancoVueComponentClassStructure> sortedScreenComponents = new ArrayList<>();
            if (listClassName != null && !listClassName.isEmpty()) {
                createClassList = true;
            }

            String routeRecordMapName = input.getRouteRecordMap();
            if (routeRecordMapName != null && !routeRecordMapName.isEmpty()) {
                BlancoVueComponentUtil.createRouteRecordMap = true;
            }

            String permissionKindMapName = input.getPermissionKindMap();
            if (permissionKindMapName != null && !permissionKindMapName.isEmpty()) {
                BlancoVueComponentUtil.createPermissionKindMap = true;
            }

            // Next, scans the directory specified as the meta directory.
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
                 * If the listClass is specified, it will collect a list of auto-generated classes.
                 */
                for (int index2 = 0; createClassList && index2 < structures.length; index2++) {
                    BlancoVueComponentClassStructure classStructure = structures[index2];
                    if ("screen".equalsIgnoreCase(classStructure.getComponentKind())) {
                        screenComponentStructures.add(classStructure);
                    }
                }
                /*
                 * sort screenComponentStructures
                 */
                if (screenComponentStructures.size() == 1) {
                    sortedScreenComponents.add(screenComponentStructures.get(0));
                } else if (screenComponentStructures.size() > 1) {
                    sortedScreenComponents = screenComponentStructures.stream().sorted(
                            new Comparator<BlancoVueComponentClassStructure>() {
                                @Override
                                public int compare(BlancoVueComponentClassStructure o1, BlancoVueComponentClassStructure o2) {
                                    return o1.getName().compareTo(o2.getName());
                                }
                            }
                    ).collect(Collectors.toList());
                }
            }

            /*
             * If the listClass is specified, it will create a ValueObject that holds the list of auto-generated classes.
             */
            if (createClassList) {
                if (sortedScreenComponents.isEmpty()) {
                    System.out.println("[WARN] listClass is specified but no meta file. : " + listClassName);
                } else {
                    final BlancoVueComponentXml2TypeScriptClass xml2Class = new BlancoVueComponentXml2TypeScriptClass();
                    xml2Class.setEncoding(input.getEncoding());
                    xml2Class.setVerbose(input.getVerbose());
                    xml2Class.setTargetStyleAdvanced(isTargetStyleAdvanced);
                    xml2Class.setXmlRootElement(input.getXmlrootelement());
                    xml2Class.setSheetLang(new BlancoCgSupportedLang().convertToInt(input.getSheetType()));
                    xml2Class.setTabs(input.getTabs());
                    xml2Class.setListClass(listClassName);
                    xml2Class.processListClass(sortedScreenComponents, new File(strTarget));
                }
            }

            if (BlancoVueComponentUtil.createRouteRecordMap) {
                if (sortedScreenComponents.isEmpty()) {
                    System.out.println("[WARN] routeRecordMap is specified but no meta file. : " + routeRecordMapName);
                } else {
                    final BlancoVueComponentXml2TypeScriptClass xml2Class = new BlancoVueComponentXml2TypeScriptClass();
                    xml2Class.setEncoding(input.getEncoding());
                    xml2Class.setVerbose(input.getVerbose());
                    xml2Class.setTargetStyleAdvanced(isTargetStyleAdvanced);
                    xml2Class.setXmlRootElement(input.getXmlrootelement());
                    xml2Class.setSheetLang(new BlancoCgSupportedLang().convertToInt(input.getSheetType()));
                    xml2Class.setTabs(input.getTabs());
                    xml2Class.setRouteRecordMap(routeRecordMapName);
                    xml2Class.processRouteRecordMap(sortedScreenComponents, new File(strTarget));
                    xml2Class.processRouteRecordMapInterface(new File(strTarget));
                    if (!BlancoStringUtil.null2Blank(BlancoVueComponentUtil.breadCrumbInterface).trim().isEmpty()) {
                        xml2Class.processBreadCrumbInterface(new File(strTarget));
                    }
                    if (!BlancoStringUtil.null2Blank(BlancoVueComponentUtil.menuItemInterface).trim().isEmpty()) {
                        xml2Class.processMenuInfoInterface(new File(strTarget));
                    }
                }
            }

            if (BlancoVueComponentUtil.createPermissionKindMap) {
                final BlancoVueComponentXml2TypeScriptClass xml2Class = new BlancoVueComponentXml2TypeScriptClass();
                xml2Class.setEncoding(input.getEncoding());
                xml2Class.setVerbose(input.getVerbose());
                xml2Class.setTargetStyleAdvanced(isTargetStyleAdvanced);
                xml2Class.setXmlRootElement(input.getXmlrootelement());
                xml2Class.setSheetLang(new BlancoCgSupportedLang().convertToInt(input.getSheetType()));
                xml2Class.setTabs(input.getTabs());
                xml2Class.setPermissionKindMap(permissionKindMapName);
                xml2Class.processPermissionKindMap(sortedScreenComponents, new File(strTarget));
                xml2Class.processPermissionKindMapInterface(new File(strTarget));
            }

            return BlancoVueComponentBatchProcess.END_SUCCESS;
        } catch (TransformerException e) {
            throw new IOException("An exception has occurred during the XML conversion process: " + e.toString());
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
