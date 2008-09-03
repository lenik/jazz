package net.bodz.bas.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

import net.bodz.bas.lang.err.UnexpectedException;

public class WriterOutputStream extends OutputStream {

    protected final Writer writer;
    private CharsetDecoder decoder;
    private ByteBuffer     bytebuf;
    private CharBuffer     charbuf;

    public WriterOutputStream(Writer writer, Charset charset) {
        this.writer = writer;
        this.decoder = charset.newDecoder();
        this.bytebuf = ByteBuffer.allocate(32);
        this.charbuf = CharBuffer.allocate(32);
    }

    public WriterOutputStream(Writer writer, String charsetName) {
        this(writer, Charset.forName(charsetName));
    }

    public WriterOutputStream(Writer writer) {
        this(writer, Charset.defaultCharset());
    }

    @Override
    public void write(int b) throws IOException {
        bytebuf.put((byte) b);
        decode(false);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        bytebuf.put(b, off, len);
        decode(false);
    }

    @Override
    public void write(byte[] b) throws IOException {
        bytebuf.put(b);
        decode(false);
    }

    @Override
    public void flush() throws IOException {
        decode(true);
        writer.flush();
    }

    protected void decode(boolean end) throws IOException {
        bytebuf.flip();
        {
            CoderResult result = decoder.decode(bytebuf, charbuf, end);
            if (result.isOverflow())
                throw new UnexpectedException("charbuf overflow");
            if (result.isError()) {
                if (result.isMalformed())
                    handleMalformed(bytebuf);
                else if (result.isUnmappable())
                    handleUnmappable(bytebuf);
                else
                    handleError(bytebuf);
            }
            charbuf.flip();
            {
                int len = charbuf.limit();
                for (int i = 0; i < len; i++) {
                    char c = charbuf.charAt(i);
                    writer.write(c);
                }
            }
            charbuf.clear();
        }
        bytebuf.compact();
    }

    protected void handleMalformed(ByteBuffer buffer) throws IOException {
        handleError(buffer);
    }

    protected void handleUnmappable(ByteBuffer buffer) throws IOException {
        handleError(buffer);
    }

    protected void handleError(ByteBuffer buffer) throws IOException {
        writeErrorByte(buffer.get());
    }

    protected void writeErrorByte(byte b) throws IOException {
        writer.write('\uFFFD');
    }

}
