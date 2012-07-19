package net.bodz.bas.vfs;

import java.io.File;

import net.bodz.bas.traits.IAttributes;
import net.bodz.bas.vfs.path.IPath;

public interface IFsEntry
        extends Cloneable {

    /**
     * Extension attributes.
     * 
     * @return <code>null</code> If no attribute.
     */
    IAttributes getAttributes();

    IFsEntry clone();

    /**
     * @return non-<code>null</code> file container where this file entry belongs to.
     */
    IFileSystem getVolume();

    /**
     * @return non-<code>null</code> path which denotes this file entry.
     */
    IPath getPath();

    /**
     * The same as {@link #getPath()}, {@link IPath#getBaseName()}
     * 
     * @return <code>null</code> if file doesn't have a name
     */
    String getName();

    /**
     * Get the creation time of an fs entry.
     * 
     * @return <code>null</code> if create-time is unknown.
     */
    Long getCreationTime();

    /**
     * Get the last modified time of an fs entry.
     * 
     * @return <code>null</code> if last-modified-time is unknown.
     */
    Long getLastModifiedTime();

    /**
     * Change the last modified time of an fs entry.
     * 
     * @return <code>false</code> If failed to set the last modified time attribute.
     */
    boolean setLastModifiedTime(long lastModifiedTime);

    /**
     * Get modifier bits of interesting selection only.
     * <p>
     * Some modifier bits are expensive to test, so only the interesting bits should be specified to
     * reduce the test cost.
     * 
     * @param mask
     *            Bit mask to select which file modifiers are interesting and will be tested.
     * @return Modifier bits, may contains the bits which are not interesting.
     *         <p>
     *         If you want to restrict the returned bits to only the <code>mask</code> specified,
     *         then a post bit-wise AND operation should be performed.
     * @see FileModifier
     */
    int getModifiers(int mask);

    /**
     * Get all file modifiers.
     * 
     * @see FileModifier
     */
    int getModifiers();

    /**
     * @return <code>null</code> if the existence of this file is unknown.
     */
    Boolean exists();

    boolean isExisted();

    boolean isNotExisted();

    boolean isBlob();

    boolean isTree();

    boolean isReadable();

    boolean isWritable();

    boolean isHidden();

    /**
     * Get the parent fs entry which this entry resides in.
     * <p>
     * Generally, this is the same as: <code>getPath().getParent().toFile()</code>.
     * 
     * @return Parent {@link IFsTree} object or <code>null</code> if there's none.
     * @throws VFSException
     *             If faield to resolve the parent file.
     */
    IFsTree getParentFile()
            throws FileResolveException;

    boolean isAutoCreateParents();

    void setAutoCreateParents(boolean autoCreateTree);

    /**
     * Delete the fs entry, maybe file or directory.
     * 
     * If this is a directory, then only the directory represented by this fs entry is deleted. No
     * parent will be deleted.
     * 
     * @return <code>true</code> if and only if the entry is successfully deleted.
     * @see File#delete()
     */
    boolean delete();

    /**
     * Once deletion has been requested, it is not possible to cancel the request. This method
     * should therefore be used with care.
     * <p>
     * <i>Don't use delete on exit feature whenever possible. </i>
     * 
     * @return <code>false</code> If delete on exit isn't supported by underlying system.
     * @see File#deleteOnExit()
     */
    @Deprecated
    boolean deleteOnExit();

    /**
     * Renames the file denoted by this abstract pathname.
     * 
     * Many aspects of the behavior of this method are inherently platform-dependent: The rename
     * operation might not be able to move a file from one filesystem to another, it might not be
     * atomic, and it might not succeed if a file with the destination abstract pathname already
     * exists. The return value should always be checked to make sure that the rename operation was
     * successful.
     * 
     * @see File#renameTo(File)
     */
    boolean renameTo(IPath newPath);

}
