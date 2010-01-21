package net.bodz.bas.io.resource.builtin;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import net.bodz.bas.io.resource.AbstractStreamInputSource;
import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.sio.InputStreamByteIn;
import net.bodz.bas.sio.ReaderCharIn;

public class InputStreamSource
        extends AbstractStreamInputSource {

    private final InputStream in;

    public InputStreamSource(InputStream in) {
        if (in == null)
            throw new NullPointerException("in");
        this.in = in;
    }

    /**
     * @return {@link InputStream} with {@link InputStream#close()} filtered out.
     */
    @Override
    public InputStream newInputStream()
            throws IOException {
        return new FilterInputStream(in) {
            @Override
            public void close()
                    throws IOException {
            }
        };
    }

    @Override
    public Reader newReader()
            throws IOException {
        return new InputStreamReader(newInputStream(), getCharset());
    }

    @Override
    public IByteIn newByteIn()
            throws IOException {
        return new InputStreamByteIn(newInputStream());
    }

    @Override
    public ICharIn newCharIn()
            throws IOException {
        return new ReaderCharIn(newReader());
    }

}
