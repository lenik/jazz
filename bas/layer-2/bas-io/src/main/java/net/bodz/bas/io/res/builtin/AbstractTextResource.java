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

public abstract class AbstractTextResource
        extends AbstractRandomResource {

    @Override
    protected IByteIn _newByteIn(OpenOption... options)
            throws IOException {
        ICharIn charIn = _newCharIn(options);
        EncodedByteIn byteIn = new EncodedByteIn(charIn, getCharset());
        return byteIn;
    }

    @Override
    protected IByteOut _newByteOut(OpenOption... options)
            throws IOException {
        OutputStream outputStream = _newOutputStream(options);
        return new OutputStreamByteOut(outputStream);
    }

    @Override
    protected OutputStream _newOutputStream(OpenOption... options)
            throws IOException {
        Writer writer = _newWriter(options);
        return new WriterOutputStream(writer, getCharset());
    }

}
