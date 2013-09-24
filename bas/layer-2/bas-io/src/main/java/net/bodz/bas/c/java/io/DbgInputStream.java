package net.bodz.bas.c.java.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DbgInputStream
        extends FilterInputStream {

    public DbgInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read()
            throws IOException {
        int b = super.read();
        return b;
    }

    @Override
    public int read(byte[] b)
            throws IOException {
        int cb = super.read(b);
        return cb;
    }

    @Override
    public int read(byte[] b, int off, int len)
            throws IOException {
        int cb = super.read(b, off, len);
        return cb;
    }

}
