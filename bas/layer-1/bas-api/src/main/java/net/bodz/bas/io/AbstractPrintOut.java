package net.bodz.bas.io;

public abstract class AbstractPrintOut
        extends AbstractCharOut
        implements
            IPrintOut {

    @Override
    public void flush() {
        IPrintOut.super.flush();
    }

    @Override
    public void close() {
        IPrintOut.super.close();
    }

}
