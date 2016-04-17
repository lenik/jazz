package net.bodz.bas.io;

import java.io.IOException;
import java.io.Writer;
import java.nio.CharBuffer;

import net.bodz.bas.io.impl.NullCharOut;

public interface ICharOut
        extends ISimpleCharOut, IFlushable, ICloseable {

    /**
     * @throws NullPointerException
     *             If <code>chars</code> is <code>null</code>.
     */
    void write(char[] chars)
            throws IOException;

    /**
     * @throws NullPointerException
     *             If <code>chars</code> is <code>null</code>.
     */
    void write(char[] chars, int off, int len)
            throws IOException;

    /**
     * @throws NullPointerException
     *             If <code>s</code> is <code>null</code>.
     */
    void write(String s)
            throws IOException;

    /**
     * @throws NullPointerException
     *             If <code>s</code> is <code>null</code>.
     */
    void write(String s, int off, int len)
            throws IOException;

    /**
     * @throws NullPointerException
     *             If <code>chars</code> is <code>null</code>.
     */
    void write(CharSequence chars, int off, int len)
            throws IOException;

    /**
     * @throws NullPointerException
     *             If <code>charBuffer</code> is <code>null</code>.
     */
    void write(CharBuffer charBuffer)
            throws IOException;

    Writer toWriter();

    ICharOut NULL = new NullCharOut();

    class fn {

        public static void write(ICharOut out, CharBuffer buf)
                throws IOException {
            if (buf == null)
                throw new NullPointerException("buf");
            char[] array = buf.array();
            int offset = buf.arrayOffset();
            int length = buf.position();
            out.write(array, offset, length);
        }

        public static void dump(ICharOut out, ICharIn charIn)
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
