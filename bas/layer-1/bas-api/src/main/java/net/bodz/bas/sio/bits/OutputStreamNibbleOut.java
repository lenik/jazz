package net.bodz.bas.sio.bits;

import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamNibbleOut
        extends AbstractNibbleOut {

    private final OutputStream out;
    private int buffer;
    private boolean buffered;

    public OutputStreamNibbleOut(OutputStream out) {
        if (out == null)
            throw new NullPointerException("out");
        this.out = out;
    }

    @Override
    public boolean isBuffered() {
        return buffered;
    }

    @Override
    public void write4b(int nibble)
            throws IOException {
        if (buffered) {
            int b = (buffer << 4) | nibble;
            buffered = false;
            out.write(b);
        } else {
            buffer = nibble;
            buffered = true;
        }
    }

    @Override
    protected void write4bBuffered(byte[] buf, int off, int len)
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
        if (buffered) {
            int b = buffer << 4;
            out.write(b);
            buffered = false;
        }
        out.close();
    }

    @Override
    public String toString() {
        if (buffered)
            return out + "." + NibbleUtil.toHex(buffer, 1);
        else
            return out.toString();
    }

}
