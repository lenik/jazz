package net.bodz.bas.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import net.bodz.bas.io.adapter.ByteOutOutputStream;
import net.bodz.bas.io.adapter.ByteOutPrintStream;

public abstract class AbstractByteOut
        implements IByteOut {

    @Override
    public void write(byte[] buf)
            throws IOException {
        write(buf, 0, buf.length);
    }

    @Override
    public void write(ByteBuffer byteBuffer)
            throws IOException {
        if (byteBuffer == null)
            throw new NullPointerException("byteBuffer");
        byte[] array = byteBuffer.array();
        int offset = byteBuffer.arrayOffset();
        int length = byteBuffer.position();
        write(array, offset, length);
    }

    public void dump(IByteIn byteIn)
            throws IOException {
        if (byteIn == null)
            throw new NullPointerException("byteIn");
        byte[] buf = new byte[4096];
        while (true) {
            int cb = byteIn.read(buf);
            if (cb == -1)
                return;
            write(buf, 0, cb);
        }
    }

    /**
     * Do nothing in default implementation.
     */
    @Override
    public void flush(boolean strict)
            throws IOException {
    }

    @Override
    public void flush()
            throws IOException {
        flush(false);
    }

    @Override
    public void close()
            throws IOException {
        flush(true);
    }

    public OutputStream toOutputStream() {
        return new ByteOutOutputStream(this);
    }

    public OutputStream toPrintStream() {
        return new ByteOutPrintStream(this);
    }

}
