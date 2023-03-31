package net.bodz.bas.io.data;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.AbstractByteOut;
import net.bodz.bas.io.IDataOut;
import net.bodz.bas.io.StringLengthType;

public abstract class AbstractDataOut
        extends AbstractByteOut
        implements
            IDataOut {

    static final int FIX_PADDING = 0xFF;

    @Override
    public void writeFloat(float f)
            throws IOException {
        writeDword(Float.floatToIntBits(f));
    }

    @Override
    public void writeDouble(double d)
            throws IOException {
        writeQword(Double.doubleToLongBits(d));
    }

    @Override
    public void writeBool(boolean b)
            throws IOException {
        write(b ? (byte) 1 : (byte) 0);
    }

    @Override
    public void writeChar(char ch)
            throws IOException {
        writeWord(ch);
    }

    @Override
    public int writeUtf8Char(char _ch)
            throws IOException {
        int ch = _ch;
        if (ch <= 0x7f) {
            // 7bit: 0xxxxxxx
            write(ch);
            return 1;
        }

        else if (ch <= 0x7ff) {
            // 11bit: 110xxxxx . 10xxxxxx
            write(0xC0 | (ch >> 6));
            write(0x80 | (ch & 0x3F));
            return 2;
        }

        else {
            // 16bit: 1110xxxx . 10xxxxxx . 10xxxxxx
            write(0xE0 | (ch >> 12));
            write(0x80 | ((ch >> 6) & 0x3F));
            write(0x80 | (ch & 0x3F));
            return 3;
        }
    }

    @Override
    public int writeChar(char ch, Charset charset)
            throws IOException {
        byte[] bytes = String.valueOf(ch).getBytes(charset);
        write(bytes);
        return bytes.length;
    }

    @Override
    public void writeWords(short[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            writeWord(buf[off++]);
    }

    @Override
    public void writeDwords(int[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            writeDword(buf[off++]);
    }

    @Override
    public void writeQwords(long[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            writeQword(buf[off++]);
    }

    @Override
    public void writeFloats(float[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            writeFloat(buf[off++]);
    }

    @Override
    public void writeDoubles(double[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            writeDouble(buf[off++]);
    }

    @Override
    public void writeBools(boolean[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            writeBool(buf[off++]);
    }

    // writeChars

    @Override
    public int writeChars(char[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            writeChar(buf[off++]);
        return len * 2;
    }

    @Override
    public int writeChars(char[] buf, int off, int len, Charset charset)
            throws IOException {
        String str = new String(buf, off, len);
        byte[] bytes = str.getBytes(charset);
        write(bytes);
        return bytes.length;
    }

    @Override
    public int writeUtf8Chars(char[] buf, int off, int len)
            throws IOException {
        int cb = 0;
        for (int i = 0; i < len; i++)
            cb += writeUtf8Char(buf[off++]);
        return cb;
    }

    // writeString

    @Override
    public void writeString(StringLengthType lengthType, String str)
            throws IOException {
        int len = str.length();

        if (lengthType.countByChar)
            lengthType.writeCountField(this, len);

        if (lengthType.countByByte)
            lengthType.writeCountField(this, len * 2);

        for (int i = 0; i < len; i++)
            writeChar(str.charAt(i));

        if (lengthType.hasTerminator)
            writeChar(lengthType.terminateChar);
    }

    @Override
    public void writeUtf8String(StringLengthType lengthType, String str)
            throws IOException {
        if (lengthType.countByByte) {
            byte[] bytes = str.getBytes(Charsets.UTF_8);
            lengthType.writeCountField(this, bytes.length);
            write(bytes);
            return;
        }

        int len = str.length();
        if (lengthType.countByChar)
            lengthType.writeCountField(this, len);

        for (int i = 0; i < len; i++) {
            writeUtf8Char(str.charAt(i));
        }

        if (lengthType.hasTerminator)
            writeUtf8Char(lengthType.terminateChar);
    }

    @Override
    public void writeString(StringLengthType lengthType, String str, Charset charset)
            throws IOException {
        if (charset == null)
            throw new NullPointerException("charset");

        byte[] bytes = str.getBytes(charset);

        if (lengthType.countByChar)
            lengthType.writeCountField(this, str.length());

        if (lengthType.countByByte)
            lengthType.writeCountField(this, bytes.length);

        write(bytes);

        if (lengthType.hasTerminator)
            writeChar(lengthType.terminateChar, charset);
    }

    // writeFixedSizeChars

    @Override
    public void writeFixedSizeChars(int fixedSize, char padding, char[] buf, int off, int len)
            throws IOException {
        int nByte = len * 2;
        if (nByte > fixedSize)
            nByte = fixedSize;

        boolean extraByte = nByte % 1 != 0;
        nByte &= ~1;

        int nChar = nByte / 2;
        writeChars(buf, off, nChar);

        while (nByte + 2 <= fixedSize) {
            writeChar(padding);
            nByte += 2;
        }
        if (extraByte)
            writeByte(FIX_PADDING);
    }

    @Override
    public int writeFixedSizeUtf8Chars(int fixedSize, char padding, char[] buf, int off, int len)
            throws IOException {
        int maxBytesPerChar = 3;
        int maxCharsToWrite;
        int nByte = 0;
        int pos = 0;
        while (nByte < fixedSize && pos < len) {
            int remaining = fixedSize - nByte;
            maxCharsToWrite = remaining / maxBytesPerChar;
            if (maxCharsToWrite == 0) {
                char ch = buf[off + pos];
                int nextCharLen = Utf8Utils.computeUtf8Length(ch);
                if (nextCharLen <= remaining)
                    maxCharsToWrite = 1;
                else
                    break;
            }
            if (maxCharsToWrite > len - pos)
                maxCharsToWrite = len - pos;
            int cb = writeUtf8Chars(buf, off, maxCharsToWrite);
            nByte += cb;
            pos += maxCharsToWrite;
        }
        assert nByte <= fixedSize;
        int nByteEncoded = nByte;

        int paddingSize = Utf8Utils.computeUtf8Length(padding);
        while (nByte + paddingSize <= fixedSize) {
            nByte += writeUtf8Char(padding);
        }

        while (nByte < fixedSize) {
            write(FIX_PADDING);
            nByte++;
        }
        return nByteEncoded;
    }

    @Override
    public int writeFixedSizeChars(int fixedSize, char padding, char[] buf, int off, int len, Charset charset)
            throws IOException, ParseException {
        // CharEncoder ce = new CharEncoder(charset, this);
        CharsetEncoder encoder = charset.newEncoder();
        float maxBytesPerChar = encoder.maxBytesPerChar();
        int maxCharsToWrite;
        int nByte = 0;
        int pos = 0;
        while (nByte < fixedSize && pos < len) {
            int remaining = fixedSize - nByte;
            maxCharsToWrite = (int) (remaining / maxBytesPerChar);
            if (maxCharsToWrite == 0) {
                char ch = buf[off + pos];
                byte[] bytes = Charsets.encodeChar(charset, ch);
                int nextCharLen = bytes.length;
                if (nextCharLen <= remaining)
                    maxCharsToWrite = 1;
                else
                    break;
            }
            if (maxCharsToWrite > len - pos)
                maxCharsToWrite = len - pos;
            int cb = writeChars(buf, off, maxCharsToWrite);
            nByte += cb;
            pos += maxCharsToWrite;
        }
        assert nByte <= fixedSize;
        int nByteEncoded = nByte;

        byte[] paddingBytes = Charsets.encodeChar(charset, padding);
        int paddingSize = paddingBytes.length;
        while (nByte + paddingSize <= fixedSize) {
            write(paddingBytes);
            nByte += paddingSize;
        }

        while (nByte < fixedSize) {
            write(FIX_PADDING);
            nByte++;
        }
        return nByteEncoded;
    }

    @Override
    public void writeFixedSizeString(int fixedSize, char padding, String str)
            throws IOException {
        char[] chars = str.toCharArray();
        writeFixedSizeChars(fixedSize, padding, chars);
    }

    @Override
    public int writeFixedSizeUtf8String(int fixedSize, char padding, String str)
            throws IOException {
        char[] chars = str.toCharArray();
        return writeFixedSizeUtf8Chars(fixedSize, padding, chars);
    }

    @Override
    public int writeFixedSizeString(int fixedSize, char padding, String str, Charset charset)
            throws IOException, ParseException {
        char[] chars = str.toCharArray();
        return writeFixedSizeChars(fixedSize, padding, chars, charset);
    }

}
