package net.bodz.bas.vfs;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.c.java.nio.TreeDeleteOption;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public interface IFsObject
        extends IQueryable {

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
     * @return <code>null</code> if the existence of this file is unknown.
     */
    Boolean exists();

    boolean isExisted();

    boolean isNotExisted();

    IFileAttributes getAttributes();

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

    /**
     * Delete the fs entry, maybe file or directory.
     * <p>
     * In default mode, i.e.,when the {@link TreeDeleteOption#DELETE_TREE delete tree} option is
     * specified, the directory can only be deleted if it's empty.
     * <p>
     * If {@link TreeDeleteOption#REMOVE_EMPTY_PARENTS remove empty parents} option isn't specified,
     * delete an empty directory should preserve any empty parent dirs.
     * 
     * @return Count of fs objects actually been deleted.
     * @see File#delete()
     */
    int delete(DeleteOption... options)
            throws IOException;

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
    boolean deleteOnExit(DeleteOption... options);

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
    boolean renameTo(String target)
            throws BadPathException, IOException;

}
