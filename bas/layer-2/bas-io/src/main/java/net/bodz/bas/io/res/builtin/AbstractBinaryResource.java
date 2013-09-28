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

public abstract class AbstractBinaryResource
        extends AbstractRandomResource {

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
