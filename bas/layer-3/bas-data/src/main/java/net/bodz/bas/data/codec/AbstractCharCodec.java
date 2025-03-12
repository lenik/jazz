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
import net.bodz.bas.io.BByteOut;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.StringCharIn;
import net.bodz.bas.io.adapter.ByteBufferByteIn;
import net.bodz.bas.io.adapter.ByteBufferByteOut;
import net.bodz.bas.io.adapter.CharBufferCharIn;
import net.bodz.bas.io.adapter.CharBufferCharOut;
import net.bodz.bas.io.adapter.InputStreamByteIn;
import net.bodz.bas.io.adapter.OutputStreamByteOut;
import net.bodz.bas.io.adapter.ReaderCharIn;
import net.bodz.bas.io.adapter.WriterCharOut;

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
        ICharIn charIn = new StringCharIn(string);
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
        ICharIn charIn = new CharBufferCharIn(charBuffer);
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
        IByteIn byteIn = new ByteBufferByteIn(byteBuffer);
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
        ICharIn charIn = new CharBufferCharIn(in);
        IByteOut byteOut = new ByteBufferByteOut(out);
        encode(charIn, byteOut);
    }

    @Override
    public void decode(ByteBuffer in, CharBuffer out)
            throws IOException, DecodeException {
        IByteIn byteIn = new ByteBufferByteIn(in);
        ICharOut charOut = new CharBufferCharOut(out);
        decode(byteIn, charOut);
    }

    @Override
    public void encode(Reader in, OutputStream out)
            throws IOException, EncodeException {
        ICharIn charIn = new ReaderCharIn(in);
        IByteOut byteOut = new OutputStreamByteOut(out);
        encode(charIn, byteOut);
    }

    @Override
    public void decode(InputStream in, Writer out)
            throws IOException, DecodeException {
        IByteIn byteIn = new InputStreamByteIn(in);
        ICharOut charOut = new WriterCharOut(out);
        decode(byteIn, charOut);
    }

}
