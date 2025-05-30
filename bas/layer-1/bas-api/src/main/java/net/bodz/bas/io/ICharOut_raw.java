package net.bodz.bas.io;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.nio.CharBuffer;

import net.bodz.bas.io.adapter.CharOutWriter;
import net.bodz.bas.meta.decl.NotNull;

public interface ICharOut_raw
        extends ISimpleCharOut,
                Flushable,
                Closeable {

    /**
     * @throws NullPointerException If <code>chars</code> is <code>null</code>.
     */
    default void write(@NotNull char[] chars)
            throws IOException {
        write(chars, 0, chars.length);
    }

    /**
     * @throws NullPointerException If <code>chars</code> is <code>null</code>.
     */
    void write(@NotNull char[] chars, int off, int len)
            throws IOException;

    /**
     * @throws NullPointerException If <code>s</code> is <code>null</code>.
     */
    default void write(@NotNull String s)
            throws IOException {
        write(s, 0, s.length());
    }

    /**
     * @throws NullPointerException If <code>s</code> is <code>null</code>.
     */
    void write(@NotNull String s, int off, int len)
            throws IOException;

    /**
     * @throws NullPointerException If <code>chars</code> is <code>null</code>.
     */
    default void write(@NotNull CharSequence chars)
            throws IOException {
        write(chars, 0, chars.length());
    }

    /**
     * @throws NullPointerException If <code>chars</code> is <code>null</code>.
     */
    void write(@NotNull CharSequence chars, int start, int end)
            throws IOException;

    /**
     * @throws NullPointerException If <code>charBuffer</code> is <code>null</code>.
     */
    default void write(@NotNull CharBuffer charBuffer)
            throws IOException {
        fn.write(this, charBuffer);
    }

    default void dump(@NotNull ICharIn charIn)
            throws IOException {
        fn.dump(this, charIn);
    }

    default Writer toWriter() {
        return new CharOutWriter(this);
    }

    class fn {

        public static void write(ICharOut_raw out, CharBuffer buf)
                throws IOException {
            if (buf == null)
                throw new NullPointerException("buf");
            int offset = buf.arrayOffset();
            int pos = buf.position();
            int len = buf.remaining();
            out.write(buf.array(), offset + pos, len);
            buf.position(buf.limit());
        }

        public static void dump(ICharOut_raw out, ICharIn charIn)
                throws IOException {
            if (charIn == null)
                throw new NullPointerException("charIn");
            char[] buf = new char[4096];
            while (true) {
                int cc = charIn.read(buf);
                if (cc == -1)
                    return;
                out.write(buf, 0, cc);
            }
        }

    }

}
