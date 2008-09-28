package net.bodz.bas.text.encodings;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

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

    void decode(Reader in, OutputStream out) throws IOException;

    void decode(Object charIn, Object byteOut) throws IOException;

    /**
     * @return <code>null</code> if <code>charIn</code> is null.
     */
    byte[] decode(Object charIn, int cbAppx) throws IOException;

    /**
     * @return <code>null</code> if <code>charIn</code> is null.
     */
    byte[] decode(Object charIn) throws IOException;

    /**
     * @return <code>null</code> if <code>s</code> is null.
     */
    byte[] decode(String s);

}
