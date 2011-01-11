package net.bodz.bas.sio.nibbles;

import java.io.IOException;


public abstract class AbstractNibbleOut
        implements INibbleOut {

    @Override
    public void write4b(byte[] buf, int nibbleOffset, int nibbleCount)
            throws IOException {
        if (buf == null)
            throw new NullPointerException("buf");
        if (nibbleCount < 0)
            throw new IllegalArgumentException("nibbleCount is negative: " + nibbleCount);
        if (nibbleCount == 0)
            return;
        int byteOffset = nibbleOffset >> 1;
        if ((nibbleOffset & 1) != 0) {
            byte b = buf[byteOffset++];
            int low = b & 0x0F;
            write4b(low);
            nibbleCount--;
        }

        int byteCount = nibbleCount >> 1;
        if (isBuffered())
            write4bNonBuffered(buf, byteOffset, byteCount);
        else
            write4bBuffered(buf, byteOffset, byteCount);
        byteOffset += byteCount;

        if ((nibbleCount & 1) != 0) {
            int lastNibble = buf[byteOffset] >> 4;
            write4b(lastNibble);
        }
    }

    protected void write4bNonBuffered(byte[] buf, int off, int len)
            throws IOException {
        while (len > 0) {
            byte b = buf[off++];
            int high = b >> 4;
            int low = b & 0x0F;
            write4b(high);
            write4b(low);
            len--;
        }
    }

    protected void write4bBuffered(byte[] buf, int off, int len)
            throws IOException {
        write4bNonBuffered(buf, off, len);
    }

    /**
     * @see #write4bBuffered(byte[], int, int)
     * @see #write4bNonBuffered(byte[], int, int)
     */
    @Override
    public void write4b(byte[] buf)
            throws IOException {
        if (isBuffered())
            write4bNonBuffered(buf, 0, buf.length);
        else
            write4bBuffered(buf, 0, buf.length);
    }

    @Override
    public void flush()
            throws IOException {
        flush(false);
    }

    /**
     * Do nothing in default implementation.
     */
    @Override
    public void flush(boolean strict)
            throws IOException {
    }

    @Override
    public void close()
            throws IOException {
        flush(true);
    }

}
