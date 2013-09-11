package net.bodz.bas.io.data;

import java.io.IOException;

import net.bodz.bas.io.AbstractByteOut;
import net.bodz.bas.io.IDataOut;
import net.bodz.bas.io.StringFlags;

public abstract class AbstractDataOut
        extends AbstractByteOut
        implements IDataOut {

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
    public void writeChar(int flags, char ch)
            throws IOException {
        if ((flags & StringFlags._16BIT) != 0)
            writeWord(ch);
        else
            writeUtf8Char(ch);
    }

    public synchronized void writeUtf8Char(char ch)
            throws IOException {
        if (ch <= 0x7f)
            // 7bit: 0xxxxxxx
            write(ch);

        else if (ch <= 0x7ff) {
            // 11bit: 110xxxxx . 10xxxxxx
            ch -= 0x80;
            write(0xC0 | (ch >> 6));
            write(0x80 | (ch & 0x3F));
        }

        else {
            // 16bit: 1110xxxx . 10xxxxxx . 10xxxxxx
            ch -= 0x800;
            write(0xE0 | (ch >> 12));
            write(0x80 | ((ch >> 6) & 0x3F));
            write(0x80 | (ch & 0x3F));
        }
    }

    @Override
    public final void writeWords(short[] buf)
            throws IOException {
        writeWords(buf, 0, buf.length);
    }

    @Override
    public void writeWords(short[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            writeWord(buf[off++]);
    }

    @Override
    public final void writeDwords(int[] buf)
            throws IOException {
        writeDwords(buf, 0, buf.length);
    }

    @Override
    public void writeDwords(int[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            writeDword(buf[off++]);
    }

    @Override
    public final void writeQwords(long[] buf)
            throws IOException {
        writeQwords(buf, 0, buf.length);
    }

    @Override
    public void writeQwords(long[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            writeQword(buf[off++]);
    }

    @Override
    public final void writeFloats(float[] buf)
            throws IOException {
        writeFloats(buf, 0, buf.length);
    }

    @Override
    public void writeFloats(float[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            writeFloat(buf[off++]);
    }

    @Override
    public final void writeDoubles(double[] buf)
            throws IOException {
        writeDoubles(buf, 0, buf.length);
    }

    @Override
    public void writeDoubles(double[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            writeDouble(buf[off++]);
    }

    @Override
    public final void writeBools(boolean[] buf)
            throws IOException {
        writeBools(buf, 0, buf.length);
    }

    @Override
    public void writeBools(boolean[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            writeBool(buf[off++]);
    }

    @Override
    public final void writeChars(int flags, char[] buf)
            throws IOException {
        writeChars(flags, buf, 0, buf.length);
    }

    @Override
    public void writeChars(int flags, char[] buf, int off, int len)
            throws IOException {
        for (int i = 0; i < len; i++)
            writeChar(flags, buf[off++]);
    }

    @Override
    public final void writeString(int flags, String str)
            throws IOException {
        writeString(flags, str, null);
    }

}
