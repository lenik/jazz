package net.bodz.bas.io.resource;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;

import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.OutputStreamByteOut;
import net.bodz.bas.sio.PrintStreamPrintOut;
import net.bodz.bas.sio.WriterCharOut;

public abstract class JavaioStreamOutputTarget
        extends AbstractStreamOutputTarget {

    @Override
    protected abstract OutputStream _newOutputStream(boolean append)
            throws IOException;

    @Override
    protected Writer _newWriter(boolean append)
            throws IOException {
        OutputStream out = _newOutputStream(append);
        return new OutputStreamWriter(out, getCharset());
    }

    @Override
    protected IByteOut _newByteOut(boolean append)
            throws IOException {
        OutputStream out = _newOutputStream(append);
        return new OutputStreamByteOut(out);
    }

    @Override
    protected IPrintOut _newPrintOut(boolean append)
            throws IOException {
        PrintStream ps = newPrintStream(append);
        return new PrintStreamPrintOut(ps);
    }

    @Override
    protected ICharOut _newCharOut(boolean append)
            throws IOException {
        Writer writer = _newWriter(append);
        return new WriterCharOut(writer);
    }

}
