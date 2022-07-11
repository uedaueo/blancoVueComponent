package blanco.vuecomponent.valueobject;

import java.util.List;

import blanco.cg.valueobject.BlancoCgField;

/**
 * Vueコンポーネントのクラスをあらわすバリューオブジェクトクラス。このクラスの設定情報をもとにクラスが自動生成されます。
 */
public class BlancoVueComponentClassStructure {
    /**
     * フィールド名を指定します。必須項目です。
     *
     * フィールド: [name]。
     */
    private String fName;

    /**
     * パッケージ名を指定します。必須項目です。
     *
     * フィールド: [package]。
     */
    private String fPackage;

    /**
     * listClassが指定された場合に、プロパティ名として使われます。未指定の場合はnameをlowerCamelCaseに変換して使用します。
     *
     * フィールド: [classAlias]。
     */
    private String fClassAlias;

    /**
     * vue-routerのpathとして使用されます。
     *
     * フィールド: [routerPath]。
     */
    private String fRouterPath;

    /**
     * vue-routerのnameとして使用されます。
     *
     * フィールド: [routerName]。
     */
    private String fRouterName;

    /**
     * 本番時にファイルを配置する際のベースディレクトリ。主にTypeScriptのimport文生成時に使用する事を想定しています。
     *
     * フィールド: [basedir]。
     */
    private String fBasedir;

    /**
     * 本番時に実装クラスを配置する際のベースディレクトリ。主にTypeScriptのimport文生成時に使用する事を想定しています。
     *
     * フィールド: [impledir]。
     */
    private String fImpledir;

    /**
     * クラスの説明です。
     *
     * フィールド: [description]。
     */
    private String fDescription;

    /**
     * クラスの補助説明です。文字参照エンコード済みの値を格納してください。
     *
     * フィールド: [descriptionList]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     */
    private List<String> fDescriptionList = new java.util.ArrayList<java.lang.String>();

    /**
     * component source コードの先頭に書かれるコード群です。
     *
     * フィールド: [componentHeaderList]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     */
    private List<String> fComponentHeaderList = new java.util.ArrayList<java.lang.String>();

    /**
     * api 定義コードの先頭に書かれるコード群です。
     *
     * フィールド: [apiHeaderList]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     */
    private List<String> fApiHeaderList = new java.util.ArrayList<java.lang.String>();

    /**
     * props 定義コードの先頭に書かれるコード群です。
     *
     * フィールド: [propsHeaderList]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     */
    private List<String> fPropsHeaderList = new java.util.ArrayList<java.lang.String>();

    /**
     * emits 定義コードの先頭に書かれるコード群です。
     *
     * フィールド: [emitsHeaderList]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     */
    private List<String> fEmitsHeaderList = new java.util.ArrayList<java.lang.String>();

    /**
     * コンポーネントの種別。screen or part
     *
     * フィールド: [componentKind]。
     * デフォルト: [&quot;screen&quot;]。
     */
    private String fComponentKind = "screen";

    /**
     * 認証が必要なクラスかどうか
     *
     * フィールド: [authRequired]。
     * デフォルト: [false]。
     */
    private Boolean fAuthRequired = false;

    /**
     * クラスが拡張可能かどうか。kotlin では通常は true。
     *
     * フィールド: [forceReload]。
     * デフォルト: [false]。
     */
    private Boolean fForceReload = false;

    /**
     * templateを使用するかどうか
     *
     * フィールド: [useTemplate]。
     * デフォルト: [false]。
     */
    private Boolean fUseTemplate = false;

    /**
     * scriptを使用するかどうか
     *
     * フィールド: [useScript]。
     * デフォルト: [false]。
     */
    private Boolean fUseScript = false;

    /**
     * styleを使用するかどうか
     *
     * フィールド: [useStyle]。
     * デフォルト: [false]。
     */
    private Boolean fUseStyle = false;

    /**
     * フィールド名の名前変形をおこなうかどうか。
     *
     * フィールド: [adjustFieldName]。
     * デフォルト: [true]。
     */
    private Boolean fAdjustFieldName = true;

    /**
     * デフォルト値の変形をおこなうかどうか。※なるべく変形を利用しないことを推奨したい。※プログラムAPIとして生成する際には、このフィールドを明示的に設定してください。
     *
     * フィールド: [adjustDefaultValue]。
     * デフォルト: [false]。
     */
    private Boolean fAdjustDefaultValue = false;

    /**
     * TypeScript 独自。blancoで一括生成されたクラスについて、import文を自動生成します。
     *
     * フィールド: [createImportList]。
     * デフォルト: [true]。
     */
    private Boolean fCreateImportList = true;

    /**
     * 継承するクラスを指定します。
     *
     * フィールド: [extends]。
     */
    private String fExtends;

    /**
     * 使用するコンポーネントの(java.lang.String)の一覧。自動探索はされないので、componentHeaderListに明示的に記載する必要があります。
     *
     * フィールド: [componentList]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     */
    private List<String> fComponentList = new java.util.ArrayList<java.lang.String>();

    /**
     * propsのリストを記憶します。
     *
     * フィールド: [propsList]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.vuecomponent.valueobject.BlancoVueComponentPropsStructure&gt;()]。
     */
    private List<BlancoVueComponentPropsStructure> fPropsList = new java.util.ArrayList<blanco.vuecomponent.valueobject.BlancoVueComponentPropsStructure>();

    /**
     * apiのリストを記憶します。
     *
     * フィールド: [apiList]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.vuecomponent.valueobject.BlancoVueComponentApiStructure&gt;()]。
     */
    private List<BlancoVueComponentApiStructure> fApiList = new java.util.ArrayList<blanco.vuecomponent.valueobject.BlancoVueComponentApiStructure>();

    /**
     * emitsのリストを記憶します。
     *
     * フィールド: [emitsList]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.vuecomponent.valueobject.BlancoVueComponentEmitsStructure&gt;()]。
     */
    private List<BlancoVueComponentEmitsStructure> fEmitsList = new java.util.ArrayList<blanco.vuecomponent.valueobject.BlancoVueComponentEmitsStructure>();

    /**
     * ファイル説明
     *
     * フィールド: [fileDescription]。
     */
    private String fFileDescription;

    /**
     * クラス定義に含めるコンストラクタの引数マップです。&lt;引数名, 型&gt; となります。
     *
     * フィールド: [constructorArgList]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.cg.valueobject.BlancoCgField&gt;()]。
     */
    private List<BlancoCgField> fConstructorArgList = new java.util.ArrayList<blanco.cg.valueobject.BlancoCgField>();

    /**
     * コンポーネントの表示名を定義します。
     *
     * フィールド: [subject]。
     */
    private String fSubject;

    /**
     * 遷移後の参照に一貫性を期待
     *
     * フィールド: [expectConsistentAfterTransition]。
     * デフォルト: [false]。
     */
    private Boolean fExpectConsistentAfterTransition = false;

    /**
     * コンポーネントの別名を定義します。
     *
     * フィールド: [alias]。
     */
    private String fAlias;

    /**
     * setup関数を実装するかどうか
     *
     * フィールド: [useSetup]。
     * デフォルト: [false]。
     */
    private Boolean fUseSetup = false;

    /**
     * data関数を実装するかどうか
     *
     * フィールド: [useData]。
     * デフォルト: [false]。
     */
    private Boolean fUseData = false;

    /**
     * BeforeRouterLeave関数名を定義します。
     *
     * フィールド: [beforeRouterLeave]。
     * デフォルト: [&quot;&quot;]。
     */
    private String fBeforeRouterLeave = "";

    /**
     * フィールド [name] の値を設定します。
     *
     * フィールドの説明: [フィールド名を指定します。必須項目です。]。
     *
     * @param argName フィールド[name]に設定する値。
     */
    public void setName(final String argName) {
        fName = argName;
    }

    /**
     * フィールド [name] の値を取得します。
     *
     * フィールドの説明: [フィールド名を指定します。必須項目です。]。
     *
     * @return フィールド[name]から取得した値。
     */
    public String getName() {
        return fName;
    }

    /**
     * フィールド [package] の値を設定します。
     *
     * フィールドの説明: [パッケージ名を指定します。必須項目です。]。
     *
     * @param argPackage フィールド[package]に設定する値。
     */
    public void setPackage(final String argPackage) {
        fPackage = argPackage;
    }

    /**
     * フィールド [package] の値を取得します。
     *
     * フィールドの説明: [パッケージ名を指定します。必須項目です。]。
     *
     * @return フィールド[package]から取得した値。
     */
    public String getPackage() {
        return fPackage;
    }

    /**
     * フィールド [classAlias] の値を設定します。
     *
     * フィールドの説明: [listClassが指定された場合に、プロパティ名として使われます。未指定の場合はnameをlowerCamelCaseに変換して使用します。]。
     *
     * @param argClassAlias フィールド[classAlias]に設定する値。
     */
    public void setClassAlias(final String argClassAlias) {
        fClassAlias = argClassAlias;
    }

    /**
     * フィールド [classAlias] の値を取得します。
     *
     * フィールドの説明: [listClassが指定された場合に、プロパティ名として使われます。未指定の場合はnameをlowerCamelCaseに変換して使用します。]。
     *
     * @return フィールド[classAlias]から取得した値。
     */
    public String getClassAlias() {
        return fClassAlias;
    }

    /**
     * フィールド [routerPath] の値を設定します。
     *
     * フィールドの説明: [vue-routerのpathとして使用されます。]。
     *
     * @param argRouterPath フィールド[routerPath]に設定する値。
     */
    public void setRouterPath(final String argRouterPath) {
        fRouterPath = argRouterPath;
    }

    /**
     * フィールド [routerPath] の値を取得します。
     *
     * フィールドの説明: [vue-routerのpathとして使用されます。]。
     *
     * @return フィールド[routerPath]から取得した値。
     */
    public String getRouterPath() {
        return fRouterPath;
    }

    /**
     * フィールド [routerName] の値を設定します。
     *
     * フィールドの説明: [vue-routerのnameとして使用されます。]。
     *
     * @param argRouterName フィールド[routerName]に設定する値。
     */
    public void setRouterName(final String argRouterName) {
        fRouterName = argRouterName;
    }

    /**
     * フィールド [routerName] の値を取得します。
     *
     * フィールドの説明: [vue-routerのnameとして使用されます。]。
     *
     * @return フィールド[routerName]から取得した値。
     */
    public String getRouterName() {
        return fRouterName;
    }

    /**
     * フィールド [basedir] の値を設定します。
     *
     * フィールドの説明: [本番時にファイルを配置する際のベースディレクトリ。主にTypeScriptのimport文生成時に使用する事を想定しています。]。
     *
     * @param argBasedir フィールド[basedir]に設定する値。
     */
    public void setBasedir(final String argBasedir) {
        fBasedir = argBasedir;
    }

    /**
     * フィールド [basedir] の値を取得します。
     *
     * フィールドの説明: [本番時にファイルを配置する際のベースディレクトリ。主にTypeScriptのimport文生成時に使用する事を想定しています。]。
     *
     * @return フィールド[basedir]から取得した値。
     */
    public String getBasedir() {
        return fBasedir;
    }

    /**
     * フィールド [impledir] の値を設定します。
     *
     * フィールドの説明: [本番時に実装クラスを配置する際のベースディレクトリ。主にTypeScriptのimport文生成時に使用する事を想定しています。]。
     *
     * @param argImpledir フィールド[impledir]に設定する値。
     */
    public void setImpledir(final String argImpledir) {
        fImpledir = argImpledir;
    }

    /**
     * フィールド [impledir] の値を取得します。
     *
     * フィールドの説明: [本番時に実装クラスを配置する際のベースディレクトリ。主にTypeScriptのimport文生成時に使用する事を想定しています。]。
     *
     * @return フィールド[impledir]から取得した値。
     */
    public String getImpledir() {
        return fImpledir;
    }

    /**
     * フィールド [description] の値を設定します。
     *
     * フィールドの説明: [クラスの説明です。]。
     *
     * @param argDescription フィールド[description]に設定する値。
     */
    public void setDescription(final String argDescription) {
        fDescription = argDescription;
    }

    /**
     * フィールド [description] の値を取得します。
     *
     * フィールドの説明: [クラスの説明です。]。
     *
     * @return フィールド[description]から取得した値。
     */
    public String getDescription() {
        return fDescription;
    }

    /**
     * フィールド [descriptionList] の値を設定します。
     *
     * フィールドの説明: [クラスの補助説明です。文字参照エンコード済みの値を格納してください。]。
     *
     * @param argDescriptionList フィールド[descriptionList]に設定する値。
     */
    public void setDescriptionList(final List<String> argDescriptionList) {
        fDescriptionList = argDescriptionList;
    }

    /**
     * フィールド [descriptionList] の値を取得します。
     *
     * フィールドの説明: [クラスの補助説明です。文字参照エンコード済みの値を格納してください。]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     *
     * @return フィールド[descriptionList]から取得した値。
     */
    public List<String> getDescriptionList() {
        return fDescriptionList;
    }

    /**
     * フィールド [componentHeaderList] の値を設定します。
     *
     * フィールドの説明: [component source コードの先頭に書かれるコード群です。]。
     *
     * @param argComponentHeaderList フィールド[componentHeaderList]に設定する値。
     */
    public void setComponentHeaderList(final List<String> argComponentHeaderList) {
        fComponentHeaderList = argComponentHeaderList;
    }

    /**
     * フィールド [componentHeaderList] の値を取得します。
     *
     * フィールドの説明: [component source コードの先頭に書かれるコード群です。]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     *
     * @return フィールド[componentHeaderList]から取得した値。
     */
    public List<String> getComponentHeaderList() {
        return fComponentHeaderList;
    }

    /**
     * フィールド [apiHeaderList] の値を設定します。
     *
     * フィールドの説明: [api 定義コードの先頭に書かれるコード群です。]。
     *
     * @param argApiHeaderList フィールド[apiHeaderList]に設定する値。
     */
    public void setApiHeaderList(final List<String> argApiHeaderList) {
        fApiHeaderList = argApiHeaderList;
    }

    /**
     * フィールド [apiHeaderList] の値を取得します。
     *
     * フィールドの説明: [api 定義コードの先頭に書かれるコード群です。]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     *
     * @return フィールド[apiHeaderList]から取得した値。
     */
    public List<String> getApiHeaderList() {
        return fApiHeaderList;
    }

    /**
     * フィールド [propsHeaderList] の値を設定します。
     *
     * フィールドの説明: [props 定義コードの先頭に書かれるコード群です。]。
     *
     * @param argPropsHeaderList フィールド[propsHeaderList]に設定する値。
     */
    public void setPropsHeaderList(final List<String> argPropsHeaderList) {
        fPropsHeaderList = argPropsHeaderList;
    }

    /**
     * フィールド [propsHeaderList] の値を取得します。
     *
     * フィールドの説明: [props 定義コードの先頭に書かれるコード群です。]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     *
     * @return フィールド[propsHeaderList]から取得した値。
     */
    public List<String> getPropsHeaderList() {
        return fPropsHeaderList;
    }

    /**
     * フィールド [emitsHeaderList] の値を設定します。
     *
     * フィールドの説明: [emits 定義コードの先頭に書かれるコード群です。]。
     *
     * @param argEmitsHeaderList フィールド[emitsHeaderList]に設定する値。
     */
    public void setEmitsHeaderList(final List<String> argEmitsHeaderList) {
        fEmitsHeaderList = argEmitsHeaderList;
    }

    /**
     * フィールド [emitsHeaderList] の値を取得します。
     *
     * フィールドの説明: [emits 定義コードの先頭に書かれるコード群です。]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     *
     * @return フィールド[emitsHeaderList]から取得した値。
     */
    public List<String> getEmitsHeaderList() {
        return fEmitsHeaderList;
    }

    /**
     * フィールド [componentKind] の値を設定します。
     *
     * フィールドの説明: [コンポーネントの種別。screen or part]。
     *
     * @param argComponentKind フィールド[componentKind]に設定する値。
     */
    public void setComponentKind(final String argComponentKind) {
        fComponentKind = argComponentKind;
    }

    /**
     * フィールド [componentKind] の値を取得します。
     *
     * フィールドの説明: [コンポーネントの種別。screen or part]。
     * デフォルト: [&quot;screen&quot;]。
     *
     * @return フィールド[componentKind]から取得した値。
     */
    public String getComponentKind() {
        return fComponentKind;
    }

    /**
     * フィールド [authRequired] の値を設定します。
     *
     * フィールドの説明: [認証が必要なクラスかどうか]。
     *
     * @param argAuthRequired フィールド[authRequired]に設定する値。
     */
    public void setAuthRequired(final Boolean argAuthRequired) {
        fAuthRequired = argAuthRequired;
    }

    /**
     * フィールド [authRequired] の値を取得します。
     *
     * フィールドの説明: [認証が必要なクラスかどうか]。
     * デフォルト: [false]。
     *
     * @return フィールド[authRequired]から取得した値。
     */
    public Boolean getAuthRequired() {
        return fAuthRequired;
    }

    /**
     * フィールド [forceReload] の値を設定します。
     *
     * フィールドの説明: [クラスが拡張可能かどうか。kotlin では通常は true。]。
     *
     * @param argForceReload フィールド[forceReload]に設定する値。
     */
    public void setForceReload(final Boolean argForceReload) {
        fForceReload = argForceReload;
    }

    /**
     * フィールド [forceReload] の値を取得します。
     *
     * フィールドの説明: [クラスが拡張可能かどうか。kotlin では通常は true。]。
     * デフォルト: [false]。
     *
     * @return フィールド[forceReload]から取得した値。
     */
    public Boolean getForceReload() {
        return fForceReload;
    }

    /**
     * フィールド [useTemplate] の値を設定します。
     *
     * フィールドの説明: [templateを使用するかどうか]。
     *
     * @param argUseTemplate フィールド[useTemplate]に設定する値。
     */
    public void setUseTemplate(final Boolean argUseTemplate) {
        fUseTemplate = argUseTemplate;
    }

    /**
     * フィールド [useTemplate] の値を取得します。
     *
     * フィールドの説明: [templateを使用するかどうか]。
     * デフォルト: [false]。
     *
     * @return フィールド[useTemplate]から取得した値。
     */
    public Boolean getUseTemplate() {
        return fUseTemplate;
    }

    /**
     * フィールド [useScript] の値を設定します。
     *
     * フィールドの説明: [scriptを使用するかどうか]。
     *
     * @param argUseScript フィールド[useScript]に設定する値。
     */
    public void setUseScript(final Boolean argUseScript) {
        fUseScript = argUseScript;
    }

    /**
     * フィールド [useScript] の値を取得します。
     *
     * フィールドの説明: [scriptを使用するかどうか]。
     * デフォルト: [false]。
     *
     * @return フィールド[useScript]から取得した値。
     */
    public Boolean getUseScript() {
        return fUseScript;
    }

    /**
     * フィールド [useStyle] の値を設定します。
     *
     * フィールドの説明: [styleを使用するかどうか]。
     *
     * @param argUseStyle フィールド[useStyle]に設定する値。
     */
    public void setUseStyle(final Boolean argUseStyle) {
        fUseStyle = argUseStyle;
    }

    /**
     * フィールド [useStyle] の値を取得します。
     *
     * フィールドの説明: [styleを使用するかどうか]。
     * デフォルト: [false]。
     *
     * @return フィールド[useStyle]から取得した値。
     */
    public Boolean getUseStyle() {
        return fUseStyle;
    }

    /**
     * フィールド [adjustFieldName] の値を設定します。
     *
     * フィールドの説明: [フィールド名の名前変形をおこなうかどうか。]。
     *
     * @param argAdjustFieldName フィールド[adjustFieldName]に設定する値。
     */
    public void setAdjustFieldName(final Boolean argAdjustFieldName) {
        fAdjustFieldName = argAdjustFieldName;
    }

    /**
     * フィールド [adjustFieldName] の値を取得します。
     *
     * フィールドの説明: [フィールド名の名前変形をおこなうかどうか。]。
     * デフォルト: [true]。
     *
     * @return フィールド[adjustFieldName]から取得した値。
     */
    public Boolean getAdjustFieldName() {
        return fAdjustFieldName;
    }

    /**
     * フィールド [adjustDefaultValue] の値を設定します。
     *
     * フィールドの説明: [デフォルト値の変形をおこなうかどうか。※なるべく変形を利用しないことを推奨したい。※プログラムAPIとして生成する際には、このフィールドを明示的に設定してください。]。
     *
     * @param argAdjustDefaultValue フィールド[adjustDefaultValue]に設定する値。
     */
    public void setAdjustDefaultValue(final Boolean argAdjustDefaultValue) {
        fAdjustDefaultValue = argAdjustDefaultValue;
    }

    /**
     * フィールド [adjustDefaultValue] の値を取得します。
     *
     * フィールドの説明: [デフォルト値の変形をおこなうかどうか。※なるべく変形を利用しないことを推奨したい。※プログラムAPIとして生成する際には、このフィールドを明示的に設定してください。]。
     * デフォルト: [false]。
     *
     * @return フィールド[adjustDefaultValue]から取得した値。
     */
    public Boolean getAdjustDefaultValue() {
        return fAdjustDefaultValue;
    }

    /**
     * フィールド [createImportList] の値を設定します。
     *
     * フィールドの説明: [TypeScript 独自。blancoで一括生成されたクラスについて、import文を自動生成します。]。
     *
     * @param argCreateImportList フィールド[createImportList]に設定する値。
     */
    public void setCreateImportList(final Boolean argCreateImportList) {
        fCreateImportList = argCreateImportList;
    }

    /**
     * フィールド [createImportList] の値を取得します。
     *
     * フィールドの説明: [TypeScript 独自。blancoで一括生成されたクラスについて、import文を自動生成します。]。
     * デフォルト: [true]。
     *
     * @return フィールド[createImportList]から取得した値。
     */
    public Boolean getCreateImportList() {
        return fCreateImportList;
    }

    /**
     * フィールド [extends] の値を設定します。
     *
     * フィールドの説明: [継承するクラスを指定します。]。
     *
     * @param argExtends フィールド[extends]に設定する値。
     */
    public void setExtends(final String argExtends) {
        fExtends = argExtends;
    }

    /**
     * フィールド [extends] の値を取得します。
     *
     * フィールドの説明: [継承するクラスを指定します。]。
     *
     * @return フィールド[extends]から取得した値。
     */
    public String getExtends() {
        return fExtends;
    }

    /**
     * フィールド [componentList] の値を設定します。
     *
     * フィールドの説明: [使用するコンポーネントの(java.lang.String)の一覧。自動探索はされないので、componentHeaderListに明示的に記載する必要があります。]。
     *
     * @param argComponentList フィールド[componentList]に設定する値。
     */
    public void setComponentList(final List<String> argComponentList) {
        fComponentList = argComponentList;
    }

    /**
     * フィールド [componentList] の値を取得します。
     *
     * フィールドの説明: [使用するコンポーネントの(java.lang.String)の一覧。自動探索はされないので、componentHeaderListに明示的に記載する必要があります。]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     *
     * @return フィールド[componentList]から取得した値。
     */
    public List<String> getComponentList() {
        return fComponentList;
    }

    /**
     * フィールド [propsList] の値を設定します。
     *
     * フィールドの説明: [propsのリストを記憶します。]。
     *
     * @param argPropsList フィールド[propsList]に設定する値。
     */
    public void setPropsList(final List<BlancoVueComponentPropsStructure> argPropsList) {
        fPropsList = argPropsList;
    }

    /**
     * フィールド [propsList] の値を取得します。
     *
     * フィールドの説明: [propsのリストを記憶します。]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.vuecomponent.valueobject.BlancoVueComponentPropsStructure&gt;()]。
     *
     * @return フィールド[propsList]から取得した値。
     */
    public List<BlancoVueComponentPropsStructure> getPropsList() {
        return fPropsList;
    }

    /**
     * フィールド [apiList] の値を設定します。
     *
     * フィールドの説明: [apiのリストを記憶します。]。
     *
     * @param argApiList フィールド[apiList]に設定する値。
     */
    public void setApiList(final List<BlancoVueComponentApiStructure> argApiList) {
        fApiList = argApiList;
    }

    /**
     * フィールド [apiList] の値を取得します。
     *
     * フィールドの説明: [apiのリストを記憶します。]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.vuecomponent.valueobject.BlancoVueComponentApiStructure&gt;()]。
     *
     * @return フィールド[apiList]から取得した値。
     */
    public List<BlancoVueComponentApiStructure> getApiList() {
        return fApiList;
    }

    /**
     * フィールド [emitsList] の値を設定します。
     *
     * フィールドの説明: [emitsのリストを記憶します。]。
     *
     * @param argEmitsList フィールド[emitsList]に設定する値。
     */
    public void setEmitsList(final List<BlancoVueComponentEmitsStructure> argEmitsList) {
        fEmitsList = argEmitsList;
    }

    /**
     * フィールド [emitsList] の値を取得します。
     *
     * フィールドの説明: [emitsのリストを記憶します。]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.vuecomponent.valueobject.BlancoVueComponentEmitsStructure&gt;()]。
     *
     * @return フィールド[emitsList]から取得した値。
     */
    public List<BlancoVueComponentEmitsStructure> getEmitsList() {
        return fEmitsList;
    }

    /**
     * フィールド [fileDescription] の値を設定します。
     *
     * フィールドの説明: [ファイル説明]。
     *
     * @param argFileDescription フィールド[fileDescription]に設定する値。
     */
    public void setFileDescription(final String argFileDescription) {
        fFileDescription = argFileDescription;
    }

    /**
     * フィールド [fileDescription] の値を取得します。
     *
     * フィールドの説明: [ファイル説明]。
     *
     * @return フィールド[fileDescription]から取得した値。
     */
    public String getFileDescription() {
        return fFileDescription;
    }

    /**
     * フィールド [constructorArgList] の値を設定します。
     *
     * フィールドの説明: [クラス定義に含めるコンストラクタの引数マップです。<引数名, 型> となります。]。
     *
     * @param argConstructorArgList フィールド[constructorArgList]に設定する値。
     */
    public void setConstructorArgList(final List<BlancoCgField> argConstructorArgList) {
        fConstructorArgList = argConstructorArgList;
    }

    /**
     * フィールド [constructorArgList] の値を取得します。
     *
     * フィールドの説明: [クラス定義に含めるコンストラクタの引数マップです。<引数名, 型> となります。]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.cg.valueobject.BlancoCgField&gt;()]。
     *
     * @return フィールド[constructorArgList]から取得した値。
     */
    public List<BlancoCgField> getConstructorArgList() {
        return fConstructorArgList;
    }

    /**
     * フィールド [subject] の値を設定します。
     *
     * フィールドの説明: [コンポーネントの表示名を定義します。]。
     *
     * @param argSubject フィールド[subject]に設定する値。
     */
    public void setSubject(final String argSubject) {
        fSubject = argSubject;
    }

    /**
     * フィールド [subject] の値を取得します。
     *
     * フィールドの説明: [コンポーネントの表示名を定義します。]。
     *
     * @return フィールド[subject]から取得した値。
     */
    public String getSubject() {
        return fSubject;
    }

    /**
     * フィールド [expectConsistentAfterTransition] の値を設定します。
     *
     * フィールドの説明: [遷移後の参照に一貫性を期待]。
     *
     * @param argExpectConsistentAfterTransition フィールド[expectConsistentAfterTransition]に設定する値。
     */
    public void setExpectConsistentAfterTransition(final Boolean argExpectConsistentAfterTransition) {
        fExpectConsistentAfterTransition = argExpectConsistentAfterTransition;
    }

    /**
     * フィールド [expectConsistentAfterTransition] の値を取得します。
     *
     * フィールドの説明: [遷移後の参照に一貫性を期待]。
     * デフォルト: [false]。
     *
     * @return フィールド[expectConsistentAfterTransition]から取得した値。
     */
    public Boolean getExpectConsistentAfterTransition() {
        return fExpectConsistentAfterTransition;
    }

    /**
     * フィールド [alias] の値を設定します。
     *
     * フィールドの説明: [コンポーネントの別名を定義します。]。
     *
     * @param argAlias フィールド[alias]に設定する値。
     */
    public void setAlias(final String argAlias) {
        fAlias = argAlias;
    }

    /**
     * フィールド [alias] の値を取得します。
     *
     * フィールドの説明: [コンポーネントの別名を定義します。]。
     *
     * @return フィールド[alias]から取得した値。
     */
    public String getAlias() {
        return fAlias;
    }

    /**
     * フィールド [useSetup] の値を設定します。
     *
     * フィールドの説明: [setup関数を実装するかどうか]。
     *
     * @param argUseSetup フィールド[useSetup]に設定する値。
     */
    public void setUseSetup(final Boolean argUseSetup) {
        fUseSetup = argUseSetup;
    }

    /**
     * フィールド [useSetup] の値を取得します。
     *
     * フィールドの説明: [setup関数を実装するかどうか]。
     * デフォルト: [false]。
     *
     * @return フィールド[useSetup]から取得した値。
     */
    public Boolean getUseSetup() {
        return fUseSetup;
    }

    /**
     * フィールド [useData] の値を設定します。
     *
     * フィールドの説明: [data関数を実装するかどうか]。
     *
     * @param argUseData フィールド[useData]に設定する値。
     */
    public void setUseData(final Boolean argUseData) {
        fUseData = argUseData;
    }

    /**
     * フィールド [useData] の値を取得します。
     *
     * フィールドの説明: [data関数を実装するかどうか]。
     * デフォルト: [false]。
     *
     * @return フィールド[useData]から取得した値。
     */
    public Boolean getUseData() {
        return fUseData;
    }

    /**
     * フィールド [beforeRouterLeave] の値を設定します。
     *
     * フィールドの説明: [BeforeRouterLeave関数名を定義します。]。
     *
     * @param argBeforeRouterLeave フィールド[beforeRouterLeave]に設定する値。
     */
    public void setBeforeRouterLeave(final String argBeforeRouterLeave) {
        fBeforeRouterLeave = argBeforeRouterLeave;
    }

    /**
     * フィールド [beforeRouterLeave] の値を取得します。
     *
     * フィールドの説明: [BeforeRouterLeave関数名を定義します。]。
     * デフォルト: [&quot;&quot;]。
     *
     * @return フィールド[beforeRouterLeave]から取得した値。
     */
    public String getBeforeRouterLeave() {
        return fBeforeRouterLeave;
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
        buf.append("blanco.vuecomponent.valueobject.BlancoVueComponentClassStructure[");
        buf.append("name=" + fName);
        buf.append(",package=" + fPackage);
        buf.append(",classAlias=" + fClassAlias);
        buf.append(",routerPath=" + fRouterPath);
        buf.append(",routerName=" + fRouterName);
        buf.append(",basedir=" + fBasedir);
        buf.append(",impledir=" + fImpledir);
        buf.append(",description=" + fDescription);
        buf.append(",descriptionList=" + fDescriptionList);
        buf.append(",componentHeaderList=" + fComponentHeaderList);
        buf.append(",apiHeaderList=" + fApiHeaderList);
        buf.append(",propsHeaderList=" + fPropsHeaderList);
        buf.append(",emitsHeaderList=" + fEmitsHeaderList);
        buf.append(",componentKind=" + fComponentKind);
        buf.append(",authRequired=" + fAuthRequired);
        buf.append(",forceReload=" + fForceReload);
        buf.append(",useTemplate=" + fUseTemplate);
        buf.append(",useScript=" + fUseScript);
        buf.append(",useStyle=" + fUseStyle);
        buf.append(",adjustFieldName=" + fAdjustFieldName);
        buf.append(",adjustDefaultValue=" + fAdjustDefaultValue);
        buf.append(",createImportList=" + fCreateImportList);
        buf.append(",extends=" + fExtends);
        buf.append(",componentList=" + fComponentList);
        buf.append(",propsList=" + fPropsList);
        buf.append(",apiList=" + fApiList);
        buf.append(",emitsList=" + fEmitsList);
        buf.append(",fileDescription=" + fFileDescription);
        buf.append(",constructorArgList=" + fConstructorArgList);
        buf.append(",subject=" + fSubject);
        buf.append(",expectConsistentAfterTransition=" + fExpectConsistentAfterTransition);
        buf.append(",alias=" + fAlias);
        buf.append(",useSetup=" + fUseSetup);
        buf.append(",useData=" + fUseData);
        buf.append(",beforeRouterLeave=" + fBeforeRouterLeave);
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
    public void copyTo(final BlancoVueComponentClassStructure target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: BlancoVueComponentClassStructure#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fName
        // Type: java.lang.String
        target.fName = this.fName;
        // Name: fPackage
        // Type: java.lang.String
        target.fPackage = this.fPackage;
        // Name: fClassAlias
        // Type: java.lang.String
        target.fClassAlias = this.fClassAlias;
        // Name: fRouterPath
        // Type: java.lang.String
        target.fRouterPath = this.fRouterPath;
        // Name: fRouterName
        // Type: java.lang.String
        target.fRouterName = this.fRouterName;
        // Name: fBasedir
        // Type: java.lang.String
        target.fBasedir = this.fBasedir;
        // Name: fImpledir
        // Type: java.lang.String
        target.fImpledir = this.fImpledir;
        // Name: fDescription
        // Type: java.lang.String
        target.fDescription = this.fDescription;
        // Name: fDescriptionList
        // Type: java.util.List
        // Field[fDescriptionList] is an unsupported type[java.util.Listjava.lang.String].
        // Name: fComponentHeaderList
        // Type: java.util.List
        // Field[fComponentHeaderList] is an unsupported type[java.util.Listjava.lang.String].
        // Name: fApiHeaderList
        // Type: java.util.List
        // Field[fApiHeaderList] is an unsupported type[java.util.Listjava.lang.String].
        // Name: fPropsHeaderList
        // Type: java.util.List
        // Field[fPropsHeaderList] is an unsupported type[java.util.Listjava.lang.String].
        // Name: fEmitsHeaderList
        // Type: java.util.List
        // Field[fEmitsHeaderList] is an unsupported type[java.util.Listjava.lang.String].
        // Name: fComponentKind
        // Type: java.lang.String
        target.fComponentKind = this.fComponentKind;
        // Name: fAuthRequired
        // Type: java.lang.Boolean
        target.fAuthRequired = this.fAuthRequired;
        // Name: fForceReload
        // Type: java.lang.Boolean
        target.fForceReload = this.fForceReload;
        // Name: fUseTemplate
        // Type: java.lang.Boolean
        target.fUseTemplate = this.fUseTemplate;
        // Name: fUseScript
        // Type: java.lang.Boolean
        target.fUseScript = this.fUseScript;
        // Name: fUseStyle
        // Type: java.lang.Boolean
        target.fUseStyle = this.fUseStyle;
        // Name: fAdjustFieldName
        // Type: java.lang.Boolean
        target.fAdjustFieldName = this.fAdjustFieldName;
        // Name: fAdjustDefaultValue
        // Type: java.lang.Boolean
        target.fAdjustDefaultValue = this.fAdjustDefaultValue;
        // Name: fCreateImportList
        // Type: java.lang.Boolean
        target.fCreateImportList = this.fCreateImportList;
        // Name: fExtends
        // Type: java.lang.String
        target.fExtends = this.fExtends;
        // Name: fComponentList
        // Type: java.util.List
        // Field[fComponentList] is an unsupported type[java.util.Listjava.lang.String].
        // Name: fPropsList
        // Type: java.util.List
        // Field[fPropsList] is an unsupported type[java.util.Listblanco.vuecomponent.valueobject.BlancoVueComponentPropsStructure].
        // Name: fApiList
        // Type: java.util.List
        // Field[fApiList] is an unsupported type[java.util.Listblanco.vuecomponent.valueobject.BlancoVueComponentApiStructure].
        // Name: fEmitsList
        // Type: java.util.List
        // Field[fEmitsList] is an unsupported type[java.util.Listblanco.vuecomponent.valueobject.BlancoVueComponentEmitsStructure].
        // Name: fFileDescription
        // Type: java.lang.String
        target.fFileDescription = this.fFileDescription;
        // Name: fConstructorArgList
        // Type: java.util.List
        // Field[fConstructorArgList] is an unsupported type[java.util.Listblanco.cg.valueobject.BlancoCgField].
        // Name: fSubject
        // Type: java.lang.String
        target.fSubject = this.fSubject;
        // Name: fExpectConsistentAfterTransition
        // Type: java.lang.Boolean
        target.fExpectConsistentAfterTransition = this.fExpectConsistentAfterTransition;
        // Name: fAlias
        // Type: java.lang.String
        target.fAlias = this.fAlias;
        // Name: fUseSetup
        // Type: java.lang.Boolean
        target.fUseSetup = this.fUseSetup;
        // Name: fUseData
        // Type: java.lang.Boolean
        target.fUseData = this.fUseData;
        // Name: fBeforeRouterLeave
        // Type: java.lang.String
        target.fBeforeRouterLeave = this.fBeforeRouterLeave;
    }
}
