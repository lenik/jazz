package net.bodz.bas.io;

import java.io.Closeable;
import java.io.IOException;
import java.nio.CharBuffer;

public interface ICharIn
        extends ISimpleCharIn, Closeable {

    /**
     * @return -1 If reaches the end.
     * @throws NullPointerException
     *             If <code>chars</code> is <code>null</code>.
     */
    int read(char[] chars)
            throws IOException;

    /**
     * @return -1 If reaches the end.
     * @throws NullPointerException
     *             If <code>chars</code> is <code>null</code>.
     */
    int read(char[] chars, int off, int len)
            throws IOException;

    /**
     * Fill the remaing as much as possible, and advance the position.
     * 
     * @return Count of characters filled in the <code>charBuffer</code>. Return -1 If reaches the
     *         end.
     * @throws NullPointerException
     *             If <code>charBuffer</code> is <code>null</code>.
     */
    int read(CharBuffer charBuffer)
            throws IOException;

    /**
     * @return <code>null</code> If reached to EOF.
     */
    String readString(int maxCharacters)
            throws IOException;

    @Override
    void close()
            throws IOException;

}