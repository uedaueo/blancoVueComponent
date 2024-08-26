package blanco.vuecomponent.resourcebundle;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * blancoVueComponentが利用するリソースバンドルを蓄えます。
 *
 * リソースバンドル定義: [BlancoVueComponent]。<BR>
 * このクラスはリソースバンドル定義書から自動生成されたリソースバンドルクラスです。<BR>
 * 既知のロケール<BR>
 * <UL>
 * <LI>ja
 * </UL>
 */
public class BlancoVueComponentResourceBundle {
    /**
     * リソースバンドルオブジェクト。
     *
     * 内部的に実際に入力を行うリソースバンドルを記憶します。
     */
    private ResourceBundle fResourceBundle;

    /**
     * BlancoVueComponentResourceBundleクラスのコンストラクタ。
     *
     * 基底名[BlancoVueComponent]、デフォルトのロケール、呼び出し側のクラスローダを使用して、リソースバンドルを取得します。
     */
    public BlancoVueComponentResourceBundle() {
        try {
            fResourceBundle = ResourceBundle.getBundle("blanco/vuecomponent/resourcebundle/BlancoVueComponent");
        } catch (MissingResourceException ex) {
        }
    }

    /**
     * BlancoVueComponentResourceBundleクラスのコンストラクタ。
     *
     * 基底名[BlancoVueComponent]、指定されたロケール、呼び出し側のクラスローダを使用して、リソースバンドルを取得します。
     *
     * @param locale ロケールの指定
     */
    public BlancoVueComponentResourceBundle(final Locale locale) {
        try {
            fResourceBundle = ResourceBundle.getBundle("blanco/vuecomponent/resourcebundle/BlancoVueComponent", locale);
        } catch (MissingResourceException ex) {
        }
    }

    /**
     * BlancoVueComponentResourceBundleクラスのコンストラクタ。
     *
     * 基底名[BlancoVueComponent]、指定されたロケール、指定されたクラスローダを使用して、リソースバンドルを取得します。
     *
     * @param locale ロケールの指定
     * @param loader クラスローダの指定
     */
    public BlancoVueComponentResourceBundle(final Locale locale, final ClassLoader loader) {
        try {
            fResourceBundle = ResourceBundle.getBundle("blanco/vuecomponent/resourcebundle/BlancoVueComponent", locale, loader);
        } catch (MissingResourceException ex) {
        }
    }

    /**
     * Gets the resource bundle object held internally.
     *
     * @return The resource bundle object held internally.
     */
    public ResourceBundle getResourceBundle() {
        return fResourceBundle;
    }

    /**
     * bundle[BlancoVueComponent], key[METAFILE_DISPLAYNAME]
     *
     * [バリューオブジェクト定義書] (ja)<br>
     *
     * @return key[METAFILE_DISPLAYNAME]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMetafileDisplayname() {
        // 初期値として定義書の値を利用します。
        String strFormat = "バリューオブジェクト定義書";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("METAFILE_DISPLAYNAME");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[META2XML.ELEMENT_COMMON]
     *
     * [blancovuecomponent-common] (ja)<br>
     *
     * @return key[META2XML.ELEMENT_COMMON]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMeta2xmlElementCommon() {
        // 初期値として定義書の値を利用します。
        String strFormat = "blancovuecomponent-common";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("META2XML.ELEMENT_COMMON");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[META2XML.ELEMENT_HEADER_COMPONENTS]
     *
     * [blancovuecomponent-header-components] (ja)<br>
     *
     * @return key[META2XML.ELEMENT_HEADER_COMPONENTS]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMeta2xmlElementHeaderComponents() {
        // 初期値として定義書の値を利用します。
        String strFormat = "blancovuecomponent-header-components";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("META2XML.ELEMENT_HEADER_COMPONENTS");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[META2XML.ELEMENT_LIST_COMPONENTS]
     *
     * [blancovuecomponent-list-components] (ja)<br>
     *
     * @return key[META2XML.ELEMENT_LIST_COMPONENTS]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMeta2xmlElementListComponents() {
        // 初期値として定義書の値を利用します。
        String strFormat = "blancovuecomponent-list-components";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("META2XML.ELEMENT_LIST_COMPONENTS");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[META2XML.ELEMENT_HEADER_API]
     *
     * [blancovuecomponent-header-api] (ja)<br>
     *
     * @return key[META2XML.ELEMENT_HEADER_API]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMeta2xmlElementHeaderApi() {
        // 初期値として定義書の値を利用します。
        String strFormat = "blancovuecomponent-header-api";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("META2XML.ELEMENT_HEADER_API");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[META2XML.ELEMENT_LIST_API]
     *
     * [blancovuecomponent-list-api] (ja)<br>
     *
     * @return key[META2XML.ELEMENT_LIST_API]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMeta2xmlElementListApi() {
        // 初期値として定義書の値を利用します。
        String strFormat = "blancovuecomponent-list-api";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("META2XML.ELEMENT_LIST_API");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[META2XML.ELEMENT_HEADER_PROPS]
     *
     * [blancovuecomponent-header-props] (ja)<br>
     *
     * @return key[META2XML.ELEMENT_HEADER_PROPS]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMeta2xmlElementHeaderProps() {
        // 初期値として定義書の値を利用します。
        String strFormat = "blancovuecomponent-header-props";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("META2XML.ELEMENT_HEADER_PROPS");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[META2XML.ELEMENT_LIST_PROPS]
     *
     * [blancovuecomponent-list-props] (ja)<br>
     *
     * @return key[META2XML.ELEMENT_LIST_PROPS]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMeta2xmlElementListProps() {
        // 初期値として定義書の値を利用します。
        String strFormat = "blancovuecomponent-list-props";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("META2XML.ELEMENT_LIST_PROPS");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[META2XML.ELEMENT_HEADER_EMITS]
     *
     * [blancovuecomponent-header-emits] (ja)<br>
     *
     * @return key[META2XML.ELEMENT_HEADER_EMITS]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMeta2xmlElementHeaderEmits() {
        // 初期値として定義書の値を利用します。
        String strFormat = "blancovuecomponent-header-emits";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("META2XML.ELEMENT_HEADER_EMITS");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[META2XML.ELEMENT_LIST_EMITS]
     *
     * [blancovuecomponent-list-emits] (ja)<br>
     *
     * @return key[META2XML.ELEMENT_LIST_EMITS]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMeta2xmlElementListEmits() {
        // 初期値として定義書の値を利用します。
        String strFormat = "blancovuecomponent-list-emits";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("META2XML.ELEMENT_LIST_EMITS");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[META2XML.ELEMENT_BREAD_CRUMBS]
     *
     * [blancovuecomponent-bread-crumbs] (ja)<br>
     *
     * @return key[META2XML.ELEMENT_BREAD_CRUMBS]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMeta2xmlElementBreadCrumbs() {
        // 初期値として定義書の値を利用します。
        String strFormat = "blancovuecomponent-bread-crumbs";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("META2XML.ELEMENT_BREAD_CRUMBS");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[META2XML.ELEMENT_MENU_INFO]
     *
     * [blancovuecomponent-menu-info] (ja)<br>
     *
     * @return key[META2XML.ELEMENT_MENU_INFO]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMeta2xmlElementMenuInfo() {
        // 初期値として定義書の値を利用します。
        String strFormat = "blancovuecomponent-menu-info";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("META2XML.ELEMENT_MENU_INFO");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[XML2JAVACLASS.FIELD.NAME]
     *
     * [フィールド: [{0}]。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2JAVACLASS.FIELD.NAME]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2javaclassFieldName(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "フィールド: [{0}]。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.FIELD.NAME");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2JAVACLASS.FIELD.DEFAULT]
     *
     * [デフォルト: [{0}]。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2JAVACLASS.FIELD.DEFAULT]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2javaclassFieldDefault(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "デフォルト: [{0}]。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.FIELD.DEFAULT");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2JAVACLASS.SET.JAVADOC.01]
     *
     * [フィールド [{0}] の値を設定します。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2JAVACLASS.SET.JAVADOC.01]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2javaclassSetJavadoc01(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "フィールド [{0}] の値を設定します。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.SET.JAVADOC.01");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2JAVACLASS.SET.JAVADOC.02]
     *
     * [フィールドの説明: [{0}]。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2JAVACLASS.SET.JAVADOC.02]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2javaclassSetJavadoc02(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "フィールドの説明: [{0}]。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.SET.JAVADOC.02");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2JAVACLASS.SET.ARG.JAVADOC]
     *
     * [フィールド[{0}]に設定する値。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2JAVACLASS.SET.ARG.JAVADOC]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2javaclassSetArgJavadoc(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "フィールド[{0}]に設定する値。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.SET.ARG.JAVADOC");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2JAVACLASS.GET.JAVADOC.01]
     *
     * [フィールド [{0}] の値を取得します。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2JAVACLASS.GET.JAVADOC.01]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2javaclassGetJavadoc01(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "フィールド [{0}] の値を取得します。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.GET.JAVADOC.01");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2JAVACLASS.GET.JAVADOC.02]
     *
     * [フィールドの説明: [{0}]。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2JAVACLASS.GET.JAVADOC.02]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2javaclassGetJavadoc02(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "フィールドの説明: [{0}]。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.GET.JAVADOC.02");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2JAVACLASS.GET.DEFAULT.JAVADOC]
     *
     * [デフォルト: [{0}]。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2JAVACLASS.GET.DEFAULT.JAVADOC]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2javaclassGetDefaultJavadoc(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "デフォルト: [{0}]。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.GET.DEFAULT.JAVADOC");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2JAVACLASS.GET.RETURN.JAVADOC]
     *
     * [フィールド[{0}]から取得した値。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2JAVACLASS.GET.RETURN.JAVADOC]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2javaclassGetReturnJavadoc(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "フィールド[{0}]から取得した値。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2JAVACLASS.GET.RETURN.JAVADOC");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.ERR001]
     *
     * [クラス名[{0}]のパッケージ名が指定されていません。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.ERR001]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileErr001(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "クラス名[{0}]のパッケージ名が指定されていません。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.ERR001");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.ERR002]
     *
     * [メッセージ定義ID[{0}]において、キー[{1}]が2回以上あらわれました。同じキーは2回以上指定することはできません。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @param arg1 置換文字列{1}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.ERR002]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileErr002(final String arg0, final String arg1) {
        // 初期値として定義書の値を利用します。
        String strFormat = "メッセージ定義ID[{0}]において、キー[{1}]が2回以上あらわれました。同じキーは2回以上指定することはできません。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.ERR002");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0, arg1}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.ERR003]
     *
     * [クラス名[{0}]のフィールド[{1}]の型名が指定されていません。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @param arg1 置換文字列{1}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.ERR003]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileErr003(final String arg0, final String arg1) {
        // 初期値として定義書の値を利用します。
        String strFormat = "クラス名[{0}]のフィールド[{1}]の型名が指定されていません。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.ERR003");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0, arg1}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.ERR004]
     *
     * [クラス名[{0}] でフィールド名が指定されていないものがあります。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.ERR004]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileErr004(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "クラス名[{0}] でフィールド名が指定されていないものがあります。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.ERR004");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.ERR005]
     *
     * [クラス名[{0}] フィールド[{1}]の「型」が指定されていません。「型」を指定してください。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @param arg1 置換文字列{1}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.ERR005]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileErr005(final String arg0, final String arg1) {
        // 初期値として定義書の値を利用します。
        String strFormat = "クラス名[{0}] フィールド[{1}]の「型」が指定されていません。「型」を指定してください。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.ERR005");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0, arg1}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.ERR006]
     *
     * [クラス名[{0}] フィールド[{1}]の「デフォルト値({2})」がセットされています。しかし「{3}」はデフォルト値をサポートしません。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @param arg1 置換文字列{1}を置換する値。java.lang.String型を与えてください。
     * @param arg2 置換文字列{2}を置換する値。java.lang.String型を与えてください。
     * @param arg3 置換文字列{3}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.ERR006]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileErr006(final String arg0, final String arg1, final String arg2, final String arg3) {
        // 初期値として定義書の値を利用します。
        String strFormat = "クラス名[{0}] フィールド[{1}]の「デフォルト値({2})」がセットされています。しかし「{3}」はデフォルト値をサポートしません。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.ERR006");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0, arg1, arg2, arg3}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.FIELD.NAME]
     *
     * [フィールド [{0}]] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.FIELD.NAME]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileFieldName(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "フィールド [{0}]";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.FIELD.NAME");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.FIELD.TYPE]
     *
     * [項目の型 [{0}]] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.FIELD.TYPE]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileFieldType(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "項目の型 [{0}]";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.FIELD.TYPE");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.FIELD.DEFAULT]
     *
     * [規定値   [{0}]] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.FIELD.DEFAULT]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileFieldDefault(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "規定値   [{0}]";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.FIELD.DEFAULT");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.SET.LANGDOC.01]
     *
     * [フィールド [{0}]のセッターメソッド] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.SET.LANGDOC.01]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileSetLangdoc01(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "フィールド [{0}]のセッターメソッド";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.SET.LANGDOC.01");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.SET.LANGDOC.02]
     *
     * [項目の型 [{0}]] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.SET.LANGDOC.02]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileSetLangdoc02(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "項目の型 [{0}]";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.SET.LANGDOC.02");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.SET.ARG.LANGDOC]
     *
     * [フィールド[{0}]に格納したい値] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.SET.ARG.LANGDOC]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileSetArgLangdoc(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "フィールド[{0}]に格納したい値";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.SET.ARG.LANGDOC");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.GET.LANGDOC.01]
     *
     * [フィールド[{0}]のゲッターメソッド] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.GET.LANGDOC.01]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileGetLangdoc01(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "フィールド[{0}]のゲッターメソッド";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.GET.LANGDOC.01");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.GET.LANGDOC.02]
     *
     * [項目の型 [{0}]] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.GET.LANGDOC.02]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileGetLangdoc02(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "項目の型 [{0}]";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.GET.LANGDOC.02");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.GET.ARG.LANGDOC]
     *
     * [規定値   [{0}]] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.GET.ARG.LANGDOC]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileGetArgLangdoc(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "規定値   [{0}]";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.GET.ARG.LANGDOC");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.GET.RETURN.LANGDOC]
     *
     * [フィールド[{0}]に格納されている値] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.GET.RETURN.LANGDOC]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileGetReturnLangdoc(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "フィールド[{0}]に格納されている値";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.GET.RETURN.LANGDOC");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.TYPE.LANGDOC.01]
     *
     * [フィールド[{0}]のタイプメソッド] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.TYPE.LANGDOC.01]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileTypeLangdoc01(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "フィールド[{0}]のタイプメソッド";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.TYPE.LANGDOC.01");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.TYPE.LANGDOC.02]
     *
     * [項目の型 [{0}]] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.TYPE.LANGDOC.02]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileTypeLangdoc02(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "項目の型 [{0}]";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.TYPE.LANGDOC.02");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.TYPE.ARG.LANGDOC]
     *
     * [規定値   [{0}]] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.TYPE.ARG.LANGDOC]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileTypeArgLangdoc(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "規定値   [{0}]";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.TYPE.ARG.LANGDOC");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.TYPE.RETURN.LANGDOC]
     *
     * [フィールド[{0}]の型名文字列] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.TYPE.RETURN.LANGDOC]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileTypeReturnLangdoc(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "フィールド[{0}]の型名文字列";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.TYPE.RETURN.LANGDOC");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[ANTTASK.ERR001]
     *
     * [メタディレクトリ[{0}]が存在しません。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[ANTTASK.ERR001]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getAnttaskErr001(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "メタディレクトリ[{0}]が存在しません。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("ANTTASK.ERR001");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.ROUTECONFIG.CLASS]
     *
     * [RouteRecordを定義します: [{0}]。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.ROUTECONFIG.CLASS]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileRouteconfigClass(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "RouteRecordを定義します: [{0}]。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.ROUTECONFIG.CLASS");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.ROUTECONFIG.PARAMETER]
     *
     * [RouteRecordのパラメータです: [{0}]。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.ROUTECONFIG.PARAMETER]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileRouteconfigParameter(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "RouteRecordのパラメータです: [{0}]。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.ROUTECONFIG.PARAMETER");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.ROUTECONFIG.LIST]
     *
     * [ページコンポーネントをvue-routerでロードするための設定ファイルです] (ja)<br>
     *
     * @return key[XML2SOURCE_FILE.ROUTECONFIG.LIST]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileRouteconfigList() {
        // 初期値として定義書の値を利用します。
        String strFormat = "ページコンポーネントをvue-routerでロードするための設定ファイルです";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.ROUTECONFIG.LIST");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.PROPS_SUBJECT.DESCRIPTION]
     *
     * [コンポーネントのsubjectプロパティです] (ja)<br>
     *
     * @return key[XML2SOURCE_FILE.PROPS_SUBJECT.DESCRIPTION]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFilePropsSubjectDescription() {
        // 初期値として定義書の値を利用します。
        String strFormat = "コンポーネントのsubjectプロパティです";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.PROPS_SUBJECT.DESCRIPTION");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.PROPS_ALIAS.DESCRIPTION]
     *
     * [コンポーネントのaliasプロパティです] (ja)<br>
     *
     * @return key[XML2SOURCE_FILE.PROPS_ALIAS.DESCRIPTION]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFilePropsAliasDescription() {
        // 初期値として定義書の値を利用します。
        String strFormat = "コンポーネントのaliasプロパティです";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.PROPS_ALIAS.DESCRIPTION");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.PROPS_COMPOENENT_ID.DESCRIPTION]
     *
     * [コンポーネントのcomponentIdプロパティです] (ja)<br>
     *
     * @return key[XML2SOURCE_FILE.PROPS_COMPOENENT_ID.DESCRIPTION]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFilePropsCompoenentIdDescription() {
        // 初期値として定義書の値を利用します。
        String strFormat = "コンポーネントのcomponentIdプロパティです";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.PROPS_COMPOENENT_ID.DESCRIPTION");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.PROPS_BREAD_CRUMB_LIST.DESCRIPTION]
     *
     * [コンポーネントのbreadCrumbListプロパティです] (ja)<br>
     *
     * @return key[XML2SOURCE_FILE.PROPS_BREAD_CRUMB_LIST.DESCRIPTION]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFilePropsBreadCrumbListDescription() {
        // 初期値として定義書の値を利用します。
        String strFormat = "コンポーネントのbreadCrumbListプロパティです";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.PROPS_BREAD_CRUMB_LIST.DESCRIPTION");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.PROPS_INTERFACE.DESCRIPTION]
     *
     * [コンポーネントのプロパティを定義するインタフェイスです] (ja)<br>
     *
     * @return key[XML2SOURCE_FILE.PROPS_INTERFACE.DESCRIPTION]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFilePropsInterfaceDescription() {
        // 初期値として定義書の値を利用します。
        String strFormat = "コンポーネントのプロパティを定義するインタフェイスです";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.PROPS_INTERFACE.DESCRIPTION");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.PROPS_TYPE.DESCRIPTION]
     *
     * [propsの型を定義します] (ja)<br>
     *
     * @return key[XML2SOURCE_FILE.PROPS_TYPE.DESCRIPTION]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFilePropsTypeDescription() {
        // 初期値として定義書の値を利用します。
        String strFormat = "propsの型を定義します";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.PROPS_TYPE.DESCRIPTION");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.PROPS.DESCRIPTION]
     *
     * [propsの定義です] (ja)<br>
     *
     * @return key[XML2SOURCE_FILE.PROPS.DESCRIPTION]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFilePropsDescription() {
        // 初期値として定義書の値を利用します。
        String strFormat = "propsの定義です";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.PROPS.DESCRIPTION");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.EMITS_TYPE.DESCRIPTION]
     *
     * [emitsの型を定義します] (ja)<br>
     *
     * @return key[XML2SOURCE_FILE.EMITS_TYPE.DESCRIPTION]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileEmitsTypeDescription() {
        // 初期値として定義書の値を利用します。
        String strFormat = "emitsの型を定義します";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.EMITS_TYPE.DESCRIPTION");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.EMITS.DESCRIPTION]
     *
     * [emitsの定義です] (ja)<br>
     *
     * @return key[XML2SOURCE_FILE.EMITS.DESCRIPTION]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileEmitsDescription() {
        // 初期値として定義書の値を利用します。
        String strFormat = "emitsの定義です";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.EMITS.DESCRIPTION");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.ROUTE_SETTINGS_CONST.DESCRIPTION]
     *
     * [RouteRecordの配列を返します。] (ja)<br>
     *
     * @return key[XML2SOURCE_FILE.ROUTE_SETTINGS_CONST.DESCRIPTION]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileRouteSettingsConstDescription() {
        // 初期値として定義書の値を利用します。
        String strFormat = "RouteRecordの配列を返します。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.ROUTE_SETTINGS_CONST.DESCRIPTION");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.REQUEST_FACTORY.DESCRIPTION]
     *
     * [{0}RequestFactoryを生成します。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.REQUEST_FACTORY.DESCRIPTION]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileRequestFactoryDescription(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "{0}RequestFactoryを生成します。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.REQUEST_FACTORY.DESCRIPTION");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.REQUEST_FACTORY_METHOD.DESCRIPTION]
     *
     * [{0} クラスを生成します。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.REQUEST_FACTORY_METHOD.DESCRIPTION]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileRequestFactoryMethodDescription(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "{0} クラスを生成します。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.REQUEST_FACTORY_METHOD.DESCRIPTION");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.REQUEST_FACTORY_RETURN.DESCRIPTION]
     *
     * [{0} クラスを返却します。] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.REQUEST_FACTORY_RETURN.DESCRIPTION]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileRequestFactoryReturnDescription(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "{0} クラスを返却します。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.REQUEST_FACTORY_RETURN.DESCRIPTION");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.ROUTERECORD.MAP.INTERFACE]
     *
     * [コンポーネント別名などをキーにRouteRecordを取得するMapのインタフェイスです。] (ja)<br>
     *
     * @return key[XML2SOURCE_FILE.ROUTERECORD.MAP.INTERFACE]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileRouterecordMapInterface() {
        // 初期値として定義書の値を利用します。
        String strFormat = "コンポーネント別名などをキーにRouteRecordを取得するMapのインタフェイスです。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.ROUTERECORD.MAP.INTERFACE");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.BREAD.CRUMB.INTERFACE]
     *
     * [パンくずリストデータのインタフェイスです。] (ja)<br>
     *
     * @return key[XML2SOURCE_FILE.BREAD.CRUMB.INTERFACE]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileBreadCrumbInterface() {
        // 初期値として定義書の値を利用します。
        String strFormat = "パンくずリストデータのインタフェイスです。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.BREAD.CRUMB.INTERFACE");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.BREAD.CRUMB.NAME]
     *
     * [パンくずリストの表示名です。] (ja)<br>
     *
     * @return key[XML2SOURCE_FILE.BREAD.CRUMB.NAME]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileBreadCrumbName() {
        // 初期値として定義書の値を利用します。
        String strFormat = "パンくずリストの表示名です。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.BREAD.CRUMB.NAME");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.BREAD.CRUMB.NOLINK]
     *
     * [パンくずリストにリンクが存在しない場合に true を指定します。] (ja)<br>
     *
     * @return key[XML2SOURCE_FILE.BREAD.CRUMB.NOLINK]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileBreadCrumbNolink() {
        // 初期値として定義書の値を利用します。
        String strFormat = "パンくずリストにリンクが存在しない場合に true を指定します。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.BREAD.CRUMB.NOLINK");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.MENU.ITEM.INTERFACE]
     *
     * [メニュー情報のインタフェイスです。] (ja)<br>
     *
     * @return key[XML2SOURCE_FILE.MENU.ITEM.INTERFACE]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileMenuItemInterface() {
        // 初期値として定義書の値を利用します。
        String strFormat = "メニュー情報のインタフェイスです。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.MENU.ITEM.INTERFACE");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.MENU.ITEM.NAME]
     *
     * [メニューノードに対応するページのcomponentIdです。] (ja)<br>
     *
     * @return key[XML2SOURCE_FILE.MENU.ITEM.NAME]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileMenuItemName() {
        // 初期値として定義書の値を利用します。
        String strFormat = "メニューノードに対応するページのcomponentIdです。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.MENU.ITEM.NAME");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.MENU.ITEM.LABEL]
     *
     * [メニューノードに表示する表示名です。] (ja)<br>
     *
     * @return key[XML2SOURCE_FILE.MENU.ITEM.LABEL]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileMenuItemLabel() {
        // 初期値として定義書の値を利用します。
        String strFormat = "メニューノードに表示する表示名です。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.MENU.ITEM.LABEL");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.MENU.ITEM.DESCRIPTIOIN]
     *
     * [メニューノードの説明です。] (ja)<br>
     *
     * @return key[XML2SOURCE_FILE.MENU.ITEM.DESCRIPTIOIN]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileMenuItemDescriptioin() {
        // 初期値として定義書の値を利用します。
        String strFormat = "メニューノードの説明です。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.MENU.ITEM.DESCRIPTIOIN");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.MENU.ITEM.CHILDREN]
     *
     * [メニューノードの子階層です。] (ja)<br>
     *
     * @return key[XML2SOURCE_FILE.MENU.ITEM.CHILDREN]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileMenuItemChildren() {
        // 初期値として定義書の値を利用します。
        String strFormat = "メニューノードの子階層です。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.MENU.ITEM.CHILDREN");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.MENU.ITEM.QUERY.AND.HASH]
     *
     * [メニューノードから遷移する際に付加するQueryとHash文字列です。] (ja)<br>
     *
     * @return key[XML2SOURCE_FILE.MENU.ITEM.QUERY.AND.HASH]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileMenuItemQueryAndHash() {
        // 初期値として定義書の値を利用します。
        String strFormat = "メニューノードから遷移する際に付加するQueryとHash文字列です。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.MENU.ITEM.QUERY.AND.HASH");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.MENU.ITEM.CLASS.DESCRIPTION]
     *
     * [MenuItem を定義します。[ {0} ]] (ja)<br>
     *
     * @param arg0 置換文字列{0}を置換する値。java.lang.String型を与えてください。
     * @return key[XML2SOURCE_FILE.MENU.ITEM.CLASS.DESCRIPTION]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFileMenuItemClassDescription(final String arg0) {
        // 初期値として定義書の値を利用します。
        String strFormat = "MenuItem を定義します。[ {0} ]";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.MENU.ITEM.CLASS.DESCRIPTION");
            }
        } catch (MissingResourceException ex) {
        }
        final MessageFormat messageFormat = new MessageFormat(strFormat);
        final StringBuffer strbuf = new StringBuffer();
        // 与えられた引数を元に置換文字列を置き換えます。
        messageFormat.format(new Object[] {arg0}, strbuf, null);
        return strbuf.toString();
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.PERMISSION.KIND.MAP.INTERFACE]
     *
     * [権限種別をキーにcomponentIdの配列を取得するMapのインタフェイスです。] (ja)<br>
     *
     * @return key[XML2SOURCE_FILE.PERMISSION.KIND.MAP.INTERFACE]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFilePermissionKindMapInterface() {
        // 初期値として定義書の値を利用します。
        String strFormat = "権限種別をキーにcomponentIdの配列を取得するMapのインタフェイスです。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.PERMISSION.KIND.MAP.INTERFACE");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[XML2SOURCE_FILE.PERMISSION.KIND.MAP]
     *
     * [権限種別をキーにcomponentIdの配列を取得するMapです。] (ja)<br>
     *
     * @return key[XML2SOURCE_FILE.PERMISSION.KIND.MAP]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getXml2sourceFilePermissionKindMap() {
        // 初期値として定義書の値を利用します。
        String strFormat = "権限種別をキーにcomponentIdの配列を取得するMapです。";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("XML2SOURCE_FILE.PERMISSION.KIND.MAP");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[META2XML.ELEMENT_COMMON_CS]
     *
     * [blancovalueobjectcs-common] (ja)<br>
     *
     * @return key[META2XML.ELEMENT_COMMON_CS]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMeta2xmlElementCommonCs() {
        // 初期値として定義書の値を利用します。
        String strFormat = "blancovalueobjectcs-common";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("META2XML.ELEMENT_COMMON_CS");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[META2XML.ELEMENT_COMMON_JS]
     *
     * [blancovalueobjectjs-common] (ja)<br>
     *
     * @return key[META2XML.ELEMENT_COMMON_JS]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMeta2xmlElementCommonJs() {
        // 初期値として定義書の値を利用します。
        String strFormat = "blancovalueobjectjs-common";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("META2XML.ELEMENT_COMMON_JS");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[META2XML.ELEMENT_COMMON_VB]
     *
     * [blancovalueobjectvb-common] (ja)<br>
     *
     * @return key[META2XML.ELEMENT_COMMON_VB]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMeta2xmlElementCommonVb() {
        // 初期値として定義書の値を利用します。
        String strFormat = "blancovalueobjectvb-common";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("META2XML.ELEMENT_COMMON_VB");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[META2XML.ELEMENT_COMMON_PHP]
     *
     * [blancovalueobjectphp-common] (ja)<br>
     *
     * @return key[META2XML.ELEMENT_COMMON_PHP]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMeta2xmlElementCommonPhp() {
        // 初期値として定義書の値を利用します。
        String strFormat = "blancovalueobjectphp-common";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("META2XML.ELEMENT_COMMON_PHP");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[META2XML.ELEMENT_COMMON_RUBY]
     *
     * [blancovalueobjectruby-common] (ja)<br>
     *
     * @return key[META2XML.ELEMENT_COMMON_RUBY]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMeta2xmlElementCommonRuby() {
        // 初期値として定義書の値を利用します。
        String strFormat = "blancovalueobjectruby-common";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("META2XML.ELEMENT_COMMON_RUBY");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[META2XML.ELEMENT_COMMON_PYTHON]
     *
     * [blancovalueobjectpython-common] (ja)<br>
     *
     * @return key[META2XML.ELEMENT_COMMON_PYTHON]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMeta2xmlElementCommonPython() {
        // 初期値として定義書の値を利用します。
        String strFormat = "blancovalueobjectpython-common";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("META2XML.ELEMENT_COMMON_PYTHON");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[META2XML.ELEMENT_COMMON_KT]
     *
     * [blancovalueobjectkt-common] (ja)<br>
     *
     * @return key[META2XML.ELEMENT_COMMON_KT]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMeta2xmlElementCommonKt() {
        // 初期値として定義書の値を利用します。
        String strFormat = "blancovalueobjectkt-common";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("META2XML.ELEMENT_COMMON_KT");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }

    /**
     * bundle[BlancoVueComponent], key[META2XML.ELEMENT_COMMON_TS]
     *
     * [blancovalueobjectts-common] (ja)<br>
     *
     * @return key[META2XML.ELEMENT_COMMON_TS]に対応する値。外部から読み込みができない場合には、定義書の値を戻します。必ずnull以外の値が戻ります。
     */
    public String getMeta2xmlElementCommonTs() {
        // 初期値として定義書の値を利用します。
        String strFormat = "blancovalueobjectts-common";
        try {
            if (fResourceBundle != null) {
                strFormat = fResourceBundle.getString("META2XML.ELEMENT_COMMON_TS");
            }
        } catch (MissingResourceException ex) {
        }
        // 置換文字列はひとつもありません。
        return strFormat;
    }
}
