package net.bodz.bas.io.tmp;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.TextIndention;
import net.bodz.bas.io.adapter.PrintStreamPrintOut;
import net.bodz.bas.io.adapter.WriterPrintOut;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.meta.decl.NotNull;

public class TempOut
        extends TreeOutImpl {

    @NotNull
    private final Path file;

    public TempOut(@NotNull Path file, @NotNull IPrintOut printOut) {
        super(printOut, new TextIndention());
        this.file = file;
    }

    public TempOut(@NotNull TempWriter writer) {
        this(writer.getFile(), new WriterPrintOut(writer));
    }

    public TempOut(@NotNull Path file)
            throws IOException {
        this(file, new PrintStreamPrintOut(new PrintStream(Files.newOutputStream(file))));
    }

    public TempOut(Path file, Charset charset)
            throws IOException {
        this(file, new PrintStreamPrintOut(new PrintStream(Files.newOutputStream(file), false, charset.name())));
    }

    public TempOut(String fileName)
            throws IOException {
        this(Paths.get(fileName));
    }

    public TempOut(String fileName, Charset charset)
            throws IOException {
        this(Paths.get(fileName), charset);
    }

    @NotNull
    public Path getFile() {
        return file;
    }

}
