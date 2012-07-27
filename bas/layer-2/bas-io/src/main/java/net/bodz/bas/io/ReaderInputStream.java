package net.bodz.bas.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.util.primitive.IntMath;

public class ReaderInputStream
        extends InputStream {

    protected final Reader reader;
    private CharsetEncoder encoder;
    private CharBuffer charbuf;
    private ByteBuffer bytebuf;

    public ReaderInputStream(Reader reader, Charset charset) {
        this.reader = reader;
        this.encoder = charset.newEncoder();
        this.charbuf = CharBuffer.allocate(32);
        this.bytebuf = ByteBuffer.allocate(32);
        bytebuf.flip();
    }

    public ReaderInputStream(Reader reader, String charsetName) {
        this(reader, Charset.forName(charsetName));
    }

    public ReaderInputStream(Reader reader) {
        this(reader, Charset.defaultCharset());
    }

    protected void encode(boolean end) {
        charbuf.flip();
        {
            bytebuf.clear();
            {
                CoderResult result = encoder.encode(charbuf, bytebuf, end);
                if (result.isOverflow())
                    throw new UnexpectedException("bytebuf overflow");
                assert !result.isError() : "encode error";
            }
            bytebuf.flip();
        }
        charbuf.compact();
    }

    @Override
    public int read()
            throws IOException {
        if (!bytebuf.hasRemaining()) {
            int c = reader.read();
            if (c == -1)
                return -1;
            charbuf.put((char) c);
            encode(false);
        }
        byte b = bytebuf.get();
        return IntMath.unsign(b);
    }

    // @Override
    // public int read(byte[] b, int off, int len) throws IOException {
    // int getlen = Math.min(bytebuf.remaining(), len);
    // bytebuf.get(b, off, len);
    // len -= getlen;
    // if (len > 0) {
    // //
    // }
    // throw new NotImplementedException();
    // }

}
