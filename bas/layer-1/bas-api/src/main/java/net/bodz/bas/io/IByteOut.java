package net.bodz.bas.io;

import java.io.Flushable;
import java.io.IOException;
import java.nio.ByteBuffer;

public interface IByteOut
        extends ISimpleByteOut, Flushable, ICloseable {

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
     * @param buf
     *            Selection to limit instead of buffer.capacity();
     * @throws NullPointerException
     *             If <code>buffer</code> is <code>null</code>.
     */
    void write(ByteBuffer buf)
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

    IByteOut NULL = IDataOut.NULL_LE;

    class fn {

        public static void write(IByteOut out, ByteBuffer buf)
                throws IOException {
            if (buf == null)
                throw new NullPointerException("buf");
            byte[] array = buf.array();
            int offset = buf.arrayOffset();
            int pos = buf.position();
            int limit = buf.limit();
            out.write(array, offset + pos, limit - pos);

            // TODO:
            // int remaining = buf.remaining();
            // byte[] copy = new byte[remaining];
            // buf.get(copy, 0, remaining);
            // out.write(copy, 0, remaining);
        }

        public static void dump(IByteOut out, IByteIn byteIn)
                throws IOException {
            if (byteIn == null)
                throw new NullPointerException("byteIn");
            byte[] buf = new byte[4096];
            while (true) {
                int cb = byteIn.read(buf);
                if (cb == -1)
                    return;
                out.write(buf, 0, cb);
            }
        }

    }

}
