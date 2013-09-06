package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.io.Writer;

import net.bodz.bas.io.ICharOut;

public class CharOutWriter
        extends Writer {

    private final ICharOut charOut;

    /**
     * @throws NullPointerException
     *             If <code>charOut</code> is <code>null</code>.
     */
    public CharOutWriter(ICharOut charOut) {
        if (charOut == null)
            throw new NullPointerException("charOut");
        this.charOut = charOut;
    }

    @Override
    public void write(int c)
            throws IOException {
        charOut.write(c);
    }

    @Override
    public void write(String str, int off, int len)
            throws IOException {
        charOut.write(str, off, len);
    }

    @Override
    public void write(char[] cbuf, int off, int len)
            throws IOException {
        charOut.write(cbuf, off, len);
    }

    @Override
    public void flush()
            throws IOException {
        charOut.flush(true);
    }

    @Override
    public void close()
            throws IOException {
        charOut.close();
    }

}
