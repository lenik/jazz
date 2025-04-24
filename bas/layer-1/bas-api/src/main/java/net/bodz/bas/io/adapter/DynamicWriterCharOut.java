package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.io.Writer;

import net.bodz.bas.io.ICharOut;
import net.bodz.bas.meta.decl.NotNull;

/**
 * @see WriterCharOut
 */
public abstract class DynamicWriterCharOut
        extends Writer
        implements ICharOut {

    /**
     * @return non-<code>null</code> {@link Writer} instance.
     */
    public abstract Writer getWriter();

    @Override
    public void write(int c)
            throws IOException {
        getWriter().write(c);
    }

    @Override
    public void write(@NotNull char[] chars)
            throws IOException {
        getWriter().write(chars);
    }

    @Override
    public void write(@NotNull char[] chars, int off, int len)
            throws IOException {
        getWriter().write(chars, off, len);
    }

    @Override
    public void write(@NotNull String s)
            throws IOException {
        getWriter().write(s);
    }

    @Override
    public void write(@NotNull String string, int off, int len)
            throws IOException {
        getWriter().write(string, off, len);
    }

    @Override
    public void close()
            throws IOException {
        getWriter().close();
    }

    @Override
    public Writer toWriter() {
        return getWriter();
    }

}
