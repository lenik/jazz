package net.bodz.bas.sio;

import java.io.IOException;
import java.io.InputStream;

public abstract class DynamicInputStreamByteIn
        extends AbstractByteIn {

    public abstract InputStream getInputStream();

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

    @Override
    public InputStream toInputStream() {
        return getInputStream();
    }

}
