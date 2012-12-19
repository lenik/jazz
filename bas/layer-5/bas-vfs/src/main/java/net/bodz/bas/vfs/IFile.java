package net.bodz.bas.vfs;

import net.bodz.bas.sugar.IToolable;
import net.bodz.bas.vfs.path.BadPathException;

public interface IFile
        extends IFsBlob, IFsDir, IToolable {

    @Override
    IFile clone();

    @Override
    IFile getParentFile();

    /**
     * @throws NullPointerException
     *             If <code>childName</code> is <code>null</code>.
     */
    @Override
    IFile getChild(String childName);

    /**
     * The same as: <code>getPath().join(path).resolve()</code>.
     */
    IFile resolve(String path)
            throws BadPathException, FileResolveException;

    /**
     * @throws UnsupportedOperationException
     *             If not iterable.
     * @see #isIterable()
     */
    Iterable<? extends IFile> children()
            throws VFSException;

    /**
     * @throws UnsupportedOperationException
     *             If not iterable.
     * @see #isIterable()
     */
    Iterable<? extends IFile> children(IFilenameFilter nameFilter)
            throws VFSException;

    /**
     * @throws UnsupportedOperationException
     *             If not iterable.
     * @see #isIterable()
     */
    Iterable<? extends IFile> children(IFileFilter fileFilter)
            throws VFSException;

}
