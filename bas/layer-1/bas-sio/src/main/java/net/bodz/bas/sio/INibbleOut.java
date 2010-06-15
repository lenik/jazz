package net.bodz.bas.sio;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

public interface INibbleOut
        extends Flushable, Closeable {

    /**
     * @return <code>true</code> If any nibble is buffered and should be flushed out before close.
     */
    boolean isBuffered();

    /**
     * Only the bit 3-0 is used.
     */
    void write4b(int nibble)
            throws IOException;

    void write4b(byte[] buf, int nibbleOffset, int nibbleCount)
            throws IOException;

    void write4b(byte[] buf)
            throws IOException;

    @Override
    void flush()
            throws IOException;

    /**
     * @param strict
     *            <code>true</code> means the call will return only after all buffered contents have
     *            been written, <code>false</code> means the caller has finished its output.
     */
    void flush(boolean strict)
            throws IOException;

    @Override
    void close()
            throws IOException;

}
