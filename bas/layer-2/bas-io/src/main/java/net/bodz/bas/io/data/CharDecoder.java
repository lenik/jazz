package net.bodz.bas.io.data;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.IByteIn;

public class CharDecoder {

    IByteIn in;

    Charset charset;
    CharsetDecoder decoder;

    ByteBuffer byteBuffer;
    CharBuffer charBuffer;
    CoderResult result;

    boolean replaceMalformed;
    char replaceMalformedChar;

    boolean replaceUnmappable;
    char replaceUnmappableChar;

    // by block
    public float maxCharsPerByte;
    public float minBytesPerChar;
    byte[] readBuf;
    char[] getbuf;

    static final int blockSize = 3;
    static final int maxPrealloc = 4096;

    public CharDecoder(Charset charset, IByteIn in) {
        this.in = in;
        this.charset = charset;
        decoder = charset.newDecoder();
        byteBuffer = ByteBuffer.allocate(10);
        charBuffer = CharBuffer.allocate(10);
    }

    public CharDecoder(Charset charset, IByteIn in, int byteCapacity) {
        this.in = in;
        this.charset = charset;
        decoder = charset.newDecoder();
        byteBuffer = ByteBuffer.allocate(byteCapacity);

        float averageChars = decoder.averageCharsPerByte() * byteCapacity;
        int charCapacity = (int) averageChars + 1;
        charBuffer = CharBuffer.allocate(charCapacity);

        maxCharsPerByte = decoder.maxCharsPerByte();
        minBytesPerChar = 1.0f / maxCharsPerByte;
        readBuf = new byte[blockSize];
        getbuf = new char[blockSize];

    }

    public void fallback(char ch) {
        replaceMalformed = replaceUnmappable = true;
        replaceMalformedChar = replaceUnmappableChar = ch;
    }

    public int decodeChar()
            throws ParseException, IOException {
        int capacity = byteBuffer.capacity();
        int pos = 0;
        do {
            int byt = in.read();
            if (byt == -1) {
                if (pos != 0)
                    throw error("expect more bytes");
            }

            byteBuffer.limit(capacity);
            byteBuffer.position(pos);
            byteBuffer.put((byte) byt);
            pos++;
            byteBuffer.flip();

            charBuffer.clear();
            result = decoder.decode(byteBuffer, charBuffer, false);

            if (result.isError())
                return handleError();
        } while (charBuffer.position() == 0);

        charBuffer.flip();

        char ch = charBuffer.get();
        return ch;
    }

    public String decodeChars(int maxChars)
            throws ParseException, IOException {
        StringBuilder sb = new StringBuilder(Math.min(maxChars, maxPrealloc));
        decodeChars(sb, maxChars);
        return sb.toString();
    }

    public int decodeChars(StringBuilder buf, int maxCharsToRead)
            throws ParseException, IOException {
        int readLen = 0;
        int capacity = byteBuffer.capacity();
        byteBuffer.limit(0);
        int pos;
        while (readLen < maxCharsToRead) {
            byteBuffer.compact();
            pos = byteBuffer.position();

            int ccToRead = maxCharsToRead - readLen;
            int maxBytesCanRead = (int) (minBytesPerChar * ccToRead);
            if (maxBytesCanRead == 0)
                maxBytesCanRead = 1;
            int cb = Math.min(maxBytesCanRead, readBuf.length - pos);
            cb = in.read(readBuf, 0, cb);
            if (cb == -1) {
                if (pos != 0)
                    throw error("expect more bytes");
            }

            int nput = Math.min(capacity - pos, cb);
            byteBuffer.put(readBuf, 0, nput);
            pos += nput;
            byteBuffer.flip();

            charBuffer.clear();
            result = decoder.decode(byteBuffer, charBuffer, false);

            charBuffer.flip();
            int n = charBuffer.limit();
            readLen += n;
            while (n > 0) {
                int cc = Math.min(n, getbuf.length);
                charBuffer.get(getbuf, 0, cc);
                buf.append(getbuf, 0, cc);
                n -= cc;
            }

            if (result.isError()) {
                char fallback = handleError();
                buf.append(fallback);
            }
        }
        return readLen;
    }

    char handleError()
            throws ParseException {
        if (result.isMalformed()) {
            if (replaceMalformed)
                return replaceMalformedChar;
            throw error("malformed");
        }
        if (result.isUnmappable()) {
            if (replaceUnmappable)
                return replaceUnmappableChar;
            throw error("unmappable");
        }
        throw error("error");
    }

    ParseException error(String message) {
        return error(message, null);
    }

    ParseException error(String message, Exception cause) {
        StringBuilder sb = new StringBuilder();
        byteBuffer.flip();
        int limit = byteBuffer.limit();
        for (int i = 0; i < limit; i++) {
            int byt = byteBuffer.get() & 0xff;
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
