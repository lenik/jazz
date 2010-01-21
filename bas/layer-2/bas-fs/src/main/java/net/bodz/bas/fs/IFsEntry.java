package net.bodz.bas.fs;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import net.bodz.bas.type.traits.IAttributes;

public interface IFsEntry
        extends IAttributes {

    /**
     * @return <code>null</code> if not a {@link File}.
     */
    File getFile();

    /**
     * @return <code>null</code> if no {@link URI}.
     */
    URI getURI();

    /**
     * @return <code>null</code> if no {@link URL}.
     */
    URL getURL();

    /**
     * @return <code>null</code> if file doesn't have a name
     */
    String getName();

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

    boolean isDeletable();

    IFolder getParentFolder();

    boolean getCreateParentsMode();

    void setCreateParentsMode(boolean createParents);

}
