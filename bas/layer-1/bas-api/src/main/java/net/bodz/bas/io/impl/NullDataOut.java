package net.bodz.bas.io.impl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import net.bodz.bas.io.IDataOut;
import net.bodz.bas.io.StringLengthType;

public class NullDataOut
        implements
            IDataOut {

    private final boolean bigEndian;

    public NullDataOut(boolean bigEndian) {
        this.bigEndian = bigEndian;
    }

    @Override
    public boolean isBigEndian() {
        return bigEndian;
    }

    @Override
    public void flush()
            throws IOException {
    }

    @Override
    public void close()
            throws IOException {
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public void write(int b) {
    }

    @Override
    public void write(byte[] buf)
            throws IOException {
    }

    @Override
    public void write(byte[] buf, int off, int len) {
    }

    @Override
    public void write(ByteBuffer buffer)
            throws IOException {
    }

    @Override
    public void writeByte(int b)
            throws IOException {
    }

    @Override
    public void writeWord(int s)
            throws IOException {
    }

    @Override
    public void writeDword(int n)
            throws IOException {
    }

    @Override
    public void writeQword(long l)
            throws IOException {
    }

    @Override
    public void writeFloat(float f)
            throws IOException {
    }

    @Override
    public void writeDouble(double d)
            throws IOException {
    }

    @Override
    public void writeBool(boolean b)
            throws IOException {
    }

    @Override
    public void writeChar(char ch)
            throws IOException {
    }

    @Override
    public int writeUtf8Char(char ch)
            throws IOException {
        return 0;
    }

    @Override
    public int writeChar(char ch, Charset charset)
            throws IOException {
        return 0;
    }

    @Override
    public void writeWords(short[] buf, int off, int len)
            throws IOException {
    }

    @Override
    public void writeDwords(int[] buf, int off, int len)
            throws IOException {
    }

    @Override
    public void writeQwords(long[] buf, int off, int len)
            throws IOException {
    }

    @Override
    public void writeFloats(float[] buf, int off, int len)
            throws IOException {
    }

    @Override
    public void writeDoubles(double[] buf, int off, int len)
            throws IOException {
    }

    @Override
    public void writeBools(boolean[] buf, int off, int len)
            throws IOException {
    }

    @Override
    public int writeChars(char[] buf, int off, int len)
            throws IOException {
        return 0;
    }

    @Override
    public int writeUtf8Chars(char[] buf, int off, int len)
            throws IOException {
        return 0;
    }

    @Override
    public int writeChars(char[] buf, int off, int len, Charset charset)
            throws IOException {
        return 0;
    }

    @Override
    public void writeString(StringLengthType lengthType, String str)
            throws IOException {
    }

    @Override
    public void writeUtf8String(StringLengthType lengthType, String str)
            throws IOException {
    }

    @Override
    public void writeString(StringLengthType lengthType, String str, Charset charset)
            throws IOException {
    }

    @Override
    public void writeFixedSizeChars(int fixedSize, char padding, char[] buf, int off, int len)
            throws IOException {
    }

    @Override
    public int writeFixedSizeUtf8Chars(int fixedSize, char padding, char[] buf, int off, int len)
            throws IOException {
        return 0;
    }

    @Override
    public int writeFixedSizeChars(int fixedSize, char padding, char[] buf, int off, int len, Charset charset)
            throws IOException {
        return 0;
    }

    @Override
    public void writeFixedSizeString(int fixedSize, char padding, String str)
            throws IOException {
    }

    @Override
    public int writeFixedSizeUtf8String(int fixedSize, char padding, String str)
            throws IOException {
        return 0;
    }

    @Override
    public int writeFixedSizeString(int fixedSize, char padding, String str, Charset charset)
            throws IOException {
        return 0;
    }

}
