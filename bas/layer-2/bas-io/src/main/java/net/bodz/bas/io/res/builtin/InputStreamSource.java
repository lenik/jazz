package net.bodz.bas.io.res.builtin;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.OpenOption;

import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.adapter.InputStreamByteIn;
import net.bodz.bas.io.adapter.ReaderCharIn;
import net.bodz.bas.io.res.AbstractStreamInputSource;

public class InputStreamSource
        extends AbstractStreamInputSource {

    private final InputStream in;

    public InputStreamSource(InputStream in) {
        if (in == null)
            throw new NullPointerException("in");
        this.in = in;
    }

    public InputStreamSource(InputStream in, String charsetName) {
        super(charsetName);
        if (in == null)
            throw new NullPointerException("in");
        this.in = in;
    }

    public InputStreamSource(InputStream in, Charset charset) {
        super(charset);
        if (in == null)
            throw new NullPointerException("in");
        this.in = in;
    }

    @Override
    public boolean isCharPreferred() {
        return false;
    }

    /**
     * @return {@link InputStream} with {@link InputStream#close()} filtered out.
     */
    @Override
    protected InputStream _newInputStream(OpenOption... options)
            throws IOException {
        return new FilterInputStream(in) {
            @Override
            public void close()
                    throws IOException {
            }
        };
    }

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
        Reader in = newReader(options);
        return new ReaderCharIn(in);
    }

}
