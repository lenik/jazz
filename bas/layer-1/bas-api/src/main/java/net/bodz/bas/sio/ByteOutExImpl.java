package net.bodz.bas.sio;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ByteOutExImpl
        extends AbstractByteOutEx
        implements IByteOutEx {

    private final IByteOut baseImpl;

    ByteOutExImpl(IByteOut baseImpl) {
        if (baseImpl == null)
            throw new NullPointerException("baseImpl");
        this.baseImpl = baseImpl;
    }

    public static IByteOutEx from(IByteOut byteOut) {
        if (byteOut instanceof IByteOutEx)
            return (IByteOutEx) byteOut;
        else
            return new ByteOutExImpl(byteOut);
    }

    @Override
    public void write(int b)
            throws IOException {
        baseImpl.write(b);
    }

    @Override
    public void write(byte[] buf, int off, int len)
            throws IOException {
        baseImpl.write(buf, off, len);
    }

    @Override
    public void write(ByteBuffer buffer)
            throws IOException {
        baseImpl.write(buffer);
    }

    @Override
    public void flush(boolean strict)
            throws IOException {
        baseImpl.flush(strict);
    }

    @Override
    public void close()
            throws IOException {
        baseImpl.close();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ByteOutExImpl))
            return false;
        ByteOutExImpl o = (ByteOutExImpl) obj;
        if (!baseImpl.equals(o.baseImpl))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0x2a8fd2d2;
        hash += baseImpl.hashCode();
        return hash;
    }

}
