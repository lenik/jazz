package net.bodz.bas.vfs;


public interface IFsTree
        extends IFsEntry {

    @Override
    IFsTree clone();

    /**
     * 
     */
    boolean isIterable();

    boolean setIterable(boolean iterable);

    /**
     * @return <code>null</code> If <code>childName</code> isn't existed, or the folder isn't
     *         accessible.
     */
    IFsEntry getChild(String childName);

    /**
     * @throws UnsupportedOperationException
     *             If not iterable.
     * @see #isIterable()
     */
    Iterable<? extends IFsEntry> children()
            throws VFSException;

    /**
     * @throws UnsupportedOperationException
     *             If not iterable.
     * @see #isIterable()
     */
    Iterable<? extends IFsEntry> children(IFilenameFilter nameFilter)
            throws VFSException;

    /**
     * Create the tree represent by this object, along with all parents which are not existed.
     * 
     * @return <code>true</code> If the tree is existed or succeeded to create.
     */
    boolean createTree();

}
