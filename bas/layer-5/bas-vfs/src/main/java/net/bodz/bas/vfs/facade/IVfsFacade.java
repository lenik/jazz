package net.bodz.bas.vfs.facade;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.CopyOption;
import java.nio.file.OpenOption;

import net.bodz.bas.vfs.IFileSystem;

public interface IVfsFacade
        extends IFileSystem, IVfsFacade_File {

    String getCharsetName();

    void setCharsetName(String charsetName);

    Charset getCharset();

    void setCharset(Charset charset);

    /**
     * @param readSize
     *            -1 to read all.
     */
    byte[] read(String srcFile, int readSize, OpenOption... options)
            throws IOException;

    /**
     * @param readSize
     *            -1 to read all.
     */
    char[] readChars(String srcFile, int readSize, OpenOption... options)
            throws IOException;

    /**
     * @param readSize
     *            -1 to read all.
     */
    String readString(String srcFile, int readSize, OpenOption... options)
            throws IOException;

    void write(String dstFile, byte[] buf, int off, int len, OpenOption... options)
            throws IOException;

    void writeChars(String dstFile, char[] buf, int off, int len, OpenOption... options)
            throws IOException;

    void writeString(String dstFile, String string, OpenOption... options)
            throws IOException;

    /**
     * @return If <code>dstFile</code> is existed, and force is set to <code>false</code>, returns
     *         <code>false</code>.
     */
    boolean copy(String srcFile, String dstFile, CopyOption... options)
            throws IOException;

    /**
     * @return If <code>dstFile</code> is existed, and force is set to <code>false</code>, returns
     *         <code>false</code>.
     */
    boolean move(String srcFile, String dstFile, CopyOption... options)
            throws IOException;

    /**
     * @return If <code>dstFile</code> is existed, and force is set to <code>false</code>, returns
     *         <code>false</code>.
     */
    boolean createLink(String target, String linkFile, boolean symbolic)
            throws IOException;

    // Set<String> getPredefinedLocationKeys();

}
