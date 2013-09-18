package net.bodz.bas.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import net.bodz.bas.io.adapter.ByteInInputStream;

public abstract class AbstractByteIn
        implements IByteIn {

    @Override
    public int read(byte[] buf)
            throws IOException {
        return read(buf, 0, buf.length);
    }

    /**
     * XXX - Please check in more detail.
     */
    @Override
    public int read(ByteBuffer buf)
            throws IOException {
        int cbRead = 0;
        while (buf.hasRemaining()) {
            int b = read();
            if (b == -1)
                return cbRead == 0 ? -1 : cbRead;
            buf.put((byte) b);
            cbRead++;
        }
        return cbRead;
    }

    @Override
    public void close()
            throws IOException {
    }

    public InputStream toInputStream() {
        return new ByteInInputStream(this);
    }

}
