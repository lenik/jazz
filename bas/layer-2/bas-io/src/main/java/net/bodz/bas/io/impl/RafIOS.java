package net.bodz.bas.io.impl;

import java.io.File;
import java.io.IOException;

public class RafIOS
        extends CroppedRafIOS {

    public RafIOS(File file, String mode)
            throws IOException {
        super(file, mode, 0L, file.length());
    }

}
