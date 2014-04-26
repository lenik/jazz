package net.bodz.bas.io;

import java.io.IOException;
import java.io.Writer;
import java.nio.CharBuffer;

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
    public void write(char[] chars)
            throws IOException {
        getWrapped().write(chars);
    }

    @Override
    public void write(char[] chars, int off, int len)
            throws IOException {
        getWrapped().write(chars, off, len);
    }

    @Override
    public void write(String s)
            throws IOException {
        getWrapped().write(s);
    }

    @Override
    public void write(String s, int off, int len)
            throws IOException {
        getWrapped().write(s, off, len);
    }

    @Override
    public void write(CharSequence chars, int off, int len)
            throws IOException {
        getWrapped().write(chars, off, len);
    }

    @Override
    public void write(CharBuffer charBuffer)
            throws IOException {
        getWrapped().write(charBuffer);
    }

    @Override
    public void flush(boolean strict)
            throws IOException {
        getWrapped().flush(strict);
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
