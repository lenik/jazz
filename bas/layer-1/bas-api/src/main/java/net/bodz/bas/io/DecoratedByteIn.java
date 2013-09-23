package net.bodz.bas.io;

import java.io.IOException;
import java.nio.ByteBuffer;

import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedByteIn
        extends AbstractDecorator<IByteIn>
        implements IByteIn {

    private static final long serialVersionUID = 1L;

    public DecoratedByteIn(IByteIn _orig) {
        super(_orig);
    }

    @Override
    public void close()
            throws IOException {
        getWrapped().close();
    }

    @Override
    public boolean isClosed() {
        return getWrapped().isClosed();
    }

    @Override
    public int read()
            throws IOException {
        return getWrapped().read();
    }

    @Override
    public long skip(long n)
            throws IOException {
        return getWrapped().skip(n);
    }

    @Override
    public int read(byte[] buf)
            throws IOException {
        return getWrapped().read(buf);
    }

    @Override
    public int read(byte[] buf, int off, int len)
            throws IOException {
        return getWrapped().read(buf, off, len);
    }

    @Override
    public int read(ByteBuffer buf)
            throws IOException {
        return getWrapped().read(buf);
    }

}
