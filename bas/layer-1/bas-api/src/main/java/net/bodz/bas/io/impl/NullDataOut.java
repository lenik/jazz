package net.bodz.bas.io.impl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import net.bodz.bas.io.IDataOut;
import net.bodz.bas.io.LengthType;

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
    public void writeUtf8Char(char ch)
            throws IOException {
    }

    @Override
    public void writeChar(char ch, Charset charset)
            throws IOException {
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
    public void writeChars(char[] buf, int off, int len)
            throws IOException {
    }

    @Override
    public void writeUtf8Chars(char[] buf, int off, int len)
            throws IOException {
    }

    @Override
    public void writeString(LengthType lengthType, String str)
            throws IOException {
    }

    @Override
    public void writeUtf8String(LengthType lengthType, String str)
            throws IOException {
    }

    @Override
    public void writeString(LengthType lengthType, String str, Charset charset)
            throws IOException {
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

}
