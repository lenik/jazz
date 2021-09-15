package net.bodz.bas.io;

import java.io.Flushable;
import java.io.IOException;

public interface IFlushable
        extends
            Flushable {

    /**
     * @param sync
     *            <code>true</code> means the call will return only after all buffered contents have
     *            been written, <code>false</code> means the caller has finished its output.
     */
    void flush(boolean sync)
            throws IOException;

    /**
     * The same to {@link #flush(boolean)} with <code>sync</code> set to <code>true</code>.
     */
    @Override
    default void flush()
            throws IOException {
        flush(true);
    }

}
