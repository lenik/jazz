package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.io.InputStream;

import net.bodz.bas.io.IByteIn;

public class ByteInInputStream
        extends InputStream {

    private final IByteIn byteIn;

    /**
     * @throws NullPointerException
     *             If <code>byteIn</code> is <code>null</code>.
     */
    public ByteInInputStream(IByteIn byteIn) {
        if (byteIn == null)
            throw new NullPointerException("byteIn");
        this.byteIn = byteIn;
    }

    @Override
    public int read()
            throws IOException {
        return byteIn.read();
    }

    @Override
    public int read(byte[] b)
            throws IOException {
        return byteIn.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len)
            throws IOException {
        return byteIn.read(b, off, len);
    }

    @Override
    public void close()
            throws IOException {
        byteIn.close();
    }

}
