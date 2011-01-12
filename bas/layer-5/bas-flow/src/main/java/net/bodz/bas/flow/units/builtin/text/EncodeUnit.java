package net.bodz.bas.flow.units.builtin.text;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

import net.bodz.bas.util.exception.IllegalUsageError;

public class EncodeUnit
        extends TextProcessUnit {

    private CharsetEncoder encoder;
    private CharBuffer unconv;
    private ByteBuffer convBuf;

    public EncodeUnit(String charsetName) {
        this(Charset.forName(charsetName));
    }

    public EncodeUnit(Charset charset) {
        this(charset.newEncoder());
    }

    public EncodeUnit(CharsetEncoder decoder) {
        this(decoder, 256);
    }

    public EncodeUnit(CharsetEncoder encoder, int capacity) {
        this.encoder = encoder;
        this.unconv = CharBuffer.allocate(capacity);
        float convCapacity = capacity * encoder.averageBytesPerChar();
        this.convBuf = ByteBuffer.allocate((int) convCapacity);
    }

    @Override
    public void reset()
            throws IOException {
        unconv.reset();
        convBuf.reset();
    }

    @Override
    public void flush()
            throws IOException {
        assert convBuf.position() == 0;
        if (unconv.position() != 0)
            conv(true);
    }

    @Override
    public void recv(char[] chars, int start, int end)
            throws IOException {
        int length = end - start;
        while (length > 0) {
            int block = Math.min(length, unconv.remaining());
            unconv.put(chars, start, block);
            start += block;
            length -= block;
            conv(false);
        }
    }

    void conv(boolean endOfInput)
            throws IOException {
        unconv.flip();
        CoderResult result;
        do {
            result = encoder.encode(unconv, convBuf, endOfInput);
            if (convBuf.position() != 0) {
                convBuf.flip();
                send(convBuf);
                convBuf.clear();
            }
            // avoid empty-loop.
            else if (result.isOverflow())
                throw new IllegalUsageError("capacity too small");
            if (result.isError()) {
                // remove the error char
                char errChar = unconv.get();
                send(errChar);
            }
        } while (result.isOverflow());
        unconv.compact();
    }

}
