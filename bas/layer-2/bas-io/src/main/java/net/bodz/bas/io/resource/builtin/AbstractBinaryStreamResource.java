package net.bodz.bas.io.resource.builtin;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.OpenOption;

import net.bodz.bas.io.resource.AbstractStreamResource;
import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.sio.util.DecodedCharIn;
import net.bodz.bas.sio.util.EncodedCharOut;

public abstract class AbstractBinaryStreamResource
        extends AbstractStreamResource {

    @Override
    public ICharIn _newCharIn(OpenOption... options)
            throws IOException {
        IByteIn byteIn = _newByteIn(options);
        DecodedCharIn charIn = new DecodedCharIn(byteIn, getCharset());
        return charIn;
    }

    @Override
    public ICharOut _newCharOut(OpenOption... options)
            throws IOException {
        IByteOut byteOut = _newByteOut(options);
        ICharOut cout = new EncodedCharOut(byteOut, getCharset());
        return cout;
    }

    @Override
    protected Writer _newWriter(OpenOption... options)
            throws IOException {
        OutputStream outputStream = _newOutputStream(options);
        return new OutputStreamWriter(outputStream, getCharset());
    }

}
