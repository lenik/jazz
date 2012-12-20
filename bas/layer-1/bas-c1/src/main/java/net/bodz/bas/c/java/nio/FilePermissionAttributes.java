package net.bodz.bas.c.java.nio;

import java.nio.file.attribute.BasicFileAttributes;

public interface FilePermissionAttributes
        extends BasicFileAttributes {

    boolean isReadable();

    boolean isWritable();

    boolean isExecutable();

    boolean isDeletable();

}
