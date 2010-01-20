package net.bodz.bas.text.codec;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import net.bodz.bas.exceptions.DecodeException;
import net.bodz.bas.exceptions.EncodeException;
import net.bodz.bas.sio.BByteOut;
import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.sio.ByteBufferByteIn;
import net.bodz.bas.sio.ByteBufferByteOut;
import net.bodz.bas.sio.CharBufferCharIn;
import net.bodz.bas.sio.CharBufferCharOut;
import net.bodz.bas.sio.InputStreamByteIn;
import net.bodz.bas.sio.OutputStreamByteOut;
import net.bodz.bas.sio.ReaderCharIn;
import net.bodz.bas.sio.StringCharIn;
import net.bodz.bas.sio.WriterCharOut;

public abstract class AbstractCharCodec
        implements ICharCodec {

    private final float bytesPerChar;

    public AbstractCharCodec(int blockBytes, int blockChars) {
        if (blockBytes <= 0)
            throw new IllegalArgumentException("blockBytes must be positive integer: " + blockBytes);
        if (blockChars <= 0)
            throw new IllegalArgumentException("blockChars must be positive integer: " + blockChars);
        this.bytesPerChar = (float) blockBytes / (float) blockChars;
    }

    @Override
    public byte[] encode(String string)
            throws EncodeException {
        StringCharIn charIn = new StringCharIn(string);
        int approxBytes = (int) (string.length() * bytesPerChar) + 10;
        BByteOut byteOut = new BByteOut(approxBytes);
        try {
            encode(charIn, byteOut);
        } catch (EncodeException e) {
            throw e;
        } catch (IOException e) {
            throw new EncodeException(e.getMessage(), e);
        }
        return byteOut.toByteArray();
    }

    @Override
    public byte[] encode(char[] chars)
            throws EncodeException {
        return encode(chars, 0, chars.length);
    }

    @Override
    public byte[] encode(char[] chars, int off, int len)
            throws EncodeException {
        CharBuffer charBuffer = CharBuffer.wrap(chars, off, len);
        CharBufferCharIn charIn = new CharBufferCharIn(charBuffer);
        int approxBytes = (int) (len * bytesPerChar) + 10;
        BByteOut byteOut = new BByteOut(approxBytes);
        try {
            encode(charIn, byteOut);
        } catch (EncodeException e) {
            throw e;
        } catch (IOException e) {
            throw new EncodeException(e.getMessage(), e);
        }
        return byteOut.toByteArray();
    }

    @Override
    public String decode(byte[] bytes)
            throws DecodeException {
        return decode(bytes, 0, bytes.length);
    }

    @Override
    public String decode(byte[] bytes, int off, int len)
            throws DecodeException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes, off, len);
        ByteBufferByteIn byteIn = new ByteBufferByteIn(byteBuffer);
        int approxChars = (int) (len / bytesPerChar) + 10;
        BCharOut charOut = new BCharOut(approxChars);
        try {
            decode(byteIn, charOut);
        } catch (DecodeException e) {
            throw e;
        } catch (IOException e) {
            throw new DecodeException(e.getMessage(), e);
        }
        return charOut.toString();
    }

    @Override
    public void encode(CharBuffer in, ByteBuffer out)
            throws IOException, EncodeException {
        CharBufferCharIn charIn = new CharBufferCharIn(in);
        ByteBufferByteOut byteOut = new ByteBufferByteOut(out);
        encode(charIn, byteOut);
    }

    @Override
    public void decode(ByteBuffer in, CharBuffer out)
            throws IOException, DecodeException {
        ByteBufferByteIn byteIn = new ByteBufferByteIn(in);
        CharBufferCharOut charOut = new CharBufferCharOut(out);
        decode(byteIn, charOut);
    }

    @Override
    public void encode(Reader in, OutputStream out)
            throws IOException, EncodeException {
        ReaderCharIn charIn = new ReaderCharIn(in);
        OutputStreamByteOut byteOut = new OutputStreamByteOut(out);
        encode(charIn, byteOut);
    }

    @Override
    public void decode(InputStream in, Writer out)
            throws IOException, DecodeException {
        InputStreamByteIn byteIn = new InputStreamByteIn(in);
        WriterCharOut charOut = new WriterCharOut(out);
        decode(byteIn, charOut);
    }

}
