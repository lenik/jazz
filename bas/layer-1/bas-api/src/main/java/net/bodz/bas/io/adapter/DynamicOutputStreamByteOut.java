package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.io.OutputStream;

import net.bodz.bas.io.IByteOut;
import net.bodz.bas.meta.decl.NotNull;

/**
 * @see OutputStreamByteOut
 */
public abstract class DynamicOutputStreamByteOut
        extends OutputStream
        implements IByteOut {

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
    public void writeByte(int b)
            throws IOException {
        getOutputStream().write(b);
    }

    @Override
    public void write(@NotNull byte[] buf)
            throws IOException {
        getOutputStream().write(buf);
    }

    @Override
    public void write(@NotNull byte[] buf, int off, int len)
            throws IOException {
        getOutputStream().write(buf, off, len);
    }

    @Override
    public void flush()
            throws IOException {
        getOutputStream().flush();
    }

    @Override
    public void close()
            throws IOException {
        getOutputStream().close();
    }

    public OutputStream toOutputStream() {
        return getOutputStream();
    }

}
