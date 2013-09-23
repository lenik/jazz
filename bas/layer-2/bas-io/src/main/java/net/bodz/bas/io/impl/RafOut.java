package net.bodz.bas.io.impl;

import java.io.File;
import java.io.IOException;


public class RafOut
        extends CroppedRafOut {

    public RafOut(File file, String mode)
            throws IOException {
        this(file, mode, true);
    }

    public RafOut(File file, String mode, boolean expandable)
            throws IOException {
        super(file, mode, 0, expandable ? Long.MAX_VALUE : file.length());
    }

}
