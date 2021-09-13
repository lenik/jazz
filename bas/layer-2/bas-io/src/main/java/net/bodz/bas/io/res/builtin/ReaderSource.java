package net.bodz.bas.io.res.builtin;

import java.io.FilterReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.OpenOption;

import net.bodz.bas.c.java.io.ReaderInputStream;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.adapter.InputStreamByteIn;
import net.bodz.bas.io.adapter.ReaderCharIn;
import net.bodz.bas.io.res.AbstractStreamInputSource;

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

    @Override
    public boolean isCharPreferred() {
        return true;
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
