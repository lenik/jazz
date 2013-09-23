package net.bodz.bas.io.impl;

import java.io.IOException;
import java.nio.CharBuffer;

import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.ITellable;

public class TellableCharIn
        implements ICharIn, ITellable {

    private final ICharIn in;
    private long pos;

    public TellableCharIn(ICharIn in) {
        if (in == null)
            throw new NullPointerException("in");
        this.in = in;
    }

    @Override
    public void close()
            throws IOException {
        in.close();
    }

    @Override
    public boolean isClosed() {
        return in.isClosed();
    }

    @Override
    public int read()
            throws IOException {
        int b = in.read();
        if (b != -1)
            pos++;
        return b;
    }

    @Override
    public int read(char[] buf)
            throws IOException {
        int cb = in.read(buf);
        if (cb != -1)
            pos += cb;
        return cb;
    }

    @Override
    public int read(char[] buf, int off, int len)
            throws IOException {
        int cb = in.read(buf, off, len);
        if (cb != -1)
            pos += cb;
        return cb;
    }

    @Override
    public int read(CharBuffer buf)
            throws IOException {
        int cb = in.read(buf);
        if (cb != -1)
            pos += cb;
        return cb;
    }

    @Override
    public String readString(int maxCharacters)
            throws IOException {
        String str = in.readString(maxCharacters);
        if (str != null)
            pos += str.length();
        return str;
    }

    @Override
    public long tell() {
        return pos;
    }

}
