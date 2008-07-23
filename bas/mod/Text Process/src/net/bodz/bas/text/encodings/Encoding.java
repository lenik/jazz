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

    String encode(Object byteIn, int cb) throws IOException;

    String encode(Object bytesIn) throws IOException;

    String encode(byte[] bytes);

    void decode(Reader in, OutputStream out) throws IOException;

    void decode(Object charIn, Object byteOut) throws IOException;

    byte[] decode(Object charIn, int cb) throws IOException;

    byte[] decode(Object charIn) throws IOException;

    byte[] decode(String s);

}
