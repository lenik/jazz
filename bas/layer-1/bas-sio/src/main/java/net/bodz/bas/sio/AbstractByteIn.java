package net.bodz.bas.sio;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

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
    public int read(ByteBuffer buffer)
            throws IOException {
        int cbRead = 0;
        while (buffer.hasRemaining()) {
            int b = read();
            if (b == -1)
                return cbRead == 0 ? -1 : cbRead;
            buffer.put((byte) b);
        }
        return cbRead;
    }

    class InputStreamAdapter
            extends InputStream {

        @Override
        public int read()
                throws IOException {
            return AbstractByteIn.this.read();
        }

        @Override
        public int read(byte[] b)
                throws IOException {
            return AbstractByteIn.this.read(b);
        }

        @Override
        public int read(byte[] b, int off, int len)
                throws IOException {
            return AbstractByteIn.this.read(b, off, len);
        }

    }

    public InputStream toInputStream() {
        return new InputStreamAdapter();
    }

}
