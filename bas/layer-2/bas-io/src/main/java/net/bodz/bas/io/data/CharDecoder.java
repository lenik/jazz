package net.bodz.bas.io.data;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnsupportedFeatureException;
import net.bodz.bas.io.IByteIn;

public class CharDecoder
        extends AbstractCharCodec {

    IByteIn in;

    Charset charset;
    CharsetDecoder decoder;

    ByteBuffer inBuffer;
    CharBuffer outBuffer;
    CoderResult result;

    boolean replaceMalformed;
    char replaceMalformedChar;

    // by block
    public float maxCharsPerByte;
    public float _minBytesPerChar;
    byte[] iblock;

    public CharDecoder(Charset charset, IByteIn in) {
        this.in = in;
        this.charset = charset;
        decoder = charset.newDecoder();
        inBuffer = ByteBuffer.allocate(10);
        outBuffer = CharBuffer.allocate(10);
    }

    public CharDecoder(Charset charset, IByteIn in, int byteCapacity) {
        super(true); // by-blocks

        this.in = in;
        this.charset = charset;
        decoder = charset.newDecoder();
        inBuffer = ByteBuffer.allocate(byteCapacity);

        float averageChars = decoder.averageCharsPerByte() * byteCapacity;
        int charCapacity = (int) averageChars + 1;
        outBuffer = CharBuffer.allocate(charCapacity);

        maxCharsPerByte = decoder.maxCharsPerByte();
        if (maxCharsPerByte > 1.0f)
            throw new UnsupportedFeatureException("compressed encoding");

        _minBytesPerChar = 1.0f / maxCharsPerByte;
        iblock = new byte[blockSize];
    }

    public void fallback(char ch) {
        replaceMalformed = true;
        replaceMalformedChar = ch;
    }

    public int decodeChar()
            throws ParseException, IOException {
        int capacity = inBuffer.capacity();
        int pos = 0;
        do {
            int byt = in.read();
            if (byt == -1) {
                if (pos != 0)
                    throw error("expect more bytes");
            }

            inBuffer.limit(capacity);
            inBuffer.position(pos);
            inBuffer.put((byte) byt);
            pos++;
            inBuffer.flip();

            outBuffer.clear();
            result = decoder.decode(inBuffer, outBuffer, false);

            if (result.isError())
                return handleError();
        } while (outBuffer.position() == 0);

        outBuffer.flip();

        char ch = outBuffer.get();
        return ch;
    }

    public String decodeChars(int maxChars)
            throws ParseException, IOException {
        StringBuilder sb = new StringBuilder(Math.min(maxChars, maxPrealloc));
        decodeChars(sb, maxChars);
        return sb.toString();
    }

    public ConvertedLengths decodeChars(StringBuilder buf, int maxCharsToDecode)
            throws ParseException, IOException {
        int nByteRead = 0;
        int nCharDecoded = 0;

        inBuffer.limit(0);
        while (nCharDecoded < maxCharsToDecode) {
            int nCharsReq = maxCharsToDecode - nCharDecoded;
            int maxBytesCanRead = (int) (nCharsReq * _minBytesPerChar);
            if (maxBytesCanRead == 0)
                maxBytesCanRead = 1;

            int cb = Math.min(maxBytesCanRead, iblock.length);
            cb = in.read(iblock, 0, cb);
            if (cb == -1) { // EOF
                if (inBuffer.hasRemaining())
                    throw error("expect more bytes");
                else
                    break;
            }

            nByteRead += cb;
            nCharDecoded += decodeBlock(buf, iblock, 0, cb).charCount;
        }
        return ConvertedLengths.decoded(nByteRead, nCharDecoded);
    }

    public ConvertedLengths decodeBlock(StringBuilder buf, byte[] block, int off, int len)
            throws ParseException, IOException {
        int nCharDecoded = 0;
        int pos = off;
        while (off < len) {
            inBuffer.compact();
            pos += copy(inBuffer, block, pos, len - pos);

            inBuffer.flip();

            outBuffer.clear();
            result = decoder.decode(inBuffer, outBuffer, false);

            outBuffer.flip();
            boolean empty = !outBuffer.hasRemaining();
            nCharDecoded += copy(buf, outBuffer);

            if (result.isError()) {
                char fallback = handleError();
                buf.append(fallback);
                // decoder.replaceWith(fallback);
            }
            if (empty)
                break;
        }
        return ConvertedLengths.decoded(pos - off, nCharDecoded);
    }

    char handleError()
            throws ParseException {
        if (result.isMalformed()) {
            if (replaceMalformed)
                return replaceMalformedChar;
            throw error("malformed");
        }
        throw error("error");
    }

    ParseException error(String message) {
        return error(message, null);
    }

    ParseException error(String message, Exception cause) {
        StringBuilder sb = new StringBuilder();
        inBuffer.flip();
        int limit = inBuffer.limit();
        for (int i = 0; i < limit; i++) {
            int byt = inBuffer.get() & 0xff;
            if (i != 0)
                sb.append(" ");
            sb.append(Integer.toHexString(byt));
        }
        String description = String.format("%s: pending bytes: %s", message, sb.toString());
        if (cause == null)
            return new ParseException(description);
        else
            return new ParseException(description, cause);
    }

}
