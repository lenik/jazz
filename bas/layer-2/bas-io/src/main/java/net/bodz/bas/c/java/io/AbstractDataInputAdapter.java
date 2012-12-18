package net.bodz.bas.c.java.io;

import java.io.Closeable;
import java.io.DataInput;
import java.io.IOException;

import net.bodz.bas.t.model.AbstractDecorator;

public class AbstractDataInputAdapter<T extends DataInput>
        extends AbstractDecorator<T>
        implements IDataInput {

    private static final long serialVersionUID = 1L;

    private final Closeable closeable;

    public AbstractDataInputAdapter(T dataInput, Closeable closable) {
        super(dataInput);
        this.closeable = closable;
    }

    @Override
    public void readFully(byte[] b)
            throws IOException {
        _orig.readFully(b);
    }

    @Override
    public void readFully(byte[] b, int off, int len)
            throws IOException {
        _orig.readFully(b, off, len);
    }

    @Override
    public int skipBytes(int n)
            throws IOException {
        return _orig.skipBytes(n);
    }

    @Override
    public boolean readBoolean()
            throws IOException {
        return _orig.readBoolean();
    }

    @Override
    public byte readByte()
            throws IOException {
        return _orig.readByte();
    }

    @Override
    public int readUnsignedByte()
            throws IOException {
        return _orig.readUnsignedByte();
    }

    @Override
    public short readShort()
            throws IOException {
        return _orig.readShort();
    }

    @Override
    public int readUnsignedShort()
            throws IOException {
        return _orig.readUnsignedShort();
    }

    @Override
    public char readChar()
            throws IOException {
        return _orig.readChar();
    }

    @Override
    public int readInt()
            throws IOException {
        return _orig.readInt();
    }

    @Override
    public long readLong()
            throws IOException {
        return _orig.readLong();
    }

    @Override
    public float readFloat()
            throws IOException {
        return _orig.readFloat();
    }

    @Override
    public double readDouble()
            throws IOException {
        return _orig.readDouble();
    }

    @Override
    public String readLine()
            throws IOException {
        return _orig.readLine();
    }

    @Override
    public String readUTF()
            throws IOException {
        return _orig.readUTF();
    }

    @Override
    public void close()
            throws IOException {
        closeable.close();
    }

}
