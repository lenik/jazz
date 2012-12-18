package net.bodz.bas.io.resource.builtin;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import net.bodz.bas.io.resource.AbstractStreamResource;
import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.sio.util.DecodedCharIn;
import net.bodz.bas.sio.util.EncodedCharOut;

public abstract class AbstractBinaryStreamResource
        extends AbstractStreamResource {

    @Override
    protected Writer _newWriter(boolean append)
            throws IOException {
        OutputStream outputStream = newOutputStream(append);
        return new OutputStreamWriter(outputStream, getCharset());
    }

    @Override
    public ICharIn _newCharIn()
            throws IOException {
        IByteIn byteIn = _newByteIn();
        DecodedCharIn charIn = new DecodedCharIn(byteIn, getCharset());
        return charIn;
    }

    @Override
    public ICharOut _newCharOut(boolean append)
            throws IOException {
        ICharOut cout = new EncodedCharOut(_newByteOut(append), getCharset().newEncoder());
        return cout;
    }

}
