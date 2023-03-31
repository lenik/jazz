package net.bodz.bas.io;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.err.ParseException;
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
        Charset charset = Charset.forName(encoding);
        return writeChar(ch, charset);
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
        Charset charset = Charset.forName(encoding);
        return writeChars(buf, 0, buf.length, charset);
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
        Charset charset = Charset.forName(encoding);
        writeString(lengthType, str, charset);
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

    // writeFixedSizeChars

    default void writeFixedSizeChars(int fixedSize, char[] buf)
            throws IOException {
        writeFixedSizeChars(fixedSize, getPaddingChar(), buf, 0, buf.length);
    }

    default void writeFixedSizeChars(int fixedSize, char[] buf, int off, int len)
            throws IOException {
        writeFixedSizeChars(fixedSize, getPaddingChar(), buf, off, len);
    }

    default int writeFixedSizeUtf8Chars(int fixedSize, char[] buf)
            throws IOException {
        return writeFixedSizeUtf8Chars(fixedSize, getPaddingChar(), buf, 0, buf.length);
    }

    default int writeFixedSizeUtf8Chars(int fixedSize, char[] buf, int off, int len)
            throws IOException {
        return writeFixedSizeUtf8Chars(fixedSize, getPaddingChar(), buf, off, len);
    }

    default int writeFixedSizeChars(int fixedSize, char[] buf, String encoding)
            throws IOException, ParseException {
        Charset charset = Charset.forName(encoding);
        return writeFixedSizeChars(fixedSize, getPaddingChar(), buf, 0, buf.length, charset);
    }

    default int writeFixedSizeChars(int fixedSize, char[] buf, Charset charset)
            throws IOException, ParseException {
        return writeFixedSizeChars(fixedSize, getPaddingChar(), buf, 0, buf.length, charset);
    }

    default int writeFixedSizeChars(int fixedSize, char[] buf, int off, int len, String encoding)
            throws IOException, ParseException {
        Charset charset = Charset.forName(encoding);
        return writeFixedSizeChars(fixedSize, getPaddingChar(), buf, off, len, charset);
    }

    default int writeFixedSizeChars(int fixedSize, char[] buf, int off, int len, Charset charset)
            throws IOException, ParseException {
        return writeFixedSizeChars(fixedSize, getPaddingChar(), buf, off, len, charset);
    }

    // writeFixedSizeChars_padding

    default void writeFixedSizeChars(int fixedSize, char padding, char[] buf)
            throws IOException {
        writeFixedSizeChars(fixedSize, padding, buf, 0, buf.length);
    }

    void writeFixedSizeChars(int fixedSize, char padding, char[] buf, int off, int len)
            throws IOException;

    default int writeFixedSizeUtf8Chars(int fixedSize, char padding, char[] buf)
            throws IOException {
        return writeFixedSizeUtf8Chars(fixedSize, padding, buf, 0, buf.length);
    }

    int writeFixedSizeUtf8Chars(int fixedSize, char padding, char[] buf, int off, int len)
            throws IOException;

    default int writeFixedSizeChars(int fixedSize, char padding, char[] buf, String encoding)
            throws IOException, ParseException {
        Charset charset = Charset.forName(encoding);
        return writeFixedSizeChars(fixedSize, padding, buf, 0, buf.length, charset);
    }

    default int writeFixedSizeChars(int fixedSize, char padding, char[] buf, int off, int len, String encoding)
            throws IOException, ParseException {
        Charset charset = Charset.forName(encoding);
        return writeFixedSizeChars(fixedSize, padding, buf, off, len, charset);
    }

    default int writeFixedSizeChars(int fixedSize, char padding, char[] buf, Charset charset)
            throws IOException, ParseException {
        return writeFixedSizeChars(fixedSize, padding, buf, 0, buf.length, charset);
    }

    int writeFixedSizeChars(int fixedSize, char padding, char[] buf, int off, int len, Charset charset)
            throws IOException, ParseException;

    // writeFixedSizeString

    default void writeFixedSizeString(int fixedSize, String str)
            throws IOException {
        writeFixedSizeString(fixedSize, getPaddingChar(), str);
    }

    default int writeFixedSizeUtf8String(int fixedSize, String str)
            throws IOException {
        return writeFixedSizeUtf8String(fixedSize, getPaddingChar(), str);
    }

    default int writeFixedSizeString(int fixedSize, String str, String encoding)
            throws IOException, ParseException {
        Charset charset = Charset.forName(encoding);
        return writeFixedSizeString(fixedSize, getPaddingChar(), str, charset);
    }

    default int writeFixedSizeString(int fixedSize, String str, Charset charset)
            throws IOException, ParseException {
        return writeFixedSizeString(fixedSize, getPaddingChar(), str, charset);
    }

    // writeFixedSizeString-padding

    void writeFixedSizeString(int fixedSize, char padding, String str)
            throws IOException;

    int writeFixedSizeUtf8String(int fixedSize, char padding, String str)
            throws IOException;

    default int writeFixedSizeString(int fixedSize, char padding, String str, String encoding)
            throws IOException, ParseException {
        Charset charset = Charset.forName(encoding);
        return writeFixedSizeString(fixedSize, padding, str, charset);
    }

    int writeFixedSizeString(int fixedSize, char padding, String str, Charset charset)
            throws IOException, ParseException;

    IDataOut NULL_LE = new NullDataOut(false);
    IDataOut NULL_BE = new NullDataOut(true);

}
