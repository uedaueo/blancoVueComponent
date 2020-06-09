package blanco.vuecomponent.valueobject;

import java.util.List;

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
     * 本番時にファイルを配置する歳のベースディレクトリ。主にTypeScriptのimport文生成時に使用する事を想定しています。
     *
     * フィールド: [basedir]。
     */
    private String fBasedir;

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
    private List<java.lang.String> fDescriptionList = new java.util.ArrayList<java.lang.String>();

    /**
     * クラスのアノテーションを指定します。
     *
     * フィールド: [annotationList]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     */
    private List<java.lang.String> fAnnotationList = new java.util.ArrayList<java.lang.String>();

    /**
     * importを指定します。
     *
     * フィールド: [importList]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     */
    private List<java.lang.String> fImportList = new java.util.ArrayList<java.lang.String>();

    /**
     * source コードの先頭に書かれるコード群です。
     *
     * フィールド: [headerList]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     */
    private List<java.lang.String> fHeaderList = new java.util.ArrayList<java.lang.String>();

    /**
     * クラスのアクセス。通常は public。
     *
     * フィールド: [access]。
     * デフォルト: [&quot;public&quot;]。
     */
    private String fAccess = "public";

    /**
     * dataクラスかどうか。
     *
     * フィールド: [data]。
     * デフォルト: [true]。
     */
    private Boolean fData = true;

    /**
     * クラスが拡張可能かどうか。kotlin では通常は true。
     *
     * フィールド: [final]。
     * デフォルト: [true]。
     */
    private Boolean fFinal = true;

    /**
     * 抽象クラスかどうか。通常は false。
     *
     * フィールド: [abstract]。
     * デフォルト: [false]。
     */
    private Boolean fAbstract = false;

    /**
     * インタフェイスかどうか。通常は false。
     *
     * フィールド: [interface]。
     * デフォルト: [false]。
     */
    private Boolean fInterface = false;

    /**
     * toStringメソッドを生成するかどうか。
     *
     * フィールド: [generateToString]。
     * デフォルト: [true]。
     */
    private Boolean fGenerateToString = true;

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
     * デフォルト: [true]。
     */
    private Boolean fAdjustDefaultValue = true;

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
     * 実装するインタフェース(java.lang.String)の一覧。
     *
     * フィールド: [implementsList]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     */
    private List<java.lang.String> fImplementsList = new java.util.ArrayList<java.lang.String>();

    /**
     * フィールドを記憶するリストを指定します。
     *
     * フィールド: [fieldList]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.vuecomponent.valueobject.BlancoVueComponentFieldStructure&gt;()]。
     */
    private List<blanco.vuecomponent.valueobject.BlancoVueComponentFieldStructure> fFieldList = new java.util.ArrayList<blanco.vuecomponent.valueobject.BlancoVueComponentFieldStructure>();

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
    private List<blanco.cg.valueobject.BlancoCgField> fConstructorArgList = new java.util.ArrayList<blanco.cg.valueobject.BlancoCgField>();

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
     * フィールド [basedir] の値を設定します。
     *
     * フィールドの説明: [本番時にファイルを配置する歳のベースディレクトリ。主にTypeScriptのimport文生成時に使用する事を想定しています。]。
     *
     * @param argBasedir フィールド[basedir]に設定する値。
     */
    public void setBasedir(final String argBasedir) {
        fBasedir = argBasedir;
    }

    /**
     * フィールド [basedir] の値を取得します。
     *
     * フィールドの説明: [本番時にファイルを配置する歳のベースディレクトリ。主にTypeScriptのimport文生成時に使用する事を想定しています。]。
     *
     * @return フィールド[basedir]から取得した値。
     */
    public String getBasedir() {
        return fBasedir;
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
    public void setDescriptionList(final List<java.lang.String> argDescriptionList) {
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
    public List<java.lang.String> getDescriptionList() {
        return fDescriptionList;
    }

    /**
     * フィールド [annotationList] の値を設定します。
     *
     * フィールドの説明: [クラスのアノテーションを指定します。]。
     *
     * @param argAnnotationList フィールド[annotationList]に設定する値。
     */
    public void setAnnotationList(final List<java.lang.String> argAnnotationList) {
        fAnnotationList = argAnnotationList;
    }

    /**
     * フィールド [annotationList] の値を取得します。
     *
     * フィールドの説明: [クラスのアノテーションを指定します。]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     *
     * @return フィールド[annotationList]から取得した値。
     */
    public List<java.lang.String> getAnnotationList() {
        return fAnnotationList;
    }

    /**
     * フィールド [importList] の値を設定します。
     *
     * フィールドの説明: [importを指定します。]。
     *
     * @param argImportList フィールド[importList]に設定する値。
     */
    public void setImportList(final List<java.lang.String> argImportList) {
        fImportList = argImportList;
    }

    /**
     * フィールド [importList] の値を取得します。
     *
     * フィールドの説明: [importを指定します。]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     *
     * @return フィールド[importList]から取得した値。
     */
    public List<java.lang.String> getImportList() {
        return fImportList;
    }

    /**
     * フィールド [headerList] の値を設定します。
     *
     * フィールドの説明: [source コードの先頭に書かれるコード群です。]。
     *
     * @param argHeaderList フィールド[headerList]に設定する値。
     */
    public void setHeaderList(final List<java.lang.String> argHeaderList) {
        fHeaderList = argHeaderList;
    }

    /**
     * フィールド [headerList] の値を取得します。
     *
     * フィールドの説明: [source コードの先頭に書かれるコード群です。]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     *
     * @return フィールド[headerList]から取得した値。
     */
    public List<java.lang.String> getHeaderList() {
        return fHeaderList;
    }

    /**
     * フィールド [access] の値を設定します。
     *
     * フィールドの説明: [クラスのアクセス。通常は public。]。
     *
     * @param argAccess フィールド[access]に設定する値。
     */
    public void setAccess(final String argAccess) {
        fAccess = argAccess;
    }

    /**
     * フィールド [access] の値を取得します。
     *
     * フィールドの説明: [クラスのアクセス。通常は public。]。
     * デフォルト: [&quot;public&quot;]。
     *
     * @return フィールド[access]から取得した値。
     */
    public String getAccess() {
        return fAccess;
    }

    /**
     * フィールド [data] の値を設定します。
     *
     * フィールドの説明: [dataクラスかどうか。]。
     *
     * @param argData フィールド[data]に設定する値。
     */
    public void setData(final Boolean argData) {
        fData = argData;
    }

    /**
     * フィールド [data] の値を取得します。
     *
     * フィールドの説明: [dataクラスかどうか。]。
     * デフォルト: [true]。
     *
     * @return フィールド[data]から取得した値。
     */
    public Boolean getData() {
        return fData;
    }

    /**
     * フィールド [final] の値を設定します。
     *
     * フィールドの説明: [クラスが拡張可能かどうか。kotlin では通常は true。]。
     *
     * @param argFinal フィールド[final]に設定する値。
     */
    public void setFinal(final Boolean argFinal) {
        fFinal = argFinal;
    }

    /**
     * フィールド [final] の値を取得します。
     *
     * フィールドの説明: [クラスが拡張可能かどうか。kotlin では通常は true。]。
     * デフォルト: [true]。
     *
     * @return フィールド[final]から取得した値。
     */
    public Boolean getFinal() {
        return fFinal;
    }

    /**
     * フィールド [abstract] の値を設定します。
     *
     * フィールドの説明: [抽象クラスかどうか。通常は false。]。
     *
     * @param argAbstract フィールド[abstract]に設定する値。
     */
    public void setAbstract(final Boolean argAbstract) {
        fAbstract = argAbstract;
    }

    /**
     * フィールド [abstract] の値を取得します。
     *
     * フィールドの説明: [抽象クラスかどうか。通常は false。]。
     * デフォルト: [false]。
     *
     * @return フィールド[abstract]から取得した値。
     */
    public Boolean getAbstract() {
        return fAbstract;
    }

    /**
     * フィールド [interface] の値を設定します。
     *
     * フィールドの説明: [インタフェイスかどうか。通常は false。]。
     *
     * @param argInterface フィールド[interface]に設定する値。
     */
    public void setInterface(final Boolean argInterface) {
        fInterface = argInterface;
    }

    /**
     * フィールド [interface] の値を取得します。
     *
     * フィールドの説明: [インタフェイスかどうか。通常は false。]。
     * デフォルト: [false]。
     *
     * @return フィールド[interface]から取得した値。
     */
    public Boolean getInterface() {
        return fInterface;
    }

    /**
     * フィールド [generateToString] の値を設定します。
     *
     * フィールドの説明: [toStringメソッドを生成するかどうか。]。
     *
     * @param argGenerateToString フィールド[generateToString]に設定する値。
     */
    public void setGenerateToString(final Boolean argGenerateToString) {
        fGenerateToString = argGenerateToString;
    }

    /**
     * フィールド [generateToString] の値を取得します。
     *
     * フィールドの説明: [toStringメソッドを生成するかどうか。]。
     * デフォルト: [true]。
     *
     * @return フィールド[generateToString]から取得した値。
     */
    public Boolean getGenerateToString() {
        return fGenerateToString;
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
     * デフォルト: [true]。
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
     * フィールド [implementsList] の値を設定します。
     *
     * フィールドの説明: [実装するインタフェース(java.lang.String)の一覧。]。
     *
     * @param argImplementsList フィールド[implementsList]に設定する値。
     */
    public void setImplementsList(final List<java.lang.String> argImplementsList) {
        fImplementsList = argImplementsList;
    }

    /**
     * フィールド [implementsList] の値を取得します。
     *
     * フィールドの説明: [実装するインタフェース(java.lang.String)の一覧。]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     *
     * @return フィールド[implementsList]から取得した値。
     */
    public List<java.lang.String> getImplementsList() {
        return fImplementsList;
    }

    /**
     * フィールド [fieldList] の値を設定します。
     *
     * フィールドの説明: [フィールドを記憶するリストを指定します。]。
     *
     * @param argFieldList フィールド[fieldList]に設定する値。
     */
    public void setFieldList(final List<blanco.vuecomponent.valueobject.BlancoVueComponentFieldStructure> argFieldList) {
        fFieldList = argFieldList;
    }

    /**
     * フィールド [fieldList] の値を取得します。
     *
     * フィールドの説明: [フィールドを記憶するリストを指定します。]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.vuecomponent.valueobject.BlancoVueComponentFieldStructure&gt;()]。
     *
     * @return フィールド[fieldList]から取得した値。
     */
    public List<blanco.vuecomponent.valueobject.BlancoVueComponentFieldStructure> getFieldList() {
        return fFieldList;
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
    public void setConstructorArgList(final List<blanco.cg.valueobject.BlancoCgField> argConstructorArgList) {
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
    public List<blanco.cg.valueobject.BlancoCgField> getConstructorArgList() {
        return fConstructorArgList;
    }

    /**
     * このバリューオブジェクトの文字列表現を取得します。
     *
     * <P>使用上の注意</P>
     * <UL>
     * <LI>オブジェクトのシャロー範囲のみ文字列化の処理対象となります。
     * <LI>オブジェクトが循環参照している場合には、このメソッドは使わないでください。
     * </UL>
     *
     * @return バリューオブジェクトの文字列表現。
     */
    @Override
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("blanco.vuecomponent.valueobject.BlancoVueComponentClassStructure[");
        buf.append("name=" + fName);
        buf.append(",package=" + fPackage);
        buf.append(",classAlias=" + fClassAlias);
        buf.append(",basedir=" + fBasedir);
        buf.append(",description=" + fDescription);
        buf.append(",descriptionList=" + fDescriptionList);
        buf.append(",annotationList=" + fAnnotationList);
        buf.append(",importList=" + fImportList);
        buf.append(",headerList=" + fHeaderList);
        buf.append(",access=" + fAccess);
        buf.append(",data=" + fData);
        buf.append(",final=" + fFinal);
        buf.append(",abstract=" + fAbstract);
        buf.append(",interface=" + fInterface);
        buf.append(",generateToString=" + fGenerateToString);
        buf.append(",adjustFieldName=" + fAdjustFieldName);
        buf.append(",adjustDefaultValue=" + fAdjustDefaultValue);
        buf.append(",createImportList=" + fCreateImportList);
        buf.append(",extends=" + fExtends);
        buf.append(",implementsList=" + fImplementsList);
        buf.append(",fieldList=" + fFieldList);
        buf.append(",fileDescription=" + fFileDescription);
        buf.append(",constructorArgList=" + fConstructorArgList);
        buf.append("]");
        return buf.toString();
    }

    /**
     * このバリューオブジェクトを指定のターゲットに複写します。
     *
     * <P>使用上の注意</P>
     * <UL>
     * <LI>オブジェクトのシャロー範囲のみ複写処理対象となります。
     * <LI>オブジェクトが循環参照している場合には、このメソッドは使わないでください。
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
        // Name: fBasedir
        // Type: java.lang.String
        target.fBasedir = this.fBasedir;
        // Name: fDescription
        // Type: java.lang.String
        target.fDescription = this.fDescription;
        // Name: fDescriptionList
        // Type: java.util.List
        if (this.fDescriptionList != null) {
            final java.util.Iterator<java.lang.String> iterator = this.fDescriptionList.iterator();
            for (; iterator.hasNext();) {
                java.lang.String loopSource = iterator.next();
                java.lang.String loopTarget = null;
                loopTarget = loopSource;
                target.fDescriptionList.add(loopTarget);
            }
        }
        // Name: fAnnotationList
        // Type: java.util.List
        if (this.fAnnotationList != null) {
            final java.util.Iterator<java.lang.String> iterator = this.fAnnotationList.iterator();
            for (; iterator.hasNext();) {
                java.lang.String loopSource = iterator.next();
                java.lang.String loopTarget = null;
                loopTarget = loopSource;
                target.fAnnotationList.add(loopTarget);
            }
        }
        // Name: fImportList
        // Type: java.util.List
        if (this.fImportList != null) {
            final java.util.Iterator<java.lang.String> iterator = this.fImportList.iterator();
            for (; iterator.hasNext();) {
                java.lang.String loopSource = iterator.next();
                java.lang.String loopTarget = null;
                loopTarget = loopSource;
                target.fImportList.add(loopTarget);
            }
        }
        // Name: fHeaderList
        // Type: java.util.List
        if (this.fHeaderList != null) {
            final java.util.Iterator<java.lang.String> iterator = this.fHeaderList.iterator();
            for (; iterator.hasNext();) {
                java.lang.String loopSource = iterator.next();
                java.lang.String loopTarget = null;
                loopTarget = loopSource;
                target.fHeaderList.add(loopTarget);
            }
        }
        // Name: fAccess
        // Type: java.lang.String
        target.fAccess = this.fAccess;
        // Name: fData
        // Type: java.lang.Boolean
        target.fData = this.fData;
        // Name: fFinal
        // Type: java.lang.Boolean
        target.fFinal = this.fFinal;
        // Name: fAbstract
        // Type: java.lang.Boolean
        target.fAbstract = this.fAbstract;
        // Name: fInterface
        // Type: java.lang.Boolean
        target.fInterface = this.fInterface;
        // Name: fGenerateToString
        // Type: java.lang.Boolean
        target.fGenerateToString = this.fGenerateToString;
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
        // Name: fImplementsList
        // Type: java.util.List
        if (this.fImplementsList != null) {
            final java.util.Iterator<java.lang.String> iterator = this.fImplementsList.iterator();
            for (; iterator.hasNext();) {
                java.lang.String loopSource = iterator.next();
                java.lang.String loopTarget = null;
                loopTarget = loopSource;
                target.fImplementsList.add(loopTarget);
            }
        }
        // Name: fFieldList
        // Type: java.util.List
        if (this.fFieldList != null) {
            final java.util.Iterator<blanco.vuecomponent.valueobject.BlancoVueComponentFieldStructure> iterator = this.fFieldList.iterator();
            for (; iterator.hasNext();) {
                blanco.vuecomponent.valueobject.BlancoVueComponentFieldStructure loopSource = iterator.next();
                blanco.vuecomponent.valueobject.BlancoVueComponentFieldStructure loopTarget = null;
                // フィールド[generics]はサポート外の型[blanco.vuecomponent.valueobject.BlancoVueComponentFieldStructure]です。
                target.fFieldList.add(loopTarget);
            }
        }
        // Name: fFileDescription
        // Type: java.lang.String
        target.fFileDescription = this.fFileDescription;
        // Name: fConstructorArgList
        // Type: java.util.List
        if (this.fConstructorArgList != null) {
            final java.util.Iterator<blanco.cg.valueobject.BlancoCgField> iterator = this.fConstructorArgList.iterator();
            for (; iterator.hasNext();) {
                blanco.cg.valueobject.BlancoCgField loopSource = iterator.next();
                blanco.cg.valueobject.BlancoCgField loopTarget = null;
                // フィールド[generics]はサポート外の型[blanco.cg.valueobject.BlancoCgField]です。
                target.fConstructorArgList.add(loopTarget);
            }
        }
    }
}
