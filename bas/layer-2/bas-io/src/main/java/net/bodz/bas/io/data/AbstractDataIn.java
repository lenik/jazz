package net.bodz.bas.io.data;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.c.java.nio.MalformedInputException;
import net.bodz.bas.err.DecodeException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.AbstractByteIn;
import net.bodz.bas.io.EncodedChar;
import net.bodz.bas.io.EncodedString;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.StringLengthType;

public abstract class AbstractDataIn
        extends AbstractByteIn
        implements
            IDataIn {

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
    public char readUtf8Char_fast()
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
    public char readUtf8Char()
            throws IOException, MalformedInputException {
        int x = readByte() & 0xFF;

        if (x <= 0x7F)
            // 7bit: 0xxxxxxx
            return (char) x;

        int y = readByte() & 0xFF;
        if (x <= 0xDF) {
            // 11bit: 110xxxxx . 10xxxxxx
            if ((y & 0xC0) != 0x80)
                throw new MalformedInputException("malformed utf-8 char (2/2)");
            x = (x & 0x1F) << 6;
            y &= 0x3F;
            return (char) (0 + (x | y));
        }

        int z = readByte() & 0xFF;
        if (x <= 0xEF) {
            // 16bit: 1110xxxx . 10xxxxxx . 10xxxxxx
            if ((y & 0xC0) != 0x80)
                throw new MalformedInputException("malformed utf-8 second char (2/3)");
            if ((z & 0xC0) != 0x80)
                throw new MalformedInputException("malformed utf-8 third char (3/3)");
            x = (x & 0x0F) << 12;
            y = (y & 0x3F) << 6;
            z &= 0x3F;
            return (char) (0 + (x | y | z));
        }

        throw new MalformedInputException("Bad UTF-8 char sequence.");
    }

    @Override
    public final EncodedChar readChar(Charset charset)
            throws IOException, ParseException {
        return readChar(charset, -1);
    }

    @Override
    public final EncodedChar readChar(Charset charset, char fallback)
            throws IOException {
        try {
            return readChar(charset, (int) fallback);
        } catch (ParseException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

    protected EncodedChar readChar(Charset charset, int fallback)
            throws IOException, ParseException {
        CharDecoder decoder = new CharDecoder(charset, this);
        if (fallback != -1)
            decoder.fallback((char) fallback);
        StringBuilder sb = new StringBuilder(1);
        int size = decoder.decodeChar(sb);
        if (size == 0)
            throw new EOFException();
        return EncodedChar.decoded(size, sb.charAt(0));
    }

    @Override
    public final void readBytes(byte[] buf, int off, int len)
            throws IOException {
        if (len == 0)
            return;

        int cb = read(buf, off, len);

        // XXX if (cb == -1)
        if (cb != len)
            throw new EOFException();
    }

    @Override
    public void readWords(short[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            buf[off++] = readWord();
    }

    @Override
    public void readDwords(int[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            buf[off++] = readDword();
    }

    @Override
    public void readQwords(long[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            buf[off++] = readQword();
    }

    @Override
    public void readFloats(float[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            buf[off++] = readFloat();
    }

    @Override
    public void readDoubles(double[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            buf[off++] = readDouble();
    }

    @Override
    public void readBools(boolean[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            buf[off++] = readBool();
    }

    @Override
    public int readChars(char[] buf, int off, int len)
            throws IOException {
        byte[] bb = new byte[len * 2];
        readBytes(bb);
        int j = -1;

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
        return len * 2;
    }

    @Override
    public void readUtf8Chars_fast(char[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++) {
            char ch = readUtf8Char_fast();
            buf[off++] = ch;
        }
    }

    @Override
    public int readUtf8Chars(char[] buf, int off, int len)
            throws IOException, MalformedInputException {
        int size = 0;
        for (int i = 0; i < len; i++) {
            char ch = readUtf8Char();
            buf[off++] = ch;
            size += Utf8Utils.computeUtf8Length(ch);
        }
        return size;
    }

    @Override
    public final String readString(StringLengthType lengthType, int providedCount)
            throws IOException {
        if (lengthType.hasTerminator)
            return readStringUntil(lengthType.terminator);
        int count = lengthType.readCountField(this, providedCount);
        if (lengthType.countByChar) {
            char[] buf = new char[count];
            readChars(buf);
            return RawStrings.newString(lengthType.raw, buf);
        } else {
            int nChar = count / 2;
            char[] buf = new char[nChar];
            readChars(buf);
            if (count % 2 == 1) {
                @SuppressWarnings("unused") // extra byte, just drop it.
                byte extraByte = readByte();
            }
            return RawStrings.newString(lengthType.raw, buf);
        }
    }

    public String readStringUntil(char delim)
            throws IOException {
        StringBuilder sb = new StringBuilder();
        char ch;
        while ((ch = readChar()) != delim) {
            sb.append(ch);
        }
        return sb.toString();
    }

    @Override
    public String readUtf8String_fast(StringLengthType lengthType, int providedCount)
            throws IOException {
        if (lengthType.hasTerminator)
            return readUtf8StringUntil_fast(lengthType.terminator);
        int count = lengthType.readCountField(this, providedCount);
        if (lengthType.countByChar) {
            char[] buf = new char[count];
            readUtf8Chars_fast(buf);
            return RawStrings.newString(lengthType.raw, buf);
        } else {
            byte[] buf = new byte[count];
            readBytes(buf);
            String str = new String(buf, Charsets.UTF_8);
            return RawStrings.compact(lengthType.raw, str);
        }
    }

    public String readUtf8StringUntil_fast(char delim)
            throws IOException {
        StringBuilder sb = new StringBuilder();
        char ch;
        while ((ch = readUtf8Char_fast()) != delim) {
            sb.append(ch);
        }
        return sb.toString();
    }

    @Override
    public EncodedString readUtf8String(StringLengthType lengthType, int providedCount)
            throws IOException, ParseException {
        if (lengthType.hasTerminator)
            return readUtf8StringUntil(lengthType.terminator);
        int count = lengthType.readCountField(this, providedCount);
        if (lengthType.countByChar) {
            char[] buf = new char[count];
            int nByte = readUtf8Chars(buf);

            int len = buf.length;
            if (lengthType.autoCompact)
                len = RawStrings.fixLen(buf, 0, len);
            String str = new String(buf, 0, len);

            return EncodedString.decoded(nByte, str);
        } else {
            byte[] buf = new byte[count];
            readBytes(buf);

            int len = buf.length;
            if (lengthType.autoCompact)
                len = RawStrings.fixLen(buf, 0, len);
            String str = new String(buf, 0, len, Charsets.UTF_8);

            return EncodedString.decoded(count, str);
        }
    }

    public EncodedString readUtf8StringUntil(char delim)
            throws IOException, MalformedInputException {
        StringBuilder sb = new StringBuilder();
        int size = 0;
        char ch;
        while ((ch = readUtf8Char()) != delim) {
            sb.append(ch);
            size += Utf8Utils.computeUtf8Length(ch);
        }
        return EncodedString.decoded(size, sb.toString());
    }

    // XXX
    public String readString1(StringLengthType lengthType, int providedCount, Charset charset)
            throws IOException, ParseException {
        int count = lengthType.readCountField(this, providedCount);
        if (lengthType.countByByte) {
            byte[] buf = new byte[count];
            readBytes(buf);

            int len = buf.length;
            if (lengthType.autoCompact)
                len = RawStrings.fixLen(buf, 0, len);

            return new String(buf, 0, len, charset);
        } else {
            StringBuilder sb = new StringBuilder(count);
            if (lengthType.hasTerminator) {
                while (true) {
                    EncodedChar chInfo = readChar(charset);
                    char ch = chInfo.character;
                    if (ch == lengthType.terminator)
                        break;
                    sb.append(ch);
                }
            } else {
                int nByte = 0;
                boolean end = false;
                while (nByte < count) {
                    EncodedChar chInfo = readChar(charset);
                    char ch = chInfo.character;
                    if (lengthType.autoCompact)
                        if (ch == 0)
                            end = true;
                    if (!end)
                        sb.append(ch);
                    nByte += chInfo.byteCount;
                }
            }
            return sb.toString();
        }
    }

    @Override
    public int readChars(char[] buf, int off, int len, Charset charset)
            throws IOException, ParseException {
        if (len == 0)
            return 0;
        int byteCapacity = CharDecoder.decodeBufferSize;
        // OPT: byteCapacity = Math.min(byteCapacity, len * 3);

        CharDecoder decoder = new CharDecoder(charset, this, byteCapacity);

        StringBuilder sb = new StringBuilder();
        ConvertedLengths lens = decoder.decodeChars(sb, len);
        if (lens.charCount != len)
            throw new EOFException();
        return lens.byteCount;
    }

    @Override
    public EncodedString readString(StringLengthType lengthType, int providedCount, Charset charset)
            throws IOException, ParseException {
        int count = lengthType.readCountField(this, providedCount);
        if (lengthType.countByByte) {
            byte[] buf = new byte[count];
            readBytes(buf);

            int len;
            if (lengthType.autoCompact)
                len = RawStrings.fixLen(buf, 0, buf.length);
            else
                len = buf.length;
            String str = new String(buf, 0, len, charset);
            return EncodedString.decoded(count, str);
        }

        // TODO -- continue..

        StringBuilder sb = new StringBuilder(count);
        if (lengthType.hasTerminator) {
            CharDecoder decoder = new CharDecoder(charset, this);
            StringBuilder chBuf = new StringBuilder();
            int nByte = 0;
            while (true) {
                chBuf.setLength(0);
                int chSize = decoder.decodeChar(chBuf);
                if (chSize == 0)
                    throw new EOFException();
                nByte += chSize;
                char ch = chBuf.charAt(0);
                if (ch == lengthType.terminator)
                    break;
                sb.append(ch);
            }
            return EncodedString.decoded(nByte, sb.toString());
        }

        int byteCapacity = CharDecoder.decodeBufferSize;
        if (count != 0) {
            if (lengthType.countByByte)
                byteCapacity = Math.min(byteCapacity, count);
            else
                byteCapacity = Math.min(byteCapacity, count * 3);
        }

        CharDecoder decoder = new CharDecoder(charset, this, byteCapacity);

        int nChar = 0;
        int nByte = 0;
        while (nChar < count) {
            int remaining = count - nChar;

            int chunkMax = remaining;
            if (lengthType.countByByte) {
                float _minBytes = chunkMax * decoder._minBytesPerChar;
                int minBytes = (int) _minBytes;
                if (minBytes == 0)
                    minBytes = 1;
                chunkMax = Math.min(minBytes, CharDecoder.blockSize);
            }
            ConvertedLengths lens = decoder.decodeChars(sb, chunkMax);
            nByte += lens.byteCount;
            nChar += lens.charCount;
        }
        if (nChar != count)
            throw new EOFException();
        return EncodedString.decoded(nByte, sb.toString());
    }

}
