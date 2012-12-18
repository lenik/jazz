package net.bodz.bas.c.java.io;

import java.io.Closeable;
import java.io.DataOutput;
import java.io.Flushable;
import java.io.IOException;

import net.bodz.bas.t.model.AbstractDecorator;

public class AbstractDataOutputAdapter<T extends DataOutput>
        extends AbstractDecorator<T>
        implements IDataOutput {

    private static final long serialVersionUID = 1L;

    private final Flushable flushable;
    private final Closeable closeable;

    public <FC extends Flushable & Closeable> AbstractDataOutputAdapter(T _orig, FC flushClose) {
        super(_orig);
        this.flushable = flushClose;
        this.closeable = flushClose;
    }

    @Override
    public void write(int b)
            throws IOException {
        _orig.write(b);
    }

    @Override
    public void write(byte[] b)
            throws IOException {
        _orig.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len)
            throws IOException {
        _orig.write(b, off, len);
    }

    @Override
    public void writeBoolean(boolean v)
            throws IOException {
        _orig.writeBoolean(v);
    }

    @Override
    public void writeByte(int v)
            throws IOException {
        _orig.writeByte(v);
    }

    @Override
    public void writeShort(int v)
            throws IOException {
        _orig.writeShort(v);
    }

    @Override
    public void writeChar(int v)
            throws IOException {
        _orig.writeChar(v);
    }

    @Override
    public void writeInt(int v)
            throws IOException {
        _orig.writeInt(v);
    }

    @Override
    public void writeLong(long v)
            throws IOException {
        _orig.writeLong(v);
    }

    @Override
    public void writeFloat(float v)
            throws IOException {
        _orig.writeFloat(v);
    }

    @Override
    public void writeDouble(double v)
            throws IOException {
        _orig.writeDouble(v);
    }

    @Override
    public void writeBytes(String s)
            throws IOException {
        _orig.writeBytes(s);
    }

    @Override
    public void writeChars(String s)
            throws IOException {
        _orig.writeChars(s);
    }

    @Override
    public void writeUTF(String s)
            throws IOException {
        _orig.writeUTF(s);
    }

    @Override
    public void flush()
            throws IOException {
        flushable.flush();
    }

    @Override
    public void close()
            throws IOException {
        closeable.close();
    }

}
