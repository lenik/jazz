package net.bodz.bas.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.model.AbstractDecorator;
import net.bodz.bas.t.model.IWrapper;

public class DecoratedDataOut
        extends AbstractDecorator<IDataOut>
        implements
            IWrapper<IDataOut>,
            IDataOut {

    private static final long serialVersionUID = 1L;

    public DecoratedDataOut(IDataOut _orig) {
        super(_orig);
    }

    @Override
    public void write(int b)
            throws IOException {
        getWrapped().write(b);
    }

    @Override
    public void write(byte[] buf)
            throws IOException {
        getWrapped().write(buf);
    }

    @Override
    public void write(ByteBuffer buf)
            throws IOException {
        getWrapped().write(buf);
    }

    @Override
    public void flush()
            throws IOException {
        getWrapped().flush();
    }

    @Override
    public void close()
            throws IOException {
        getWrapped().close();
    }

    @Override
    public boolean isClosed() {
        return getWrapped().isClosed();
    }

    @Override
    public boolean isBigEndian() {
        return getWrapped().isBigEndian();
    }

    @Override
    public void writeByte(int b)
            throws IOException {
        getWrapped().writeByte(b);
    }

    @Override
    public void writeWord(int w)
            throws IOException {
        getWrapped().writeWord(w);
    }

    @Override
    public void writeDword(int dw)
            throws IOException {
        getWrapped().writeDword(dw);
    }

    @Override
    public void write(byte[] buf, int off, int len)
            throws IOException {
        getWrapped().write(buf, off, len);
    }

    @Override
    public void writeQword(long qw)
            throws IOException {
        getWrapped().writeQword(qw);
    }

    @Override
    public void writeFloat(float v)
            throws IOException {
        getWrapped().writeFloat(v);
    }

    @Override
    public void writeDouble(double v)
            throws IOException {
        getWrapped().writeDouble(v);
    }

    @Override
    public void writeBool(boolean v)
            throws IOException {
        getWrapped().writeBool(v);
    }

    @Override
    public void writeChar(char ch)
            throws IOException {
        getWrapped().writeChar(ch);
    }

    @Override
    public int writeUtf8Char(char ch)
            throws IOException {
        return getWrapped().writeUtf8Char(ch);
    }

    @Override
    public int writeChar(char ch, Charset charset)
            throws IOException {
        return getWrapped().writeChar(ch, charset);
    }

    @Override
    public void writeWords(short[] buf, int off, int len)
            throws IOException {
        getWrapped().writeWords(buf, off, len);
    }

    @Override
    public void writeDwords(int[] buf, int off, int len)
            throws IOException {
        getWrapped().writeDwords(buf, off, len);
    }

    @Override
    public void writeQwords(long[] buf, int off, int len)
            throws IOException {
        getWrapped().writeQwords(buf, off, len);
    }

    @Override
    public void writeFloats(float[] buf, int off, int len)
            throws IOException {
        getWrapped().writeFloats(buf, off, len);
    }

    @Override
    public void writeDoubles(double[] buf, int off, int len)
            throws IOException {
        getWrapped().writeDoubles(buf, off, len);
    }

    @Override
    public void writeBools(boolean[] buf, int off, int len)
            throws IOException {
        getWrapped().writeBools(buf, off, len);
    }

    @Override
    public int writeChars(char[] buf, int off, int len)
            throws IOException {
        return getWrapped().writeChars(buf, off, len);
    }

    @Override
    public int writeUtf8Chars(char[] buf, int off, int len)
            throws IOException {
        return getWrapped().writeUtf8Chars(buf, off, len);
    }

    @Override
    public int writeChars(char[] buf, int off, int len, Charset charset)
            throws IOException {
        return getWrapped().writeChars(buf, off, len, charset);
    }

    @Override
    public void writeString(LengthType lengthType, String str)
            throws IOException {
        getWrapped().writeString(lengthType, str);
    }

    @Override
    public void writeUtf8String(LengthType lengthType, String str)
            throws IOException {
        getWrapped().writeUtf8String(lengthType, str);
    }

    @Override
    public void writeString(LengthType lengthType, String str, Charset charset)
            throws IOException {
        getWrapped().writeString(lengthType, str, charset);
    }

    @Override
    public void writeFixedSizeChars(int fixedSize, char padding, char[] buf, int off, int len)
            throws IOException {
        getWrapped().writeFixedSizeChars(fixedSize, padding, buf, off, len);
    }

    @Override
    public int writeFixedSizeUtf8Chars(int fixedSize, char padding, char[] buf, int off, int len)
            throws IOException {
        return getWrapped().writeFixedSizeUtf8Chars(fixedSize, padding, buf, off, len);
    }

    @Override
    public int writeFixedSizeChars(int fixedSize, char padding, char[] buf, int off, int len, Charset charset)
            throws IOException, ParseException {
        return getWrapped().writeFixedSizeChars(fixedSize, padding, buf, off, len, charset);
    }

    @Override
    public void writeFixedSizeString(int fixedSize, char padding, String str)
            throws IOException {
        getWrapped().writeFixedSizeString(fixedSize, padding, str);
    }

    @Override
    public int writeFixedSizeUtf8String(int fixedSize, char padding, String str)
            throws IOException {
        return getWrapped().writeFixedSizeUtf8String(fixedSize, padding, str);
    }

    @Override
    public int writeFixedSizeString(int fixedSize, char padding, String str, Charset charset)
            throws IOException, ParseException {
        return getWrapped().writeFixedSizeString(fixedSize, padding, str, charset);
    }

}
