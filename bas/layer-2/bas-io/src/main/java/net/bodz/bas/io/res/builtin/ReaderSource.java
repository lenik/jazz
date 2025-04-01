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
import net.bodz.bas.meta.decl.NotNull;

public class ReaderSource
        extends AbstractStreamInputSource<ReaderSource> {

    @NotNull
    private final Reader in;

    public ReaderSource(@NotNull Reader reader) {
        this.in = reader;
    }

    @NotNull
    public Reader getReader() {
        return in;
    }

    @Override
    public boolean isTextModePreferred() {
        return true;
    }

    /**
     * @return {@link Reader} with {@link Reader#close()} filtered out.
     */
    @NotNull
    @Override
    public Reader newReader(OpenOption... options)
            throws IOException {
        return new FilterReader(in) {
            @Override
            public void close() {
            }
        };
    }

    @NotNull
    @Override
    public InputStream newInputStream(OpenOption... options)
            throws IOException {
        return new ReaderInputStream(newReader(options), getCharset());
    }

    @NotNull
    @Override
    public IByteIn newByteIn(OpenOption... options)
            throws IOException {
        return new InputStreamByteIn(newInputStream(options));
    }

    @NotNull
    @Override
    public ICharIn newCharIn(OpenOption... options)
            throws IOException {
        return new ReaderCharIn(newReader(options));
    }

}
