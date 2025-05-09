package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.io.OutputStream;

import net.bodz.bas.io.IByteOut;
import net.bodz.bas.meta.decl.NotNull;

public class OutputStreamByteOut
        extends OutputStream
        implements IByteOut {

    protected final OutputStream out;

    public OutputStreamByteOut(OutputStream stream) {
        this.out = stream;
    }

    @Override
    public void write(int b)
            throws IOException {
        out.write(b);
    }

    @Override
    public void writeByte(int b)
            throws IOException {
        out.write(b);
    }

    @Override
    public void write(@NotNull byte[] buf)
            throws IOException {
        out.write(buf);
    }

    @Override
    public void write(@NotNull byte[] buf, int off, int len)
            throws IOException {
        out.write(buf, off, len);
    }

    @Override
    public void flush()
            throws IOException {
        out.flush();
    }

    @Override
    public void close()
            throws IOException {
        out.close();
    }

    public OutputStream toOutputStream() {
        return out;
    }

}
