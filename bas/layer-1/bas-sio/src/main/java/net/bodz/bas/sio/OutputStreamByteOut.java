package net.bodz.bas.sio;

import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamByteOut
        extends ByteOut {

    protected final OutputStream out;

    public OutputStreamByteOut(OutputStream stream) {
        this.out = stream;
    }

    public OutputStream getOutputStream() {
        return out;
    }

    @Override
    public void write(byte[] buf, int off, int len)
            throws IOException {
        out.write(buf, off, len);
    }

    @Override
    public void flush()
            throws IOException {
        out.flush();
    }

}
