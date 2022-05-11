package blanco.vuecomponent;

import blanco.valueobjectts.BlancoValueObjectTsXmlParser;
import blanco.valueobjectts.valueobject.BlancoValueObjectTsClassStructure;
import blanco.vuecomponent.task.valueobject.BlancoVueComponentProcessInput;
import blanco.vuecomponent.valueobject.BlancoVueComponentClassStructure;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Gets the list of objects created in BlancoValueObject from XML and stores it.
 *
 * Created by tueda on 15/07/05.
 */
public class BlancoVueComponentUtil {

    static public boolean isVerbose = false;

    public static HashMap<String, BlancoValueObjectTsClassStructure> objects = new HashMap<>();

    static public void processValueObjects(final BlancoVueComponentProcessInput input) throws IOException {
        if (isVerbose) {
            System.out.println("BlancoVueComponentUtil : processValueObjects start !");
        }

        /* tmpdir is unique. */
        String baseTmpdir = input.getTmpdir();
        /* searchTmpdir is comma separated. */
        String tmpTmpdirs = input.getSearchTmpdir();
        List<String> searchTmpdirList = null;
        if (tmpTmpdirs != null && !tmpTmpdirs.equals(baseTmpdir)) {
            String[] searchTmpdirs = tmpTmpdirs.split(",");
            searchTmpdirList = new ArrayList<>(Arrays.asList(searchTmpdirs));
        }
        if (searchTmpdirList == null) {
            searchTmpdirList = new ArrayList<>();
        }
        searchTmpdirList.add(baseTmpdir);

        for (String tmpdir : searchTmpdirList) {
            searchTmpdir(tmpdir.trim());
        }
    }

    static private void searchTmpdir(String tmpdir) {

        // Reads information from XML-ized intermediate files.
        final File[] fileMeta3 = new File(tmpdir
                + BlancoVueComponentConstants.OBJECT_SUBDIRECTORY)
                .listFiles();

        if (fileMeta3 == null) {
            System.out.println("!!! NO FILES in " + tmpdir
                    + BlancoVueComponentConstants.OBJECT_SUBDIRECTORY);
            return;
        }

        for (int index = 0; index < fileMeta3.length; index++) {
            if (fileMeta3[index].getName().endsWith(".xml") == false) {
                continue;
            }

            BlancoValueObjectTsXmlParser parser = new BlancoValueObjectTsXmlParser();
//            parser.setVerbose(this.isVerbose());
            /*
             * First, searches all the sheets and makes a list of class and package names.
             * This is because the package name is not specified when specifying a class in the PHP format definition.
             */
            final BlancoValueObjectTsClassStructure[] structures = parser.parse(fileMeta3[index]);

            if (structures != null ) {
                for (int index2 = 0; index2 < structures.length; index2++) {
                    BlancoValueObjectTsClassStructure structure = structures[index2];
                    if (structure != null) {
                        if (isVerbose) {
                            System.out.println("processValueObjects: " + structure.getName());
                        }
                        objects.put(structure.getName(), structure);
                    } else {
                        System.out.println("processValueObjects: a structure is NULL!!!");
                    }
                }
            } else {
                System.out.println("processValueObjects: structures are NULL!!!");
            }
        }
    }

    /**
     * Make canonical classname into Simple.
     *
     * @param argClassNameCanon
     * @return simpleName
     */
    static public String getSimpleClassName(final String argClassNameCanon) {
        if (argClassNameCanon == null) {
            return "";
        }

        String simpleName = "";
        final int findLastDot = argClassNameCanon.lastIndexOf('.');
        if (findLastDot == -1) {
            simpleName = argClassNameCanon;
        } else if (findLastDot != argClassNameCanon.length() - 1) {
            simpleName = argClassNameCanon.substring(findLastDot + 1);
        }
        return simpleName;
    }

    /**
     * Make canonical classname into packageName
     *
     * @param argClassNameCanon
     * @return
     */
    static public String getPackageName(final String argClassNameCanon) {
        if (argClassNameCanon == null) {
            return "";
        }

        String simpleName = "";
        final int findLastDot = argClassNameCanon.lastIndexOf('.');
        if (findLastDot > 0) {
            simpleName = argClassNameCanon.substring(0, findLastDot);
        }
        return simpleName;
    }

    /**
     * Generates import statements.
     * @param argPackageName
     * @param argClassName
     * @param argImportHeaderList
     * @param argBasedir
     * @param argPropertyPackage
     */
    static public void makeImportHeaderList(
            final String argPackageName,
            final String argClassName,
            final Map<String, List<String>> argImportHeaderList,
            final String argBasedir,
            final String argPropertyPackage) {
        if (argClassName == null || argClassName.length() == 0) {
            System.out.println("BlancoVueComponentUtil#makeImportHeaderList className is not specified. SKIP.");
            return;
        }
//        if (isVerbose) {
//            System.out.println("BlancoVueComponentUtil#makeImportHeaderList: START : " + argPackageName + ", " + argClassName + ", " + argBasedir);
//        }
        String importFrom = "./" + argClassName;
        if (argPackageName != null &&
                argPackageName.length() != 0 &&
                argPackageName.equals(argPropertyPackage) != true) {
            String classNameCanon = argPackageName.replace('.', '/') + "/" + argClassName;

            String basedir = "";
            if (argBasedir != null) {
                basedir = argBasedir;
            }
            importFrom = basedir + "/" + classNameCanon;
        }

        List<String> importClassList = argImportHeaderList.get(importFrom);
        if (importClassList == null) {
            importClassList = new ArrayList<>();
            argImportHeaderList.put(importFrom, importClassList);
        }
        boolean isMatch = false;
        for (String myClass : importClassList) {
            if (argClassName.equals(myClass)) {
                isMatch = true;
                break;
            }
        }
        if (!isMatch) {
            importClassList.add(argClassName);
            if (isVerbose) {
                System.out.println("BlancoVueComponentUtil#makeImportHeaderList: new import { " + argClassName + " } from \"" + importFrom + "\"");
            }
        }
    }

    /**
     * Adds to importHeaderList with only checking for duplicates.
     *
     * @param argClassName
     * @param argImportFrom
     * @param argImportHeaderList
     */
    static public void addImportHeaderList(
            String argClassName,
            String argImportFrom,
            final Map<String, List<String>> argImportHeaderList
    ) {
        List<String> importClassList = argImportHeaderList.get(argImportFrom);
        if (importClassList == null) {
            importClassList = new ArrayList<>();
            argImportHeaderList.put(argImportFrom, importClassList);
        }
        boolean isMatch = false;
        for (String myClass : importClassList) {
            if (argClassName.equals(myClass)) {
                isMatch = true;
                break;
            }
        }
        if (!isMatch) {
            importClassList.add(argClassName);
            if (isVerbose) {
                System.out.println("BlancoVueComponentUtil#addImportHeaderList: new import { " + argClassName + " } from \"" + argImportFrom + "\"");
            }
        }
    }

    static public String convertPhpType2Ts(String argPhpType, BlancoVueComponentClassStructure argObjClassStructure, Map<String, List<String>> argHeaderList, boolean isGeneric) {
        String targetType = argPhpType;
        if ("boolean".equalsIgnoreCase(argPhpType)) {
            targetType = "boolean";
        } else if ("integer".equalsIgnoreCase(argPhpType)) {
            targetType = "number";
        } else if ("double".equalsIgnoreCase(argPhpType)) {
            targetType = "number";
        } else if ("float".equalsIgnoreCase(argPhpType)) {
            targetType = "number";
        } else if ("string".equalsIgnoreCase(argPhpType)) {
            targetType = "string";
        } else if ("array".equalsIgnoreCase(argPhpType)) {
            if (isGeneric) {
                throw new IllegalArgumentException("Generic Type Exception");
            }
            targetType = "Array";
        } else if ("object".equalsIgnoreCase(argPhpType)) {
            targetType = "any";
        } else {
            /* Searches for a package name with this name. */
            BlancoValueObjectTsClassStructure voStructure = BlancoVueComponentUtil.objects.get(argPhpType);

            String packageName = null;
            if (voStructure != null) {
                packageName = voStructure.getPackage();
                if (packageName == null) {
                    // Tries to isolate the package name.
                    String simpleName = BlancoVueComponentUtil.getSimpleClassName(argPhpType);
                    if (simpleName != null && !simpleName.equals(argPhpType)) {
                        packageName = BlancoVueComponentUtil.getPackageName(argPhpType);
                        argPhpType = simpleName;
                    }
                }
                if (packageName != null) {
                    targetType = packageName + "." + argPhpType;
                }

                /* Others are written as is. */

                /*
                 * Creates import information for TypeScript.
                 * Note that the package may be the same as the component, but the basedir may be different.
                 */
                if (argObjClassStructure.getCreateImportList()) {
                    BlancoVueComponentUtil.makeImportHeaderList(packageName, argPhpType, argHeaderList, voStructure.getBasedir(), "");
                }
            }
        }
        return targetType;
    }
}
