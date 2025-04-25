package net.bodz.bas.io;

import java.io.IOException;

public class NoLookCharsAhead
        implements ILookCharsAhead {

    @Override
    public int getLookCapacity() {
        return 0;
    }

    @Override
    public int getLookLimit() {
        return 0;
    }

    @Override
    public Character look()
            throws IOException {
        return null;
    }

    @Override
    public int lookChar()
            throws IOException {
        return -1;
    }

    @Override
    public int look(char[] buf)
            throws IOException {
        return 0;
    }

    @Override
    public int look(char[] buf, int off, int len)
            throws IOException {
        return 0;
    }

    @Override
    public String look(int len)
            throws IOException {
        if (len == 0)
            return "";
        else
            return null;
    }

    public static final NoLookCharsAhead INSTANCE = new NoLookCharsAhead();

}
