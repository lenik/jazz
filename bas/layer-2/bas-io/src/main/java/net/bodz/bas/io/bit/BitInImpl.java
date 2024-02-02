package net.bodz.bas.io.bit;

import java.io.EOFException;
import java.io.IOException;

import net.bodz.bas.io.IByteIn;

public class BitInImpl
        implements
            IBitIn {

    static final int defaultBlockSize = 4096;

    IByteIn in;
    byte[] block;
    int size;
    int pos;

    int bitBuf;
    int bitMask;
    int nBit;

    public BitInImpl(IByteIn in) {
        this(in, 4096);
    }

    public BitInImpl(IByteIn in, int blockSize) {
        this.in = in;
        this.block = new byte[blockSize];
    }

    public static BitInImpl from(IByteIn in) {
        return new BitInImpl(in);
    }

    @Override
    public int readBit()
            throws IOException {
        if (bitMask == 0) {
            if (load() == -1)
                return -1;
        }
        int bit = (bitBuf & bitMask) != 0 ? 1 : 0;
        bitMask >>>= 1;
        nBit--;
        return bit;
    }

    @Override
    public int read()
            throws IOException {
        try {
            int n = (int) readLong(8);
            return n;
        } catch (EOFException e) {
            return -1;
        }
    }

    @Override
    public long readLong(int n)
            throws IOException {
        if (n <= 0)
            throw new IllegalArgumentException();

        long val = 0;
        if (n < nBit) {
            int unusedBits = nBit - n;
            int unusedMask = (1 << unusedBits) - 1;
            int mask = (bitMask | (bitMask - 1)) - unusedMask;
            val = (bitBuf & mask) >>> unusedBits;
            bitMask >>>= n;
            nBit -= n;
        } else { // n >= nBit
            int nTail = n - nBit;
            if (nBit != 0) {
                int mask = (bitMask | (bitMask - 1));
                val = bitBuf & mask;
                bitMask = 0;
                nBit = 0;
            }

            while (nTail > 0) {
                while (pos < size && nTail > 0) {
                    int b = block[pos++] & 0xFF;
                    if (nTail >= 8) {
                        val = (val << 8) | b;
                        nTail -= 8;
                    } else {
                        bitBuf = b; // <left[nTail] | right[nBit] unused>
                        nBit = 8 - nTail;
                        bitMask = 1 << (nBit - 1);
                        int left = bitBuf >>> nBit;
                        val = (val << nTail) | left;
                        nTail = 0;
                    }
                }
                if (load(false) == -1)
                    throw new EOFException();
            }
        }
        return val;
    }

    void compact() {
        if (pos > 0 && pos < size) {
            int unread = size - pos;
            System.arraycopy(block, pos, block, 0, unread); // move unread data to head
            size = unread;
            pos = 0;
        }
    }

    int load()
            throws IOException {
        return load(true);
    }

    int load(boolean loadBit)
            throws IOException {
        if (bitMask != 0)
            return 0;

        int cbRead = 0;
        if (pos >= size) {
            compact();
            int sizeToRead = block.length - size;
            cbRead = in.read(block, size, sizeToRead);
            if (cbRead != -1)
                size += cbRead;
        }

        if (loadBit) {
            bitBuf = block[pos++];
            bitMask = 0x80;
            nBit = 8;
        }
        return cbRead;
    }

    @Override
    public int availableBits() {
        return (size - pos) * 8 + nBit;
    }

    @Override
    public void dropToByte() {
        bitMask = 0;
        nBit = 0;
    }

    @Override
    public void close()
            throws IOException {
        in.close();
    }

}
