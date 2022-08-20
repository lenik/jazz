package net.bodz.bas.io;

import java.io.IOException;

public abstract class DecoratedPrintOut
        extends DecoratedCharOut
        implements
            IPrintOut {

    private static final long serialVersionUID = 1L;

    public DecoratedPrintOut(ICharOut _orig) {
        super(_orig);
    }

    @Override
    public ICharOut getWrapped() {
        return _orig;
    }

    @Override
    public void close() {
        IPrintOut.super.close();
    }

    @Override
    public void _closeX()
            throws IOException {
        getWrapped().close();
    }

    @Override
    public boolean isClosed() {
        return getWrapped().isClosed();
    }

    @Override
    public void flush() {
        IPrintOut.super.flush();
    }

    @Override
    public void _flushX()
            throws IOException {
        getWrapped().flush();
    }

}
