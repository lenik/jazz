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
    public abstract OutputStream newOutputStream()
            throws IOException;

    @Override
    public Writer newWriter()
            throws IOException {
        OutputStream out = newOutputStream();
        return new OutputStreamWriter(out, getCharset());
    }

    @Override
    public IByteOut newByteOut()
            throws IOException {
        OutputStream out = newOutputStream();
        return new OutputStreamByteOut(out);
    }

    @Override
    public IPrintOut newPrintOut()
            throws IOException {
        PrintStream ps = newPrintStream();
        return new PrintStreamPrintOut(ps);
    }

    @Override
    public ICharOut newCharOut()
            throws IOException {
        Writer writer = newWriter();
        return new WriterCharOut(writer);
    }

}
