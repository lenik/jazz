package net.bodz.bas.text.codec;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

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
import net.bodz.bas.util.exception.DecodeException;
import net.bodz.bas.util.exception.EncodeException;

public abstract class AbstractByteCodec
        implements IByteCodec {

    private final float bytesPerChar;

    public AbstractByteCodec(int blockBytes, int blockChars) {
        if (blockBytes <= 0)
            throw new IllegalArgumentException("blockBytes must be positive integer: " + blockBytes);
        if (blockChars <= 0)
            throw new IllegalArgumentException("blockChars must be positive integer: " + blockChars);
        this.bytesPerChar = (float) blockBytes / (float) blockChars;
    }

    @Override
    public String encode(byte[] bytes)
            throws EncodeException {
        if (bytes == null)
            throw new NullPointerException("bytes");
        return encode(bytes, 0, bytes.length);
    }

    @Override
    public byte[] decode(char[] chars)
            throws DecodeException {
        if (chars == null)
            throw new NullPointerException("chars");
        return decode(chars, 0, chars.length);
    }

    @Override
    public String encode(byte[] bytes, int off, int len)
            throws EncodeException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes, off, len);
        ByteBufferByteIn byteIn = new ByteBufferByteIn(byteBuffer);
        int approxChars = (int) (len / bytesPerChar) + 10;
        BCharOut charOut = new BCharOut(approxChars);
        try {
            encode(byteIn, charOut);
        } catch (EncodeException e) {
            throw e;
        } catch (IOException e) {
            throw new EncodeException(e.getMessage(), e);
        }
        return charOut.toString();
    }

    @Override
    public byte[] decode(char[] chars, int off, int len)
            throws DecodeException {
        CharBuffer charBuffer = CharBuffer.wrap(chars, off, len);
        CharBufferCharIn charIn = new CharBufferCharIn(charBuffer);
        int approxBytes = (int) (len * bytesPerChar) + 10;
        BByteOut byteOut = new BByteOut(approxBytes);
        try {
            decode(charIn, byteOut);
        } catch (DecodeException e) {
            throw e;
        } catch (IOException e) {
            throw new DecodeException(e.getMessage(), e);
        }
        return byteOut.toByteArray();
    }

    @Override
    public byte[] decode(String string, int start, int end)
            throws DecodeException {
        StringCharIn charIn = new StringCharIn(string, start, end);
        int approxBytes = (int) (end * bytesPerChar) + 10;
        BByteOut byteOut = new BByteOut(approxBytes);
        try {
            decode(charIn, byteOut);
        } catch (DecodeException e) {
            throw e;
        } catch (IOException e) {
            throw new DecodeException(e.getMessage(), e);
        }
        return byteOut.toByteArray();
    }

    @Override
    public byte[] decode(String string)
            throws DecodeException {
        if (string == null)
            throw new NullPointerException("string");
        return decode(string, 0, string.length());
    }

    @Override
    public void encode(ByteBuffer in, CharBuffer out)
            throws IOException, EncodeException {
        ByteBufferByteIn byteIn = new ByteBufferByteIn(in);
        CharBufferCharOut charOut = new CharBufferCharOut(out);
        encode(byteIn, charOut);
    }

    @Override
    public void decode(CharBuffer in, ByteBuffer out)
            throws IOException, DecodeException {
        CharBufferCharIn charIn = new CharBufferCharIn(in);
        ByteBufferByteOut byteOut = new ByteBufferByteOut(out);
        decode(charIn, byteOut);
    }

    @Override
    public void encode(InputStream in, Writer out)
            throws IOException, EncodeException {
        InputStreamByteIn byteIn = new InputStreamByteIn(in);
        WriterCharOut charOut = new WriterCharOut(out);
        encode(byteIn, charOut);
    }

    @Override
    public void decode(Reader in, OutputStream out)
            throws IOException, DecodeException {
        ReaderCharIn charIn = new ReaderCharIn(in);
        OutputStreamByteOut byteOut = new OutputStreamByteOut(out);
        decode(charIn, byteOut);
    }

}
