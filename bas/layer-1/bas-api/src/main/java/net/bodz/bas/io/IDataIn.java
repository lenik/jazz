package net.bodz.bas.io;

import java.io.EOFException;
import java.io.IOException;

import net.bodz.bas.err.DecodeException;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;

public interface IDataIn
        extends IByteIn {

    boolean isBigEndian();

    /**
     * @exception EOFException
     *                if this stream reaches the end before reading all the bytes.
     * @exception IOException
     *                if an I/O error occurs.
     */
    void readFully(byte[] buf)
            throws IOException;

    /**
     * @exception EOFException
     *                if this stream reaches the end before reading all the bytes.
     * @exception IOException
     *                if an I/O error occurs.
     */
    void readFully(byte[] buf, int off, int len)
            throws IOException;

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
    boolean readBoolean()
            throws IOException;

    /**
     * @throws DecodeException
     *             If illegal utf-8 char sequence occurred.
     * @exception EOFException
     *                if this stream reaches the end before reading all the bytes.
     * @exception IOException
     *                if an I/O error occurs.
     */
    char readUtf8Char()
            throws IOException;

    /**
     * Read UTF-encoded string.
     * 
     * @throws FormatException
     *             If the string format is illegal.
     * @exception EOFException
     *                if this stream reaches the end before reading all the bytes.
     *                <ul>
     *                <li>For fixed-length/size string, there are insufficient chars.
     *                <li>For xxx-term-ed string, the term char seq is missing.
     *                </ul>
     * @exception IOException
     *                if an I/O error occurs.
     * 
     * @see StringFlags#_16BIT
     * @see StringFlags#_32BIT
     * @see StringFlags#NULL_TERM
     * @see StringFlags#CR_TERM
     * @see StringFlags#LF_TERM
     * @see StringFlags#LENGTH_PREFIX
     * @see StringFlags#SIZE_PREFIX
     * @see StringFlags#LONG
     */
    String readString(int flags)
            throws IOException;

    /**
     * Read string in specific encoding.
     * 
     * @param encoding
     *            <code>null</code> for UTF-8/16/32.
     * @throws FormatException
     *             If the string format is illegal.
     * @exception EOFException
     *                if this stream reaches the end before reading all the bytes.
     *                <ul>
     *                <li>For fixed-length/size string, there are insufficient chars.
     *                <li>For xxx-term-ed string, the term char seq is missing.
     *                </ul>
     * @exception IOException
     *                if an I/O error occurs.
     * 
     * @see StringFlags#_16BIT
     * @see StringFlags#_32BIT
     * @see StringFlags#NULL_TERM
     * @see StringFlags#CR_TERM
     * @see StringFlags#LF_TERM
     * @see StringFlags#LENGTH_PREFIX
     * @see StringFlags#SIZE_PREFIX
     * @see StringFlags#LONG
     */
    String readString(int flags, String encoding)
            throws IOException, ParseException;

}
