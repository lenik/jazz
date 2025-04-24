package net.bodz.bas.io;

import java.io.IOException;
import java.io.Writer;
import java.nio.CharBuffer;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.model.AbstractDecorator;

public abstract class DecoratedCharOut
        extends AbstractDecorator<ICharOut>
        implements ICharOut {

    private static final long serialVersionUID = 1L;

    public DecoratedCharOut(ICharOut _orig) {
        super(_orig);
    }

    @Override
    public void write(int ch)
            throws IOException {
        getWrapped().write(ch);
    }

    @Override
    public void write(@NotNull char[] chars)
            throws IOException {
        getWrapped().write(chars);
    }

    @Override
    public void write(@NotNull char[] chars, int off, int len)
            throws IOException {
        getWrapped().write(chars, off, len);
    }

    @Override
    public void write(@NotNull String s)
            throws IOException {
        getWrapped().write(s);
    }

    @Override
    public void write(@NotNull String s, int off, int len)
            throws IOException {
        getWrapped().write(s, off, len);
    }

    @Override
    public void write(@NotNull CharSequence chars)
            throws IOException {
        getWrapped().write(chars);
    }

    @Override
    public void write(@NotNull CharSequence chars, int start, int end)
            throws IOException {
        getWrapped().write(chars, start, end);
    }

    @Override
    public void write(@NotNull CharBuffer charBuffer)
            throws IOException {
        getWrapped().write(charBuffer);
    }

    @Override
    public void flush()
            throws IOException {
        getWrapped().flush();
    }

    @Override
    public void close()
            throws IOException {
        getWrapped().close();
    }

    @Override
    public Writer toWriter() {
        return getWrapped().toWriter();
    }

}
