package net.bodz.bas.io.resource.builtin;

import java.io.FilterReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.OpenOption;

import net.bodz.bas.io.ReaderInputStream;
import net.bodz.bas.io.resource.AbstractStreamInputSource;
import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.sio.InputStreamByteIn;
import net.bodz.bas.sio.ReaderCharIn;

public class ReaderSource
        extends AbstractStreamInputSource {

    private final Reader in;

    /**
     * @throws NullPointerException
     *             If <code>reader</code> is <code>null</code>.
     */
    public ReaderSource(Reader in) {
        if (in == null)
            throw new NullPointerException("in");
        this.in = in;
    }

    /**
     * @return {@link Reader} with {@link Reader#close()} filtered out.
     */
    @Override
    protected Reader _newReader(OpenOption... options)
            throws IOException {
        return new FilterReader(in) {
            @Override
            public void close()
                    throws IOException {
            }
        };
    }

    @Override
    protected InputStream _newInputStream(OpenOption... options)
            throws IOException {
        return new ReaderInputStream(newReader(options), getCharset());
    }

    @Override
    public IByteIn _newByteIn(OpenOption... options)
            throws IOException {
        return new InputStreamByteIn(newInputStream(options));
    }

    @Override
    public ICharIn _newCharIn(OpenOption... options)
            throws IOException {
        return new ReaderCharIn(newReader(options));
    }

}
