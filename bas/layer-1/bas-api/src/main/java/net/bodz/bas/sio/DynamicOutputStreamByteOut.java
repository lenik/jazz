package net.bodz.bas.sio;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @see OutputStreamByteOut
 */
public abstract class DynamicOutputStreamByteOut
        extends AbstractByteOutEx {

    /**
     * @return non-<code>null</code> {@link OutputStream} instance.
     */
    public abstract OutputStream getOutputStream();

    @Override
    public void write(int b)
            throws IOException {
        getOutputStream().write(b);
    }

    @Override
    public void write(byte[] buf)
            throws IOException {
        getOutputStream().write(buf);
    }

    @Override
    public void write(byte[] buf, int off, int len)
            throws IOException {
        getOutputStream().write(buf, off, len);
    }

    @Override
    public void flush(boolean strict)
            throws IOException {
        getOutputStream().flush();
    }

    @Override
    public void close()
            throws IOException {
        getOutputStream().close();
    }

    @Override
    public OutputStream toOutputStream() {
        return getOutputStream();
    }

}
