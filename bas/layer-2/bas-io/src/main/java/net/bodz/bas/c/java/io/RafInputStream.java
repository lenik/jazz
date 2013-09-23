package net.bodz.bas.c.java.io;

import java.io.File;
import java.io.IOException;

public class RafInputStream
        extends CroppedRafInputStream {

    public RafInputStream(String fileName, String mode)
            throws IOException {
        this(new File(fileName), mode);
    }

    public RafInputStream(File file, String mode)
            throws IOException {
        super(file, mode, 0, file.length());
    }

}
