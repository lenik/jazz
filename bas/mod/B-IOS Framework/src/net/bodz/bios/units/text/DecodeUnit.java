package net.bodz.bios.units.text;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

public class DecodeUnit extends BinaryProcessUnit {

    private CharsetDecoder decoder;
    private ByteBuffer     unconv;
    private CharBuffer     convBuf;
    private boolean        flush;

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
    public void reset() throws IOException {
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public void recv(byte[] bytes, int start, int end) throws IOException {
        unconv.put(bytes, start, end - start);
        boolean endOfInput = flush;
        CoderResult result = decoder.decode(unconv, convBuf, endOfInput);
        // result.isMalformed()
        String conv = convBuf.toString();
        convBuf.clear();
        send(conv);
    }

}
