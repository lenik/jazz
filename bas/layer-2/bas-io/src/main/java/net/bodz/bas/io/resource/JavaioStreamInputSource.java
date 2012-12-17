package net.bodz.bas.io.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.sio.InputStreamByteIn;
import net.bodz.bas.sio.util.DecodedCharIn;

public abstract class JavaioStreamInputSource
        extends AbstractStreamInputSource {

    @Override
    protected abstract InputStream _newInputStream()
            throws IOException;

    @Override
    protected Reader _newReader()
            throws IOException {
        InputStream in = newInputStream();
        return new InputStreamReader(in, getCharset());
    }

    @Override
    public IByteIn _newByteIn()
            throws IOException {
        InputStream in = newInputStream();
        return new InputStreamByteIn(in);
    }

    @Override
    public ICharIn _newCharIn()
            throws IOException {
        IByteIn in = _newByteIn();
        return new DecodedCharIn(in, getCharset().newDecoder());
    }

}
