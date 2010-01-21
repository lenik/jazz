package net.bodz.bas.flow.units;

import java.io.IOException;

import net.bodz.bas.exceptions.IllegalUsageError;
import net.bodz.bas.exceptions.UnexpectedException;
import net.bodz.bas.flow.InPort;

public abstract class SOSourceUnit
        extends SOUnit
        implements SourceUnit {

    @Override
    public int getInPorts() {
        return 0;
    }

    @Override
    public InPort getInPort(int index) {
        throw new IllegalUsageError();
    }

    @Override
    public abstract boolean pump(int timeout)
            throws IOException, InterruptedException;

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
