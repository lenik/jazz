package net.bodz.bas.sio;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface IByteIn
        extends ISimpleByteIn {

    /**
     * @throws NullPointerException
     *             If <code>buf</code> is <code>null</code>.
     */
    int read(byte[] buf)
            throws IOException;

    /**
     * @throws NullPointerException
     *             If <code>buf</code> is <code>null</code>.
     */
    int read(byte[] buf, int off, int len)
            throws IOException;

    /**
     * @param buffer
     *            Selection to limit instead of buffer.capacity();
     * @throws NullPointerException
     *             If <code>buffer</code> is <code>null</code>.
     */
    int read(ByteBuffer buffer)
            throws IOException;

    void close()
            throws IOException;

}
