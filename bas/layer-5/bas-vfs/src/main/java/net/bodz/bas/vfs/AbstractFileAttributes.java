package net.bodz.bas.vfs;

import java.io.IOException;
import java.nio.file.attribute.FileTime;

public class AbstractFileAttributes
        implements IFileAttributes {

    @Override
    public FileTime getCreationTime() {
        return null;
    }

    @Override
    public FileTime getLastModifiedTime() {
        return null;
    }

    @Override
    public boolean setLastModifiedTime(FileTime lastModifiedTime)
            throws IOException {
        return false;
    }

    @Override
    public boolean isSymLink() {
        return false;
    }

    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public boolean setHidden(boolean hidden)
            throws IOException {
        return false;
    }

    @Override
    public boolean isReadable() {
        return false;
    }

    @Override
    public boolean isWritable() {
        return false;
    }

    /** ⇱ Implementation Of {@link IFsBlobAttributes}. */
    /* _____________________________ */static section.iface __BLOB__;

    @Override
    public boolean isExecutable() {
        return false;
    }

    @Override
    public boolean isSeekable() {
        return false;
    }

    /** ⇱ Implementation Of {@link IFsDirAttributes}. */
    /* _____________________________ */static section.iface __DIR__;

    @Override
    public boolean isIterable() {
        return false;
    }

}
