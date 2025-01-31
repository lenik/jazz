package net.bodz.bas.io.res.builtin;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.OpenOption;

import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.impl.DecodedCharIn;
import net.bodz.bas.io.impl.EncodedCharOut;
import net.bodz.bas.io.res.AbstractRandomResource;
import net.bodz.bas.meta.decl.DefaultImpl;

public abstract class AbstractBinaryResource<This>
        extends AbstractRandomResource<This> {

    @DefaultImpl(DecodedCharIn.class)
    @Override
    public ICharIn newCharIn(OpenOption... options)
            throws IOException {
        IByteIn byteIn = newByteIn(options);
        DecodedCharIn charIn = new DecodedCharIn(byteIn, getCharset());
        return charIn;
    }

    @DefaultImpl(EncodedCharOut.class)
    @Override
    public ICharOut newCharOut(OpenOption... options)
            throws IOException {
        IByteOut byteOut = newByteOut(options);
        ICharOut cout = new EncodedCharOut(byteOut, getCharset());
        return cout;
    }

    @DefaultImpl(OutputStreamWriter.class)
    @Override
    public Writer newWriter(OpenOption... options)
            throws IOException {
        OutputStream outputStream = newOutputStream(options);
        return new OutputStreamWriter(outputStream, getCharset());
    }

}
