package net.bodz.bas.sio;

import java.io.File;
import java.io.IOException;

public class TempCharOut
        extends WriterCharOut {

    private final File file;

    public TempCharOut(TempWriter writer) {
        super(writer);
        this.file = writer.getFile();
    }

    public TempCharOut(String fileName)
            throws IOException {
        this(new TempWriter(fileName));
    }

    public TempCharOut(File file)
            throws IOException {
        this(new TempWriter(file));
    }

    public TempCharOut(String fileName, Object charset)
            throws IOException {
        this(new TempWriter(fileName, charset));
    }

    public TempCharOut(File file, Object charset)
            throws IOException {
        this(new TempWriter(file, charset));
    }

    public File getFile() {
        return file;
    }

}
