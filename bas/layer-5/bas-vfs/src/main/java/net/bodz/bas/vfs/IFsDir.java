package net.bodz.bas.vfs;

public interface IFsDir
        extends IFsObject {

    boolean isIterable();

    /**
     * @return <code>null</code> If <code>childName</code> isn't existed, or the folder isn't
     *         accessible.
     */
    IFsObject getChild(String childName);

    /**
     * @throws UnsupportedOperationException
     *             If not iterable.
     * @see #isIterable()
     */
    Iterable<? extends IFsObject> children()
            throws VFSException;

    /**
     * @throws UnsupportedOperationException
     *             If not iterable.
     * @see #isIterable()
     */
    Iterable<? extends IFsObject> children(IFilenameFilter nameFilter)
            throws VFSException;

    /**
     * Create all parent dirs and this dir.
     * 
     * @return <code>true</code> if the dir is existed or the creation is succeeded.
     */
    boolean mkdirs();

    /**
     * Create this dir only.
     * 
     * @return <code>true</code> if the dir is existed or the creation is succeeded.
     */
    boolean mkdir();

}
