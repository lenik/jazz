package net.bodz.bas.io;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.nio.ByteBuffer;

import net.bodz.bas.meta.decl.NotNull;

@FunctionalInterface
public interface IByteOut
        extends ISimpleByteOut,
                Flushable,
                Closeable {

    /**
     * @throws NullPointerException If <code>buf</code> is <code>null</code>.
     */
    default void write(@NotNull byte[] buf)
            throws IOException {
        write(buf, 0, buf.length);
    }

    /**
     * @throws NullPointerException If <code>buf</code> is <code>null</code>.
     */
    default void write(@NotNull byte[] buf, int off, int len)
            throws IOException {
        int end = off + len;
        for (int i = off; i < end; i++)
            writeByte(buf[i]);
    }

    /**
     * @param buf Selection to limit instead of buffer.capacity();
     * @throws NullPointerException If <code>buffer</code> is <code>null</code>.
     */
    default void write(ByteBuffer buf)
            throws IOException {
        int offset = buf.arrayOffset();
        int pos = buf.position();
        write(buf.array(), offset + pos, buf.remaining());
        buf.position(buf.limit());
    }

    @Override
    default void flush()
            throws IOException {
    }

    @Override
    default void close()
            throws IOException {
    }

    IByteOut NULL = IDataOut.NULL_LE;

    class fn {

        public static void dump(@NotNull IByteOut out, @NotNull IByteIn byteIn)
                throws IOException {
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
