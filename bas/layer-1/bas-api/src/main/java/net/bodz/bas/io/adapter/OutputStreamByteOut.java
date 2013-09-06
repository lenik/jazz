package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.io.OutputStream;

import net.bodz.bas.io.AbstractByteOut;

public class OutputStreamByteOut
        extends AbstractByteOut {

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
    public void write(byte[] buf)
            throws IOException {
        out.write(buf);
    }

    @Override
    public void write(byte[] buf, int off, int len)
            throws IOException {
        out.write(buf, off, len);
    }

    @Override
    public void flush(boolean strict)
            throws IOException {
        out.flush();
    }

    @Override
    public void close()
            throws IOException {
        out.close();
    }

    @Override
    public OutputStream toOutputStream() {
        return out;
    }

}
