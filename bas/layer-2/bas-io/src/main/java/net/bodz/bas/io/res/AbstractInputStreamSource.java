package net.bodz.bas.io.res;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.OpenOption;

import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.adapter.InputStreamByteIn;
import net.bodz.bas.io.impl.DecodedCharIn;
import net.bodz.bas.meta.decl.DefaultImpl;
import net.bodz.bas.meta.decl.NotNull;

public abstract class AbstractInputStreamSource<This>
        extends AbstractStreamInputSource<This> {

    @NotNull
    @Override
    public abstract InputStream newInputStream(OpenOption... options)
            throws IOException;

    @NotNull
    @DefaultImpl(InputStreamReader.class)
    @Override
    public Reader newReader(OpenOption... options)
            throws IOException {
        InputStream in = newInputStream(options);
        return new InputStreamReader(in, getCharset());
    }

    @NotNull
    @DefaultImpl(InputStreamByteIn.class)
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
        IByteIn in = newByteIn(options);
        return new DecodedCharIn(in, getCharset().newDecoder());
    }

}
