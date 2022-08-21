package net.bodz.bas.io;

import java.io.IOException;

public abstract class AbstractPrintOut
        extends AbstractCharOut
        implements
            IPrintOut {

    @Override
    public void flush() {
        try {
            _flushX();
        } catch (IOException e) {
            throw new PrintException(e);
        }
    }

    @Override
    public void close() {
        try {
            _closeX();
        } catch (IOException e) {
            throw new PrintException(e);
        }
    }

}
