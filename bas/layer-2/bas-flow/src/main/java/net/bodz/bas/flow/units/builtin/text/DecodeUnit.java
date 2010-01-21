package net.bodz.bas.flow.units.builtin.text;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

import net.bodz.bas.exceptions.IllegalUsageError;

public class DecodeUnit
        extends BinaryProcessUnit {

    private CharsetDecoder decoder;
    private ByteBuffer unconv;
    private CharBuffer convBuf;

    public DecodeUnit(String charsetName) {
        this(Charset.forName(charsetName));
    }

    public DecodeUnit(Charset charset) {
        this(charset.newDecoder());
    }

    public DecodeUnit(CharsetDecoder decoder) {
        this(decoder, 256);
    }

    public DecodeUnit(CharsetDecoder decoder, int capacity) {
        this.decoder = decoder;
        this.unconv = ByteBuffer.allocate(capacity);
        float convCapacity = capacity * decoder.averageCharsPerByte();
        this.convBuf = CharBuffer.allocate((int) convCapacity);
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
    public void recv(byte[] bytes, int start, int end)
            throws IOException {
        int length = end - start;
        while (length > 0) {
            int block = Math.min(length, unconv.remaining());
            unconv.put(bytes, start, block);
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
            result = decoder.decode(unconv, convBuf, endOfInput);
            if (convBuf.position() != 0) {
                convBuf.flip();
                send(convBuf);
                convBuf.clear();
            }
            // avoid empty-loop.
            else if (result.isOverflow())
                throw new IllegalUsageError("capacity too small");
            if (result.isError()) {
                // remove the error byte
                byte errByte = unconv.get();
                send(errByte);
            }
        } while (result.isOverflow());
        unconv.compact();
    }

}
