/*
 * このソースコードは blanco Frameworkにより自動生成されました。
 */
package blanco.sample.valueobject;

import java.math.BigDecimal;
import java.util.List;

/**
 * BlancoValueObjectのなかで利用されるサンプルのValueObjectです。継承と実装のサンプルです。また、フィールド名の変形は行いません。このファイルはblancoValueObjectプログラムそのものの処理には利用されません！単なるサンプルです。
 */
public class BlancoValueObjectSampleStructure extends Thread implements Runnable {
    /**
     * デフォルト値あり
     *
     * フィールド: [stringField1]。
     * デフォルト: [123]。
     */
    private String fstringField1 = "123";

    /**
     * フィールド: [stringField2]。
     */
    private String fstringField2;

    /**
     * デフォルト値あり
     *
     * フィールド: [booleanField1]。
     * デフォルト: [false]。
     */
    private boolean fbooleanField1 = false;

    /**
     * フィールド: [booleanField2]。
     */
    private boolean fbooleanField2;

    /**
     * デフォルト値あり
     *
     * フィールド: [booleanObjField1]。
     * デフォルト: [true]。
     */
    private Boolean fbooleanObjField1 = new Boolean(true);

    /**
     * フィールド: [booleanObjField2]。
     */
    private Boolean fbooleanObjField2;

    /**
     * デフォルト値あり
     *
     * フィールド: [shortField1]。
     * デフォルト: [123]。
     */
    private short fshortField1 = 123;

    /**
     * フィールド: [shortField2]。
     */
    private short fshortField2;

    /**
     * デフォルト値あり
     *
     * フィールド: [shortObjField1]。
     * デフォルト: [3456]。
     */
    private Short fshortObjField1 = new Short((short) 3456);

    /**
     * フィールド: [shortObjField2]。
     */
    private Short fshortObjField2;

    /**
     * デフォルト値あり
     *
     * フィールド: [intField1]。
     * デフォルト: [44444]。
     */
    private int fintField1 = 44444;

    /**
     * フィールド: [intField2]。
     */
    private int fintField2;

    /**
     * デフォルト値あり
     *
     * フィールド: [intObjField1]。
     * デフォルト: [1012]。
     */
    private Integer fintObjField1 = new Integer(1012);

    /**
     * フィールド: [intObjField2]。
     */
    private Integer fintObjField2;

    /**
     * デフォルト値あり
     *
     * フィールド: [longField1]。
     * デフォルト: [1234L]。
     */
    private long flongField1 = 1234L;

    /**
     * フィールド: [longField2]。
     */
    private long flongField2;

    /**
     * デフォルト値あり
     *
     * フィールド: [longObjField1]。
     * デフォルト: [5678L]。
     */
    private Long flongObjField1 = new Long(5678L);

    /**
     * フィールド: [longObjField2]。
     */
    private Long flongObjField2;

    /**
     * デフォルト値あり
     *
     * フィールド: [bigdecimalField1]。
     * デフォルト: [123.456]。
     */
    private BigDecimal fbigdecimalField1 = new BigDecimal("123.456");

    /**
     * nullの試験
     *
     * フィールド: [bigdecimalField2]。
     */
    private BigDecimal fbigdecimalField2;

    /**
     * デフォルト値あり
     *
     * フィールド: [arraylistField1]。
     * デフォルト: [new java.util.ArrayList<java.lang.String>()]。
     */
    private List<java.lang.String> farraylistField1 = new java.util.ArrayList<java.lang.String>();

    /**
     * フィールド: [arraylistField2]。
     */
    private List<java.lang.String> farraylistField2;

    /**
     * 文字列配列の例
     *
     * フィールド: [stringArray1]。
     */
    private String[] fstringArray1;

    /**
     * フィールド [stringField1] の値を設定します。
     *
     * フィールドの説明: [デフォルト値あり]。
     *
     * @param argstringField1 フィールド[stringField1]に設定する値。
     */
    public void setstringField1(final String argstringField1) {
        fstringField1 = argstringField1;
    }

    /**
     * フィールド [stringField1] の値を取得します。
     *
     * フィールドの説明: [デフォルト値あり]。
     * デフォルト: [123]。
     *
     * @return フィールド[stringField1]から取得した値。
     */
    public String getstringField1() {
        return fstringField1;
    }

    /**
     * フィールド [stringField2] の値を設定します。
     *
     * @param argstringField2 フィールド[stringField2]に設定する値。
     */
    public void setstringField2(final String argstringField2) {
        fstringField2 = argstringField2;
    }

    /**
     * フィールド [stringField2] の値を取得します。
     *
     * @return フィールド[stringField2]から取得した値。
     */
    public String getstringField2() {
        return fstringField2;
    }

    /**
     * フィールド [booleanField1] の値を設定します。
     *
     * フィールドの説明: [デフォルト値あり]。
     *
     * @param argbooleanField1 フィールド[booleanField1]に設定する値。
     */
    public void setbooleanField1(final boolean argbooleanField1) {
        fbooleanField1 = argbooleanField1;
    }

    /**
     * フィールド [booleanField1] の値を取得します。
     *
     * フィールドの説明: [デフォルト値あり]。
     * デフォルト: [false]。
     *
     * @return フィールド[booleanField1]から取得した値。
     */
    public boolean getbooleanField1() {
        return fbooleanField1;
    }

    /**
     * フィールド [booleanField2] の値を設定します。
     *
     * @param argbooleanField2 フィールド[booleanField2]に設定する値。
     */
    public void setbooleanField2(final boolean argbooleanField2) {
        fbooleanField2 = argbooleanField2;
    }

    /**
     * フィールド [booleanField2] の値を取得します。
     *
     * @return フィールド[booleanField2]から取得した値。
     */
    public boolean getbooleanField2() {
        return fbooleanField2;
    }

    /**
     * フィールド [booleanObjField1] の値を設定します。
     *
     * フィールドの説明: [デフォルト値あり]。
     *
     * @param argbooleanObjField1 フィールド[booleanObjField1]に設定する値。
     */
    public void setbooleanObjField1(final Boolean argbooleanObjField1) {
        fbooleanObjField1 = argbooleanObjField1;
    }

    /**
     * フィールド [booleanObjField1] の値を取得します。
     *
     * フィールドの説明: [デフォルト値あり]。
     * デフォルト: [true]。
     *
     * @return フィールド[booleanObjField1]から取得した値。
     */
    public Boolean getbooleanObjField1() {
        return fbooleanObjField1;
    }

    /**
     * フィールド [booleanObjField2] の値を設定します。
     *
     * @param argbooleanObjField2 フィールド[booleanObjField2]に設定する値。
     */
    public void setbooleanObjField2(final Boolean argbooleanObjField2) {
        fbooleanObjField2 = argbooleanObjField2;
    }

    /**
     * フィールド [booleanObjField2] の値を取得します。
     *
     * @return フィールド[booleanObjField2]から取得した値。
     */
    public Boolean getbooleanObjField2() {
        return fbooleanObjField2;
    }

    /**
     * フィールド [shortField1] の値を設定します。
     *
     * フィールドの説明: [デフォルト値あり]。
     *
     * @param argshortField1 フィールド[shortField1]に設定する値。
     */
    public void setshortField1(final short argshortField1) {
        fshortField1 = argshortField1;
    }

    /**
     * フィールド [shortField1] の値を取得します。
     *
     * フィールドの説明: [デフォルト値あり]。
     * デフォルト: [123]。
     *
     * @return フィールド[shortField1]から取得した値。
     */
    public short getshortField1() {
        return fshortField1;
    }

    /**
     * フィールド [shortField2] の値を設定します。
     *
     * @param argshortField2 フィールド[shortField2]に設定する値。
     */
    public void setshortField2(final short argshortField2) {
        fshortField2 = argshortField2;
    }

    /**
     * フィールド [shortField2] の値を取得します。
     *
     * @return フィールド[shortField2]から取得した値。
     */
    public short getshortField2() {
        return fshortField2;
    }

    /**
     * フィールド [shortObjField1] の値を設定します。
     *
     * フィールドの説明: [デフォルト値あり]。
     *
     * @param argshortObjField1 フィールド[shortObjField1]に設定する値。
     */
    public void setshortObjField1(final Short argshortObjField1) {
        fshortObjField1 = argshortObjField1;
    }

    /**
     * フィールド [shortObjField1] の値を取得します。
     *
     * フィールドの説明: [デフォルト値あり]。
     * デフォルト: [3456]。
     *
     * @return フィールド[shortObjField1]から取得した値。
     */
    public Short getshortObjField1() {
        return fshortObjField1;
    }

    /**
     * フィールド [shortObjField2] の値を設定します。
     *
     * @param argshortObjField2 フィールド[shortObjField2]に設定する値。
     */
    public void setshortObjField2(final Short argshortObjField2) {
        fshortObjField2 = argshortObjField2;
    }

    /**
     * フィールド [shortObjField2] の値を取得します。
     *
     * @return フィールド[shortObjField2]から取得した値。
     */
    public Short getshortObjField2() {
        return fshortObjField2;
    }

    /**
     * フィールド [intField1] の値を設定します。
     *
     * フィールドの説明: [デフォルト値あり]。
     *
     * @param argintField1 フィールド[intField1]に設定する値。
     */
    public void setintField1(final int argintField1) {
        fintField1 = argintField1;
    }

    /**
     * フィールド [intField1] の値を取得します。
     *
     * フィールドの説明: [デフォルト値あり]。
     * デフォルト: [44444]。
     *
     * @return フィールド[intField1]から取得した値。
     */
    public int getintField1() {
        return fintField1;
    }

    /**
     * フィールド [intField2] の値を設定します。
     *
     * @param argintField2 フィールド[intField2]に設定する値。
     */
    public void setintField2(final int argintField2) {
        fintField2 = argintField2;
    }

    /**
     * フィールド [intField2] の値を取得します。
     *
     * @return フィールド[intField2]から取得した値。
     */
    public int getintField2() {
        return fintField2;
    }

    /**
     * フィールド [intObjField1] の値を設定します。
     *
     * フィールドの説明: [デフォルト値あり]。
     *
     * @param argintObjField1 フィールド[intObjField1]に設定する値。
     */
    public void setintObjField1(final Integer argintObjField1) {
        fintObjField1 = argintObjField1;
    }

    /**
     * フィールド [intObjField1] の値を取得します。
     *
     * フィールドの説明: [デフォルト値あり]。
     * デフォルト: [1012]。
     *
     * @return フィールド[intObjField1]から取得した値。
     */
    public Integer getintObjField1() {
        return fintObjField1;
    }

    /**
     * フィールド [intObjField2] の値を設定します。
     *
     * @param argintObjField2 フィールド[intObjField2]に設定する値。
     */
    public void setintObjField2(final Integer argintObjField2) {
        fintObjField2 = argintObjField2;
    }

    /**
     * フィールド [intObjField2] の値を取得します。
     *
     * @return フィールド[intObjField2]から取得した値。
     */
    public Integer getintObjField2() {
        return fintObjField2;
    }

    /**
     * フィールド [longField1] の値を設定します。
     *
     * フィールドの説明: [デフォルト値あり]。
     *
     * @param arglongField1 フィールド[longField1]に設定する値。
     */
    public void setlongField1(final long arglongField1) {
        flongField1 = arglongField1;
    }

    /**
     * フィールド [longField1] の値を取得します。
     *
     * フィールドの説明: [デフォルト値あり]。
     * デフォルト: [1234L]。
     *
     * @return フィールド[longField1]から取得した値。
     */
    public long getlongField1() {
        return flongField1;
    }

    /**
     * フィールド [longField2] の値を設定します。
     *
     * @param arglongField2 フィールド[longField2]に設定する値。
     */
    public void setlongField2(final long arglongField2) {
        flongField2 = arglongField2;
    }

    /**
     * フィールド [longField2] の値を取得します。
     *
     * @return フィールド[longField2]から取得した値。
     */
    public long getlongField2() {
        return flongField2;
    }

    /**
     * フィールド [longObjField1] の値を設定します。
     *
     * フィールドの説明: [デフォルト値あり]。
     *
     * @param arglongObjField1 フィールド[longObjField1]に設定する値。
     */
    public void setlongObjField1(final Long arglongObjField1) {
        flongObjField1 = arglongObjField1;
    }

    /**
     * フィールド [longObjField1] の値を取得します。
     *
     * フィールドの説明: [デフォルト値あり]。
     * デフォルト: [5678L]。
     *
     * @return フィールド[longObjField1]から取得した値。
     */
    public Long getlongObjField1() {
        return flongObjField1;
    }

    /**
     * フィールド [longObjField2] の値を設定します。
     *
     * @param arglongObjField2 フィールド[longObjField2]に設定する値。
     */
    public void setlongObjField2(final Long arglongObjField2) {
        flongObjField2 = arglongObjField2;
    }

    /**
     * フィールド [longObjField2] の値を取得します。
     *
     * @return フィールド[longObjField2]から取得した値。
     */
    public Long getlongObjField2() {
        return flongObjField2;
    }

    /**
     * フィールド [bigdecimalField1] の値を設定します。
     *
     * フィールドの説明: [デフォルト値あり]。
     *
     * @param argbigdecimalField1 フィールド[bigdecimalField1]に設定する値。
     */
    public void setbigdecimalField1(final BigDecimal argbigdecimalField1) {
        fbigdecimalField1 = argbigdecimalField1;
    }

    /**
     * フィールド [bigdecimalField1] の値を取得します。
     *
     * フィールドの説明: [デフォルト値あり]。
     * デフォルト: [123.456]。
     *
     * @return フィールド[bigdecimalField1]から取得した値。
     */
    public BigDecimal getbigdecimalField1() {
        return fbigdecimalField1;
    }

    /**
     * フィールド [bigdecimalField2] の値を設定します。
     *
     * フィールドの説明: [nullの試験]。
     *
     * @param argbigdecimalField2 フィールド[bigdecimalField2]に設定する値。
     */
    public void setbigdecimalField2(final BigDecimal argbigdecimalField2) {
        fbigdecimalField2 = argbigdecimalField2;
    }

    /**
     * フィールド [bigdecimalField2] の値を取得します。
     *
     * フィールドの説明: [nullの試験]。
     *
     * @return フィールド[bigdecimalField2]から取得した値。
     */
    public BigDecimal getbigdecimalField2() {
        return fbigdecimalField2;
    }

    /**
     * フィールド [arraylistField1] の値を設定します。
     *
     * フィールドの説明: [デフォルト値あり]。
     *
     * @param argarraylistField1 フィールド[arraylistField1]に設定する値。
     */
    public void setarraylistField1(final List<java.lang.String> argarraylistField1) {
        farraylistField1 = argarraylistField1;
    }

    /**
     * フィールド [arraylistField1] の値を取得します。
     *
     * フィールドの説明: [デフォルト値あり]。
     * デフォルト: [new java.util.ArrayList<java.lang.String>()]。
     *
     * @return フィールド[arraylistField1]から取得した値。
     */
    public List<java.lang.String> getarraylistField1() {
        return farraylistField1;
    }

    /**
     * フィールド [arraylistField2] の値を設定します。
     *
     * @param argarraylistField2 フィールド[arraylistField2]に設定する値。
     */
    public void setarraylistField2(final List<java.lang.String> argarraylistField2) {
        farraylistField2 = argarraylistField2;
    }

    /**
     * フィールド [arraylistField2] の値を取得します。
     *
     * @return フィールド[arraylistField2]から取得した値。
     */
    public List<java.lang.String> getarraylistField2() {
        return farraylistField2;
    }

    /**
     * フィールド [stringArray1] の値を設定します。
     *
     * フィールドの説明: [文字列配列の例]。
     *
     * @param argstringArray1 フィールド[stringArray1]に設定する値。
     */
    public void setstringArray1(final String[] argstringArray1) {
        fstringArray1 = argstringArray1;
    }

    /**
     * フィールド [stringArray1] の値を取得します。
     *
     * フィールドの説明: [文字列配列の例]。
     *
     * @return フィールド[stringArray1]から取得した値。
     */
    public String[] getstringArray1() {
        return fstringArray1;
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
        buf.append("blanco.sample.valueobject.BlancoValueObjectSampleStructure[");
        buf.append("stringField1=" + fstringField1);
        buf.append(",stringField2=" + fstringField2);
        buf.append(",booleanField1=" + fbooleanField1);
        buf.append(",booleanField2=" + fbooleanField2);
        buf.append(",booleanObjField1=" + fbooleanObjField1);
        buf.append(",booleanObjField2=" + fbooleanObjField2);
        buf.append(",shortField1=" + fshortField1);
        buf.append(",shortField2=" + fshortField2);
        buf.append(",shortObjField1=" + fshortObjField1);
        buf.append(",shortObjField2=" + fshortObjField2);
        buf.append(",intField1=" + fintField1);
        buf.append(",intField2=" + fintField2);
        buf.append(",intObjField1=" + fintObjField1);
        buf.append(",intObjField2=" + fintObjField2);
        buf.append(",longField1=" + flongField1);
        buf.append(",longField2=" + flongField2);
        buf.append(",longObjField1=" + flongObjField1);
        buf.append(",longObjField2=" + flongObjField2);
        buf.append(",bigdecimalField1=" + fbigdecimalField1);
        buf.append(",bigdecimalField2=" + fbigdecimalField2);
        buf.append(",arraylistField1=" + farraylistField1);
        buf.append(",arraylistField2=" + farraylistField2);
        if (fstringArray1 == null) {
            buf.append(",stringArray1=null");
        } else {
            buf.append(",stringArray1=[");
            for (int index = 0; index < fstringArray1.length; index++) {
                buf.append((index == 0 ? "" : ", ") + fstringArray1[index]);
            }
            buf.append("]");
        }
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
    public void copyTo(final BlancoValueObjectSampleStructure target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: BlancoValueObjectSampleStructure#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fstringField1
        // Type: java.lang.String
        target.fstringField1 = this.fstringField1;
        // Name: fstringField2
        // Type: java.lang.String
        target.fstringField2 = this.fstringField2;
        // Name: fbooleanField1
        // Type: boolean
        target.fbooleanField1 = this.fbooleanField1;
        // Name: fbooleanField2
        // Type: boolean
        target.fbooleanField2 = this.fbooleanField2;
        // Name: fbooleanObjField1
        // Type: java.lang.Boolean
        target.fbooleanObjField1 = this.fbooleanObjField1;
        // Name: fbooleanObjField2
        // Type: java.lang.Boolean
        target.fbooleanObjField2 = this.fbooleanObjField2;
        // Name: fshortField1
        // Type: short
        target.fshortField1 = this.fshortField1;
        // Name: fshortField2
        // Type: short
        target.fshortField2 = this.fshortField2;
        // Name: fshortObjField1
        // Type: java.lang.Short
        target.fshortObjField1 = this.fshortObjField1;
        // Name: fshortObjField2
        // Type: java.lang.Short
        target.fshortObjField2 = this.fshortObjField2;
        // Name: fintField1
        // Type: int
        target.fintField1 = this.fintField1;
        // Name: fintField2
        // Type: int
        target.fintField2 = this.fintField2;
        // Name: fintObjField1
        // Type: java.lang.Integer
        target.fintObjField1 = this.fintObjField1;
        // Name: fintObjField2
        // Type: java.lang.Integer
        target.fintObjField2 = this.fintObjField2;
        // Name: flongField1
        // Type: long
        target.flongField1 = this.flongField1;
        // Name: flongField2
        // Type: long
        target.flongField2 = this.flongField2;
        // Name: flongObjField1
        // Type: java.lang.Long
        target.flongObjField1 = this.flongObjField1;
        // Name: flongObjField2
        // Type: java.lang.Long
        target.flongObjField2 = this.flongObjField2;
        // Name: fbigdecimalField1
        // Type: java.math.BigDecimal
        target.fbigdecimalField1 = this.fbigdecimalField1;
        // Name: fbigdecimalField2
        // Type: java.math.BigDecimal
        target.fbigdecimalField2 = this.fbigdecimalField2;
        // Name: farraylistField1
        // Type: java.util.List
        // フィールド[farraylistField1]はサポート外の型[java.util.List]です。
        // Name: farraylistField2
        // Type: java.util.List
        // フィールド[farraylistField2]はサポート外の型[java.util.List]です。
        // Name: fstringArray1
        // Type: java.lang.String[]
        if (this.fstringArray1 != null) {
            target.fstringArray1 = new java.lang.String[this.fstringArray1.length];
            for (int index = 0; index < this.fstringArray1.length; index++) {
                // copy contents...
                target.fstringArray1[index] = this.fstringArray1[index];
            }
        }
    }
}
