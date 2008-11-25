package net.bodz.bas.text.encodings;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.err.ParseException;
import static net.bodz.bas.types.util.ArrayOps.Bytes;

public abstract class _Encoding implements Encoding {

    protected final String charset;
    private final float    bpc;
    private final boolean  streamOnly;

    public _Encoding(int blockBytes, int blockChars) {
        this.charset = preferredStringCharset();
        assert blockBytes != 0;
        assert blockChars != 0;
        this.bpc = (float) blockBytes / (float) blockChars;
        this.streamOnly = streamOnlyImplementation();
    }

    @Override
    public String preferredStringCharset() {
        return "ascii";
    }

    protected boolean streamOnlyImplementation() {
        return false;
    }

    protected void _encode(InputStream in, OutputStream out) throws IOException {
        encode(in, Files.getWriter(out, charset));
    }

    @Override
    public void encode(Object byteIn, Object charOut) throws IOException {
        InputStream in = Files.getInputStream(byteIn);
        boolean closeIn = Files.shouldClose(byteIn);
        try {
            if (charOut instanceof OutputStream) {
                _encode(in, (OutputStream) charOut);
            } else {
                Writer out = Files.getWriter(charOut, charset);
                boolean closeOut = Files.shouldClose(charOut);
                try {
                    encode(in, out);
                } finally {
                    if (closeOut)
                        out.close();
                }
            }
        } finally {
            if (closeIn)
                in.close();
        }
    }

    @Override
    public String encode(Object byteIn, int cb) throws IOException {
        if (byteIn == null)
            return null;
        int cc = cb == 0 ? 0 : (int) (cb / bpc);
        if (streamOnly) {
            ByteArrayOutputStream buf = cc == 0 //
            ? new ByteArrayOutputStream()
                    : new ByteArrayOutputStream(cc);
            encode(byteIn, buf);
            byte[] byteArray = buf.toByteArray();
            return new String(byteArray, charset);
        } else {
            StringWriter buf = cc == 0 //
            ? new StringWriter()
                    : new StringWriter(cc);
            encode(byteIn, buf);
            return buf.toString();
        }
    }

    @Override
    public String encode(Object bytesIn) throws IOException {
        return encode(bytesIn, 0);
    }

    @Override
    public String encode(byte[] bytes) {
        if (bytes == null)
            return null;
        try {
            return encode(bytes, bytes.length);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public String encode(byte[] bytes, int from, int to) {
        byte[] copy = Bytes.copyOfRange(bytes, from, to);
        return encode(copy);
    }

    protected void _decode(InputStream in, OutputStream out)
            throws IOException, ParseException {
        decode(Files.getReader(in, charset), out);
    }

    @Override
    public void decode(Object charIn, Object byteOut) throws IOException,
            ParseException {
        OutputStream out = Files.getOutputStream(byteOut);
        boolean closeOut = Files.shouldClose(byteOut);
        try {
            if (charIn instanceof InputStream) {
                _decode((InputStream) charIn, out);
            } else {
                Reader in = Files.getReader(charIn, charset);
                boolean closeIn = Files.shouldClose(charIn);
                try {
                    decode(in, out);
                } finally {
                    if (closeIn)
                        in.close();
                }
            }
        } finally {
            if (closeOut)
                out.close();
        }
    }

    @Override
    public byte[] decode(Object charIn, int cc) throws IOException,
            ParseException {
        if (charIn == null)
            return null;
        int cb = cc == 0 ? 0 : (int) (cc * bpc);
        ByteArrayOutputStream buf = cb == 0 //
        ? new ByteArrayOutputStream()
                : new ByteArrayOutputStream(cb);
        decode(charIn, buf);
        byte[] byteArray = buf.toByteArray();
        return byteArray;
    }

    @Override
    public byte[] decode(Object charIn) throws IOException, ParseException {
        return decode(charIn, 0);
    }

    @Override
    public byte[] decode(String s) throws ParseException {
        if (s == null)
            return null;
        try {
            return decode(new StringReader(s), s.length());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public byte[] decode(char[] chars, int from, int to) throws ParseException {
        String s = new String(chars, from, to - from);
        return decode(s);
    }

}
