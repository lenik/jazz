package net.bodz.bas.io;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.io.impl.NullDataOut;

/**
 * The byte-order of this interface is undefined.
 */
public interface IDataOut
        extends
            IByteOut,
            ICharsetAware {

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

    void writeWChar(char ch)
            throws IOException;

    void writeChar(char ch)
            throws IOException;

    /**
     * @return number of bytes encoded.
     */
    int writeUtf8Char(char ch)
            throws IOException;

    /**
     * @return number of bytes encoded.
     */
    default int writeChar(char ch, String encoding)
            throws IOException {
        return writeChar(ch, getCharset(encoding));
    }

    int writeChar(char ch, Charset charset)
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

    // writeChars

    default int writeChars(char[] buf)
            throws IOException {
        return writeChars(buf, 0, buf.length);
    }

    int writeChars(char[] buf, int off, int len)
            throws IOException;

    default int writeUtf8Chars(char[] buf)
            throws IOException {
        return writeUtf8Chars(buf, 0, buf.length);
    }

    int writeUtf8Chars(char[] buf, int off, int len)
            throws IOException;

    default int writeChars(char[] buf, String encoding)
            throws IOException {
        return writeChars(buf, 0, buf.length, getCharset(encoding));
    }

    int writeChars(char[] buf, int off, int len, Charset charset)
            throws IOException;

    // writeString

    void writeString(StringLengthType lengthType, String str)
            throws IOException;

    void writeUtf8String(StringLengthType lengthType, String str)
            throws IOException;

    default void writeString(StringLengthType lengthType, String str, String encoding)
            throws IOException {
        writeString(lengthType, str, getCharset(encoding));
    }

    void writeString(StringLengthType lengthType, String str, Charset charset)
            throws IOException;

    // fixedSize and padding

//    default String truncateToSize(String s, int size) {
//        int len = s.length();
//        if (len < size)
//            return s;
//        return s.substring(0, size);
//    }

    default char getPaddingChar() {
        return 0;
    }

    default void setPaddingChar(char paddingChar) {
        throw new UnsupportedOperationException();
    }

    // writeCharsOfSize

    default void writeCharsOfSize(int fixedSize, char[] buf)
            throws IOException {
        writeCharsOfSize(fixedSize, getPaddingChar(), buf, 0, buf.length);
    }

    default void writeCharsOfSize(int fixedSize, char[] buf, int off, int len)
            throws IOException {
        writeCharsOfSize(fixedSize, getPaddingChar(), buf, off, len);
    }

    default int writeUtf8CharsOfSize(int fixedSize, char[] buf)
            throws IOException {
        return writeUtf8CharsOfSize(fixedSize, getPaddingChar(), buf, 0, buf.length);
    }

    default int writeUtf8CharsOfSize(int fixedSize, char[] buf, int off, int len)
            throws IOException {
        return writeUtf8CharsOfSize(fixedSize, getPaddingChar(), buf, off, len);
    }

    default int writeCharsOfSize(int fixedSize, char[] buf, String encoding)
            throws IOException {
        return writeCharsOfSize(fixedSize, getPaddingChar(), buf, 0, buf.length, getCharset(encoding));
    }

    default int writeCharsOfSize(int fixedSize, char[] buf, Charset charset)
            throws IOException {
        return writeCharsOfSize(fixedSize, getPaddingChar(), buf, 0, buf.length, charset);
    }

    default int writeCharsOfSize(int fixedSize, char[] buf, int off, int len, String encoding)
            throws IOException {
        return writeCharsOfSize(fixedSize, getPaddingChar(), buf, off, len, getCharset(encoding));
    }

    default int writeCharsOfSize(int fixedSize, char[] buf, int off, int len, Charset charset)
            throws IOException {
        return writeCharsOfSize(fixedSize, getPaddingChar(), buf, off, len, charset);
    }

    // writeCharsOfSize_padding

    default int writeCharsOfSize(int fixedSize, char padding, char[] buf)
            throws IOException {
        return writeCharsOfSize(fixedSize, padding, buf, 0, buf.length);
    }

    int writeCharsOfSize(int fixedSize, char padding, char[] buf, int off, int len)
            throws IOException;

    default int writeUtf8CharsOfSize(int fixedSize, char padding, char[] buf)
            throws IOException {
        return writeUtf8CharsOfSize(fixedSize, padding, buf, 0, buf.length);
    }

    int writeUtf8CharsOfSize(int fixedSize, char padding, char[] buf, int off, int len)
            throws IOException;

    default int writeCharsOfSize(int fixedSize, char padding, char[] buf, String encoding)
            throws IOException {
        return writeCharsOfSize(fixedSize, padding, buf, 0, buf.length, getCharset(encoding));
    }

    default int writeCharsOfSize(int fixedSize, char padding, char[] buf, int off, int len, String encoding)
            throws IOException {
        return writeCharsOfSize(fixedSize, padding, buf, off, len, getCharset(encoding));
    }

    default int writeCharsOfSize(int fixedSize, char padding, char[] buf, Charset charset)
            throws IOException {
        return writeCharsOfSize(fixedSize, padding, buf, 0, buf.length, charset);
    }

    int writeCharsOfSize(int fixedSize, char padding, char[] buf, int off, int len, Charset charset)
            throws IOException;

    // writeStringOfLength

    default int writeStringOfLength(int fixedLen, String str)
            throws IOException {
        return writeStringOfLength(fixedLen, getPaddingChar(), str);
    }

    default int writeUtf8StringOfLength(int fixedLen, String str)
            throws IOException {
        return writeUtf8StringOfLength(fixedLen, getPaddingChar(), str);
    }

    default int writeStringOfLength(int fixedLen, String str, String encoding)
            throws IOException {
        return writeStringOfLength(fixedLen, getPaddingChar(), str, getCharset(encoding));
    }

    default int writeStringOfLength(int fixedLen, String str, Charset charset)
            throws IOException {
        return writeStringOfLength(fixedLen, getPaddingChar(), str, charset);
    }

    // writeStringOfLength-padding

    int writeStringOfLength(int fixedLen, char padding, String str)
            throws IOException;

    int writeUtf8StringOfLength(int fixedLen, char padding, String str)
            throws IOException;

    default int writeStringOfLength(int fixedLen, char padding, String str, String encoding)
            throws IOException {
        return writeStringOfLength(fixedLen, padding, str, getCharset(encoding));
    }

    int writeStringOfLength(int fixedLen, char padding, String str, Charset charset)
            throws IOException;

    // writeStringOfSize

    default int writeStringOfSize(int fixedSize, String str)
            throws IOException {
        return writeStringOfSize(fixedSize, getPaddingChar(), str);
    }

    default int writeUtf8StringOfSize(int fixedSize, String str)
            throws IOException {
        return writeUtf8StringOfSize(fixedSize, getPaddingChar(), str);
    }

    default int writeStringOfSize(int fixedSize, String str, String encoding)
            throws IOException {
        return writeStringOfSize(fixedSize, getPaddingChar(), str, getCharset(encoding));
    }

    default int writeStringOfSize(int fixedSize, String str, Charset charset)
            throws IOException {
        return writeStringOfSize(fixedSize, getPaddingChar(), str, charset);
    }

    // writeStringOfSize-padding

    int writeStringOfSize(int fixedSize, char padding, String str)
            throws IOException;

    int writeUtf8StringOfSize(int fixedSize, char padding, String str)
            throws IOException;

    default int writeStringOfSize(int fixedSize, char padding, String str, String encoding)
            throws IOException {
        return writeStringOfSize(fixedSize, padding, str, getCharset(encoding));
    }

    int writeStringOfSize(int fixedSize, char padding, String str, Charset charset)
            throws IOException;

    IDataOut NULL_LE = new NullDataOut(false);
    IDataOut NULL_BE = new NullDataOut(true);

}
