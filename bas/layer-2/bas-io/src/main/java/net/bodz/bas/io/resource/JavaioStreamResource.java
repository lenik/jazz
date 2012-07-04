package net.bodz.bas.io.resource;

import java.io.*;

import net.bodz.bas.sio.*;
import net.bodz.bas.sio.util.DecodedCharIn;

public abstract class JavaioStreamResource
        extends AbstractStreamResource {

    // IStreamInputSource

    @Override
    public abstract InputStream newInputStream()
            throws IOException;

    @Override
    public Reader newReader()
            throws IOException {
        InputStream in = newInputStream();
        return new InputStreamReader(in, getCharset());
    }

    @Override
    public IByteIn newByteIn()
            throws IOException {
        InputStream in = newInputStream();
        return new InputStreamByteIn(in);
    }

    @Override
    public ICharIn newCharIn()
            throws IOException {
        IByteIn in = newByteIn();
        return new DecodedCharIn(in, getCharset().newDecoder());
    }

    // IStreamOutputTarget

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
