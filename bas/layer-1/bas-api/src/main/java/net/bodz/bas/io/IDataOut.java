package net.bodz.bas.io;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.io.impl.NullDataOut;

/**
 * The byte-order of this interface is undefined.
 */
public interface IDataOut
        extends
            IByteOut {

    boolean isBigEndian();

    void writeByte(int b)
            throws IOException;

    void writeWord(int w)
            throws IOException;

    void writeDword(int dw)
            throws IOException;

    void writeQword(long qw)
            throws IOException;

    /**
     * @see Float#floatToIntBits(float)
     */
    void writeFloat(float v)
            throws IOException;

    /**
     * @see Double#doubleToLongBits(double)
     */
    void writeDouble(double v)
            throws IOException;

    void writeBool(boolean v)
            throws IOException;

    void writeChar(char ch)
            throws IOException;

    void writeUtf8Char(char ch)
            throws IOException;

    default void writeChar(char ch, String encoding)
            throws IOException {
        Charset charset = Charset.forName(encoding);
        writeChar(ch, charset);
    }

    void writeChar(char ch, Charset charset)
            throws IOException;

    default void writeWords(short[] buf)
            throws IOException {
        writeWords(buf, 0, buf.length);
    }

    void writeWords(short[] buf, int off, int len)
            throws IOException;

    default void writeDwords(int[] buf)
            throws IOException {
        writeDwords(buf, 0, buf.length);
    }

    void writeDwords(int[] buf, int off, int len)
            throws IOException;

    default void writeQwords(long[] buf)
            throws IOException {
        writeQwords(buf, 0, buf.length);
    }

    void writeQwords(long[] buf, int off, int len)
            throws IOException;

    default void writeFloats(float[] buf)
            throws IOException {
        writeFloats(buf, 0, buf.length);
    }

    void writeFloats(float[] buf, int off, int len)
            throws IOException;

    default void writeDoubles(double[] buf)
            throws IOException {
        writeDoubles(buf, 0, buf.length);
    }

    void writeDoubles(double[] buf, int off, int len)
            throws IOException;

    default void writeBools(boolean[] buf)
            throws IOException {
        writeBools(buf, 0, buf.length);
    }

    void writeBools(boolean[] buf, int off, int len)
            throws IOException;

    default void writeChars(char[] buf)
            throws IOException {
        writeChars(buf, 0, buf.length);
    }

    void writeChars(char[] buf, int off, int len)
            throws IOException;

    default void writeUtf8Chars(char[] buf)
            throws IOException {
        writeUtf8Chars(buf, 0, buf.length);
    }

    void writeUtf8Chars(char[] buf, int off, int len)
            throws IOException;

    void writeString(LengthType lengthType, String str)
            throws IOException;

    void writeUtf8String(LengthType lengthType, String str)
            throws IOException;

    default void writeString(LengthType lengthType, String str, String encoding)
            throws IOException {
        Charset charset = Charset.forName(encoding);
        writeString(lengthType, str, charset);
    }

    void writeString(LengthType lengthType, String str, Charset charset)
            throws IOException;

    IDataOut NULL_LE = new NullDataOut(false);
    IDataOut NULL_BE = new NullDataOut(true);

}
