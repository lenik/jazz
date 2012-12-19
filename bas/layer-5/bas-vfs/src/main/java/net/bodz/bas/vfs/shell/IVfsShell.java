package net.bodz.bas.vfs.shell;

import java.io.IOException;
import java.nio.charset.Charset;

public interface IVfsShell {

    boolean isForce();

    void setForce(boolean force);

    String getCharsetName();

    void setCharsetName(String charsetName);

    Charset getCharset();

    void setCharset(Charset charset);

    /**
     * @param readSize
     *            -1 to read all.
     */
    byte[] read(String srcFile, int readSize)
            throws IOException;

    /**
     * @param readSize
     *            -1 to read all.
     */
    char[] readChars(String srcFile, int readSize)
            throws IOException;

    /**
     * @param readSize
     *            -1 to read all.
     */
    String readString(String srcFile, int readSize)
            throws IOException;

    void write(String dstFile, byte[] buf, int off, int len)
            throws IOException;

    void writeChars(String dstFile, char[] buf, int off, int len)
            throws IOException;

    void writeString(String dstFile, String string)
            throws IOException;

    /**
     * @return If <code>dstFile</code> is existed, and force is set to <code>false</code>, returns
     *         <code>false</code>.
     */
    boolean copy(String srcFile, String dstFile)
            throws IOException;

    /**
     * @return If <code>dstFile</code> is existed, and force is set to <code>false</code>, returns
     *         <code>false</code>.
     */
    boolean move(String srcFile, String dstFile)
            throws IOException;

    /**
     * @return If <code>dstFile</code> is existed, and force is set to <code>false</code>, returns
     *         <code>false</code>.
     */
    boolean createLink(String target, String linkFile, boolean symbolic)
            throws IOException;

    // Set<String> getPredefinedLocationKeys();

}
