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

/**
 * @test {@link WriterOutputStreamTest}
 */
public class WriterOutputStream extends OutputStream {

    protected final Writer writer;
    private CharsetDecoder decoder;
    private ByteBuffer bytebuf;
    private CharBuffer charbuf;

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
    public void flush() throws IOException {
        decode(true);
        writer.flush();
    }

    @Override
    public void write(int b) throws IOException {
        bytebuf.put((byte) b);
        decode(false);
    }

    @Override
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        while (len > 0) {
            int blockSize = Math.min(bytebuf.remaining(), len);
            bytebuf.put(b, off, blockSize);
            off += blockSize;
            len -= blockSize;
            decode(len == 0);
        }
    }

    protected void decode(boolean end) throws IOException {
        bytebuf.flip();
        {
            CoderResult result = decoder.decode(bytebuf, charbuf, end);
            if (end)
                decoder.reset();
            if (result.isOverflow())
                throw new UnexpectedException("charbuf overflow"); //$NON-NLS-1$
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
