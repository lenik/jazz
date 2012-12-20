package net.bodz.bas.io.resource.builtin;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.file.OpenOption;

import net.bodz.bas.io.WriterOutputStream;
import net.bodz.bas.io.resource.AbstractStreamResource;
import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.sio.OutputStreamByteOut;
import net.bodz.bas.sio.util.EncodedByteIn;

public abstract class AbstractTextStreamResource
        extends AbstractStreamResource {

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
