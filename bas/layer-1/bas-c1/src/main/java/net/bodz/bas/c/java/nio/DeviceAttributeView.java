package net.bodz.bas.c.java.nio;

import java.io.IOException;
import java.nio.file.attribute.FileAttributeView;

public interface DeviceAttributeView
        extends FileAttributeView {

    /**
     * Returns the name of the attribute view. Attribute views of this type have the name
     * {@code "device"}.
     */
    @Override
    String name();

    DeviceAttributes readAttributes()
            throws IOException;

}
