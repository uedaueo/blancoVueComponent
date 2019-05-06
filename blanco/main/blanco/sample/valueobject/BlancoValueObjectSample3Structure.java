/*
 * このソースコードは blanco Frameworkにより自動生成されました。
 */
package blanco.sample.valueobject;

/**
 * クラス説明。改行など参照文字の試験。１行目。(&lt;&gt;&quot;←文字参照エンコーディングされる)
 *
 * ２行目。(<>"←そのまま出力)
 * ３行目。(<>"←そのまま出力)
 * ４行目。(<>"←そのまま出力)
 */
public class BlancoValueObjectSample3Structure {
    /**
     * フィールド説明。改行など参照文字の試験。１行目。(&lt;&gt;&quot;←文字参照エンコーディングされる)
     *
     * ２行目。(<>"←そのまま出力)
     * ３行目。(<>"←そのまま出力)
     * ４行目。(<>"←そのまま出力)
     * フィールド: [field1]。
     * デフォルト: ["あいう"]。
     */
    private String fField1 = "\"あいう\"";

    /**
     * フィールド [field1] の値を設定します。
     *
     * フィールドの説明: [フィールド説明。改行など参照文字の試験。１行目。(<>"←文字参照エンコーディングされる)]。
     * ２行目。(<>"←そのまま出力)
     * ３行目。(<>"←そのまま出力)
     * ４行目。(<>"←そのまま出力)
     *
     * @param argField1 フィールド[field1]に設定する値。
     */
    public void setField1(final String argField1) {
        fField1 = argField1;
    }

    /**
     * フィールド [field1] の値を取得します。
     *
     * フィールドの説明: [フィールド説明。改行など参照文字の試験。１行目。(<>"←文字参照エンコーディングされる)]。
     * ２行目。(<>"←そのまま出力)
     * ３行目。(<>"←そのまま出力)
     * ４行目。(<>"←そのまま出力)
     * デフォルト: ["あいう"]。
     *
     * @return フィールド[field1]から取得した値。
     */
    public String getField1() {
        return fField1;
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
        buf.append("blanco.sample.valueobject.BlancoValueObjectSample3Structure[");
        buf.append("field1=" + fField1);
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
    public void copyTo(final BlancoValueObjectSample3Structure target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: BlancoValueObjectSample3Structure#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fField1
        // Type: java.lang.String
        target.fField1 = this.fField1;
    }
}
