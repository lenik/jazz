package net.bodz.bas.io.res;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.nio.file.OpenOption;

import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.adapter.OutputStreamByteOut;
import net.bodz.bas.io.adapter.PrintStreamPrintOut;
import net.bodz.bas.io.adapter.WriterCharOut;

public abstract class AbstractOutputStreamTarget<This>
        extends AbstractStreamOutputTarget<This> {

    @Override
    public abstract OutputStream newOutputStream(OpenOption... options)
            throws IOException;

    @Override
    public Writer newWriter(OpenOption... options)
            throws IOException {
        OutputStream out = newOutputStream(options);
        return new OutputStreamWriter(out, getCharset());
    }

    @Override
    public IByteOut newByteOut(OpenOption... options)
            throws IOException {
        OutputStream out = newOutputStream(options);
        return new OutputStreamByteOut(out);
    }

    @Override
    public IPrintOut newPrintOut(OpenOption... options)
            throws IOException {
        PrintStream ps = newPrintStream(options);
        return new PrintStreamPrintOut(ps);
    }

    @Override
    public ICharOut newCharOut(OpenOption... options)
            throws IOException {
        Writer writer = newWriter(options);
        return new WriterCharOut(writer);
    }

}
