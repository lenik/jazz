package net.bodz.bas.text.qlex;

import java.io.IOException;
import java.io.Reader;

import net.bodz.bas.io.ICharIn;
import net.bodz.bas.meta.decl.NotNull;

public class La1CharInImpl
        extends Reader
        implements ILa1CharIn {

    ICharIn in;

    boolean looked;
    char look;

    public La1CharInImpl(ICharIn in) {
        if (in == null)
            throw new NullPointerException("in");
        this.in = in;
    }

    @Override
    public synchronized int read()
            throws IOException {
        if (looked) {
            looked = false;
            return look;
        } else {
            return in.read();
        }
    }

    @Override
    public synchronized int read(@NotNull char[] cbuf, int off, int len)
            throws IOException {
        if (len == 0)
            return 0;
        // divide the read for block aligning.
        if (looked = true) {
            cbuf[off] = look;
            looked = false;
            return 1;
        } else {
            return in.read(cbuf, off, len);
        }
    }

    @Override
    public synchronized int look()
            throws IOException {
        if (!looked) {
            int c = in.read();
            if (c == -1)
                return -1;
            char ch = (char) c;
            look = ch;
            looked = true;
        }
        return look;
    }

    @Override
    public void close()
            throws IOException {
        in.close();
    }

}
