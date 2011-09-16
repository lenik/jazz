package net.bodz.bas.sio.bits;

import java.io.IOException;
import java.io.InputStream;


public class InputStreamNibbleIn
        extends AbstractNibbleIn {

    private final InputStream in;
    private int buffer;
    private boolean buffered;

    public InputStreamNibbleIn(InputStream in) {
        if (in == null)
            throw new NullPointerException("in");
        this.in = in;
    }

    @Override
    public boolean isBuffered() {
        return buffered;
    }

    @Override
    public int read4b()
            throws IOException {
        if (buffered) {
            buffered = false;
            return buffer;
        } else {
            int byt = in.read();
            if (byt == -1)
                return -1;
            int high = byt >> 4;
            buffer = byt & 0xf;
            buffered = true;
            return high;
        }
    }

    @Override
    protected int read4bNonBuffered(byte[] buf, int off, int len)
            throws IOException {
        int cb = in.read(buf, off, len);
        if (cb == -1)
            return -1;
        return cb * 2;
    }

}
