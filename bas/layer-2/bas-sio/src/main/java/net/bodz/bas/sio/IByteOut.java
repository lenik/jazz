package net.bodz.bas.sio;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.nio.ByteBuffer;

public interface IByteOut
        extends ISimpleByteOut, Flushable, Closeable {

    /**
     * @throws NullPointerException
     *             If <code>buf</code> is <code>null</code>.
     */
    void write(byte[] buf)
            throws IOException;

    /**
     * @throws NullPointerException
     *             If <code>buf</code> is <code>null</code>.
     */
    void write(byte[] buf, int off, int len)
            throws IOException;

    /**
     * @param buffer
     *            Selection to limit instead of buffer.capacity();
     * @throws NullPointerException
     *             If <code>buffer</code> is <code>null</code>.
     */
    void write(ByteBuffer buffer)
            throws IOException;

    /**
     * @param strict
     *            <code>true</code> means the call will return only after all buffered contents have
     *            been written, <code>false</code> means the caller has finished its output.
     */
    void flush(boolean strict)
            throws IOException;

    /**
     * The same to {@link #flush(boolean)} with <code>strict</code> set to <code>true</code>.
     */
    @Override
    void flush()
            throws IOException;

    @Override
    void close()
            throws IOException;

}
