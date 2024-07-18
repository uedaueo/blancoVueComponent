package blanco.vuecomponent.valueobject;

import java.util.List;

/**
 * バリューオブジェクトのフィールドをあらわすバリューオブジェクトクラス。このクラスの設定情報をもとにフィールドとセッター・ゲッターが自動生成されます。
 */
public class BlancoVueComponentPropsStructure {
    /**
     * 項目番号。省略可能です。
     *
     * フィールド: [no]。
     */
    private String fNo;

    /**
     * フィールド名を指定します。必須項目です。
     *
     * フィールド: [name]。
     */
    private String fName;

    /**
     * 型名をパッケージ名のフル修飾付で指定します。必須項目です。
     *
     * フィールド: [type]。
     */
    private String fType;

    /**
     * 型が期待する総称型の具体的な型名を指定します．
     *
     * フィールド: [generic]。
     */
    private String fGeneric;

    /**
     * アノテーション文字列です（＠は除く）
     *
     * フィールド: [annotationList]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     */
    private List<String> fAnnotationList = new java.util.ArrayList<java.lang.String>();

    /**
     * デフォルト値を指定します。
     *
     * フィールド: [default]。
     */
    private String fDefault;

    /**
     * abstract変数かどうか
     *
     * フィールド: [abstract]。
     * デフォルト: [false]。
     */
    private Boolean fAbstract = false;

    /**
     * nullable変数かどうか
     *
     * フィールド: [nullable]。
     * デフォルト: [false]。
     */
    private Boolean fNullable = false;

    /**
     * 変更不可変数かどうか
     *
     * フィールド: [value]。
     * デフォルト: [false]。
     */
    private Boolean fValue = false;

    /**
     * コンストラクタ引数かどう
     *
     * フィールド: [constArg]。
     * デフォルト: [false]。
     */
    private Boolean fConstArg = false;

    /**
     * フィールドの説明です。
     *
     * フィールド: [description]。
     */
    private String fDescription;

    /**
     * フィールドの補助説明です。文字参照エンコード済みの値を格納してください。
     *
     * フィールド: [descriptionList]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     */
    private List<String> fDescriptionList = new java.util.ArrayList<java.lang.String>();

    /**
     * (JavaDocへの反映のみ)正規表現を指定します。
     *
     * フィールド: [pattern]。
     */
    private String fPattern;

    /**
     * (JavaDocへの反映のみ)最小の長さを指定します。
     *
     * フィールド: [minLength]。
     */
    private String fMinLength;

    /**
     * (JavaDocへの反映のみ)最大の長さを指定します。
     *
     * フィールド: [maxLength]。
     */
    private String fMaxLength;

    /**
     * (JavaDocへの反映のみ)長さを指定します。
     *
     * フィールド: [length]。
     */
    private String fLength;

    /**
     * (JavaDocへの反映のみ)最小値を指定します。
     *
     * フィールド: [minInclusive]。
     */
    private String fMinInclusive;

    /**
     * (JavaDocへの反映のみ)最大値を指定します。
     *
     * フィールド: [maxInclusive]。
     */
    private String fMaxInclusive;

    /**
     * 必須プロパティかどうか
     *
     * フィールド: [required]。
     * デフォルト: [false]。
     */
    private Boolean fRequired = false;

    /**
     * 対応するパス文字列パラメータを指定します。
     *
     * フィールド: [pathParam]。
     * デフォルト: [&quot;&quot;]。
     */
    private String fPathParam = "";

    /**
     * 対応するクエリ文字列パラメータを指定します。
     *
     * フィールド: [queryParam]。
     * デフォルト: [&quot;&quot;]。
     */
    private String fQueryParam = "";

    /**
     * フィールド [no] の値を設定します。
     *
     * フィールドの説明: [項目番号。省略可能です。]。
     *
     * @param argNo フィールド[no]に設定する値。
     */
    public void setNo(final String argNo) {
        fNo = argNo;
    }

    /**
     * フィールド [no] の値を取得します。
     *
     * フィールドの説明: [項目番号。省略可能です。]。
     *
     * @return フィールド[no]から取得した値。
     */
    public String getNo() {
        return fNo;
    }

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
     * フィールド [type] の値を設定します。
     *
     * フィールドの説明: [型名をパッケージ名のフル修飾付で指定します。必須項目です。]。
     *
     * @param argType フィールド[type]に設定する値。
     */
    public void setType(final String argType) {
        fType = argType;
    }

    /**
     * フィールド [type] の値を取得します。
     *
     * フィールドの説明: [型名をパッケージ名のフル修飾付で指定します。必須項目です。]。
     *
     * @return フィールド[type]から取得した値。
     */
    public String getType() {
        return fType;
    }

    /**
     * フィールド [generic] の値を設定します。
     *
     * フィールドの説明: [型が期待する総称型の具体的な型名を指定します．]。
     *
     * @param argGeneric フィールド[generic]に設定する値。
     */
    public void setGeneric(final String argGeneric) {
        fGeneric = argGeneric;
    }

    /**
     * フィールド [generic] の値を取得します。
     *
     * フィールドの説明: [型が期待する総称型の具体的な型名を指定します．]。
     *
     * @return フィールド[generic]から取得した値。
     */
    public String getGeneric() {
        return fGeneric;
    }

    /**
     * フィールド [annotationList] の値を設定します。
     *
     * フィールドの説明: [アノテーション文字列です（＠は除く）]。
     *
     * @param argAnnotationList フィールド[annotationList]に設定する値。
     */
    public void setAnnotationList(final List<String> argAnnotationList) {
        fAnnotationList = argAnnotationList;
    }

    /**
     * フィールド [annotationList] の値を取得します。
     *
     * フィールドの説明: [アノテーション文字列です（＠は除く）]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     *
     * @return フィールド[annotationList]から取得した値。
     */
    public List<String> getAnnotationList() {
        return fAnnotationList;
    }

    /**
     * フィールド [default] の値を設定します。
     *
     * フィールドの説明: [デフォルト値を指定します。]。
     *
     * @param argDefault フィールド[default]に設定する値。
     */
    public void setDefault(final String argDefault) {
        fDefault = argDefault;
    }

    /**
     * フィールド [default] の値を取得します。
     *
     * フィールドの説明: [デフォルト値を指定します。]。
     *
     * @return フィールド[default]から取得した値。
     */
    public String getDefault() {
        return fDefault;
    }

    /**
     * フィールド [abstract] の値を設定します。
     *
     * フィールドの説明: [abstract変数かどうか]。
     *
     * @param argAbstract フィールド[abstract]に設定する値。
     */
    public void setAbstract(final Boolean argAbstract) {
        fAbstract = argAbstract;
    }

    /**
     * フィールド [abstract] の値を取得します。
     *
     * フィールドの説明: [abstract変数かどうか]。
     * デフォルト: [false]。
     *
     * @return フィールド[abstract]から取得した値。
     */
    public Boolean getAbstract() {
        return fAbstract;
    }

    /**
     * フィールド [nullable] の値を設定します。
     *
     * フィールドの説明: [nullable変数かどうか]。
     *
     * @param argNullable フィールド[nullable]に設定する値。
     */
    public void setNullable(final Boolean argNullable) {
        fNullable = argNullable;
    }

    /**
     * フィールド [nullable] の値を取得します。
     *
     * フィールドの説明: [nullable変数かどうか]。
     * デフォルト: [false]。
     *
     * @return フィールド[nullable]から取得した値。
     */
    public Boolean getNullable() {
        return fNullable;
    }

    /**
     * フィールド [value] の値を設定します。
     *
     * フィールドの説明: [変更不可変数かどうか]。
     *
     * @param argValue フィールド[value]に設定する値。
     */
    public void setValue(final Boolean argValue) {
        fValue = argValue;
    }

    /**
     * フィールド [value] の値を取得します。
     *
     * フィールドの説明: [変更不可変数かどうか]。
     * デフォルト: [false]。
     *
     * @return フィールド[value]から取得した値。
     */
    public Boolean getValue() {
        return fValue;
    }

    /**
     * フィールド [constArg] の値を設定します。
     *
     * フィールドの説明: [コンストラクタ引数かどう]。
     *
     * @param argConstArg フィールド[constArg]に設定する値。
     */
    public void setConstArg(final Boolean argConstArg) {
        fConstArg = argConstArg;
    }

    /**
     * フィールド [constArg] の値を取得します。
     *
     * フィールドの説明: [コンストラクタ引数かどう]。
     * デフォルト: [false]。
     *
     * @return フィールド[constArg]から取得した値。
     */
    public Boolean getConstArg() {
        return fConstArg;
    }

    /**
     * フィールド [description] の値を設定します。
     *
     * フィールドの説明: [フィールドの説明です。]。
     *
     * @param argDescription フィールド[description]に設定する値。
     */
    public void setDescription(final String argDescription) {
        fDescription = argDescription;
    }

    /**
     * フィールド [description] の値を取得します。
     *
     * フィールドの説明: [フィールドの説明です。]。
     *
     * @return フィールド[description]から取得した値。
     */
    public String getDescription() {
        return fDescription;
    }

    /**
     * フィールド [descriptionList] の値を設定します。
     *
     * フィールドの説明: [フィールドの補助説明です。文字参照エンコード済みの値を格納してください。]。
     *
     * @param argDescriptionList フィールド[descriptionList]に設定する値。
     */
    public void setDescriptionList(final List<String> argDescriptionList) {
        fDescriptionList = argDescriptionList;
    }

    /**
     * フィールド [descriptionList] の値を取得します。
     *
     * フィールドの説明: [フィールドの補助説明です。文字参照エンコード済みの値を格納してください。]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     *
     * @return フィールド[descriptionList]から取得した値。
     */
    public List<String> getDescriptionList() {
        return fDescriptionList;
    }

    /**
     * フィールド [pattern] の値を設定します。
     *
     * フィールドの説明: [(JavaDocへの反映のみ)正規表現を指定します。]。
     *
     * @param argPattern フィールド[pattern]に設定する値。
     */
    public void setPattern(final String argPattern) {
        fPattern = argPattern;
    }

    /**
     * フィールド [pattern] の値を取得します。
     *
     * フィールドの説明: [(JavaDocへの反映のみ)正規表現を指定します。]。
     *
     * @return フィールド[pattern]から取得した値。
     */
    public String getPattern() {
        return fPattern;
    }

    /**
     * フィールド [minLength] の値を設定します。
     *
     * フィールドの説明: [(JavaDocへの反映のみ)最小の長さを指定します。]。
     *
     * @param argMinLength フィールド[minLength]に設定する値。
     */
    public void setMinLength(final String argMinLength) {
        fMinLength = argMinLength;
    }

    /**
     * フィールド [minLength] の値を取得します。
     *
     * フィールドの説明: [(JavaDocへの反映のみ)最小の長さを指定します。]。
     *
     * @return フィールド[minLength]から取得した値。
     */
    public String getMinLength() {
        return fMinLength;
    }

    /**
     * フィールド [maxLength] の値を設定します。
     *
     * フィールドの説明: [(JavaDocへの反映のみ)最大の長さを指定します。]。
     *
     * @param argMaxLength フィールド[maxLength]に設定する値。
     */
    public void setMaxLength(final String argMaxLength) {
        fMaxLength = argMaxLength;
    }

    /**
     * フィールド [maxLength] の値を取得します。
     *
     * フィールドの説明: [(JavaDocへの反映のみ)最大の長さを指定します。]。
     *
     * @return フィールド[maxLength]から取得した値。
     */
    public String getMaxLength() {
        return fMaxLength;
    }

    /**
     * フィールド [length] の値を設定します。
     *
     * フィールドの説明: [(JavaDocへの反映のみ)長さを指定します。]。
     *
     * @param argLength フィールド[length]に設定する値。
     */
    public void setLength(final String argLength) {
        fLength = argLength;
    }

    /**
     * フィールド [length] の値を取得します。
     *
     * フィールドの説明: [(JavaDocへの反映のみ)長さを指定します。]。
     *
     * @return フィールド[length]から取得した値。
     */
    public String getLength() {
        return fLength;
    }

    /**
     * フィールド [minInclusive] の値を設定します。
     *
     * フィールドの説明: [(JavaDocへの反映のみ)最小値を指定します。]。
     *
     * @param argMinInclusive フィールド[minInclusive]に設定する値。
     */
    public void setMinInclusive(final String argMinInclusive) {
        fMinInclusive = argMinInclusive;
    }

    /**
     * フィールド [minInclusive] の値を取得します。
     *
     * フィールドの説明: [(JavaDocへの反映のみ)最小値を指定します。]。
     *
     * @return フィールド[minInclusive]から取得した値。
     */
    public String getMinInclusive() {
        return fMinInclusive;
    }

    /**
     * フィールド [maxInclusive] の値を設定します。
     *
     * フィールドの説明: [(JavaDocへの反映のみ)最大値を指定します。]。
     *
     * @param argMaxInclusive フィールド[maxInclusive]に設定する値。
     */
    public void setMaxInclusive(final String argMaxInclusive) {
        fMaxInclusive = argMaxInclusive;
    }

    /**
     * フィールド [maxInclusive] の値を取得します。
     *
     * フィールドの説明: [(JavaDocへの反映のみ)最大値を指定します。]。
     *
     * @return フィールド[maxInclusive]から取得した値。
     */
    public String getMaxInclusive() {
        return fMaxInclusive;
    }

    /**
     * フィールド [required] の値を設定します。
     *
     * フィールドの説明: [必須プロパティかどうか]。
     *
     * @param argRequired フィールド[required]に設定する値。
     */
    public void setRequired(final Boolean argRequired) {
        fRequired = argRequired;
    }

    /**
     * フィールド [required] の値を取得します。
     *
     * フィールドの説明: [必須プロパティかどうか]。
     * デフォルト: [false]。
     *
     * @return フィールド[required]から取得した値。
     */
    public Boolean getRequired() {
        return fRequired;
    }

    /**
     * フィールド [pathParam] の値を設定します。
     *
     * フィールドの説明: [対応するパス文字列パラメータを指定します。]。
     *
     * @param argPathParam フィールド[pathParam]に設定する値。
     */
    public void setPathParam(final String argPathParam) {
        fPathParam = argPathParam;
    }

    /**
     * フィールド [pathParam] の値を取得します。
     *
     * フィールドの説明: [対応するパス文字列パラメータを指定します。]。
     * デフォルト: [&quot;&quot;]。
     *
     * @return フィールド[pathParam]から取得した値。
     */
    public String getPathParam() {
        return fPathParam;
    }

    /**
     * フィールド [queryParam] の値を設定します。
     *
     * フィールドの説明: [対応するクエリ文字列パラメータを指定します。]。
     *
     * @param argQueryParam フィールド[queryParam]に設定する値。
     */
    public void setQueryParam(final String argQueryParam) {
        fQueryParam = argQueryParam;
    }

    /**
     * フィールド [queryParam] の値を取得します。
     *
     * フィールドの説明: [対応するクエリ文字列パラメータを指定します。]。
     * デフォルト: [&quot;&quot;]。
     *
     * @return フィールド[queryParam]から取得した値。
     */
    public String getQueryParam() {
        return fQueryParam;
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
        buf.append("blanco.vuecomponent.valueobject.BlancoVueComponentPropsStructure[");
        buf.append("no=" + fNo);
        buf.append(",name=" + fName);
        buf.append(",type=" + fType);
        buf.append(",generic=" + fGeneric);
        buf.append(",annotationList=" + fAnnotationList);
        buf.append(",default=" + fDefault);
        buf.append(",abstract=" + fAbstract);
        buf.append(",nullable=" + fNullable);
        buf.append(",value=" + fValue);
        buf.append(",constArg=" + fConstArg);
        buf.append(",description=" + fDescription);
        buf.append(",descriptionList=" + fDescriptionList);
        buf.append(",pattern=" + fPattern);
        buf.append(",minLength=" + fMinLength);
        buf.append(",maxLength=" + fMaxLength);
        buf.append(",length=" + fLength);
        buf.append(",minInclusive=" + fMinInclusive);
        buf.append(",maxInclusive=" + fMaxInclusive);
        buf.append(",required=" + fRequired);
        buf.append(",pathParam=" + fPathParam);
        buf.append(",queryParam=" + fQueryParam);
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
    public void copyTo(final BlancoVueComponentPropsStructure target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: BlancoVueComponentPropsStructure#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fNo
        // Type: java.lang.String
        target.fNo = this.fNo;
        // Name: fName
        // Type: java.lang.String
        target.fName = this.fName;
        // Name: fType
        // Type: java.lang.String
        target.fType = this.fType;
        // Name: fGeneric
        // Type: java.lang.String
        target.fGeneric = this.fGeneric;
        // Name: fAnnotationList
        // Type: java.util.List
        // Field[fAnnotationList] is an unsupported type[java.util.Listjava.lang.String].
        // Name: fDefault
        // Type: java.lang.String
        target.fDefault = this.fDefault;
        // Name: fAbstract
        // Type: java.lang.Boolean
        target.fAbstract = this.fAbstract;
        // Name: fNullable
        // Type: java.lang.Boolean
        target.fNullable = this.fNullable;
        // Name: fValue
        // Type: java.lang.Boolean
        target.fValue = this.fValue;
        // Name: fConstArg
        // Type: java.lang.Boolean
        target.fConstArg = this.fConstArg;
        // Name: fDescription
        // Type: java.lang.String
        target.fDescription = this.fDescription;
        // Name: fDescriptionList
        // Type: java.util.List
        // Field[fDescriptionList] is an unsupported type[java.util.Listjava.lang.String].
        // Name: fPattern
        // Type: java.lang.String
        target.fPattern = this.fPattern;
        // Name: fMinLength
        // Type: java.lang.String
        target.fMinLength = this.fMinLength;
        // Name: fMaxLength
        // Type: java.lang.String
        target.fMaxLength = this.fMaxLength;
        // Name: fLength
        // Type: java.lang.String
        target.fLength = this.fLength;
        // Name: fMinInclusive
        // Type: java.lang.String
        target.fMinInclusive = this.fMinInclusive;
        // Name: fMaxInclusive
        // Type: java.lang.String
        target.fMaxInclusive = this.fMaxInclusive;
        // Name: fRequired
        // Type: java.lang.Boolean
        target.fRequired = this.fRequired;
        // Name: fPathParam
        // Type: java.lang.String
        target.fPathParam = this.fPathParam;
        // Name: fQueryParam
        // Type: java.lang.String
        target.fQueryParam = this.fQueryParam;
    }
}
