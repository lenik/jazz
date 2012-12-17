package net.bodz.bas.io.resource.builtin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import net.bodz.bas.io.resource.AbstractStreamResource;
import net.bodz.bas.sio.*;
import net.bodz.bas.sio.util.DecodedCharIn;

public class BytesResource
        extends AbstractStreamResource {

    ByteArrayOutputStream bytes;

    public BytesResource() {
        this(null);
    }

    public BytesResource(ByteArrayOutputStream bytes) {
        this.bytes = bytes;
    }

    public boolean isAllocated() {
        return bytes != null;
    }

    public void unallocate() {
        bytes = null;
    }

    public byte[] getData() {
        if (bytes == null)
            return null;
        else
            return bytes.toByteArray();
    }

    public int getLength() {
        if (bytes == null)
            return 0;
        else
            return bytes.size();
    }

    /**
     * @return {@link OutputStream} with {@link OutputStream#close()} filtered out.
     */
    @Override
    protected OutputStream _newOutputStream()
            throws IOException {
        if (bytes == null)
            bytes = new ByteArrayOutputStream();

        if (!isAppendMode())
            bytes.reset();

        return bytes;
    }

    @Override
    protected Writer _newWriter()
            throws IOException {
        return new OutputStreamWriter(newOutputStream(), getCharset());
    }

    @Override
    protected IByteOut _newByteOut()
            throws IOException {
        return new OutputStreamByteOut(newOutputStream());
    }

    @Override
    protected ICharOut _newCharOut()
            throws IOException {
        return newPrintOut();
    }

    @Override
    protected IPrintOut _newPrintOut()
            throws IOException {
        return new WriterPrintOut(newWriter());
    }

    @Override
    protected IByteIn _newByteIn()
            throws IOException {
        byte[] snapshot = bytes.toByteArray();
        return new BByteIn(snapshot);
    }

    @Override
    public ICharIn _newCharIn()
            throws IOException {
        IByteIn byteIn = _newByteIn();
        DecodedCharIn charIn = new DecodedCharIn(byteIn, getCharset());
        return charIn;
    }

}
