package net.bodz.bas.io;

import java.io.IOException;

public interface ILookCharsAhead
        extends ILookAhead<Character> {

    @Override
    default Character look()
            throws IOException {
        int ch = lookChar();
        if (ch == -1)
            return null;
        else
            return (char) ch;
    }

    /**
     * @return -1 if EOF
     */
    int lookChar()
            throws IOException;

    /**
     * @return 0 at EOF
     */
    default int look(char[] buf)
            throws IOException {
        return look(buf, 0, buf.length);
    }

    /**
     * @return 0 at EOF
     */
    int look(char[] buf, int off, int len)
            throws IOException;

    /**
     * @return null at EOF.
     */
    default String look(int len)
            throws IOException {
        if (len == 0)
            return "";
        char[] buf = new char[len];
        int n = look(buf);
        return String.valueOf(buf, 0, n);
    }

    NoLookCharsAhead NULL = new NoLookCharsAhead();

}
