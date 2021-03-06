package net.bodz.bas.flow.unit.builtins;

import java.io.Flushable;
import java.io.IOException;

import net.bodz.bas.io.IByteOut;

public class ByteOutSinkUnit
        extends ByteProcessSinkUnit {

    private IByteOut out;
    private Flushable flushable;

    public ByteOutSinkUnit(IByteOut out, boolean autoFlush)
            throws IOException {
        this.out = out;
        setAutoFlush(autoFlush);
    }

    public ByteOutSinkUnit(IByteOut out)
            throws IOException {
        this(out, false);
    }

    public IByteOut getByteOut() {
        return out;
    }

    public void setByteOut(IByteOut out) {
        this.out = out;
    }

    public boolean isAutoFlush() {
        return flushable != null;
    }

    public void setAutoFlush(boolean autoFlush) {
        if (autoFlush && out instanceof Flushable)
            this.flushable = (Flushable) out;
    }

    @Override
    public void reset()
            throws IOException {
    }

    @Override
    public void flush()
            throws IOException {
        if (out instanceof Flushable)
            ((Flushable) out).flush();
    }

    @Override
    public void recv(byte[] bytes, int start, int end)
            throws IOException {
        int length = end - start;
        out.write(bytes, start, length);
        if (flushable != null)
            flushable.flush();
    }

}
