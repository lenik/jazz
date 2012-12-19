package net.bodz.bas.vfs;

public interface IFileType {

    boolean isExisted();

    boolean isNotExisted();

    boolean isBlob();

    boolean isDirectory();

    boolean isReadable();

    boolean setReadable(boolean readable);

    boolean isWritable();

    boolean setWritable(boolean writable);

    boolean isHidden();

    boolean setHidden(boolean hidden);

    boolean isExecutable();

    boolean setExecutable(boolean executable);

}
