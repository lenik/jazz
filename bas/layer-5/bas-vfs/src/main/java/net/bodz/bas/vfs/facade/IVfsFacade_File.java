package net.bodz.bas.vfs.facade;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.OpenOption;

import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.IPath;

public interface IVfsFacade_File {

    /**
     * @param readSize
     *            -1 to read all.
     */
    byte[] read(IFile src, int readSize, OpenOption... options)
            throws IOException;

    /**
     * @param readSize
     *            -1 to read all.
     */
    char[] readChars(IFile src, int readSize, OpenOption... options)
            throws IOException;

    /**
     * @param readSize
     *            -1 to read all.
     */
    String readString(IFile src, int readSize, OpenOption... options)
            throws IOException;

    void write(IFile dst, byte[] buf, int off, int len, OpenOption... options)
            throws IOException;

    void writeChars(IFile dst, char[] buf, int off, int len, OpenOption... options)
            throws IOException;

    void writeString(IFile dst, String string, OpenOption... options)
            throws IOException;

    /**
     * @return If <code>dstFile</code> is existed, and force is set to <code>false</code>, returns
     *         <code>false</code>.
     */
    boolean copy(IFile src, IFile dst, CopyOption... options)
            throws IOException;

    /**
     * @return If <code>dstFile</code> is existed, and force is set to <code>false</code>, returns
     *         <code>false</code>.
     */
    boolean move(IFile src, IFile dst, CopyOption... options)
            throws IOException;

    /**
     * @return If <code>dstFile</code> is existed, and force is set to <code>false</code>, returns
     *         <code>false</code>.
     */
    boolean createLink(IPath targetPath, IFile linkFile, boolean symbolic)
            throws IOException;

    // Set<String> getPredefinedLocationKeys();

}
