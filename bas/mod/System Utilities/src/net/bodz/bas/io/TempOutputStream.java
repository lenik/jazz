package net.bodz.bas.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;

public class TempOutputStream extends FilterOutputStream {

    private File    file;
    private boolean cleaned;

    public TempOutputStream(String fileName) throws IOException {
        this(new File(fileName));
    }

    public TempOutputStream(File file) throws IOException {
        super(new FileOutputStream(file));
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    protected void finalize() throws Throwable {
        tryClean();
    }

    private void tryClean() {
        if (!cleaned && file.exists()) {
            if (clean(file))
                cleaned = true;
        }
    }

    protected boolean clean(File file) {
        return file.delete();
    }

}
