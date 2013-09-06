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

public abstract class AbstractOutputStreamTarget
        extends AbstractStreamOutputTarget {

    @Override
    protected abstract OutputStream _newOutputStream(OpenOption... options)
            throws IOException;

    @Override
    protected Writer _newWriter(OpenOption... options)
            throws IOException {
        OutputStream out = _newOutputStream(options);
        return new OutputStreamWriter(out, getCharset());
    }

    @Override
    protected IByteOut _newByteOut(OpenOption... options)
            throws IOException {
        OutputStream out = _newOutputStream(options);
        return new OutputStreamByteOut(out);
    }

    @Override
    protected IPrintOut _newPrintOut(OpenOption... options)
            throws IOException {
        PrintStream ps = newPrintStream(options);
        return new PrintStreamPrintOut(ps);
    }

    @Override
    protected ICharOut _newCharOut(OpenOption... options)
            throws IOException {
        Writer writer = _newWriter(options);
        return new WriterCharOut(writer);
    }

}
