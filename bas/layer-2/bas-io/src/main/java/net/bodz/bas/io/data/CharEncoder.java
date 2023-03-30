package net.bodz.bas.io.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnsupportedFeatureException;
import net.bodz.bas.io.ICharIn;

public class CharEncoder
        extends AbstractCharCodec {

    ICharIn in;

    Charset charset;
    CharsetEncoder encoder;

    CharBuffer inBuffer;
    ByteBuffer outBuffer;
    CoderResult result;

    boolean replaceUnmappable;
    byte[] replaceUnmappableChar;

    // by block
    public float maxBytesPerChar;
    public float _minCharsPerBytes;
    char[] iblock;

    public CharEncoder(Charset charset, ICharIn in) {
        this.in = in;
        this.charset = charset;
        encoder = charset.newEncoder();
        inBuffer = CharBuffer.allocate(10);
        outBuffer = ByteBuffer.allocate(10);
    }

    public CharEncoder(Charset charset, ICharIn in, int inCapacity) {
        super(true); // by-blocks

        this.in = in;
        this.charset = charset;
        encoder = charset.newEncoder();
        inBuffer = CharBuffer.allocate(inCapacity);

        float averageBytes = encoder.averageBytesPerChar() * inCapacity;
        int outCapacity = (int) averageBytes + 1;
        outBuffer = ByteBuffer.allocate(outCapacity);

        maxBytesPerChar = encoder.maxBytesPerChar();
        if (maxBytesPerChar < 1.0f)
            throw new UnsupportedFeatureException("compressed encoding");

        _minCharsPerBytes = 1.0f / maxBytesPerChar;
        iblock = new char[blockSize];
    }

    public void fallback(byte[] bin) {
        replaceUnmappable = true;
        replaceUnmappableChar = bin;
    }

    public byte[] encodeChar()
            throws ParseException, IOException {
        int chr = in.read();
        if (chr == -1)
            return null;
        return encodeChar((char) chr);
    }

    public byte[] encodeChar(char ch)
            throws ParseException, IOException {
        inBuffer.compact();
        inBuffer.put(ch);
        inBuffer.flip();

        outBuffer.clear();
        result = encoder.encode(inBuffer, outBuffer, false);

        if (result.isError())
            return handleError();

        outBuffer.flip();

        int n = outBuffer.limit();
        byte[] bytes = new byte[n];
        for (int i = 0; i < n; i++)
            bytes[i] = outBuffer.get();
        return bytes;
    }

    public byte[] encodeChars(int maxBytes)
            throws ParseException, IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream(Math.min(maxBytes, maxPrealloc));
        encodeChars(buf, maxBytes);
        return buf.toByteArray();
    }

    public ConvertedLengths encodeChars(OutputStream out, int maxBytesToEncode)
            throws ParseException, IOException {
        int nCharRead = 0;
        int nByteEncoded = 0;

        inBuffer.limit(0);
        while (nByteEncoded < maxBytesToEncode) {
            int nBytesReq = maxBytesToEncode - nByteEncoded;
            int maxCharsCanRead = (int) (nBytesReq / maxBytesPerChar);
            if (maxCharsCanRead == 0) {
                /*
                 * To encode to a specific size, it must look-ahead for the last char.
                 *
                 * This will cause the lost of the last char. so you may have to use an LA(1) input source.
                 */
                // Let the caller to deal with the last char.
                // maxCharsCanRead = 1;
                break;
            }

            int cc = Math.min(maxCharsCanRead, iblock.length);
            cc = in.read(iblock, 0, cc);
            if (cc == -1) { // EOF
                if (inBuffer.hasRemaining()) // unexpected for encoder, thou
                    throw error("expect more chars");
                else
                    break;
            }

            nCharRead += cc;
            nByteEncoded += encodeBlock(out, iblock, 0, cc).charCount;
        }
        return ConvertedLengths.decoded(nCharRead, nByteEncoded);
    }

    public ConvertedLengths encodeBlock(OutputStream out, char[] block, int off, int len)
            throws ParseException, IOException {
        int nByteEncoded = 0;
        int pos = off;
        while (off < len) {
            inBuffer.compact();
            pos += copy(inBuffer, block, pos, len - pos);

            inBuffer.flip();

            outBuffer.clear();
            result = encoder.encode(inBuffer, outBuffer, false);

            outBuffer.flip();
            boolean empty = outBuffer.hasRemaining();
            nByteEncoded += copy(out, outBuffer);

            if (result.isError()) {
                byte[] fallback = handleError();
                out.write(fallback);
                // decoder.replaceWith(fallback);
            }
            if (empty)
                break;
        }
        return ConvertedLengths.encoded(pos - off, nByteEncoded);
    }

    byte[] handleError()
            throws ParseException {
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
        StringBuilder msg = new StringBuilder();
        inBuffer.flip();
        int limit = inBuffer.limit();
        for (int i = 0; i < limit; i++) {
            char ch = inBuffer.get();
            if (i != 0)
                msg.append(" ");
            msg.append(String.format("'%c' (0x%x)", ch, (int) ch));
        }
        String description = String.format("%s: pending chars: %s", message, msg.toString());
        if (cause == null)
            return new ParseException(description);
        else
            return new ParseException(description, cause);
    }

}
