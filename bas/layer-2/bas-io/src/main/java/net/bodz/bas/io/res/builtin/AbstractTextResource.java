package net.bodz.bas.io.res.builtin;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.file.OpenOption;

import net.bodz.bas.c.java.io.WriterOutputStream;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.adapter.OutputStreamByteOut;
import net.bodz.bas.io.impl.EncodedByteIn;
import net.bodz.bas.io.res.AbstractRandomResource;
import net.bodz.bas.meta.decl.DefaultImpl;
import net.bodz.bas.meta.decl.NotNull;

public abstract class AbstractTextResource<This>
        extends AbstractRandomResource<This> {

    @Override
    public boolean isTextModePreferred() {
        return true;
    }

    @NotNull
    @DefaultImpl(EncodedByteIn.class)
    @Override
    public IByteIn newByteIn(OpenOption... options)
            throws IOException {
        ICharIn charIn = newCharIn(options);
        EncodedByteIn byteIn = new EncodedByteIn(charIn, getCharset());
        return byteIn;
    }

    @NotNull
    @DefaultImpl(OutputStreamByteOut.class)
    @Override
    public IByteOut newByteOut(OpenOption... options)
            throws IOException {
        OutputStream outputStream = newOutputStream(options);
        return new OutputStreamByteOut(outputStream);
    }

    @NotNull
    @DefaultImpl(WriterOutputStream.class)
    @Override
    public OutputStream newOutputStream(OpenOption... options)
            throws IOException {
        Writer writer = newWriter(options);
        return new WriterOutputStream(writer, getCharset());
    }

}
