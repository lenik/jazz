package net.bodz.bas.vfs;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.traits.IAttributes;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public interface IFsEntry
        extends Cloneable {

    IFsEntry clone();

    /**
     * Get the VFS device which this file entry belongs to.
     * 
     * @return Non-<code>null</code> vfs device.
     */
    IVfsDevice getDevice();

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
     * Extension attributes.
     * 
     * @return <code>null</code> If no attribute.
     */
    IAttributes getAttributes();

    /**
     * Get flag bits of interesting selection only.
     * <p>
     * Some flag bits are expensive to test, so only the interesting bits should be specified to
     * reduce the test cost.
     * 
     * @param mask
     *            Bit mask to select which file flags are interesting and will be tested.
     * @return Modifier bits, may contains the bits which are not interesting.
     *         <p>
     *         If you want to restrict the returned bits to only the <code>mask</code> specified,
     *         then a post bit-wise AND operation should be performed.
     * @see FileFlags
     */
    int getFlags(int mask);

    /**
     * Get all file flags.
     * 
     * @see FileFlags
     */
    int getFlags();

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
     * @return <code>null</code> if the existence of this file is unknown.
     */
    Boolean exists();

    boolean isExisted();

    boolean isNotExisted();

    boolean isBlob();

    boolean isDirectory();

    boolean isReadable();

    boolean setReadable(boolean readable);

    boolean isWritable();

    boolean setWritable(boolean writable);

    boolean isHidden();

    boolean setHidden(boolean hidden);

    /**
     * Get the parent fs entry which this entry resides in.
     * <p>
     * Generally, this is the same as: <code>getPath().getParent().toFile()</code>.
     * 
     * @return Parent {@link IFsDir} object or <code>null</code> if there's none.
     * @throws VFSException
     *             If faield to resolve the parent file.
     */
    IFsDir getParentFile()
            throws FileResolveException;

    boolean isAutoCreateParents();

    void autoCreateParents();

    /**
     * Delete the fs entry, maybe file or directory.
     * <p>
     * The directory can only be deleted if it's empty. Delete an empty directory should preserve
     * any empty parent dirs.
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
    boolean renameTo(String destSpec)
            throws BadPathException;

    boolean createLink(String targetSpec, boolean symbolic)
            throws IOException;

}
