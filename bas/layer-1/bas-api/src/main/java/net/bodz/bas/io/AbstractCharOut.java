package net.bodz.bas.io;

import java.io.IOException;
import java.io.Writer;

public abstract class AbstractCharOut
        extends Writer
        implements
            ICharOut {

    private boolean closed;

    protected void ensureOpen() {
        if (closed)
            throw new IllegalStateException("already closed");
    }

    @Override
    public Writer toWriter() {
        return this;
    }

    /** ⇱ Implementation Of {@link ICharOut}. */
    /* _____________________________ */static section.iface __CHAR_OUT__;

    /**
     * @throws NullPointerException
     *             If <code>s</code> is <code>null</code>.
     */
    @Override
    public void write(String s)
            throws IOException {
        write(s.toCharArray());
    }

    /**
     * @throws NullPointerException
     *             If <code>s</code> is <code>null</code>.
     */
    @Override
    public void write(String s, int off, int len)
            throws IOException {
        char[] buf = new char[len];
        s.getChars(off, off + len, buf, 0);
        write(buf, 0, len);
    }

    /**
     * @throws NullPointerException
     *             If <code>chars</code> is <code>null</code>.
     */
    @Override
    public void write(CharSequence chars)
            throws IOException {
        write(chars, 0, chars.length());
    }

    /**
     * @throws NullPointerException
     *             If <code>chars</code> is <code>null</code>.
     */
    @Override
    public void write(CharSequence chars, int start, int end)
            throws IOException {
        if (chars == null)
            throw new NullPointerException("chars");
        for (int i = start; i < end; i++) {
            char ch = chars.charAt(i);
            write(ch);
        }
    }

    @Override
    public void close()
            throws IOException {
        flush();
        closed = true;
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    /** ⇱ Implementation Of {@link Appendable}. */
    /* _____________________________ */static section.iface __APPENDABLE__;

    @Override
    public AbstractCharOut append(CharSequence csq)
            throws IOException {
        write(csq, 0, csq.length());
        return this;
    }

    @Override
    public AbstractCharOut append(CharSequence csq, int start, int end)
            throws IOException {
        write(csq, start, end);
        return this;
    }

    public AbstractCharOut append(String s)
            throws IOException {
        write(s);
        return this;
    }

}
