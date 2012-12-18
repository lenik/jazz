package net.bodz.bas.io.resource.builtin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import net.bodz.bas.sio.BByteIn;
import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.OutputStreamByteOut;

public class BytesResource
        extends AbstractBinaryStreamResource {

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
    protected OutputStream _newOutputStream(boolean append)
            throws IOException {
        if (bytes == null)
            bytes = new ByteArrayOutputStream();

        // XXX BytesResource append
        if (!append)
            bytes.reset();

        return bytes;
    }

    @Override
    protected IByteOut _newByteOut(boolean append)
            throws IOException {
        return new OutputStreamByteOut(newOutputStream(append));
    }

    @Override
    protected IByteIn _newByteIn()
            throws IOException {
        byte[] snapshot = bytes.toByteArray();
        return new BByteIn(snapshot);
    }

}
