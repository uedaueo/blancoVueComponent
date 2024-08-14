package blanco.vuecomponent;

import blanco.commons.util.BlancoStringUtil;
import blanco.vuecomponent.valueobject.BlancoVueComponentClassStructure;
import org.w3c.dom.Document;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * This is a transformer class for outputting XML files.
 */
public class BlancoVueComponentXmlTransformer {

    /**
     * Whether to run in debug mode.
     */
    private static final boolean IS_DEBUG = false;

    /**
     * The number of tabs.
     */
    private int tabs = 2;

    public int getTabs() {
        return tabs;
    }

    public void setTabs(int tabs) {
        this.tabs = tabs;
    }

    private OutputStream outputStream = null;
    private String lineSeparator = System.getProperty("line.separator");

    public void preapreTransform(
            final File argOutputDirectory,
            final BlancoVueComponentClassStructure argClassStructure) {
        if (argOutputDirectory == null) {
            throw new IllegalArgumentException(
                    "An output destination root directory was given as null. Aborts the process.");
        }

        if (argOutputDirectory.exists() == false) {
            if (argOutputDirectory.mkdirs() == false) {
                throw new IllegalArgumentException("The output destination root directory ["
                        + argOutputDirectory.getAbsolutePath()
                        + "] does not exist, so we tried to create it, but failed. Aborts the process.");
            }
        }
        if (argOutputDirectory.isDirectory() == false) {
            throw new IllegalArgumentException("A file ["
                    + argOutputDirectory.getAbsolutePath() + "] that is not a directory was given as the output root directory. Aborts the process.");
        }

        try {
            // Converts a package name to a directory name.
            String strSubdirectory = BlancoStringUtil.replaceAll(
                    BlancoStringUtil.null2Blank(argClassStructure.getPackage()),
                    '.', '/');
            if (strSubdirectory.length() > 0) {
                // Adds a slash only if subdirectories exist.
                strSubdirectory = "/" + strSubdirectory;
            }

            final File targetPackageDirectory = new File(argOutputDirectory
                    .getAbsolutePath()
                    + strSubdirectory);
            if (targetPackageDirectory.exists() == false) {
                if (targetPackageDirectory.mkdirs() == false) {
                    throw new IllegalArgumentException("Failed to generate the output destination package directory ["
                            + targetPackageDirectory.getAbsolutePath()
                            + "].");
                }
            }

            // Finalizes the output destination file.
            final File fileTarget = new File(targetPackageDirectory
                    .getAbsolutePath()
                    + "/" + argClassStructure.getName() + ".vue");

            this.outputStream = new BufferedOutputStream(
                    new FileOutputStream(fileTarget));

        } catch (IOException ex) {
            throw new IllegalArgumentException("An exception occurred in the process of outputting the source code."
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

    public void replaceLineSeparator() {
        String LS = System.lineSeparator();

    }

    public void transform(final Document argDocument) {

        if (argDocument == null) {
            throw new IllegalArgumentException(
                    "The process of converting the DOM tree to the output XML stream was given null as the XML document. Give it a non-null value as the XML document.");
        }
        if (this.outputStream == null) {
            throw new IllegalArgumentException(
                    "The process of converting the DOM tree to the output XML stream was given null as the stream. Give it a non-null value as stream.");
        }

        try {
            final TransformerFactory tf = TransformerFactory.newInstance();
            final Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            final DOMSource source = new DOMSource(argDocument);
            ByteArrayOutputStream tmpOut = new ByteArrayOutputStream();
            transformer.transform(source, new StreamResult(tmpOut));
            // on windows environment, transformer generates CRLF code.
            String xml = tmpOut.toString("utf-8");
            xml = xml.replaceAll("\r\n", System.getProperty("line.separator"));
            this.outputStream.write(xml.getBytes());
            tmpOut.close();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(
                    "Unexpected exception: XML transformation configuration exception occurred." + e.toString());
        } catch (TransformerException e) {
            throw new IllegalArgumentException("Unexpected exception: An XML conversion exception occurred."
                    + e.toString());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unexpected exception: Unsupported Encoding." + e.toString());
        } catch (IOException e) {
            throw new IllegalArgumentException("Unexpected exception: Error on file writing." + e);
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
