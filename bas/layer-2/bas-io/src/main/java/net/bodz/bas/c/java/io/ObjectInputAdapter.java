package net.bodz.bas.c.java.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInput;

public class ObjectInputAdapter
        extends AbstractDataInputAdapter<ObjectInput>
        implements IObjectInput {

    private static final long serialVersionUID = 1L;

    public ObjectInputAdapter(ObjectInput objInput, Closeable closable) {
        super(objInput, closable);
    }

    @Override
    public Object readObject()
            throws ClassNotFoundException, IOException {
        return _orig.readObject();
    }

    @Override
    public int read()
            throws IOException {
        return _orig.read();
    }

    @Override
    public int read(byte[] b)
            throws IOException {
        return _orig.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len)
            throws IOException {
        return _orig.read(b, off, len);
    }

    @Override
    public long skip(long n)
            throws IOException {
        return _orig.skip(n);
    }

    @Override
    public int available()
            throws IOException {
        return _orig.available();
    }

}
