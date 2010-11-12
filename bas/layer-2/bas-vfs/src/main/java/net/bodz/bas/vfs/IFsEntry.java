package net.bodz.bas.vfs;

import java.io.IOException;

import net.bodz.bas.type.traits.IAttributes;
import net.bodz.bas.vfs.path.IPath;

public interface IFsEntry
        extends Cloneable {

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
     * Extension attributes.
     * 
     * @return <code>null</code> If no attribute.
     */
    IAttributes getAttributes();

    /**
     * @return <code>null</code> if file doesn't have a name, or empty string if file doesn't have
     *         an extension.
     */
    String getExtension(boolean withDot, int maxWords);

    /**
     * @return <code>null</code> if create-time is unknown.
     */
    Long getCreatedTime();

    /**
     * @return <code>null</code> if last-modified-time is unknown.
     */
    Long getLastModifiedTime();

    void setLastModifiedTime(long date)
            throws IOException;

    /**
     * @return <code>null</code> if the existence of this file is unknown.
     */
    Boolean exists();

    boolean isExisted();

    boolean isNotExisted();

    boolean isFile();

    boolean isFolder();

    boolean isReadable();

    boolean isWritable();

    boolean isHidden();

    IFolder getParentFolder();

    boolean getCreateParentsMode();

    void setCreateParentsMode(boolean createParents);

}
