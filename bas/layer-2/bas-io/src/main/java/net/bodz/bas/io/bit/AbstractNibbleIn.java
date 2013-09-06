package net.bodz.bas.io.bit;

import java.io.IOException;

public abstract class AbstractNibbleIn
        implements INibbleIn {

    @Override
    public int read4b(byte[] buf, int nibbleOffset, int nibbleCount)
            throws IOException {
        if (buf == null)
            throw new NullPointerException("buf");
        if (nibbleCount < 0)
            throw new IllegalArgumentException("nibbleCount is negative: " + nibbleCount);
        if (nibbleCount == 0)
            return 0;
        int byteOffset = nibbleOffset >> 1;
        int readCount = 0;
        if ((nibbleOffset & 1) != 0) {
            int low = read4b();
            if (low == -1)
                return -1;
            // assert low >= 0 && low < 0x10;
            int b = buf[byteOffset];
            b = (b & 0xF0) | low;
            buf[byteOffset++] = (byte) b;
            nibbleOffset++;
            nibbleCount--;
            readCount++;
        }

        if (nibbleCount > 1) {
            int byteCount = nibbleCount >> 1;
            int cn;
            if (isBuffered())
                cn = read4bBuffered(buf, byteOffset, byteCount);
            else
                cn = read4bNonBuffered(buf, byteOffset, byteCount);

            if (cn != -1) {
                nibbleOffset += cn;
                nibbleCount -= cn;
                readCount += cn;
            }
        }

        // if nibbleCount>1, either input reaches EOF, or the buffer overflow.
        // while (nibbleCount > 0) {
        if (nibbleCount == 1) {
            int nib = read4b();
            if (nib != -1) {
                byteOffset = nibbleOffset >> 1;
                // if (byteOffset > buf.length)
                // throw new IndexOutOfBoundsException();
                int high, low;
                if ((nibbleOffset & 1) == 0) {
                    high = nib << 4;
                    low = buf[byteOffset] & 0x0f;
                } else {
                    high = buf[byteOffset] & 0xf0;
                    low = nib;
                }
                buf[byteOffset] = (byte) (high | low);
                // nibbleOffset++;
                // nibbleCount--;
                readCount++;
            }
        }

        if (readCount == 0)
            return -1;
        return readCount;
    }

    /**
     * Some nibbles by previous read were buffered.
     * 
     * @return nibble count successfully read, -1 if EOF reached.
     */
    protected int read4bBuffered(byte[] buf, int off, int len)
            throws IOException {
        int n = 0;
        for (int i = 0; i < len; i++) {
            int byt = read4b();
            if (byt == -1)
                break;
            byt <<= 4;
            n++;

            int low = read4b();
            if (low == -1) {
                byt |= buf[off] & 0xf;
                buf[off] = (byte) byt;
                break;
            }

            byt |= low;
            n++;
            buf[off++] = (byte) byt;
        }
        if (n == 0)
            return -1;
        return n;
    }

    /**
     * No nibble by previous read was buffered.
     * 
     * @return nibble count successfully read, -1 if EOF reached.
     */
    protected int read4bNonBuffered(byte[] buf, int off, int len)
            throws IOException {
        return read4bBuffered(buf, off, len);
    }

    /**
     * Default read in auto buffered mode.
     * 
     * @see #read4bBuffered(byte[], int, int)
     * @see #read4bNonBuffered(byte[], int, int)
     */
    @Override
    public int read4b(byte[] buf)
            throws IOException {
        if (isBuffered())
            return read4bBuffered(buf, 0, buf.length);
        else
            return read4bNonBuffered(buf, 0, buf.length);
    }

    /**
     * Do nothing in abstract implementation.
     */
    @Override
    public void close()
            throws IOException {
    }

}
