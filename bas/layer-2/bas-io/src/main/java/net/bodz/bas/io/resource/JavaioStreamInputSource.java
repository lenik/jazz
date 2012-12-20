package net.bodz.bas.io.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.OpenOption;

import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.sio.InputStreamByteIn;
import net.bodz.bas.sio.util.DecodedCharIn;

public abstract class JavaioStreamInputSource
        extends AbstractStreamInputSource {

    @Override
    protected abstract InputStream _newInputStream(OpenOption... options)
            throws IOException;

    @Override
    protected Reader _newReader(OpenOption... options)
            throws IOException {
        InputStream in = newInputStream(options);
        return new InputStreamReader(in, getCharset());
    }

    @Override
    public IByteIn _newByteIn(OpenOption... options)
            throws IOException {
        InputStream in = newInputStream(options);
        return new InputStreamByteIn(in);
    }

    @Override
    public ICharIn _newCharIn(OpenOption... options)
            throws IOException {
        IByteIn in = _newByteIn(options);
        return new DecodedCharIn(in, getCharset().newDecoder());
    }

}
