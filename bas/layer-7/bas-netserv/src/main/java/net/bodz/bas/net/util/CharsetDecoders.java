package net.bodz.bas.net.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.t.buffer.IByteBuffer;
import net.bodz.bas.t.buffer.ICharBuffer;

public class CharsetDecoders {

    static final Logger logger = LoggerFactory.getLogger(CharsetDecoders.class);

    CharsetDecoder decoder;
    boolean debug;

    ByteBuffer byteBuf;
    CharBuffer charBuf;
    boolean reuseByteBuf = false;

    public CharsetDecoders(Charset charset) {
        decoder = charset.newDecoder();
        decoder.onMalformedInput(CodingErrorAction.REPLACE);

        if (debug) {
            byteBuf = ByteBuffer.allocate(4);
            charBuf = CharBuffer.allocate(2);
        } else {
            byteBuf = ByteBuffer.allocate(4096);
            charBuf = CharBuffer.allocate(4096);
        }
    }

    public void decode(IByteBuffer input, ICharBuffer output, boolean endOfInput) {
        while (input.isNotEmpty()) {
            int block = Math.min(input.length(), byteBuf.remaining());
            if (block == 0)
                throw new UnexpectedException();
            input.get(0, byteBuf, block);
            boolean _endOfInput = endOfInput && block == input.length();

            byteBuf.flip();
            decoder.decode(byteBuf, charBuf, _endOfInput);
            byteBuf.compact();

            if (reuseByteBuf) {
                input.delete(0, block);
            } else {
                int pending = byteBuf.remaining();
                input.delete(0, block - pending);
                byteBuf.clear();
            }

            charBuf.flip();
            output.append(charBuf);
            charBuf.clear();
        } // while targetBuffer is not empty
        assert input.isEmpty();
    }

}
