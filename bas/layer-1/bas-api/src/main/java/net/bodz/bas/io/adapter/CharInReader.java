package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.io.Reader;

import net.bodz.bas.io.ICharIn;

public class CharInReader
        extends Reader {

    private final ICharIn charIn;

    public CharInReader(ICharIn charIn) {
        if (charIn == null)
            throw new NullPointerException("charIn");
        this.charIn = charIn;
    }

    @Override
    public int read()
            throws IOException {
        return charIn.read();
    }

    @Override
    public int read(char[] cbuf, int off, int len)
            throws IOException {
        return charIn.read(cbuf, off, len);
    }

    @Override
    public void close()
            throws IOException {
        charIn.close();
    }

}
