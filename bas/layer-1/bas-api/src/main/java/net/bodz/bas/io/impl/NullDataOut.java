package net.bodz.bas.io.impl;

import java.io.IOException;
import java.nio.ByteBuffer;

import net.bodz.bas.io.IDataOut;

public class NullDataOut
        implements IDataOut {

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
    public void writeBoolean(boolean b)
            throws IOException {
    }

    @Override
    public void writeUtf8Char(char ch)
            throws IOException {
    }

    @Override
    public void writeString(int flags, String str)
            throws IOException {
    }

    @Override
    public void writeString(int flags, String str, String encoding)
            throws IOException {
    }

    @Override
    public void flush(boolean strict)
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

}
