package net.bodz.bas.vfs;

import java.io.IOException;
import java.nio.file.attribute.FileTime;

public interface IMutableFileAttributes
        extends IFileAttributes {

    /**
     * Change the last modified time of an fs entry.
     */
    void setTimes(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime createTime)
            throws IOException;

}
