package net.bodz.bas.io.tmp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public class TempWriter
        extends FilterWriter {

    private File file;
    private boolean cleaned;

    public TempWriter(String fileName)
            throws IOException {
        this(new File(fileName));
    }

    public TempWriter(File file)
            throws IOException {
        super(new FileWriter(file));
        this.file = file;
    }

    public TempWriter(String filename, Charset charset)
            throws IOException {
        super(new OutputStreamWriter(new FileOutputStream(filename), charset));
        this.file = new File(filename);
    }

    public TempWriter(File file, Charset charset)
            throws IOException {
        super(new OutputStreamWriter(new FileOutputStream(file), charset));
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    @Override
    public void close()
            throws IOException {
        super.close();
    }

    @Override
    protected void finalize()
            throws Throwable {
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
