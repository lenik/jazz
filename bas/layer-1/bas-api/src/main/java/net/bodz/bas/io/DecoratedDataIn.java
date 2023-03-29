package net.bodz.bas.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.model.AbstractDecorator;
import net.bodz.bas.t.model.IWrapper;

public class DecoratedDataIn
        extends AbstractDecorator<IDataIn>
        implements
            IWrapper<IDataIn>,
            IDataIn {

    private static final long serialVersionUID = 1L;

    public DecoratedDataIn(IDataIn _orig) {
        super(_orig);
    }

    @Override
    public int read()
            throws IOException {
        return getWrapped().read();
    }

    @Override
    public void close()
            throws IOException {
        getWrapped().close();
    }

    @Override
    public long skip(long n)
            throws IOException {
        return getWrapped().skip(n);
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
    public int read(byte[] buf)
            throws IOException {
        return getWrapped().read(buf);
    }

    @Override
    public byte readByte()
            throws IOException {
        return getWrapped().readByte();
    }

    @Override
    public int read(byte[] buf, int off, int len)
            throws IOException {
        return getWrapped().read(buf, off, len);
    }

    @Override
    public short readWord()
            throws IOException {
        return getWrapped().readWord();
    }

    @Override
    public int read(ByteBuffer buf)
            throws IOException {
        return getWrapped().read(buf);
    }

    @Override
    public int readDword()
            throws IOException {
        return getWrapped().readDword();
    }

    @Override
    public long readQword()
            throws IOException {
        return getWrapped().readQword();
    }

    @Override
    public float readFloat()
            throws IOException {
        return getWrapped().readFloat();
    }

    @Override
    public double readDouble()
            throws IOException {
        return getWrapped().readDouble();
    }

    @Override
    public boolean readBool()
            throws IOException {
        return getWrapped().readBool();
    }

    @Override
    public char readChar()
            throws IOException {
        return getWrapped().readChar();
    }

    @Override
    public char readUtf8Char()
            throws IOException {
        return getWrapped().readUtf8Char();
    }

    @Override
    public char readChar(Charset charset)
            throws IOException, ParseException {
        return getWrapped().readChar(charset);
    }

    @Override
    public char readChar(Charset charset, char fallback)
            throws IOException {
        return getWrapped().readChar(charset, fallback);
    }

    @Override
    public void readBytes(byte[] buf, int off, int len)
            throws IOException {
        getWrapped().readBytes(buf, off, len);
    }

    @Override
    public void readWords(short[] buf, int off, int len)
            throws IOException {
        getWrapped().readWords(buf, off, len);
    }

    @Override
    public void readDwords(int[] buf, int off, int len)
            throws IOException {
        getWrapped().readDwords(buf, off, len);
    }

    @Override
    public void readQwords(long[] buf, int off, int len)
            throws IOException {
        getWrapped().readQwords(buf, off, len);
    }

    @Override
    public void readFloats(float[] buf, int off, int len)
            throws IOException {
        getWrapped().readFloats(buf, off, len);
    }

    @Override
    public void readDoubles(double[] buf, int off, int len)
            throws IOException {
        getWrapped().readDoubles(buf, off, len);
    }

    @Override
    public void readBools(boolean[] buf, int off, int len)
            throws IOException {
        getWrapped().readBools(buf, off, len);
    }

    @Override
    public void readChars(char[] buf, int off, int len)
            throws IOException {
        getWrapped().readChars(buf, off, len);
    }

    @Override
    public void readUtf8Chars(char[] buf, int off, int len)
            throws IOException {
        getWrapped().readUtf8Chars(buf, off, len);
    }

    @Override
    public String readString(LengthType lengthType)
            throws IOException {
        return getWrapped().readString(lengthType);
    }

    @Override
    public String readUtf8String(LengthType lengthType)
            throws IOException, ParseException {
        return getWrapped().readUtf8String(lengthType);
    }

    @Override
    public String readString(LengthType lengthType, Charset charset)
            throws IOException, ParseException {
        return getWrapped().readString(lengthType, charset);
    }

}
