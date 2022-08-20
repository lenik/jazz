package net.bodz.bas.io;

import java.io.IOException;

import net.bodz.bas.io.impl.NullCharOut;

public interface ICharOut
        extends
            ICharOut_raw {

    /**
     * @throws NullPointerException
     *             If <code>chars</code> is <code>null</code>.
     */
    @Override
    void write(char[] chars, int off, int len)
            throws IOException;

    /**
     * @throws NullPointerException
     *             If <code>s</code> is <code>null</code>.
     */
    @Override
    default void write(String s)
            throws IOException {
        write(s.toCharArray());
    }

    /**
     * @throws NullPointerException
     *             If <code>s</code> is <code>null</code>.
     */
    @Override
    default void write(String s, int off, int len)
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
    default void write(CharSequence chars)
            throws IOException {
        write(chars, 0, chars.length());
    }

    /**
     * @throws NullPointerException
     *             If <code>chars</code> is <code>null</code>.
     */
    @Override
    default void write(CharSequence chars, int start, int end)
            throws IOException {
        if (chars == null)
            throw new NullPointerException("chars");
        for (int i = start; i < end; i++) {
            char ch = chars.charAt(i);
            write(ch);
        }
    }

    ICharOut NULL = new NullCharOut();

}
