package net.bodz.bas.io;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;

public interface IByteIn
        extends ISimpleByteIn, Closeable {

    /**
     * @return the actual number of bytes skipped.
     */
    long skip(long n)
            throws IOException;

    /**
     * @return <code>-1</code> if the EOF is reached.
     * @throws NullPointerException
     *             If <code>buf</code> is <code>null</code>.
     */
    int read(byte[] buf)
            throws IOException;

    /**
     * @return <code>-1</code> if the EOF is reached.
     * @throws NullPointerException
     *             If <code>buf</code> is <code>null</code>.
     */
    int read(byte[] buf, int off, int len)
            throws IOException;

    /**
     * Fill the remaing as much as possible, and advance the position.
     * 
     * @return Count of bytes filled in the <code>byteBuffer</code>. Return -1 If reaches the end.
     * @throws NullPointerException
     *             If <code>byteBuffer</code> is <code>null</code>.
     */
    int read(ByteBuffer byteBuffer)
            throws IOException;

    @Override
    void close()
            throws IOException;

}