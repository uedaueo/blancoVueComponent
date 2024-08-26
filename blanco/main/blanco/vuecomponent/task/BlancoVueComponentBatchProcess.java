package blanco.vuecomponent.task;

import java.io.IOException;

import blanco.vuecomponent.task.valueobject.BlancoVueComponentProcessInput;

/**
 * Batch process class [BlancoVueComponentBatchProcess].
 *
 * <P>Example of a batch processing call.</P>
 * <code>
 * java -classpath (classpath) blanco.vuecomponent.task.BlancoVueComponentBatchProcess -help
 * </code>
 */
public class BlancoVueComponentBatchProcess {
    /**
     * Normal end.
     */
    public static final int END_SUCCESS = 0;

    /**
     * Termination due to abnormal input. In the case that java.lang.IllegalArgumentException is raised internally.
     */
    public static final int END_ILLEGAL_ARGUMENT_EXCEPTION = 7;

    /**
     * Termination due to I/O exception. In the case that java.io.IOException is raised internally.
     */
    public static final int END_IO_EXCEPTION = 8;

    /**
     * Abnormal end. In the case that batch process fails to start or java.lang.Error or java.lang.RuntimeException is raised internally.
     */
    public static final int END_ERROR = 9;

    /**
     * The entry point when executed from the command line.
     *
     * @param args Agruments inherited from the console.
     */
    public static final void main(final String[] args) {
        final BlancoVueComponentBatchProcess batchProcess = new BlancoVueComponentBatchProcess();

        // Arguments for batch process.
        final BlancoVueComponentProcessInput input = new BlancoVueComponentProcessInput();

        boolean isNeedUsage = false;
        boolean isFieldMetadirProcessed = false;

        // Parses command line arguments.
        for (int index = 0; index < args.length; index++) {
            String arg = args[index];
            if (arg.startsWith("-verbose=")) {
                input.setVerbose(Boolean.valueOf(arg.substring(9)).booleanValue());
            } else if (arg.startsWith("-metadir=")) {
                input.setMetadir(arg.substring(9));
                isFieldMetadirProcessed = true;
            } else if (arg.startsWith("-targetdir=")) {
                input.setTargetdir(arg.substring(11));
            } else if (arg.startsWith("-tmpdir=")) {
                input.setTmpdir(arg.substring(8));
            } else if (arg.startsWith("-encoding=")) {
                input.setEncoding(arg.substring(10));
            } else if (arg.startsWith("-tabs=")) {
                try {
                    input.setTabs(Integer.parseInt(arg.substring(6)));
                } catch (NumberFormatException e) {
                    System.out.println("BlancoVueComponentBatchProcess: Failed to start the process. Tried to parse the field [tabs] of the input parameter[input] as a number (int), but it failed.: " + e.toString());
                    System.exit(END_ILLEGAL_ARGUMENT_EXCEPTION);
                }
            } else if (arg.startsWith("-xmlrootelement=")) {
                input.setXmlrootelement(Boolean.valueOf(arg.substring(16)).booleanValue());
            } else if (arg.startsWith("-sheetType=")) {
                input.setSheetType(arg.substring(11));
            } else if (arg.startsWith("-targetStyle=")) {
                input.setTargetStyle(arg.substring(13));
            } else if (arg.startsWith("-listClass=")) {
                input.setListClass(arg.substring(11));
            } else if (arg.startsWith("-lineSeparator=")) {
                input.setLineSeparator(arg.substring(15));
            } else if (arg.startsWith("-searchTmpdir=")) {
                input.setSearchTmpdir(arg.substring(14));
            } else if (arg.startsWith("-routeRecordMapKey=")) {
                input.setRouteRecordMapKey(arg.substring(19));
            } else if (arg.startsWith("-routeRecordMap=")) {
                input.setRouteRecordMap(arg.substring(16));
            } else if (arg.startsWith("-routeRecordBreadCrumbName=")) {
                input.setRouteRecordBreadCrumbName(arg.substring(27));
            } else if (arg.startsWith("-breadCrumbInterface=")) {
                input.setBreadCrumbInterface(arg.substring(21));
            } else if (arg.startsWith("-menuItemInterface=")) {
                input.setMenuItemInterface(arg.substring(19));
            } else if (arg.startsWith("-menuItemDescription=")) {
                input.setMenuItemDescription(arg.substring(21));
            } else if (arg.equals("-?") || arg.equals("-help")) {
                usage();
                System.exit(END_SUCCESS);
            } else {
                System.out.println("BlancoVueComponentBatchProcess: The input parameter[" + arg + "] was ignored.");
                isNeedUsage = true;
            }
        }

        if (isNeedUsage) {
            usage();
        }

        if( isFieldMetadirProcessed == false) {
            System.out.println("BlancoVueComponentBatchProcess: Failed to start the process. The required field value[metadir] in the input parameter[input] is not set to a value.");
            System.exit(END_ILLEGAL_ARGUMENT_EXCEPTION);
        }

        int retCode = batchProcess.execute(input);

        // Returns the exit code.
        // Note: Please note that calling System.exit().
        System.exit(retCode);
    }

    /**
     * A method to describe the specific batch processing contents.
     *
     * This method is used to describe the actual process.
     *
     * @param input Input parameters for batch process.
     * @return The exit code for batch process. Returns one of the values END_SUCCESS, END_ILLEGAL_ARGUMENT_EXCEPTION, END_IO_EXCEPTION, END_ERROR
     * @throws IOException If an I/O exception occurs.
     * @throws IllegalArgumentException If an invalid input value is found.
     */
    public int process(final BlancoVueComponentProcessInput input) throws IOException, IllegalArgumentException {
        // Checks the input parameters.
        validateInput(input);

        // If you get a compile error at this point, You may be able to solve it by implementing a BlancoVueComponentProcess interface and creating an BlancoVueComponentProcessImpl class in package blanco.vuecomponent.task.
        final BlancoVueComponentProcess process = new BlancoVueComponentProcessImpl();

        // Executes the main body of the process.
        final int retCode = process.execute(input);

        return retCode;
    }

    /**
     * The entry point for instantiating a class and running a batch.
     *
     * This method provides the following specifications.
     * <ul>
     * <li>Checks the contents of the input parameters of the method.
     * <li>Catches exceptions such as IllegalArgumentException, RuntimeException, Error, etc. and converts them to return values.
     * </ul>
     *
     * @param input Input parameters for batch process.
     * @return The exit code for batch process. Returns one of the values END_SUCCESS, END_ILLEGAL_ARGUMENT_EXCEPTION, END_IO_EXCEPTION, END_ERROR
     * @throws IllegalArgumentException If an invalid input value is found.
     */
    public final int execute(final BlancoVueComponentProcessInput input) throws IllegalArgumentException {
        try {
            // Executes the main body of the batch process.
            int retCode = process(input);

            return retCode;
        } catch (IllegalArgumentException ex) {
            System.out.println("BlancoVueComponentBatchProcess: An input exception has occurred. Abort the batch process.:" + ex.toString());
            // Termination due to abnormal input.
            return END_ILLEGAL_ARGUMENT_EXCEPTION;
        } catch (IOException ex) {
            System.out.println("BlancoVueComponentBatchProcess: An I/O exception has occurred. Abort the batch process.:" + ex.toString());
            // Termination due to abnormal input.
            return END_IO_EXCEPTION;
        } catch (RuntimeException ex) {
            System.out.println("BlancoVueComponentBatchProcess: A runtime exception has occurred. Abort the batch process.:" + ex.toString());
            ex.printStackTrace();
            // Abnormal end.
            return END_ERROR;
        } catch (Error er) {
            System.out.println("BlancoVueComponentBatchProcess: A runtime exception has occurred. Abort the batch process.:" + er.toString());
            er.printStackTrace();
            // Abnormal end.
            return END_ERROR;
        }
    }

    /**
     * A method to show an explanation of how to use this batch processing class on the stdout.
     */
    public static final void usage() {
        System.out.println("BlancoVueComponentBatchProcess: Usage:");
        System.out.println("  java blanco.vuecomponent.task.BlancoVueComponentBatchProcess -verbose=value1 -metadir=value2 -targetdir=value3 -tmpdir=value4 -encoding=value5 -tabs=value6 -xmlrootelement=value7 -sheetType=value8 -targetStyle=value9 -listClass=value10 -lineSeparator=value11 -searchTmpdir=value12 -routeRecordMapKey=value13 -routeRecordMap=value14 -routeRecordBreadCrumbName=value15 -breadCrumbInterface=value16 -menuItemInterface=value17 -menuItemDescription=value18");
        System.out.println("    -verbose");
        System.out.println("      explanation[Whether to run in verbose mode.]");
        System.out.println("      type[boolean]");
        System.out.println("      default value[false]");
        System.out.println("    -metadir");
        System.out.println("      explanation[メタディレクトリ。xlsファイルの格納先または xmlファイルの格納先を指定します。]");
        System.out.println("      type[string]");
        System.out.println("      a required parameter");
        System.out.println("    -targetdir");
        System.out.println("      explanation[出力先フォルダを指定します。無指定の場合にはカレント直下のblancoを用います。]");
        System.out.println("      type[string]");
        System.out.println("      default value[blanco]");
        System.out.println("    -tmpdir");
        System.out.println("      explanation[テンポラリディレクトリを指定します。無指定の場合にはカレント直下のtmpを用います。]");
        System.out.println("      type[string]");
        System.out.println("      default value[tmp]");
        System.out.println("    -encoding");
        System.out.println("      explanation[自動生成するソースファイルの文字エンコーディングを指定します。]");
        System.out.println("      type[string]");
        System.out.println("    -tabs");
        System.out.println("      explanation[タブをwhite spaceいくつで置き換えるか、という値です。]");
        System.out.println("      type[number(int)]");
        System.out.println("      default value[4]");
        System.out.println("    -xmlrootelement");
        System.out.println("      explanation[XML ルート要素のアノテーションを出力するかどうか。JDK 1.6 以降が必要。]");
        System.out.println("      type[boolean]");
        System.out.println("      default value[false]");
        System.out.println("    -sheetType");
        System.out.println("      explanation[meta定義書が期待しているプログラミング言語を指定します]");
        System.out.println("      type[string]");
        System.out.println("      default value[java]");
        System.out.println("    -targetStyle");
        System.out.println("      explanation[出力先フォルダの書式を指定します。<br>\nblanco: [targetdir]/main<br>\nmaven: [targetdir]/main/java<br>\nfree: [targetdir](targetdirが無指定の場合はblanco/main)]");
        System.out.println("      type[string]");
        System.out.println("      default value[blanco]");
        System.out.println("    -listClass");
        System.out.println("      explanation[未指定または空文字でなかった場合に、対象ディレクトリ内の自動生成した画面コンポーネントのRouteRecordクラスの一覧を返す関数を生成します。この関数はvue-routerの初期設定に使用されます。関数名は . 区切りの配置場所を含めて指定します。事前にExcelシートの作成が必要です。]");
        System.out.println("      type[string]");
        System.out.println("    -lineSeparator");
        System.out.println("      explanation[行末記号をしていします。LF=0x0a, CR=0x0d, CFLF=0x0d0x0a とします。LFがデフォルトです。]");
        System.out.println("      type[string]");
        System.out.println("      default value[LF]");
        System.out.println("    -searchTmpdir");
        System.out.println("      explanation[import文作成のために検索するtmpディレクトリをカンマ区切りで指定します。指定ディレクトリ直下のvalueobjectディレクトリの下にxmlを探しにいきます。]");
        System.out.println("      type[string]");
        System.out.println("    -routeRecordMapKey");
        System.out.println("      explanation[routeRecordMap のキーとして使用する項目を指定します。項目名にはClassStructureで定義しているプロパティ名を使用します。]");
        System.out.println("      type[string]");
        System.out.println("      default value[\"name\"]");
        System.out.println("    -routeRecordMap");
        System.out.println("      explanation[未指定または空文字でなかった場合に、対象ディレクトリ内の自動生成したコンポーネント別名をキーとして、RouteRecord 関数を返す連想配列を生成します。ファイル名は . 区切りの配置場所を含めて指定します。事前にExcelシートの作成が必要です。]");
        System.out.println("      type[string]");
        System.out.println("    -routeRecordBreadCrumbName");
        System.out.println("      explanation[routeRecord#meta の breadCrumb.name に設定する項目を指定します。項目名にはClassStructureで定義しているプロパティ名を使用します。]");
        System.out.println("      type[string]");
        System.out.println("      default value[\"alias\"]");
        System.out.println("    -breadCrumbInterface");
        System.out.println("      explanation[未指定または空文字でなかった場合に、指定された配置場所にパンくずリストデータの為のインタフェイスを生成します。]");
        System.out.println("      type[string]");
        System.out.println("    -menuItemInterface");
        System.out.println("      explanation[未指定または空文字でなかった場合に、指定されたMenuItem interfaceを使用して。メニュー情報データを各画面コンポーネントの配置場所に作成します。]");
        System.out.println("      type[string]");
        System.out.println("    -menuItemDescription");
        System.out.println("      explanation[MenuItem の description に割り当てる項目名を指定します。項目名にはClassStructure で定義しているプロパティ名を使用します。]");
        System.out.println("      type[string]");
        System.out.println("      default value[\"description\"]");
        System.out.println("    -? , -help");
        System.out.println("      explanation[show the usage.]");
    }

    /**
     * A method to check the validity of input parameters for this batch processing class.
     *
     * @param input Input parameters for batch process.
     * @throws IllegalArgumentException If an invalid input value is found.
     */
    public void validateInput(final BlancoVueComponentProcessInput input) throws IllegalArgumentException {
        if (input == null) {
            throw new IllegalArgumentException("BlancoBatchProcessBatchProcess: Failed to start the process. The input parameter[input] was given as null.");
        }
        if (input.getMetadir() == null) {
            throw new IllegalArgumentException("BlancoVueComponentBatchProcess: Failed to start the process. The required field value[metadir] in the input parameter[input] is not set to a value.");
        }
    }
}
