package net.bodz.bas.flow.unit.builtins;

import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamSinkUnit
        extends ByteProcessSinkUnit {

    private OutputStream out;
    private boolean autoFlush;

    public OutputStreamSinkUnit(OutputStream out, boolean autoFlush)
            throws IOException {
        this.out = out;
        this.autoFlush = autoFlush;
    }

    public OutputStreamSinkUnit(OutputStream out)
            throws IOException {
        this(out, false);
    }

    public OutputStream getOutputStream() {
        return out;
    }

    public void setOutputStream(OutputStream out) {
        this.out = out;
    }

    public boolean isAutoFlush() {
        return autoFlush;
    }

    public void setAutoFlush(boolean autoFlush) {
        this.autoFlush = autoFlush;
    }

    @Override
    public void reset()
            throws IOException {
    }

    @Override
    public void flush()
            throws IOException {
        out.flush();
    }

    @Override
    public void recv(byte[] bytes, int start, int end)
            throws IOException {
        int length = end - start;
        out.write(bytes, start, length);
        if (isAutoFlush())
            out.flush();
    }

}
