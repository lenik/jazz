package net.bodz.bas.ar;

import net.bodz.bas.c.java.nio.UnixModeBits;
import net.bodz.bas.io.res.IStreamInputSourceWrapper;

public interface IArchiveEntry
        extends IStreamInputSourceWrapper {

    int defaultMode = 0664;

    String getName();

    /**
     * @return <code>null</code> if no comment.
     */
    String getComment();

    boolean isDirectory();

    long getCreatedTime();

    long getModifiedTime();

    /**
     * @return <code>-1</code> if the size is unknown.
     */
    long getSize();

    /**
     * @return <code>-1</code> if the size is unknown.
     */
    long getArchivedSize();

    /**
     * @see UnixModeBits
     */
    int getMode();

    /**
     * @return <code>null</code> if it's unknown.
     */
    Integer getOwnerId();

    /**
     * @return <code>null</code> if it's unknown.
     */
    String getOwner();

    /**
     * @return <code>null</code> if it's unknown.
     */
    Integer getGroupId();

    /**
     * @return <code>null</code> if it's unknown.
     */
    String getGroup();

}
