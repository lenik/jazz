package net.bodz.bas.io.tmp;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;

import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.PrintStreamPrintOut;
import net.bodz.bas.sio.TreeOutImpl;
import net.bodz.bas.sio.WriterPrintOut;
import net.bodz.bas.sio.util.TextIndention;

public class TempOut
        extends TreeOutImpl {

    private final File file;

    public TempOut(File file, IPrintOut printOut) {
        super(printOut, new TextIndention());
        this.file = file;
    }

    public TempOut(TempWriter writer) {
        this(writer.getFile(), new WriterPrintOut(writer));
    }

    public TempOut(String fileName)
            throws IOException {
        this(new File(fileName), new PrintStreamPrintOut(new PrintStream(fileName)));
    }

    public TempOut(File file)
            throws IOException {
        this(file, new PrintStreamPrintOut(new PrintStream(file)));
    }

    public TempOut(String fileName, Charset charset)
            throws IOException {
        this(new File(fileName), new PrintStreamPrintOut(new PrintStream(fileName, charset.name())));
    }

    public TempOut(File file, Charset charset)
            throws IOException {
        this(file, new PrintStreamPrintOut(new PrintStream(file, charset.name())));
    }

    public File getFile() {
        return file;
    }

}
