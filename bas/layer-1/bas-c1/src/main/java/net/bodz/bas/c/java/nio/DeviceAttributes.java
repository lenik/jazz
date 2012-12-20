package net.bodz.bas.c.java.nio;

import java.nio.file.attribute.BasicFileAttributes;

public interface DeviceAttributes
        extends BasicFileAttributes {

    boolean isRandomAccessible();

    boolean isBlockAccessible();

}
