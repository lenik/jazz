package net.bodz.bas.io;

import java.io.IOException;

public class NoLookAhead
        implements ILookAhead {

    @Override
    public int getLookMax() {
        return 0;
    }

    @Override
    public int getLookedLength() {
        return 0;
    }

    @Override
    public int look()
            throws IOException {
        return -1;
    }

    @Override
    public int look(char[] buf)
            throws IOException {
        return 0;
    }

    @Override
    public int look(char[] cbuf, int off, int len)
            throws IOException {
        return 0;
    }

    @Override
    public String look(int len)
            throws IOException {
        return null;
    }

    public static final NoLookAhead INSTANCE = new NoLookAhead();

}
