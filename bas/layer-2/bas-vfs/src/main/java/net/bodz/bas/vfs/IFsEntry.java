package net.bodz.bas.vfs;

import java.io.IOException;

import net.bodz.bas.type.traits.IAttributes;
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
    IVolume getVolume();

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
     * @return <code>null</code> if file doesn't have a name, or empty string if file doesn't have
     *         an extension.
     */
    String getExtension(boolean withDot, int maxWords);

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
     * @return <code>false</code> If there is no last modified time attribute.
     * @throws IOException
     *             If failed to set the last modified time.
     */
    boolean setLastModifiedTime(long date)
            throws IOException;

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
     */
    IFsTree getParentFile();

    boolean getCreateParentsMode();

    void setCreateParentsMode(boolean createParents);

    /**
     * Delete the fs entry, maybe file or directory.
     * 
     * If this is a directory, then only the directory represented by this fs entry is deleted. No
     * parent will be deleted.
     * 
     * @return <code>true</code> if and only if the entry is successfully deleted.
     */
    boolean delete();

    /**
     * Once deletion has been requested, it is not possible to cancel the request. This method
     * should therefore be used with care.
     * 
     * @return <code>false</code> If delete on exit isn't supported by underlying system.
     */
    boolean deleteOnExit();

}
