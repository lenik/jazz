package net.bodz.bas.vfs;

import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public interface IFileAttributes
        extends BasicFileAttributes {

    boolean isBlob();

    boolean isReadable();

    boolean isWritable();

    boolean isExecutable();

    /**
     * Return whether this file is random seekable.
     * 
     * @return <code>true</code> If this file is ramdom seekable.
     */
    boolean isRandomAccessible();

    boolean isHidden();

    IFileAttributes NULL = new NullFileAttributes();
}

class NullBasicFileAttributes
        implements BasicFileAttributes {

    @Override
    public FileTime lastModifiedTime() {
        return null;
    }

    @Override
    public FileTime lastAccessTime() {
        return null;
    }

    @Override
    public FileTime creationTime() {
        return null;
    }

    @Override
    public boolean isRegularFile() {
        return false;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public boolean isSymbolicLink() {
        return false;
    }

    @Override
    public boolean isOther() {
        return false;
    }

    @Override
    public long size() {
        return 0;
    }

    @Override
    public Object fileKey() {
        return null;
    }

}

class NullFileAttributes
        extends NullBasicFileAttributes
        implements IFileAttributes {

    @Override
    public boolean isBlob() {
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

    @Override
    public boolean isExecutable() {
        return false;
    }

    @Override
    public boolean isRandomAccessible() {
        return false;
    }

    @Override
    public boolean isHidden() {
        return false;
    }

}
