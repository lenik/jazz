package net.bodz.bios.units.text;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

public class EncodeUnit extends TextProcessUnit {

    private CharsetEncoder encoder;
    private CharBuffer     unconv;
    private ByteBuffer     convBuf;
    private boolean        flush;

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
    public void reset() throws IOException {
        convBuf.reset();
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public void recv(char[] bytes, int start, int end) throws IOException {
        unconv.put(bytes, start, end - start);
        boolean endOfInput = flush;
        CoderResult result = encoder.encode(unconv, convBuf, endOfInput);
        // result.isMalformed()
        send(convBuf);
        convBuf.clear();
    }

}
