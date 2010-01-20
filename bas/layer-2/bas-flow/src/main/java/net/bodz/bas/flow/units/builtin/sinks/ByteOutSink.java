package net.bodz.bas.flow.units.builtin.sinks;

import java.io.Flushable;
import java.io.IOException;

import net.bodz.bas.sio.IByteOut;

public class ByteOutSink
        extends ByteProcessSink {

    private IByteOut out;
    private Flushable flushable;

    public ByteOutSink(IByteOut out, boolean autoFlush)
            throws IOException {
        this.out = out;
        setAutoFlush(autoFlush);
    }

    public ByteOutSink(IByteOut out)
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
