package net.bodz.bas.io;

import java.io.IOException;

public class NoLookBytesAhead
        implements ILookBytesAhead {

    @Override
    public int getLookCapacity() {
        return 0;
    }

    @Override
    public int getLookLimit() {
        return 0;
    }

    @Override
    public Byte look()
            throws IOException {
        return null;
    }

    @Override
    public int lookByte()
            throws IOException {
        return -1;
    }

    @Override
    public int look(byte[] buf)
            throws IOException {
        return 0;
    }

    @Override
    public int look(byte[] buf, int off, int len)
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

    public static final NoLookBytesAhead INSTANCE = new NoLookBytesAhead();

}
