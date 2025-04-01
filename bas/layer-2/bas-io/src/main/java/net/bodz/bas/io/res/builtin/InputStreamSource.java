package net.bodz.bas.io.res.builtin;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.OpenOption;

import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.adapter.InputStreamByteIn;
import net.bodz.bas.io.adapter.ReaderCharIn;
import net.bodz.bas.io.res.AbstractStreamInputSource;
import net.bodz.bas.meta.decl.NotNull;

public class InputStreamSource
        extends AbstractStreamInputSource<InputStreamSource>{

    @NotNull
    private final InputStream in;

    public InputStreamSource(@NotNull  InputStream in) {
        this.in = in;
    }

    @NotNull
    public InputStream getInputStream() {
        return in;
    }

    /**
     * @return {@link InputStream} with {@link InputStream#close()} filtered out.
     */
    @NotNull
    @Override
    public InputStream newInputStream(OpenOption... options)
            throws IOException {
        return new FilterInputStream(in) {
            @Override
            public void close()
                    throws IOException {
            }
        };
    }

    @NotNull
    @Override
    public Reader newReader(OpenOption... options)
            throws IOException {
        InputStream in = newInputStream(options);
        return new InputStreamReader(in, getCharset());
    }

    @NotNull
    @Override
    public IByteIn newByteIn(OpenOption... options)
            throws IOException {
        InputStream in = newInputStream(options);
        return new InputStreamByteIn(in);
    }

    @NotNull
    @Override
    public ICharIn newCharIn(OpenOption... options)
            throws IOException {
        Reader in = newReader(options);
        return new ReaderCharIn(in);
    }

}
