package net.bodz.bas.vfs;

import net.bodz.bas.sugar.IToChain;
import net.bodz.bas.vfs.path.BadPathException;

public interface IFile
        extends IFsBlob, IFsDir, IToChain {

    @Override
    IFile getParentFile();

    /**
     * @throws NullPointerException
     *             If <code>childName</code> is <code>null</code>.
     */
    @Override
    IFile getChild(String childName);

    /**
     * @throws UnsupportedOperationException
     *             If not iterable.
     * @see #isTraversible()
     */
    Iterable<? extends IFile> children()
            throws VFSException;

    /**
     * @throws UnsupportedOperationException
     *             If not iterable.
     * @see #isTraversible()
     */
    Iterable<? extends IFile> children(IFilenameFilter nameFilter)
            throws VFSException;

    /**
     * @throws UnsupportedOperationException
     *             If not iterable.
     * @see #isTraversible()
     */
    Iterable<? extends IFile> children(IFileFilter fileFilter)
            throws VFSException;

    /**
     * The same as: <code>getPath().join(path).resolve()</code>.
     */
    IFile resolve(String path)
            throws BadPathException, FileResolveException;

}
