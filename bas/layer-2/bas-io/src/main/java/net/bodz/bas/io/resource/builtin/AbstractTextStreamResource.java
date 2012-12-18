package net.bodz.bas.io.resource.builtin;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

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
    protected OutputStream _newOutputStream(boolean append)
            throws IOException {
        Writer writer = _newWriter(append);
        return new WriterOutputStream(writer, getCharset());
    }

    @Override
    protected IByteIn _newByteIn()
            throws IOException {
        ICharIn charIn = _newCharIn();
        EncodedByteIn byteIn = new EncodedByteIn(charIn, getCharset());
        return byteIn;
    }

    @Override
    protected IByteOut _newByteOut(boolean append)
            throws IOException {
        OutputStream outputStream = _newOutputStream(append);
        return new OutputStreamByteOut(outputStream);
    }

}
