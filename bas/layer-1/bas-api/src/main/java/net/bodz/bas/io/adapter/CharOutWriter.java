package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.io.Writer;

import net.bodz.bas.io.ICharOut_raw;
import net.bodz.bas.meta.decl.NotNull;

public class CharOutWriter
        extends Writer {

    private final ICharOut_raw charOut;

    /**
     * @throws NullPointerException If <code>charOut</code> is <code>null</code>.
     */
    public CharOutWriter(ICharOut_raw charOut) {
        if (charOut == null)
            throw new NullPointerException("charOut");
        this.charOut = charOut;
    }

    @Override
    public void write(int c)
            throws IOException {
        charOut.writeChar(c);
    }

    @Override
    public void write(@NotNull String str, int off, int len)
            throws IOException {
        charOut.write(str, off, len);
    }

    @Override
    public void write(@NotNull char[] cbuf, int off, int len)
            throws IOException {
        charOut.write(cbuf, off, len);
    }

    @Override
    public void flush()
            throws IOException {
        charOut.flush();
    }

    @Override
    public void close()
            throws IOException {
        charOut.close();
    }

}
