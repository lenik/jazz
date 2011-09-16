package net.bodz.bas.sio.bits;

import java.io.IOException;


public class BNibbleIn
        extends AbstractNibbleIn {

    private final byte[] buf;
    private final int nibbleStart;
    private final int nibbleEnd;

    private int nibblePos;
    private int bytePos;
    private boolean half;

    public BNibbleIn(byte[] buf) {
        this(buf, 0, buf.length * 2);
    }

    public BNibbleIn(byte[] buf, int nibbleOffset, int nibbleCount) {
        if (buf == null)
            throw new NullPointerException("buf");
        int nc = buf.length * 2;
        if (nibbleOffset < 0 || nibbleOffset >= nc)
            throw new IndexOutOfBoundsException("nibbleOffset");
        nibbleEnd = nibbleOffset + nibbleCount;
        if (nibbleEnd < nibbleOffset || nibbleEnd > nc)
            throw new IndexOutOfBoundsException("nibbleCount");

        this.buf = buf;
        this.nibbleStart = nibbleOffset;

        bytePos = nibbleStart >> 1;

    }

    @Override
    public boolean isBuffered() {
        return half;
    }

    @Override
    public int read4b()
            throws IOException {
        if (nibblePos >= nibbleEnd)
            return -1;
        int nibble;
        if (half)
            nibble = buf[bytePos++] & 0x0f;
        else
            nibble = (buf[bytePos] & 0xff) >> 4;
        half = !half;

        nibblePos++;
        return nibble;
    }

    @Override
    protected int read4bNonBuffered(byte[] dst, int off, int len)
            throws IOException {
        int dstNibCount = len << 1;
        int srcNibCount = nibbleEnd - nibblePos;
        int cnToRead = Math.min(srcNibCount, dstNibCount);
        int cbToRead = cnToRead >> 1;
        System.arraycopy(buf, bytePos, dst, off, cbToRead);
        bytePos += cbToRead;
        nibblePos += cnToRead;
        if ((cnToRead & 1) != 0) {
            off += cbToRead;
            int high = buf[bytePos] & 0xf0;
            int low = dst[off] & 0x0f;
            dst[off] = (byte) (high | low);
            half = true;
        }
        return cnToRead;
    }

}
