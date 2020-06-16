package blanco.vuecomponent;

import blanco.commons.util.BlancoStringUtil;
import blanco.vuecomponent.valueobject.BlancoVueComponentClassStructure;
import org.w3c.dom.Document;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * XMLファイルを出力するための transformer クラスです。
 */
public class BlancoVueComponentXmlTransformer {

    /**
     * デバッグモードで動作させるかどうか。
     */
    private static final boolean IS_DEBUG = false;

    /**
     * タブ数
     */
    private int tabs = 2;

    public int getTabs() {
        return tabs;
    }

    public void setTabs(int tabs) {
        this.tabs = tabs;
    }

    private OutputStream outputStream = null;

    public void preapreTransform(
            final File argOutputDirectory,
            final BlancoVueComponentClassStructure argClassStructure) {
        if (argOutputDirectory == null) {
            throw new IllegalArgumentException(
                    "出力先ルートディレクトリにnullが与えられました。処理中断します。");
        }

        if (argOutputDirectory.exists() == false) {
            if (argOutputDirectory.mkdirs() == false) {
                throw new IllegalArgumentException("出力先ルートディレクトリ["
                        + argOutputDirectory.getAbsolutePath()
                        + "]が存在しなかったので作成しようとしましたがディレクトリ作成に失敗しました。処理中断します。");
            }
        }
        if (argOutputDirectory.isDirectory() == false) {
            throw new IllegalArgumentException("出力先ルートディレクトリにディレクトリではないファイル["
                    + argOutputDirectory.getAbsolutePath() + "]が与えられました。処理中断します。");
        }

        try {
            // パッケージ名からディレクトリ名へと変換。
            String strSubdirectory = BlancoStringUtil.replaceAll(
                    BlancoStringUtil.null2Blank(argClassStructure.getPackage()),
                    '.', '/');
            if (strSubdirectory.length() > 0) {
                // サブディレクトリが存在する場合にのみスラッシュを追加します。
                strSubdirectory = "/" + strSubdirectory;
            }

            final File targetPackageDirectory = new File(argOutputDirectory
                    .getAbsolutePath()
                    + strSubdirectory);
            if (targetPackageDirectory.exists() == false) {
                if (targetPackageDirectory.mkdirs() == false) {
                    throw new IllegalArgumentException("出力先のパッケージディレクトリ["
                            + targetPackageDirectory.getAbsolutePath()
                            + "]の生成に失敗しました。");
                }
            }

            // 出力先のファイルを確定します。
            final File fileTarget = new File(targetPackageDirectory
                    .getAbsolutePath()
                    + "/" + argClassStructure.getName() + ".vue");

            this.outputStream = new BufferedOutputStream(
                    new FileOutputStream(fileTarget));

        } catch (IOException ex) {
            throw new IllegalArgumentException("ソースコードを出力する過程で例外が発生しました。"
                    + ex.toString());
        }
    }

    public void closeTransform() {
        if (this.outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.outputStream = null;
        }
    }

    public void transform(final Document argDocument) {

        if (argDocument == null) {
            throw new IllegalArgumentException(
                    "DOMツリーを出力XMLストリームに変換する処理に、XMLドキュメントとしてnullが渡されました。XMLドキュメントにはnull以外を与えてください。");
        }
        if (this.outputStream == null) {
            throw new IllegalArgumentException(
                    "DOMツリーを出力XMLストリームに変換する処理に、ストリームとしてnullが渡されました。ストリームにはnull以外を与えてください。");
        }

        try {
            final TransformerFactory tf = TransformerFactory.newInstance();
            final Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            final DOMSource source = new DOMSource(argDocument);
            transformer.transform(source, new StreamResult(this.outputStream));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(
                    "想定しない例外: XML変換コンフィグレーション例外が発生しました。" + e.toString());
        } catch (TransformerException e) {
            throw new IllegalArgumentException("想定しない例外: XML変換例外が発生しました。"
                    + e.toString());
        }
    }

    protected void finallize() {
        if (this.outputStream != null) {
            try {
                this.outputStream.close();
                this.outputStream = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
