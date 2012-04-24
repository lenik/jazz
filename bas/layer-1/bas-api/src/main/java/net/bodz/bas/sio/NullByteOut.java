package net.bodz.bas.sio;

import java.io.IOException;
import java.nio.ByteBuffer;

public class NullByteOut
        extends AbstractByteOutEx {

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
    public void flush(boolean strict)
            throws IOException {
    }

    @Override
    public void flush()
            throws IOException {
    }

    @Override
    public void writeBE(boolean b)
            throws IOException {
    }

    @Override
    public void writeLE(boolean b)
            throws IOException {
    }

    @Override
    public void writeBE(int n)
            throws IOException {
    }

    @Override
    public void writeBE(long l)
            throws IOException {
    }

    @Override
    public void writeBE(short s)
            throws IOException {
    }

    @Override
    public void writeIeee754BE(double d)
            throws IOException {
    }

    @Override
    public void writeIeee754BE(float f)
            throws IOException {
    }

    @Override
    public void writeIeee754LE(double d)
            throws IOException {
    }

    @Override
    public void writeIeee754LE(float f)
            throws IOException {
    }

    @Override
    public void writeLE(int n)
            throws IOException {
    }

    @Override
    public void writeLE(long l)
            throws IOException {
    }

    @Override
    public void writeLE(short s)
            throws IOException {
    }

    @Override
    public synchronized void writeUtf16BE(char c)
            throws IOException {
    }

    @Override
    public void writeUtf16BE(char[] str)
            throws IOException {
    }

    @Override
    public synchronized void writeUtf16LE(char c)
            throws IOException {
    }

    @Override
    public void writeUtf16LE(char[] str)
            throws IOException {
    }

    static final NullByteOut instance = new NullByteOut();

    public static NullByteOut getInstance() {
        return instance;
    }

}
