package blanco.vuecomponent.task.valueobject;

/**
 * An input value object class for the processing class [BlancoVueComponentProcess].
 */
public class BlancoVueComponentProcessInput {
    /**
     * Whether to run in verbose mode.
     *
     * フィールド: [verbose]。
     * デフォルト: [false]。
     */
    private boolean fVerbose = false;

    /**
     * メタディレクトリ。xlsファイルの格納先または xmlファイルの格納先を指定します。
     *
     * フィールド: [metadir]。
     */
    private String fMetadir;

    /**
     * 出力先フォルダを指定します。無指定の場合にはカレント直下のblancoを用います。
     *
     * フィールド: [targetdir]。
     * デフォルト: [blanco]。
     */
    private String fTargetdir = "blanco";

    /**
     * テンポラリディレクトリを指定します。無指定の場合にはカレント直下のtmpを用います。
     *
     * フィールド: [tmpdir]。
     * デフォルト: [tmp]。
     */
    private String fTmpdir = "tmp";

    /**
     * 自動生成するソースファイルの文字エンコーディングを指定します。
     *
     * フィールド: [encoding]。
     */
    private String fEncoding;

    /**
     * タブをwhite spaceいくつで置き換えるか、という値です。
     *
     * フィールド: [tabs]。
     * デフォルト: [4]。
     */
    private int fTabs = 4;

    /**
     * XML ルート要素のアノテーションを出力するかどうか。JDK 1.6 以降が必要。
     *
     * フィールド: [xmlrootelement]。
     * デフォルト: [false]。
     */
    private boolean fXmlrootelement = false;

    /**
     * meta定義書が期待しているプログラミング言語を指定します
     *
     * フィールド: [sheetType]。
     * デフォルト: [java]。
     */
    private String fSheetType = "java";

    /**
     * 出力先フォルダの書式を指定します。&amp;lt;br&amp;gt;\nblanco: [targetdir]/main&amp;lt;br&amp;gt;\nmaven: [targetdir]/main/java&amp;lt;br&amp;gt;\nfree: [targetdir](targetdirが無指定の場合はblanco/main)
     *
     * フィールド: [targetStyle]。
     * デフォルト: [blanco]。
     */
    private String fTargetStyle = "blanco";

    /**
     * 未指定または空文字でなかった場合に、対象ディレクトリ内の自動生成した画面コンポーネントのRouteRecordクラスの一覧を返す関数を生成します。この関数はvue-routerの初期設定に使用されます。関数名は . 区切りの配置場所を含めて指定します。事前にExcelシートの作成が必要です。
     *
     * フィールド: [listClass]。
     */
    private String fListClass;

    /**
     * 行末記号をしていします。LF=0x0a, CR=0x0d, CFLF=0x0d0x0a とします。LFがデフォルトです。
     *
     * フィールド: [lineSeparator]。
     * デフォルト: [LF]。
     */
    private String fLineSeparator = "LF";

    /**
     * import文作成のために検索するtmpディレクトリをカンマ区切りで指定します。指定ディレクトリ直下のvalueobjectディレクトリの下にxmlを探しにいきます。
     *
     * フィールド: [searchTmpdir]。
     */
    private String fSearchTmpdir;

    /**
     * routeRecordMap のキーとして使用する項目を指定します。項目名にはClassStructureで定義しているプロパティ名を使用します。
     *
     * フィールド: [routeRecordMapKey]。
     * デフォルト: [&quot;name&quot;]。
     */
    private String fRouteRecordMapKey = "\"name\"";

    /**
     * 未指定または空文字でなかった場合に、対象ディレクトリ内の自動生成したコンポーネント別名をキーとして、RouteRecord 関数を返す連想配列を生成します。ファイル名は . 区切りの配置場所を含めて指定します。事前にExcelシートの作成が必要です。
     *
     * フィールド: [routeRecordMap]。
     */
    private String fRouteRecordMap;

    /**
     * routeRecord#meta の breadCrumb.name に設定する項目を指定します。項目名にはClassStructureで定義しているプロパティ名を使用します。
     *
     * フィールド: [routeRecordBreadCrumbName]。
     * デフォルト: [&quot;alias&quot;]。
     */
    private String fRouteRecordBreadCrumbName = "\"alias\"";

    /**
     * 未指定または空文字でなかった場合に、指定された配置場所にパンくずリストデータの為のインタフェイスを生成します。
     *
     * フィールド: [breadCrumbInterface]。
     */
    private String fBreadCrumbInterface;

    /**
     * 未指定または空文字でなかった場合に、指定されたMenuItem interfaceを使用して。メニュー情報データを各画面コンポーネントの配置場所に作成します。
     *
     * フィールド: [menuItemInterface]。
     */
    private String fMenuItemInterface;

    /**
     * MenuItem の description に割り当てる項目名を指定します。項目名にはClassStructure で定義しているプロパティ名を使用します。
     *
     * フィールド: [menuItemDescription]。
     * デフォルト: [&quot;description&quot;]。
     */
    private String fMenuItemDescription = "\"description\"";

    /**
     * 未指定または空文字でなかった場合に、対象ディレクトリ内の自動生成した権限種別をキーとして、componentId の配列を返す連想配列を生成します。ファイル名は . 区切りの配置場所を含めて指定します。
     *
     * フィールド: [permissionKindMap]。
     */
    private String fPermissionKindMap;

    /**
     * Nullable な property に対して、? の付与をやめて | undefined | null の定義を行う。false の場合は ? が付与されて | undefined のみ付与される。
     *
     * フィールド: [strictNullable]。
     * デフォルト: [false]。
     */
    private boolean fStrictNullable = false;

    /**
     * 対応する vuejs のバージョンを指定します。middle バージョンの違いで動作が変わる場合があるので、文字列として定義します。
     *
     * フィールド: [supportedVueVersion]。
     * デフォルト: [3.4]。
     */
    private String fSupportedVueVersion = "3.4";

    /**
     * フィールド [verbose] の値を設定します。
     *
     * フィールドの説明: [Whether to run in verbose mode.]。
     *
     * @param argVerbose フィールド[verbose]に設定する値。
     */
    public void setVerbose(final boolean argVerbose) {
        fVerbose = argVerbose;
    }

    /**
     * フィールド [verbose] の値を取得します。
     *
     * フィールドの説明: [Whether to run in verbose mode.]。
     * デフォルト: [false]。
     *
     * @return フィールド[verbose]から取得した値。
     */
    public boolean getVerbose() {
        return fVerbose;
    }

    /**
     * フィールド [metadir] の値を設定します。
     *
     * フィールドの説明: [メタディレクトリ。xlsファイルの格納先または xmlファイルの格納先を指定します。]。
     *
     * @param argMetadir フィールド[metadir]に設定する値。
     */
    public void setMetadir(final String argMetadir) {
        fMetadir = argMetadir;
    }

    /**
     * フィールド [metadir] の値を取得します。
     *
     * フィールドの説明: [メタディレクトリ。xlsファイルの格納先または xmlファイルの格納先を指定します。]。
     *
     * @return フィールド[metadir]から取得した値。
     */
    public String getMetadir() {
        return fMetadir;
    }

    /**
     * フィールド [targetdir] の値を設定します。
     *
     * フィールドの説明: [出力先フォルダを指定します。無指定の場合にはカレント直下のblancoを用います。]。
     *
     * @param argTargetdir フィールド[targetdir]に設定する値。
     */
    public void setTargetdir(final String argTargetdir) {
        fTargetdir = argTargetdir;
    }

    /**
     * フィールド [targetdir] の値を取得します。
     *
     * フィールドの説明: [出力先フォルダを指定します。無指定の場合にはカレント直下のblancoを用います。]。
     * デフォルト: [blanco]。
     *
     * @return フィールド[targetdir]から取得した値。
     */
    public String getTargetdir() {
        return fTargetdir;
    }

    /**
     * フィールド [tmpdir] の値を設定します。
     *
     * フィールドの説明: [テンポラリディレクトリを指定します。無指定の場合にはカレント直下のtmpを用います。]。
     *
     * @param argTmpdir フィールド[tmpdir]に設定する値。
     */
    public void setTmpdir(final String argTmpdir) {
        fTmpdir = argTmpdir;
    }

    /**
     * フィールド [tmpdir] の値を取得します。
     *
     * フィールドの説明: [テンポラリディレクトリを指定します。無指定の場合にはカレント直下のtmpを用います。]。
     * デフォルト: [tmp]。
     *
     * @return フィールド[tmpdir]から取得した値。
     */
    public String getTmpdir() {
        return fTmpdir;
    }

    /**
     * フィールド [encoding] の値を設定します。
     *
     * フィールドの説明: [自動生成するソースファイルの文字エンコーディングを指定します。]。
     *
     * @param argEncoding フィールド[encoding]に設定する値。
     */
    public void setEncoding(final String argEncoding) {
        fEncoding = argEncoding;
    }

    /**
     * フィールド [encoding] の値を取得します。
     *
     * フィールドの説明: [自動生成するソースファイルの文字エンコーディングを指定します。]。
     *
     * @return フィールド[encoding]から取得した値。
     */
    public String getEncoding() {
        return fEncoding;
    }

    /**
     * フィールド [tabs] の値を設定します。
     *
     * フィールドの説明: [タブをwhite spaceいくつで置き換えるか、という値です。]。
     *
     * @param argTabs フィールド[tabs]に設定する値。
     */
    public void setTabs(final int argTabs) {
        fTabs = argTabs;
    }

    /**
     * フィールド [tabs] の値を取得します。
     *
     * フィールドの説明: [タブをwhite spaceいくつで置き換えるか、という値です。]。
     * デフォルト: [4]。
     *
     * @return フィールド[tabs]から取得した値。
     */
    public int getTabs() {
        return fTabs;
    }

    /**
     * フィールド [xmlrootelement] の値を設定します。
     *
     * フィールドの説明: [XML ルート要素のアノテーションを出力するかどうか。JDK 1.6 以降が必要。]。
     *
     * @param argXmlrootelement フィールド[xmlrootelement]に設定する値。
     */
    public void setXmlrootelement(final boolean argXmlrootelement) {
        fXmlrootelement = argXmlrootelement;
    }

    /**
     * フィールド [xmlrootelement] の値を取得します。
     *
     * フィールドの説明: [XML ルート要素のアノテーションを出力するかどうか。JDK 1.6 以降が必要。]。
     * デフォルト: [false]。
     *
     * @return フィールド[xmlrootelement]から取得した値。
     */
    public boolean getXmlrootelement() {
        return fXmlrootelement;
    }

    /**
     * フィールド [sheetType] の値を設定します。
     *
     * フィールドの説明: [meta定義書が期待しているプログラミング言語を指定します]。
     *
     * @param argSheetType フィールド[sheetType]に設定する値。
     */
    public void setSheetType(final String argSheetType) {
        fSheetType = argSheetType;
    }

    /**
     * フィールド [sheetType] の値を取得します。
     *
     * フィールドの説明: [meta定義書が期待しているプログラミング言語を指定します]。
     * デフォルト: [java]。
     *
     * @return フィールド[sheetType]から取得した値。
     */
    public String getSheetType() {
        return fSheetType;
    }

    /**
     * フィールド [targetStyle] の値を設定します。
     *
     * フィールドの説明: [出力先フォルダの書式を指定します。&lt;br&gt;\nblanco: [targetdir]/main&lt;br&gt;\nmaven: [targetdir]/main/java&lt;br&gt;\nfree: [targetdir](targetdirが無指定の場合はblanco/main)]。
     *
     * @param argTargetStyle フィールド[targetStyle]に設定する値。
     */
    public void setTargetStyle(final String argTargetStyle) {
        fTargetStyle = argTargetStyle;
    }

    /**
     * フィールド [targetStyle] の値を取得します。
     *
     * フィールドの説明: [出力先フォルダの書式を指定します。&lt;br&gt;\nblanco: [targetdir]/main&lt;br&gt;\nmaven: [targetdir]/main/java&lt;br&gt;\nfree: [targetdir](targetdirが無指定の場合はblanco/main)]。
     * デフォルト: [blanco]。
     *
     * @return フィールド[targetStyle]から取得した値。
     */
    public String getTargetStyle() {
        return fTargetStyle;
    }

    /**
     * フィールド [listClass] の値を設定します。
     *
     * フィールドの説明: [未指定または空文字でなかった場合に、対象ディレクトリ内の自動生成した画面コンポーネントのRouteRecordクラスの一覧を返す関数を生成します。この関数はvue-routerの初期設定に使用されます。関数名は . 区切りの配置場所を含めて指定します。事前にExcelシートの作成が必要です。]。
     *
     * @param argListClass フィールド[listClass]に設定する値。
     */
    public void setListClass(final String argListClass) {
        fListClass = argListClass;
    }

    /**
     * フィールド [listClass] の値を取得します。
     *
     * フィールドの説明: [未指定または空文字でなかった場合に、対象ディレクトリ内の自動生成した画面コンポーネントのRouteRecordクラスの一覧を返す関数を生成します。この関数はvue-routerの初期設定に使用されます。関数名は . 区切りの配置場所を含めて指定します。事前にExcelシートの作成が必要です。]。
     *
     * @return フィールド[listClass]から取得した値。
     */
    public String getListClass() {
        return fListClass;
    }

    /**
     * フィールド [lineSeparator] の値を設定します。
     *
     * フィールドの説明: [行末記号をしていします。LF=0x0a, CR=0x0d, CFLF=0x0d0x0a とします。LFがデフォルトです。]。
     *
     * @param argLineSeparator フィールド[lineSeparator]に設定する値。
     */
    public void setLineSeparator(final String argLineSeparator) {
        fLineSeparator = argLineSeparator;
    }

    /**
     * フィールド [lineSeparator] の値を取得します。
     *
     * フィールドの説明: [行末記号をしていします。LF=0x0a, CR=0x0d, CFLF=0x0d0x0a とします。LFがデフォルトです。]。
     * デフォルト: [LF]。
     *
     * @return フィールド[lineSeparator]から取得した値。
     */
    public String getLineSeparator() {
        return fLineSeparator;
    }

    /**
     * フィールド [searchTmpdir] の値を設定します。
     *
     * フィールドの説明: [import文作成のために検索するtmpディレクトリをカンマ区切りで指定します。指定ディレクトリ直下のvalueobjectディレクトリの下にxmlを探しにいきます。]。
     *
     * @param argSearchTmpdir フィールド[searchTmpdir]に設定する値。
     */
    public void setSearchTmpdir(final String argSearchTmpdir) {
        fSearchTmpdir = argSearchTmpdir;
    }

    /**
     * フィールド [searchTmpdir] の値を取得します。
     *
     * フィールドの説明: [import文作成のために検索するtmpディレクトリをカンマ区切りで指定します。指定ディレクトリ直下のvalueobjectディレクトリの下にxmlを探しにいきます。]。
     *
     * @return フィールド[searchTmpdir]から取得した値。
     */
    public String getSearchTmpdir() {
        return fSearchTmpdir;
    }

    /**
     * フィールド [routeRecordMapKey] の値を設定します。
     *
     * フィールドの説明: [routeRecordMap のキーとして使用する項目を指定します。項目名にはClassStructureで定義しているプロパティ名を使用します。]。
     *
     * @param argRouteRecordMapKey フィールド[routeRecordMapKey]に設定する値。
     */
    public void setRouteRecordMapKey(final String argRouteRecordMapKey) {
        fRouteRecordMapKey = argRouteRecordMapKey;
    }

    /**
     * フィールド [routeRecordMapKey] の値を取得します。
     *
     * フィールドの説明: [routeRecordMap のキーとして使用する項目を指定します。項目名にはClassStructureで定義しているプロパティ名を使用します。]。
     * デフォルト: [&quot;name&quot;]。
     *
     * @return フィールド[routeRecordMapKey]から取得した値。
     */
    public String getRouteRecordMapKey() {
        return fRouteRecordMapKey;
    }

    /**
     * フィールド [routeRecordMap] の値を設定します。
     *
     * フィールドの説明: [未指定または空文字でなかった場合に、対象ディレクトリ内の自動生成したコンポーネント別名をキーとして、RouteRecord 関数を返す連想配列を生成します。ファイル名は . 区切りの配置場所を含めて指定します。事前にExcelシートの作成が必要です。]。
     *
     * @param argRouteRecordMap フィールド[routeRecordMap]に設定する値。
     */
    public void setRouteRecordMap(final String argRouteRecordMap) {
        fRouteRecordMap = argRouteRecordMap;
    }

    /**
     * フィールド [routeRecordMap] の値を取得します。
     *
     * フィールドの説明: [未指定または空文字でなかった場合に、対象ディレクトリ内の自動生成したコンポーネント別名をキーとして、RouteRecord 関数を返す連想配列を生成します。ファイル名は . 区切りの配置場所を含めて指定します。事前にExcelシートの作成が必要です。]。
     *
     * @return フィールド[routeRecordMap]から取得した値。
     */
    public String getRouteRecordMap() {
        return fRouteRecordMap;
    }

    /**
     * フィールド [routeRecordBreadCrumbName] の値を設定します。
     *
     * フィールドの説明: [routeRecord#meta の breadCrumb.name に設定する項目を指定します。項目名にはClassStructureで定義しているプロパティ名を使用します。]。
     *
     * @param argRouteRecordBreadCrumbName フィールド[routeRecordBreadCrumbName]に設定する値。
     */
    public void setRouteRecordBreadCrumbName(final String argRouteRecordBreadCrumbName) {
        fRouteRecordBreadCrumbName = argRouteRecordBreadCrumbName;
    }

    /**
     * フィールド [routeRecordBreadCrumbName] の値を取得します。
     *
     * フィールドの説明: [routeRecord#meta の breadCrumb.name に設定する項目を指定します。項目名にはClassStructureで定義しているプロパティ名を使用します。]。
     * デフォルト: [&quot;alias&quot;]。
     *
     * @return フィールド[routeRecordBreadCrumbName]から取得した値。
     */
    public String getRouteRecordBreadCrumbName() {
        return fRouteRecordBreadCrumbName;
    }

    /**
     * フィールド [breadCrumbInterface] の値を設定します。
     *
     * フィールドの説明: [未指定または空文字でなかった場合に、指定された配置場所にパンくずリストデータの為のインタフェイスを生成します。]。
     *
     * @param argBreadCrumbInterface フィールド[breadCrumbInterface]に設定する値。
     */
    public void setBreadCrumbInterface(final String argBreadCrumbInterface) {
        fBreadCrumbInterface = argBreadCrumbInterface;
    }

    /**
     * フィールド [breadCrumbInterface] の値を取得します。
     *
     * フィールドの説明: [未指定または空文字でなかった場合に、指定された配置場所にパンくずリストデータの為のインタフェイスを生成します。]。
     *
     * @return フィールド[breadCrumbInterface]から取得した値。
     */
    public String getBreadCrumbInterface() {
        return fBreadCrumbInterface;
    }

    /**
     * フィールド [menuItemInterface] の値を設定します。
     *
     * フィールドの説明: [未指定または空文字でなかった場合に、指定されたMenuItem interfaceを使用して。メニュー情報データを各画面コンポーネントの配置場所に作成します。]。
     *
     * @param argMenuItemInterface フィールド[menuItemInterface]に設定する値。
     */
    public void setMenuItemInterface(final String argMenuItemInterface) {
        fMenuItemInterface = argMenuItemInterface;
    }

    /**
     * フィールド [menuItemInterface] の値を取得します。
     *
     * フィールドの説明: [未指定または空文字でなかった場合に、指定されたMenuItem interfaceを使用して。メニュー情報データを各画面コンポーネントの配置場所に作成します。]。
     *
     * @return フィールド[menuItemInterface]から取得した値。
     */
    public String getMenuItemInterface() {
        return fMenuItemInterface;
    }

    /**
     * フィールド [menuItemDescription] の値を設定します。
     *
     * フィールドの説明: [MenuItem の description に割り当てる項目名を指定します。項目名にはClassStructure で定義しているプロパティ名を使用します。]。
     *
     * @param argMenuItemDescription フィールド[menuItemDescription]に設定する値。
     */
    public void setMenuItemDescription(final String argMenuItemDescription) {
        fMenuItemDescription = argMenuItemDescription;
    }

    /**
     * フィールド [menuItemDescription] の値を取得します。
     *
     * フィールドの説明: [MenuItem の description に割り当てる項目名を指定します。項目名にはClassStructure で定義しているプロパティ名を使用します。]。
     * デフォルト: [&quot;description&quot;]。
     *
     * @return フィールド[menuItemDescription]から取得した値。
     */
    public String getMenuItemDescription() {
        return fMenuItemDescription;
    }

    /**
     * フィールド [permissionKindMap] の値を設定します。
     *
     * フィールドの説明: [未指定または空文字でなかった場合に、対象ディレクトリ内の自動生成した権限種別をキーとして、componentId の配列を返す連想配列を生成します。ファイル名は . 区切りの配置場所を含めて指定します。]。
     *
     * @param argPermissionKindMap フィールド[permissionKindMap]に設定する値。
     */
    public void setPermissionKindMap(final String argPermissionKindMap) {
        fPermissionKindMap = argPermissionKindMap;
    }

    /**
     * フィールド [permissionKindMap] の値を取得します。
     *
     * フィールドの説明: [未指定または空文字でなかった場合に、対象ディレクトリ内の自動生成した権限種別をキーとして、componentId の配列を返す連想配列を生成します。ファイル名は . 区切りの配置場所を含めて指定します。]。
     *
     * @return フィールド[permissionKindMap]から取得した値。
     */
    public String getPermissionKindMap() {
        return fPermissionKindMap;
    }

    /**
     * フィールド [strictNullable] の値を設定します。
     *
     * フィールドの説明: [Nullable な property に対して、? の付与をやめて | undefined | null の定義を行う。false の場合は ? が付与されて | undefined のみ付与される。]。
     *
     * @param argStrictNullable フィールド[strictNullable]に設定する値。
     */
    public void setStrictNullable(final boolean argStrictNullable) {
        fStrictNullable = argStrictNullable;
    }

    /**
     * フィールド [strictNullable] の値を取得します。
     *
     * フィールドの説明: [Nullable な property に対して、? の付与をやめて | undefined | null の定義を行う。false の場合は ? が付与されて | undefined のみ付与される。]。
     * デフォルト: [false]。
     *
     * @return フィールド[strictNullable]から取得した値。
     */
    public boolean getStrictNullable() {
        return fStrictNullable;
    }

    /**
     * フィールド [supportedVueVersion] の値を設定します。
     *
     * フィールドの説明: [対応する vuejs のバージョンを指定します。middle バージョンの違いで動作が変わる場合があるので、文字列として定義します。]。
     *
     * @param argSupportedVueVersion フィールド[supportedVueVersion]に設定する値。
     */
    public void setSupportedVueVersion(final String argSupportedVueVersion) {
        fSupportedVueVersion = argSupportedVueVersion;
    }

    /**
     * フィールド [supportedVueVersion] の値を取得します。
     *
     * フィールドの説明: [対応する vuejs のバージョンを指定します。middle バージョンの違いで動作が変わる場合があるので、文字列として定義します。]。
     * デフォルト: [3.4]。
     *
     * @return フィールド[supportedVueVersion]から取得した値。
     */
    public String getSupportedVueVersion() {
        return fSupportedVueVersion;
    }

    /**
     * Gets the string representation of this value object.
     *
     * <P>Precautions for use</P>
     * <UL>
     * <LI>Only the shallow range of the object will be subject to the stringification process.
     * <LI>Do not use this method if the object has a circular reference.
     * </UL>
     *
     * @return String representation of a value object.
     */
    @Override
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("blanco.vuecomponent.task.valueobject.BlancoVueComponentProcessInput[");
        buf.append("verbose=" + fVerbose);
        buf.append(",metadir=" + fMetadir);
        buf.append(",targetdir=" + fTargetdir);
        buf.append(",tmpdir=" + fTmpdir);
        buf.append(",encoding=" + fEncoding);
        buf.append(",tabs=" + fTabs);
        buf.append(",xmlrootelement=" + fXmlrootelement);
        buf.append(",sheetType=" + fSheetType);
        buf.append(",targetStyle=" + fTargetStyle);
        buf.append(",listClass=" + fListClass);
        buf.append(",lineSeparator=" + fLineSeparator);
        buf.append(",searchTmpdir=" + fSearchTmpdir);
        buf.append(",routeRecordMapKey=" + fRouteRecordMapKey);
        buf.append(",routeRecordMap=" + fRouteRecordMap);
        buf.append(",routeRecordBreadCrumbName=" + fRouteRecordBreadCrumbName);
        buf.append(",breadCrumbInterface=" + fBreadCrumbInterface);
        buf.append(",menuItemInterface=" + fMenuItemInterface);
        buf.append(",menuItemDescription=" + fMenuItemDescription);
        buf.append(",permissionKindMap=" + fPermissionKindMap);
        buf.append(",strictNullable=" + fStrictNullable);
        buf.append(",supportedVueVersion=" + fSupportedVueVersion);
        buf.append("]");
        return buf.toString();
    }

    /**
     * Copies this value object to the specified target.
     *
     * <P>Cautions for use</P>
     * <UL>
     * <LI>Only the shallow range of the object will be subject to the copying process.
     * <LI>Do not use this method if the object has a circular reference.
     * </UL>
     *
     * @param target target value object.
     */
    public void copyTo(final BlancoVueComponentProcessInput target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: BlancoVueComponentProcessInput#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fVerbose
        // Type: boolean
        target.fVerbose = this.fVerbose;
        // Name: fMetadir
        // Type: java.lang.String
        target.fMetadir = this.fMetadir;
        // Name: fTargetdir
        // Type: java.lang.String
        target.fTargetdir = this.fTargetdir;
        // Name: fTmpdir
        // Type: java.lang.String
        target.fTmpdir = this.fTmpdir;
        // Name: fEncoding
        // Type: java.lang.String
        target.fEncoding = this.fEncoding;
        // Name: fTabs
        // Type: int
        target.fTabs = this.fTabs;
        // Name: fXmlrootelement
        // Type: boolean
        target.fXmlrootelement = this.fXmlrootelement;
        // Name: fSheetType
        // Type: java.lang.String
        target.fSheetType = this.fSheetType;
        // Name: fTargetStyle
        // Type: java.lang.String
        target.fTargetStyle = this.fTargetStyle;
        // Name: fListClass
        // Type: java.lang.String
        target.fListClass = this.fListClass;
        // Name: fLineSeparator
        // Type: java.lang.String
        target.fLineSeparator = this.fLineSeparator;
        // Name: fSearchTmpdir
        // Type: java.lang.String
        target.fSearchTmpdir = this.fSearchTmpdir;
        // Name: fRouteRecordMapKey
        // Type: java.lang.String
        target.fRouteRecordMapKey = this.fRouteRecordMapKey;
        // Name: fRouteRecordMap
        // Type: java.lang.String
        target.fRouteRecordMap = this.fRouteRecordMap;
        // Name: fRouteRecordBreadCrumbName
        // Type: java.lang.String
        target.fRouteRecordBreadCrumbName = this.fRouteRecordBreadCrumbName;
        // Name: fBreadCrumbInterface
        // Type: java.lang.String
        target.fBreadCrumbInterface = this.fBreadCrumbInterface;
        // Name: fMenuItemInterface
        // Type: java.lang.String
        target.fMenuItemInterface = this.fMenuItemInterface;
        // Name: fMenuItemDescription
        // Type: java.lang.String
        target.fMenuItemDescription = this.fMenuItemDescription;
        // Name: fPermissionKindMap
        // Type: java.lang.String
        target.fPermissionKindMap = this.fPermissionKindMap;
        // Name: fStrictNullable
        // Type: boolean
        target.fStrictNullable = this.fStrictNullable;
        // Name: fSupportedVueVersion
        // Type: java.lang.String
        target.fSupportedVueVersion = this.fSupportedVueVersion;
    }
}
