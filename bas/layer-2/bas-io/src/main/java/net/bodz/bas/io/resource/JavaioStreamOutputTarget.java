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
    protected abstract OutputStream _newOutputStream()
            throws IOException;

    @Override
    protected Writer _newWriter()
            throws IOException {
        OutputStream out = _newOutputStream();
        return new OutputStreamWriter(out, getCharset());
    }

    @Override
    protected IByteOut _newByteOut()
            throws IOException {
        OutputStream out = _newOutputStream();
        return new OutputStreamByteOut(out);
    }

    @Override
    protected IPrintOut _newPrintOut()
            throws IOException {
        PrintStream ps = newPrintStream();
        return new PrintStreamPrintOut(ps);
    }

    @Override
    protected ICharOut _newCharOut()
            throws IOException {
        Writer writer = _newWriter();
        return new WriterCharOut(writer);
    }

}
