package net.bodz.bas.io;

import java.io.IOException;
import java.nio.ByteBuffer;

public abstract class AbstractByteIOS
        extends AbstractCloseable
        implements IByteIOS {

    /** ⇱ Implementation Of {@link IByteIn}. */
    /* _____________________________ */static section.iface __IN__;

    @Override
    public final int read(byte[] buf)
            throws IOException {
        return read(buf, 0, buf.length);
    }

    @Override
    public int read(ByteBuffer dst)
            throws IOException {
        return IByteIn.fn.read(this, dst);
    }

    /** ⇱ Implementation Of {@link IByteOut}. */
    /* _____________________________ */static section.iface __OUT__;

    @Override
    public final void write(byte[] buf)
            throws IOException {
        write(buf, 0, buf.length);
    }

    @Override
    public void write(ByteBuffer src)
            throws IOException {
        IByteOut.fn.write(this, src);
    }

    @Override
    public void flush()
            throws IOException {
    }

}
