package net.bodz.bas.sio;

import java.io.IOException;

/**
 * @test {@link LANibbleInTest}
 */
public class LANibbleIn
        implements INibbleIn {

    private final INibbleIn in;

    private int buffer;
    private boolean buffered;

    public LANibbleIn(INibbleIn in) {
        if (in == null)
            throw new NullPointerException("in");
        this.in = in;
    }

    @Override
    public boolean isBuffered() {
        return buffered;
    }

    @Override
    public synchronized int read4b()
            throws IOException {
        if (buffered) {
            buffered = false;
            return buffer;
        }
        return in.read4b();
    }

    @Override
    public synchronized int read4b(byte[] buf, int nibbleOffset, int nibbleCount)
            throws IOException {
        if (buf == null)
            throw new NullPointerException("buf");
        if (nibbleCount == 0)
            return 0;
        int cn_1 = 0;
        if (buffered) {
            int byteOffset = nibbleOffset >> 1;
            int high, low;
            if ((nibbleOffset & 1) == 0) {
                high = buffer << 4;
                low = buf[byteOffset] & 0x0f;
            } else {
                high = buf[byteOffset] & 0xf0;
                low = buffer;
            }
            buf[byteOffset] = (byte) (high | low);
            nibbleOffset++;
            nibbleCount--;
            buffered = false;
            cn_1++;
        }

        int cn = in.read4b(buf, nibbleOffset, nibbleCount);
        if (cn == -1)
            if (cn_1 == 0)
                return -1;
            else
                cn = 0;
        return cn_1 + cn;
    }

    @Override
    public synchronized int read4b(byte[] buf)
            throws IOException {
        return read4b(buf, 0, buf.length * 2);
    }

    public synchronized int look4b()
            throws IOException {
        if (!buffered) {
            buffer = read4b();
            if (buffer == -1)
                return -1;
            buffered = true;
        }
        return buffer;
    }

    @Override
    public synchronized void close()
            throws IOException {
        buffered = false;
        in.close();
    }

}
