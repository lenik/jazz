package net.bodz.bas.io.resource;

import java.io.*;

import net.bodz.bas.sio.*;
import net.bodz.bas.sio.util.DecodedCharIn;

public abstract class JavaioStreamResource
        extends AbstractStreamResource {

    // IStreamInputSource

    @Override
    protected abstract InputStream _newInputStream()
            throws IOException;

    @Override
    protected Reader _newReader()
            throws IOException {
        InputStream in = _newInputStream();
        return new InputStreamReader(in, getCharset());
    }

    @Override
    public IByteIn _newByteIn()
            throws IOException {
        InputStream in = _newInputStream();
        return new InputStreamByteIn(in);
    }

    @Override
    public ICharIn _newCharIn()
            throws IOException {
        IByteIn in = _newByteIn();
        return new DecodedCharIn(in, getCharset().newDecoder());
    }

    // IStreamOutputTarget

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
