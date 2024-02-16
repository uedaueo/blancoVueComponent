package blanco.vuecomponent.valueobject;

/**
 * バリューオブジェクトのフィールドをあらわすバリューオブジェクトクラス。このクラスの設定情報をもとにフィールドとセッター・ゲッターが自動生成されます。
 */
public class BlancoVueComponentBreadCrumbStructure {
    /**
     * 項目番号。省略可能です。
     *
     * フィールド: [no]。
     */
    private String fNo;

    /**
     * componentIDを指定します。必須項目です。
     *
     * フィールド: [name]。
     */
    private String fName;

    /**
     * リンク指定の無いパンくずの場合に true
     *
     * フィールド: [nolink]。
     * デフォルト: [false]。
     */
    private Boolean fNolink = false;

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
     * フィールドの説明: [componentIDを指定します。必須項目です。]。
     *
     * @param argName フィールド[name]に設定する値。
     */
    public void setName(final String argName) {
        fName = argName;
    }

    /**
     * フィールド [name] の値を取得します。
     *
     * フィールドの説明: [componentIDを指定します。必須項目です。]。
     *
     * @return フィールド[name]から取得した値。
     */
    public String getName() {
        return fName;
    }

    /**
     * フィールド [nolink] の値を設定します。
     *
     * フィールドの説明: [リンク指定の無いパンくずの場合に true]。
     *
     * @param argNolink フィールド[nolink]に設定する値。
     */
    public void setNolink(final Boolean argNolink) {
        fNolink = argNolink;
    }

    /**
     * フィールド [nolink] の値を取得します。
     *
     * フィールドの説明: [リンク指定の無いパンくずの場合に true]。
     * デフォルト: [false]。
     *
     * @return フィールド[nolink]から取得した値。
     */
    public Boolean getNolink() {
        return fNolink;
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
        buf.append("blanco.vuecomponent.valueobject.BlancoVueComponentBreadCrumbStructure[");
        buf.append("no=" + fNo);
        buf.append(",name=" + fName);
        buf.append(",nolink=" + fNolink);
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
    public void copyTo(final BlancoVueComponentBreadCrumbStructure target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: BlancoVueComponentBreadCrumbStructure#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fNo
        // Type: java.lang.String
        target.fNo = this.fNo;
        // Name: fName
        // Type: java.lang.String
        target.fName = this.fName;
        // Name: fNolink
        // Type: java.lang.Boolean
        target.fNolink = this.fNolink;
    }
}
