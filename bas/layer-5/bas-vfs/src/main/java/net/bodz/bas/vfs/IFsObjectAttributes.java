package net.bodz.bas.vfs;

import java.io.IOException;
import java.nio.file.attribute.FileTime;

public interface IFsObjectAttributes {

    /**
     * Get the creation time of an fs entry.
     * 
     * @return <code>0</code> if create-time is unknown.
     */
    FileTime getCreationTime();

    /**
     * Get the last modified time of an fs entry.
     * 
     * @return <code>0</code> if last-modified-time is unknown.
     */
    FileTime getLastModifiedTime();

    /**
     * Change the last modified time of an fs entry.
     * 
     * @return <code>false</code> If failed to set the last modified time attribute.
     */
    boolean setLastModifiedTime(FileTime lastModifiedTime)
            throws IOException;

    boolean isSymLink();

    boolean isHidden();

    boolean setHidden(boolean hidden)
            throws IOException;

    boolean isReadable();

    boolean isWritable();

}
