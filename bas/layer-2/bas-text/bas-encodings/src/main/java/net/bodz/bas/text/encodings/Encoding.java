package net.bodz.bas.text.encodings;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.io.out.IByteOut;
import net.bodz.bas.io.out.ICharOut;

public interface Encoding {

    String preferredStringCharset();

    /**
     * @throws NullPointerException
     */
    void encode(InputStream in, ICharOut out)
            throws IOException;

    /**
     * @throws NullPointerException
     */
    void encode(InputStream in, Writer out)
            throws IOException;

    /**
     * @throws NullPointerException
     */
    String encode(InputStream in, int cbAppx)
            throws IOException;

    /**
     * @throws NullPointerException
     */
    String encode(InputStream in)
            throws IOException;

    /**
     * @throws NullPointerException
     */
    String encode(byte[] bytes);

    /**
     * @throws NullPointerException
     * @throws IndexOutOfBoundsException
     */
    String encode(byte[] bytes, int from, int to);

    /**
     * @throws NullPointerException
     */
    void decode(Reader in, OutputStream out)
            throws IOException, ParseException;

    /**
     * @throws NullPointerException
     */
    void decode(Reader in, IByteOut out)
            throws IOException, ParseException;

    /**
     * @throws NullPointerException
     */
    byte[] decode(Reader in, int cbAppx)
            throws IOException, ParseException;

    /**
     * @throws NullPointerException
     */
    byte[] decode(Reader in)
            throws IOException, ParseException;

    /**
     * @throws NullPointerException
     */
    byte[] decode(String s)
            throws ParseException;

    /**
     * @throws NullPointerException
     * @throws IndexOutOfBoundsException
     */
    byte[] decode(char[] chars, int from, int to)
            throws ParseException;

}
