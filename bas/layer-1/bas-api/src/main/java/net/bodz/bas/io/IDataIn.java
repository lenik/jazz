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

    /**
     * @exception EOFException
     *                if this stream reaches the end before reading all the bytes.
     * @exception IOException
     *                if an I/O error occurs.
     */
    char readUtf8Char()
            throws IOException;

    default char readChar(String encoding)
            throws IOException, ParseException {
        Charset charset = Charset.forName(encoding);
        return readChar(charset);
    }

    default char readChar(String encoding, char fallback)
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
    char readChar(Charset charset)
            throws IOException, ParseException;

    char readChar(Charset charset, char fallback)
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

    default void readChars(char[] buf)
            throws IOException {
        readChars(buf, 0, buf.length);
    }

    void readChars(char[] buf, int off, int len)
            throws IOException;

    default void readUtf8Chars(char[] buf)
            throws IOException {
        readUtf8Chars(buf, 0, buf.length);
    }

    void readUtf8Chars(char[] buf, int off, int len)
            throws IOException;

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

    String readUtf8String(LengthType lengthType)
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
    default String readString(LengthType lengthType, String encoding)
            throws IOException, ParseException {
        Charset charset = Charset.forName(encoding);
        return readString(lengthType, charset);
    }

    String readString(LengthType lengthType, Charset charset)
            throws IOException, ParseException;

}
