package net.bodz.bas.sio;

import java.io.Flushable;
import java.io.IOException;
import java.nio.CharBuffer;

public interface ICharOut
        extends ISimpleCharOut, Flushable, AutoCloseable {

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
