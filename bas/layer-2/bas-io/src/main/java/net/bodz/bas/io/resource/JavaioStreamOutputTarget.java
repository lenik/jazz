package net.bodz.bas.io.resource;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.nio.file.OpenOption;

import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.OutputStreamByteOut;
import net.bodz.bas.sio.PrintStreamPrintOut;
import net.bodz.bas.sio.WriterCharOut;

public abstract class JavaioStreamOutputTarget
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
