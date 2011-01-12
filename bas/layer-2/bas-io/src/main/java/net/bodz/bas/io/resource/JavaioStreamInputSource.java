package net.bodz.bas.io.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.sio.InputStreamByteIn;
import net.bodz.bas.sio.nio.DecodedCharIn;

public abstract class JavaioStreamInputSource
        extends AbstractStreamInputSource {

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

}
