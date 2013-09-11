package net.bodz.bas.io.data;

import java.io.EOFException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

import net.bodz.bas.err.DecodeException;
import net.bodz.bas.io.AbstractByteIn;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.StringFlags;

public abstract class AbstractDataIn
        extends AbstractByteIn
        implements IDataIn {

    @Override
    public byte readByte()
            throws IOException {
        int b = read();
        if (b == -1)
            throw new EOFException();
        return (byte) b;
    }

    @Override
    public float readFloat()
            throws IOException {
        int bits = readDword();
        return Float.intBitsToFloat(bits);
    }

    @Override
    public double readDouble()
            throws IOException {
        long bits = readQword();
        return Double.longBitsToDouble(bits);
    }

    @Override
    public boolean readBool()
            throws IOException {
        byte b = readByte();
        return b != 0;
    }

    @Override
    public char readChar(int flags)
            throws IOException {
        if ((flags & StringFlags._16BIT) != 0)
            // UTF-16 LE/BE depends on implementation.
            return (char) (readWord() & 0xffff);
        else
            // UTF-8
            return readUtf8Char();
    }

    public synchronized char readUtf8Char()
            throws IOException {
        int x = readByte() & 0xFF;

        if (x <= 0x7F)
            // 7bit: 0xxxxxxx
            return (char) x;

        int y = readByte() & 0xFF;
        if (x <= 0xDF) {
            // 11bit: 110xxxxx . 10xxxxxx
            x = (x & 0x1F) << 6;
            y &= 0x3F;
            return (char) (0 + (x | y));
        }

        int z = readByte() & 0xFF;
        if (x <= 0xEF) {
            // 16bit: 1110xxxx . 10xxxxxx . 10xxxxxx
            x = (x & 0x0F) << 12;
            y = (y & 0x3F) << 6;
            z &= 0x3F;
            return (char) (0 + (x | y | z));
        }

        throw new DecodeException("Bad UTF-8 char sequence.");
    }

    @Override
    public final void readBytes(byte[] buf)
            throws IOException {
        readBytes(buf, 0, buf.length);
    }

    @Override
    public final void readBytes(byte[] buf, int off, int len)
            throws IOException {
        int cb = read(buf, off, len);

        // XXX if (cb == -1)
        if (cb != len)
            throw new EOFException();
    }

    @Override
    public final void readWords(short[] buf)
            throws IOException {
        readWords(buf, 0, buf.length);
    }

    @Override
    public void readWords(short[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            buf[off++] = readWord();
    }

    @Override
    public final void readDwords(int[] buf)
            throws IOException {
        readDwords(buf, 0, buf.length);
    }

    @Override
    public void readDwords(int[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            buf[off++] = readDword();
    }

    @Override
    public final void readQwords(long[] buf)
            throws IOException {
        readQwords(buf, 0, buf.length);
    }

    @Override
    public void readQwords(long[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            buf[off++] = readQword();
    }

    @Override
    public final void readFloats(float[] buf)
            throws IOException {
        readFloats(buf, 0, buf.length);
    }

    @Override
    public void readFloats(float[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            buf[off++] = readFloat();
    }

    @Override
    public final void readDoubles(double[] buf)
            throws IOException {
        readDoubles(buf, 0, buf.length);
    }

    @Override
    public void readDoubles(double[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            buf[off++] = readDouble();
    }

    @Override
    public final void readBools(boolean[] buf)
            throws IOException {
        readBools(buf, 0, buf.length);
    }

    @Override
    public void readBools(boolean[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            buf[off++] = readBool();
    }

    @Override
    public final void readChars(int flags, char[] buf)
            throws IOException {
        readChars(flags, buf, 0, buf.length);
    }

    @Override
    public synchronized void readChars(int flags, char[] buf, int off, int len)
            throws IOException {
        byte[] bb = new byte[len * 2];
        readBytes(bb);
        int j = -1;

        if ((flags & StringFlags._16BIT) != 0)
            if (isBigEndian())
                for (int i = 0; i < len; i++) {
                    int high = bb[++j] & 0xff;
                    int low = bb[++j] & 0xff;
                    buf[off++] = (char) ((high << 8) | low);
                }
            else
                for (int i = 0; i < len; i++) {
                    int low = bb[++j] & 0xff;
                    int high = bb[++j] & 0xff;
                    buf[off++] = (char) ((high << 8) | low);
                }

        else
            for (int i = 0; i < len; i++)
                buf[off++] = readUtf8Char();
    }

    @Override
    public final String readString(int flags)
            throws IOException {
        return readString(flags, null);
    }

    @Override
    public String readString(int flags, String encoding)
            throws IOException {
        return null;
    }

}

class _ReadUtfStringImpl {

    private final int flags;
    private final String encoding;

    String termCharSeq;
    int termCharNum;

    private boolean bigEndian;

    public _ReadUtfStringImpl(int flags, String encoding)
            throws UnsupportedEncodingException {

        if ((flags & StringFlags.XXX_TERM_MASK) != 0) {
            if ((flags & StringFlags.CR_TERM) != 0)
                termCharSeq += '\r';
            if ((flags & StringFlags.LF_TERM) != 0)
                termCharSeq += '\n';
            if ((flags & StringFlags.NULL_TERM) != 0)
                termCharSeq += '\0';
            termCharNum = termCharSeq.length();
        }

        this.flags = flags;
        this.encoding = encoding;

    }

    public boolean isBigEndian() {
        return bigEndian;
    }

    public void setBigEndian(boolean bigEndian) {
        this.bigEndian = bigEndian;
    }

    public void read(IDataIn in)
            throws IOException {
        boolean _long = (flags & StringFlags.LONG) != 0;
        boolean lengthPrefix = (flags & StringFlags.LENGTH_PREFIX) != 0;
        boolean sizePrefix = (flags & StringFlags.SIZE_PREFIX) != 0;
        boolean sameEndian = bigEndian == in.isBigEndian();

        int len = Integer.MAX_VALUE;
        int size = Integer.MAX_VALUE;

        if (lengthPrefix)
            if (_long)
                len = in.readDword();
            else
                len = in.readWord();

        if (sizePrefix)
            if (_long)
                size = in.readDword();
            else
                size = in.readWord();

        EOTDetector eotDetector = new EOTDetector(termCharSeq);
        StringBuilder chars = new StringBuilder();

        if (encoding != null) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            CharBuffer charBuffer = CharBuffer.allocate(10);

            CharsetDecoder decoder = Charset.forName(encoding).newDecoder();

            int cb = 0;
            int cc = 0;
            while (cb < size && cc < len) {
                int byt = in.readByte();
                cb++;

                boolean terminated = eotDetector.push(byt);

                if (terminated) {
                    assert byteBuffer.position() == 0;
                    for (int i = 0; i < termCharNum; i++)
                        chars.append((char) eotDetector.shift());
                    cc += termCharNum;
                    break;
                }

                byteBuffer.put((byte) byt);
                CoderResult result = decoder.decode(byteBuffer, charBuffer, false);

                charBuffer.flip();
                int position = charBuffer.position();

            }
        }

        // UTF-32LE, UTF-32BE
        if ((flags & StringFlags._32BIT) != 0) {
        }

        // UTF-16LE, UTF-16BE
        else if ((flags & StringFlags._16BIT) != 0) {
        }

        // UTF-8
        else {
        }
    }

}

class EOTDetector {

    private final int[] eotCharSeq;
    private final int eotLen;
    private int[] ringbuf;
    private int ptr;

    public EOTDetector(String eotCharSeq) {
        this(toInts(eotCharSeq));
    }

    static int[] toInts(String str) {
        int len = str.length();
        int[] ints = new int[len];
        for (int i = 0; i < len; i++)
            ints[i] = str.charAt(i);
        return ints;
    }

    public EOTDetector(int[] eotCharSeq) {
        this.eotCharSeq = eotCharSeq;
        eotLen = eotCharSeq.length;
        ringbuf = new int[eotLen];
        ptr = 0;
    }

    public boolean push(int ch) {
        if (ptr == eotLen) {
            for (int i = 1; i < ptr; i++)
                ringbuf[i - 1] = ringbuf[i];
            ringbuf[ptr] = ch;
        } else {
            ringbuf[ptr++] = ch;
        }

        for (int i = 0; i < eotLen; i++)
            if (eotCharSeq[i] != ringbuf[i])
                return false;

        return true;
    }

    public void reset() {
        ptr = 0;
    }

    public boolean isFull() {
        return ptr == eotLen;
    }

    public int size() {
        return ptr;
    }

    public int shift() {
        if (ptr == 0)
            throw new IllegalStateException("nothing to shift");
        int head = ringbuf[0];
        for (int i = 1; i < eotLen; i++)
            ringbuf[i - 1] = ringbuf[i];
        ptr--;
        return head;
    }

}