package net.bodz.bas.io.impl;

import java.io.File;
import java.io.IOException;


public class RafIn
        extends CroppedRafIn {

    public RafIn(String fileName, String mode)
            throws IOException {
        this(new File(fileName), mode);
    }

    public RafIn(File file, String mode)
            throws IOException {
        super(file, mode, 0, file.length());
    }

}
