package net.bodz.bas.ar;

import net.bodz.bas.c.java.nio.UnixModeBits;
import net.bodz.bas.io.res.IStreamInputSource;

public interface IArchiveEntry
        extends IStreamInputSource {

    int defaultMode = 0664;

    String getName();

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
    String getOwner();

    /**
     * @return <code>null</code> if it's unknown.
     */
    String getGroup();

    /**
     * @return <code>null</code> if no comment.
     */
    String getComment();

}
