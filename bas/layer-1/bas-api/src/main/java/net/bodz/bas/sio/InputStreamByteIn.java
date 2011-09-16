package net.bodz.bas.sio;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

public class InputStreamByteIn
        extends AbstractByteIn {

    protected final InputStream in;

    /**
     * @throws NullPointerException
     *             If <code>in</code> is <code>null</code>.
     */
    public InputStreamByteIn(InputStream in) {
        if (in == null)
            throw new NullPointerException("in");
        this.in = in;
    }

    @Override
    public int read()
            throws IOException {
        return in.read();
    }

    @Override
    public int read(byte[] buf, int off, int len)
            throws IOException {
        return in.read(buf, off, len);
    }

    /**
     * XXX - Since Java 7?
     */
    static final Method InputStream_read_ByteBuffer;
    static {
        Method read_ByteBuffer;
        try {
            read_ByteBuffer = InputStream.class.getMethod("read", ByteBuffer.class);
        } catch (Exception e) {
            read_ByteBuffer = null;
        }
        InputStream_read_ByteBuffer = read_ByteBuffer;
    }

    // @Override
    // public int read(ByteBuffer buffer)
    // throws IOException {
    // return super.read(buffer);
    // }

    @Override
    public void close()
            throws IOException {
        in.close();
    }

    @Override
    public InputStream toInputStream() {
        return in;
    }

    @Override
    public int hashCode() {
        int hash = 0xcd9fa4fe;
        hash += in.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof InputStreamByteIn))
            return false;
        InputStreamByteIn o = (InputStreamByteIn) obj;
        return in.equals(o.in);
    }

    @Override
    public String toString() {
        return in.toString();
    }

}
