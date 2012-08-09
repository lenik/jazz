package net.bodz.bas.flow.unit;

import java.io.IOException;

import net.bodz.bas.err.UnexpectedException;

public abstract class AbstractSourceUnit
        extends AbstractUnit
        implements ISourceUnit {

    @Override
    public boolean pump()
            throws IOException {
        try {
            return pump(0);
        } catch (InterruptedException e) {
            throw new UnexpectedException(e);
        }
    }

    @Override
    public void pumpLoop(int timeout)
            throws IOException, InterruptedException {
        while (pump(timeout))
            ;
    }

    @Override
    public void pumpLoop()
            throws IOException {
        while (pump())
            ;
    }

}
