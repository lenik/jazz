package net.bodz.bas.io.tmp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import net.bodz.bas.io.adapter.OutputStreamByteOut;
import net.bodz.bas.meta.decl.NotNull;

public class TempByteOut
        extends OutputStreamByteOut {

    @NotNull
    private final Path file;

    public TempByteOut(TempOutputStream out) {
        super(out);
        this.file = out.getFile();
    }

    public TempByteOut(String fileName)
            throws IOException {
        this(new TempOutputStream(fileName));
    }

    public TempByteOut(Path file)
            throws IOException {
        this(new TempOutputStream(file));
    }

    public TempByteOut(File file)
            throws IOException {
        this(new TempOutputStream(file));
    }

    @NotNull
    public Path getFile() {
        return file;
    }

}
