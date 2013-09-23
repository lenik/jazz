package net.bodz.bas.c.java.io;

import java.io.File;
import java.io.IOException;

public class RafOutputStream
        extends CroppedRafOutputStream {

    public RafOutputStream(File file, String mode)
            throws IOException {
        this(file, mode, true);
    }

    public RafOutputStream(File file, String mode, boolean expandable)
            throws IOException {
        super(file, mode, 0, expandable ? Long.MAX_VALUE : file.length());
    }

}
