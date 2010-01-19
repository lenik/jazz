package net.bodz.bas.sio;

import java.io.File;
import java.io.IOException;

public class TempByteOut
        extends OutputStreamByteOut {

    private final File file;

    public TempByteOut(TempOutputStream out) {
        super(out);
        this.file = out.getFile();
    }

    public TempByteOut(String fileName)
            throws IOException {
        this(new TempOutputStream(fileName));
    }

    public TempByteOut(File file)
            throws IOException {
        this(new TempOutputStream(file));
    }

    public File getFile() {
        return file;
    }

}
