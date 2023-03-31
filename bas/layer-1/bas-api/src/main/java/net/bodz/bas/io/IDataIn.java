package net.bodz.bas.io;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.err.BadFormatException;
import net.bodz.bas.err.ParseException;

public interface IDataIn
        extends
            IByteIn {

    boolean isBigEndian();

    /**
     * @exception EOFException
     *                if this stream reaches the end before reading all the bytes.
     * @exception IOException
     *                if an I/O error occurs.
     */
    byte readByte()
            throws IOException;

    /**
     * @exception EOFException
     *                if this stream reaches the end before reading all the bytes.
     * @exception IOException
     *                if an I/O error occurs.
     */
    short readWord()
            throws IOException;

    /**
     * @exception EOFException
     *                if this stream reaches the end before reading all the bytes.
     * @exception IOException
     *                if an I/O error occurs.
     */
    int readDword()
            throws IOException;

    /**
     * @exception EOFException
     *                if this stream reaches the end before reading all the bytes.
     * @exception IOException
     *                if an I/O error occurs.
     */
    long readQword()
            throws IOException;

    /**
     * @exception EOFException
     *                if this stream reaches the end before reading all the bytes.
     * @exception IOException
     *                if an I/O error occurs.
     */
    float readFloat()
            throws IOException;

    /**
     * @exception EOFException
     *                if this stream reaches the end before reading all the bytes.
     * @exception IOException
     *                if an I/O error occurs.
     */
    double readDouble()
            throws IOException;

    /**
     * @exception EOFException
     *                if this stream reaches the end before reading all the bytes.
     * @exception IOException
     *                if an I/O error occurs.
     */
    boolean readBool()
            throws IOException;

    /**
     * Read UCS-16 character.
     *
     * The byte order is the same as {@link #readWord()}.
     *
     * @exception EOFException
     *                if this stream reaches the end before reading all the bytes.
     * @exception IOException
     *                if an I/O error occurs.
     */
    default char readChar()
            throws IOException {
        // UTF-16 LE/BE depends on implementation.
        return (char) (readWord() & 0xffff);
    }

    char readUtf8Char_fast()
            throws IOException;

    /**
     * @exception EOFException
     *                if this stream reaches the end before reading all the bytes.
     * @exception IOException
     *                if an I/O error occurs.
     */
    char readUtf8Char()
            throws IOException, ParseException;

    default EncodedChar readChar(String encoding)
            throws IOException, ParseException {
        Charset charset = Charset.forName(encoding);
        return readChar(charset);
    }

    default EncodedChar readChar(String encoding, char fallback)
            throws IOException {
        Charset charset = Charset.forName(encoding);
        return readChar(charset, fallback);
    }

    /**
     * @exception EOFException
     *                if this stream reaches the end before reading all the bytes.
     * @exception IOException
     *                if an I/O error occurs.
     * @exception ParseException
     *                If malformed character occurrs.
     */
    EncodedChar readChar(Charset charset)
            throws IOException, ParseException;

    EncodedChar readChar(Charset charset, char fallback)
            throws IOException;

    /**
     * @exception EOFException
     *                if this stream reaches the end before reading all the bytes.
     * @exception IOException
     *                if an I/O error occurs.
     */
    default void readBytes(byte[] buf)
            throws IOException {
        readBytes(buf, 0, buf.length);
    }

    /**
     * @exception EOFException
     *                if this stream reaches the end before reading all the bytes.
     * @exception IOException
     *                if an I/O error occurs.
     */
    void readBytes(byte[] buf, int off, int len)
            throws IOException;

    default void readWords(short[] buf)
            throws IOException {
        readWords(buf, 0, buf.length);
    }

    void readWords(short[] buf, int off, int len)
            throws IOException;

    default void readDwords(int[] buf)
            throws IOException {
        readDwords(buf, 0, buf.length);
    }

    void readDwords(int[] buf, int off, int len)
            throws IOException;

    default void readQwords(long[] buf)
            throws IOException {
        readQwords(buf, 0, buf.length);
    }

    void readQwords(long[] buf, int off, int len)
            throws IOException;

    default void readFloats(float[] buf)
            throws IOException {
        readFloats(buf, 0, buf.length);
    }

    void readFloats(float[] buf, int off, int len)
            throws IOException;

    default void readDoubles(double[] buf)
            throws IOException {
        readDoubles(buf, 0, buf.length);
    }

    void readDoubles(double[] buf, int off, int len)
            throws IOException;

    default void readBools(boolean[] buf)
            throws IOException {
        readBools(buf, 0, buf.length);
    }

    void readBools(boolean[] buf, int off, int len)
            throws IOException;

    // read[n]

    default byte[] readBytes(int n)
            throws IOException {
        byte[] buf = new byte[n];
        readBytes(buf);
        return buf;
    }

    default short[] readWords(int n)
            throws IOException {
        short[] buf = new short[n];
        readWords(buf);
        return buf;
    }

    default int[] readDwords(int n)
            throws IOException {
        int[] buf = new int[n];
        readDwords(buf);
        return buf;
    }

    default long[] readQwords(int n)
            throws IOException {
        long[] buf = new long[n];
        readQwords(buf);
        return buf;
    }

    default float[] readFloats(int n)
            throws IOException {
        float[] buf = new float[n];
        readFloats(buf);
        return buf;
    }

    default double[] readDoubles(int n)
            throws IOException {
        double[] buf = new double[n];
        readDoubles(buf);
        return buf;
    }

    default boolean[] readBools(int n)
            throws IOException {
        boolean[] buf = new boolean[n];
        readBools(buf);
        return buf;
    }

    // readChars(n)

    default EncodedCharArray readChars(int n)
            throws IOException {
        char[] buf = new char[n];
        readChars(buf);
        return new EncodedCharArray(n, buf);
    }

    default EncodedCharArray readUtf8Chars(int n)
            throws IOException, ParseException {
        char[] buf = new char[n];
        int size = readUtf8Chars(buf);
        return new EncodedCharArray(size, buf);
    }

    default EncodedCharArray readChars(int n, String encoding)
            throws IOException, ParseException {
        Charset charset = Charset.forName(encoding);
        return readChars(n, charset);
    }

    default EncodedCharArray readChars(int n, Charset charset)
            throws IOException, ParseException {
        char[] buf = new char[n];
        int size = readChars(buf, 0, buf.length, charset);
        return new EncodedCharArray(size, buf);
    }

    default EncodedString readString(int n)
            throws IOException {
        return readChars(n).toEncodedString();
    }

    default EncodedString readUtf8String(int n)
            throws IOException, ParseException {
        return readUtf8Chars(n).toEncodedString();
    }

    default EncodedString readString(int n, String encoding)
            throws IOException, ParseException {
        Charset charset = Charset.forName(encoding);
        return readChars(n, charset).toEncodedString();
    }

    default EncodedString readString(int n, Charset charset)
            throws IOException, ParseException {
        return readChars(n, charset).toEncodedString();
    }

    // readChars

    default int readChars(char[] buf)
            throws IOException {
        return readChars(buf, 0, buf.length);
    }

    int readChars(char[] buf, int off, int len)
            throws IOException;

    default void readUtf8Chars_fast(char[] buf)
            throws IOException {
        readUtf8Chars_fast(buf, 0, buf.length);
    }

    void readUtf8Chars_fast(char[] buf, int off, int len)
            throws IOException;

    default int readUtf8Chars(char[] buf)
            throws IOException, ParseException {
        return readUtf8Chars(buf, 0, buf.length);
    }

    int readUtf8Chars(char[] buf, int off, int len)
            throws IOException, ParseException;

    default int readChars(char[] buf, String encoding)
            throws IOException, ParseException {
        Charset charset = Charset.forName(encoding);
        return readChars(buf, 0, buf.length, charset);
    }

    default int readChars(char[] buf, int off, int len, String encoding)
            throws IOException, ParseException {
        Charset charset = Charset.forName(encoding);
        return readChars(buf, off, len, charset);
    }

    default int readChars(char[] buf, Charset charset)
            throws IOException, ParseException {
        return readChars(buf, 0, buf.length, charset);
    }

    int readChars(char[] buf, int off, int len, Charset charset)
            throws IOException, ParseException;

    /**
     * Read wide ucs-16 string.
     *
     * @throws BadFormatException
     *             If the string format is illegal.
     * @exception EOFException
     *                if this stream reaches the end before reading all the bytes.
     *                <ul>
     *                <li>For fixed-length/size string, there are insufficient chars.
     *                <li>For xxx-term-ed string, the term char seq is missing.
     *                </ul>
     * @exception IOException
     *                if an I/O error occurs.
     */
    String readString(LengthType lengthType)
            throws IOException;

    String readUtf8String_fast(LengthType lengthType)
            throws IOException;

    EncodedString readUtf8String(LengthType lengthType)
            throws IOException, ParseException;

    /**
     * Read string in specific encoding.
     *
     * @throws BadFormatException
     *             If the string format is illegal.
     * @exception EOFException
     *                if this stream reaches the end before reading all the bytes.
     *                <ul>
     *                <li>For fixed-length/size string, there are insufficient chars.
     *                <li>For xxx-term-ed string, the term char seq is missing.
     *                </ul>
     * @exception IOException
     *                if an I/O error occurs.
     */
    default EncodedString readString(LengthType lengthType, String encoding)
            throws IOException, ParseException {
        Charset charset = Charset.forName(encoding);
        return readString(lengthType, charset);
    }

    EncodedString readString(LengthType lengthType, Charset charset)
            throws IOException, ParseException;

}
