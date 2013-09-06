package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.io.OutputStream;

import net.bodz.bas.io.IByteOut;

public class ByteOutOutputStream
        extends OutputStream {

    private final IByteOut byteOut;

    /**
     * @throws NullPointerException
     *             If <code>byteOut</code> is <code>null</code>.
     */
    public ByteOutOutputStream(IByteOut byteOut) {
        if (byteOut == null)
            throw new NullPointerException("byteOut");
        this.byteOut = byteOut;
    }

    @Override
    public void write(int b)
            throws IOException {
        byteOut.write(b);
    }

    @Override
    public void write(byte[] b)
            throws IOException {
        byteOut.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len)
            throws IOException {
        byteOut.write(b, off, len);
    }

    @Override
    public void flush()
            throws IOException {
        byteOut.flush(true);
    }

    @Override
    public void close()
            throws IOException {
        byteOut.close();
    }

}
