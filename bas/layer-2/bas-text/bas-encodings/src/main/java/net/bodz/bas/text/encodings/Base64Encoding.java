package net.bodz.bas.text.encodings;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Encoding
        extends _Encoding {

    private static BASE64Encoder encoder;
    private static BASE64Decoder decoder;
    static {
        encoder = new BASE64Encoder();
        decoder = new BASE64Decoder();
    }

    public Base64Encoding() {
        super(5, 8);
    }

    @Override
    protected boolean streamOnlyImplementation() {
        return true;
    }

    @Override
    protected void _encode(InputStream in, OutputStream out)
            throws IOException {
        encoder.encode(in, out);
    }

    @Override
    protected void _decode(InputStream in, OutputStream out)
            throws IOException {
        decoder.decodeBuffer(in, out);
    }

    @Override
    public void encode(InputStream in, Writer out)
            throws IOException {
        _encode(in, Files.getOutputStream(out, charset));
    }

    @Override
    public void decode(Reader in, OutputStream out)
            throws IOException {
        _decode(Files.getInputStream(in, charset), out);
    }

}
