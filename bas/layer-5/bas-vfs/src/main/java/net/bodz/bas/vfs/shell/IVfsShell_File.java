package net.bodz.bas.vfs.shell;

import java.io.IOException;

import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.IPath;

public interface IVfsShell_File {

    /**
     * @param readSize
     *            -1 to read all.
     */
    byte[] read(IFile src, int readSize)
            throws IOException;

    /**
     * @param readSize
     *            -1 to read all.
     */
    char[] readChars(IFile src, int readSize)
            throws IOException;

    /**
     * @param readSize
     *            -1 to read all.
     */
    String readString(IFile src, int readSize)
            throws IOException;

    void write(IFile dst, byte[] buf, int off, int len)
            throws IOException;

    void writeChars(IFile dst, char[] buf, int off, int len)
            throws IOException;

    void writeString(IFile dst, String string)
            throws IOException;

    /**
     * @return If <code>dstFile</code> is existed, and force is set to <code>false</code>, returns
     *         <code>false</code>.
     */
    boolean copy(IFile src, IFile dst)
            throws IOException;

    /**
     * @return If <code>dstFile</code> is existed, and force is set to <code>false</code>, returns
     *         <code>false</code>.
     */
    boolean move(IFile src, IFile dst)
            throws IOException;

    /**
     * @return If <code>dstFile</code> is existed, and force is set to <code>false</code>, returns
     *         <code>false</code>.
     */
    boolean createLink(IPath targetPath, IFile linkFile, boolean symbolic)
            throws IOException;

    // Set<String> getPredefinedLocationKeys();

}
