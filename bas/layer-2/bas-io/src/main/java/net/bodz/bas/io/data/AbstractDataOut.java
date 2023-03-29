package net.bodz.bas.io.data;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.io.AbstractByteOut;
import net.bodz.bas.io.IDataOut;
import net.bodz.bas.io.LengthType;

public abstract class AbstractDataOut
        extends AbstractByteOut
        implements
            IDataOut {

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
    public void writeUtf8Char(char _ch)
            throws IOException {
        int ch = _ch;
        if (ch <= 0x7f)
            // 7bit: 0xxxxxxx
            write(ch);

        else if (ch <= 0x7ff) {
            // 11bit: 110xxxxx . 10xxxxxx
            write(0xC0 | (ch >> 6));
            write(0x80 | (ch & 0x3F));
        }

        else {
            // 16bit: 1110xxxx . 10xxxxxx . 10xxxxxx
            write(0xE0 | (ch >> 12));
            write(0x80 | ((ch >> 6) & 0x3F));
            write(0x80 | (ch & 0x3F));
        }
    }

    @Override
    public void writeChar(char ch, Charset charset)
            throws IOException {
        byte[] bytes = String.valueOf(ch).getBytes(charset);
        write(bytes);
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

    @Override
    public void writeChars(char[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            writeChar(buf[off++]);
    }

    @Override
    public void writeUtf8Chars(char[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            writeUtf8Char(buf[off++]);
    }

    @Override
    public void writeString(LengthType lengthType, String str)
            throws IOException {
        int len = str.length();

        if (lengthType.countByChars)
            lengthType.writeLengthHeader(this, len);

        if (lengthType.countByBytes)
            lengthType.writeLengthHeader(this, len * 2);

        for (int i = 0; i < len; i++)
            writeChar(str.charAt(i));

        if (lengthType.hasTerminator)
            writeChar(lengthType.terminatorChar);
    }

    @Override
    public void writeString(LengthType lengthType, String str, Charset charset)
            throws IOException {
        if (charset == null)
            throw new NullPointerException("charset");

        byte[] bytes = str.getBytes(charset);

        if (lengthType.countByChars)
            lengthType.writeLengthHeader(this, str.length());

        if (lengthType.countByBytes)
            lengthType.writeLengthHeader(this, bytes.length);

        write(bytes);

        if (lengthType.hasTerminator)
            writeChar(lengthType.terminatorChar, charset);
    }

    @Override
    public void writeUtf8String(LengthType lengthType, String str)
            throws IOException {
        if (lengthType.countByBytes) {
            byte[] bytes = str.getBytes(Charsets.UTF8);
            lengthType.writeLengthHeader(this, bytes.length);
            write(bytes);
            return;
        }

        int len = str.length();
        if (lengthType.countByChars)
            lengthType.writeLengthHeader(this, len);

        for (int i = 0; i < len; i++) {
            writeUtf8Char(str.charAt(i));
        }

        if (lengthType.hasTerminator)
            writeUtf8Char(lengthType.terminatorChar);
    }

}
