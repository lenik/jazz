package net.bodz.bas.io;

import java.io.File;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;

public class TempWriter extends FilterWriter {

    private File file;
    private boolean cleaned;

    public TempWriter(String fileName) throws IOException {
        this(new File(fileName));
    }

    public TempWriter(File file) throws IOException {
        super(new FileWriter(file));
        this.file = file;
    }

    public TempWriter(String fileName, Object charset) throws IOException {
        super(Files.getWriter(fileName, charset));
        this.file = new File(fileName);
    }

    public TempWriter(File file, Object charset) throws IOException {
        super(Files.getWriter(file, charset));
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
