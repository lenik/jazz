package net.bodz.bas.io;

import java.io.Closeable;
import java.io.IOException;
import java.nio.CharBuffer;

import net.bodz.bas.meta.decl.NotNull;

public interface ICharIn
        extends ISimpleCharIn,
                Closeable {

    /**
     * @return -1 If reaches the end.
     * @throws NullPointerException If <code>chars</code> is <code>null</code>.
     */
    default int read(@NotNull char[] chars)
            throws IOException {
        return read(chars, 0, chars.length);
    }

    /**
     * @return -1 If reaches the end.
     * @throws NullPointerException If <code>chars</code> is <code>null</code>.
     */
    int read(@NotNull char[] chars, int off, int len)
            throws IOException;

    /**
     * Fill the remaing as much as possible, and advance the position.
     *
     * @return Count of characters filled in the <code>charBuffer</code>. Return -1 If reaches the
     * end.
     * @throws NullPointerException If <code>charBuffer</code> is <code>null</code>.
     */
    default int read(@NotNull CharBuffer buf)
            throws IOException {
        int ccRead = 0;
        while (buf.hasRemaining()) {
            int ch = read();
            if (ch == -1)
                return ccRead == 0 ? -1 : ccRead;
            buf.put((char) ch);
            ccRead++;
        }
        return ccRead;
    }


    /**
     * @return <code>null</code> If reached to EOF.
     */
    default String readString(int maxCharacters)
            throws IOException {
        char[] chars = new char[maxCharacters];
        int ccRead = read(chars);
        if (ccRead == -1)
            return null;
        return new String(chars, 0, ccRead);
    }

}
