package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.io.InputStream;

import net.bodz.bas.io.AbstractByteIn;

public abstract class DynamicInputStreamByteIn
        extends AbstractByteIn {

    public abstract InputStream getInputStream();

    @Override
    public long skip(long n)
            throws IOException {
        return getInputStream().skip(n);
    }

    @Override
    public int read()
            throws IOException {
        return getInputStream().read();
    }

    @Override
    public int read(byte[] buf, int off, int len)
            throws IOException {
        return getInputStream().read(buf, off, len);
    }

    @Override
    public void close()
            throws IOException {
        getInputStream().close();
    }

}
