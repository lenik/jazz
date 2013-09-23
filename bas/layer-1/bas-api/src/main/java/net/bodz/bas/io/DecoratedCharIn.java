package net.bodz.bas.io;

import java.io.IOException;
import java.nio.CharBuffer;

import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedCharIn
        extends AbstractDecorator<ICharIn>
        implements ICharIn {

    private static final long serialVersionUID = 1L;

    public DecoratedCharIn(ICharIn _orig) {
        super(_orig);
    }

    @Override
    public void close()
            throws IOException {
        getWrapped().close();
    }

    @Override
    public boolean isClosed() {
        return getWrapped().isClosed();
    }

    @Override
    public int read()
            throws IOException {
        return getWrapped().read();
    }

    @Override
    public int read(char[] chars)
            throws IOException {
        return getWrapped().read(chars);
    }

    @Override
    public int read(char[] chars, int off, int len)
            throws IOException {
        return getWrapped().read(chars, off, len);
    }

    @Override
    public int read(CharBuffer buf)
            throws IOException {
        return getWrapped().read(buf);
    }

    @Override
    public String readString(int maxCharacters)
            throws IOException {
        return getWrapped().readString(maxCharacters);
    }

}
