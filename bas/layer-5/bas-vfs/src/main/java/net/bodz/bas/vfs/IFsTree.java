package net.bodz.bas.vfs;

import java.util.List;

import net.bodz.bas.util.iter.Mitorx;
import net.bodz.bas.vfs.util.IFilenameFilter;

public interface IFsTree
        extends IFsEntry {

    @Override
    IFsTree clone();

    boolean isIterable();

    /**
     * @return <code>null</code> If <code>childName</code> isn't existed, or the folder isn't
     *         accessible.
     */
    IFsEntry getChild(String childName)
            throws VFSException;

    /**
     * @throws UnsupportedOperationException
     *             If not iterable.
     * @see #isIterable()
     */
    Mitorx<? extends IFsEntry, VFSException> childIterator(IFilenameFilter nameFilter)
            throws VFSException;

    /**
     * @throws UnsupportedOperationException
     *             If not iterable.
     * @see #isIterable()
     */
    List<? extends IFsEntry> listChildren()
            throws VFSException;

    /**
     * @throws UnsupportedOperationException
     *             If not iterable.
     * @see #isIterable()
     */
    List<? extends IFsEntry> listChildren(IFilenameFilter nameFilter)
            throws VFSException;

    /**
     * Create the tree represent by this object, along with all parents which are not existed.
     * 
     * @return <code>true</code> If the tree is existed or succeeded to create.
     */
    boolean createTree();

}
