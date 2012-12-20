package net.bodz.bas.c.java.nio;

import java.io.IOException;
import java.nio.file.attribute.FileAttributeView;

public interface FilePermissionAttributeView
        extends FileAttributeView {

    /**
     * Returns the name of the attribute view. Attribute views of this type have the name
     * {@code "permission"}.
     */
    @Override
    String name();

    FilePermissionAttributes readAttributes()
            throws IOException;

}
