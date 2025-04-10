package net.bodz.bas.io;

import java.io.IOException;

public interface ILookAhead {

    int getLookMax();

    int getLookedLength();

    /**
     * @return -1 if EOF
     */
    int look()
            throws IOException;

    /**
     * @return 0 at EOF
     */
    int look(char[] buf)
            throws IOException;

    /**
     * @return 0 at EOF
     */
    int look(char[] cbuf, int off, int len)
            throws IOException;

    /**
     * @return null at EOF.
     */
    String look(int len)
            throws IOException;

    NoLookAhead NULL = new NoLookAhead();

}
