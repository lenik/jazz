package net.bodz.bas.io;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface IByteOut
        extends ISimpleByteOut, IFlushable, ICloseable {

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

    IByteOut NULL = IDataOut.NULL_LE;

    class fn {

        public static void write(IByteOut out, ByteBuffer buf)
                throws IOException {
            if (buf == null)
                throw new NullPointerException("buf");
            int offset = buf.arrayOffset();
            int pos = buf.position();
            out.write(buf.array(), offset + pos, buf.remaining());
            buf.position(buf.limit());
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
