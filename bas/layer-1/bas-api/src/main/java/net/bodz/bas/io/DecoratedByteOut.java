package net.bodz.bas.io;

import java.io.IOException;
import java.nio.ByteBuffer;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedByteOut
        extends AbstractDecorator<IByteOut>
        implements IByteOut {

    private static final long serialVersionUID = 1L;

    public DecoratedByteOut(IByteOut _orig) {
        super(_orig);
    }

    @Override
    public void close()
            throws IOException {
        getWrapped().close();
    }

    @Override
    public void write(int b)
            throws IOException {
        getWrapped().write(b);
    }

    @Override
    public void write(@NotNull byte[] buf)
            throws IOException {
        getWrapped().write(buf);
    }

    @Override
    public void write(@NotNull byte[] buf, int off, int len)
            throws IOException {
        getWrapped().write(buf, off, len);
    }

    @Override
    public void write(ByteBuffer buf)
            throws IOException {
        getWrapped().write(buf);
    }

    @Override
    public void flush()
            throws IOException {
        getWrapped().flush();
    }

}
