package net.bodz.bas.io.tmp;

import java.io.File;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.bodz.bas.c.java.nio.file.FileFn;
import net.bodz.bas.meta.decl.NotNull;

public class TempWriter
        extends FilterWriter {

    @NotNull
    private final Path file;
    private boolean cleaned;

    public TempWriter(@NotNull String fileName)
            throws IOException {
        this(Paths.get(fileName));
    }

    public TempWriter(@NotNull File file)
            throws IOException {
        this(file.toPath());
    }

    public TempWriter(@NotNull Path file)
            throws IOException {
        super(Files.newBufferedWriter(file));
        this.file = file;
    }

    public TempWriter(Path file, Charset charset)
            throws IOException {
        super(new OutputStreamWriter(Files.newOutputStream(file), charset));
        this.file = file;
    }

    public TempWriter(String filename, Charset charset)
            throws IOException {
        this(Paths.get(filename), charset);
    }

    public TempWriter(File file, Charset charset)
            throws IOException {
        this(file.toPath(), charset);
    }

    @NotNull
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
