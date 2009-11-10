package net.bodz.bas.text.encodings;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import net.bodz.bas.lang.err.ParseException;

public interface Encoding {

    String preferredStringCharset();

    void encode(InputStream in, Writer out) throws IOException;

    void encode(Object byteIn, Object charOut) throws IOException;

    /**
     * @return <code>null</code> if <code>byteIn</code> is null.
     */
    String encode(Object byteIn, int cbAppx) throws IOException;

    /**
     * @return <code>null</code> if <code>byteIn</code> is null.
     */
    String encode(Object byteIn) throws IOException;

    /**
     * @return <code>null</code> if <code>bytes</code> is null.
     */
    String encode(byte[] bytes);

    String encode(byte[] bytes, int from, int to);

    void decode(Reader in, OutputStream out) throws IOException, ParseException;

    void decode(Object charIn, Object byteOut) throws IOException, ParseException;

    /**
     * @return <code>null</code> if <code>charIn</code> is null.
     */
    byte[] decode(Object charIn, int cbAppx) throws IOException, ParseException;

    /**
     * @return <code>null</code> if <code>charIn</code> is null.
     */
    byte[] decode(Object charIn) throws IOException, ParseException;

    /**
     * @return <code>null</code> if <code>s</code> is null.
     */
    byte[] decode(String s) throws ParseException;

    byte[] decode(char[] chars, int from, int to) throws ParseException;

}
