package net.bodz.bas.data.codec;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import net.bodz.bas.err.DecodeException;
import net.bodz.bas.err.EncodeException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.*;
import net.bodz.bas.io.adapter.*;

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
    public String encode(byte[] bytes, int off, int len) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes, off, len);
        IByteIn byteIn = new ByteBufferByteIn(byteBuffer);
        int approxChars = (int) (len / bytesPerChar) + 10;
        BCharOut charOut = new BCharOut(approxChars);
        try {
            encode(byteIn, charOut);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
        return charOut.toString();
    }

    @Override
    public byte[] decode(char[] chars, int off, int len) {
        CharBuffer charBuffer = CharBuffer.wrap(chars, off, len);
        ICharIn charIn = new CharBufferCharIn(charBuffer);
        int approxBytes = (int) (len * bytesPerChar) + 10;
        BByteOut byteOut = new BByteOut(approxBytes);
        try {
            decode(charIn, byteOut);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
        return byteOut.toByteArray();
    }

    @Override
    public byte[] decode(String string, int start, int end) {
        ICharIn charIn = new StringCharIn(string, start, end);
        int approxBytes = (int) (end * bytesPerChar) + 10;
        BByteOut byteOut = new BByteOut(approxBytes);
        try {
            decode(charIn, byteOut);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
        return byteOut.toByteArray();
    }

    @Override
    public byte[] decode(String string) {
        if (string == null)
            throw new NullPointerException("string");
        return decode(string, 0, string.length());
    }

    @Override
    public void encode(ByteBuffer in, CharBuffer out)
            throws EncodeException {
        IByteIn byteIn = new ByteBufferByteIn(in);
        ICharOut charOut = new CharBufferCharOut(out);
        try {
            encode(byteIn, charOut);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

    @Override
    public void decode(CharBuffer in, ByteBuffer out)
            throws DecodeException {
        ICharIn charIn = new CharBufferCharIn(in);
        IByteOut byteOut = new ByteBufferByteOut(out);
        try {
            decode(charIn, byteOut);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

    @Override
    public void encode(InputStream in, Writer out)
            throws EncodeException {
        IByteIn byteIn = new InputStreamByteIn(in);
        ICharOut charOut = new WriterCharOut(out);
        try {
            encode(byteIn, charOut);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

    @Override
    public void decode(Reader in, OutputStream out)
            throws DecodeException {
        ICharIn charIn = new ReaderCharIn(in);
        IByteOut byteOut = new OutputStreamByteOut(out);
        try {
            decode(charIn, byteOut);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

}
