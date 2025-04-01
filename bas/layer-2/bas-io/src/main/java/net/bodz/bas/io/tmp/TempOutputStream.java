package net.bodz.bas.io.tmp;

import java.io.File;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.bodz.bas.c.java.nio.file.FileFn;

public class TempOutputStream
        extends FilterOutputStream {

    private final Path file;
    private boolean cleaned;

    public TempOutputStream(String fileName)
            throws IOException {
        this(Paths.get(fileName));
    }

    public TempOutputStream(File file)
            throws IOException {
        this(file.toPath());
    }

    public TempOutputStream(Path file)
            throws IOException {
        super(Files.newOutputStream(file));
        this.file = file;
    }

    public Path getFile() {
        return file;
    }

    @Override
    public void close()
            throws IOException {
        super.close();
        if (!cleaned && Files.exists(file)) {
            if (clean(file))
                cleaned = true;
        }
    }

    protected boolean clean(Path file) {
        return FileFn.delete(file);
    }

}
