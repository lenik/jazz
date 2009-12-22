package net.bodz.bios.units;

import java.io.IOException;

import net.bodz.bas.commons.annotations.TypeNote;
import net.bodz.bas.commons.exceptions.IllegalUsageError;
import net.bodz.bas.commons.exceptions.UnexpectedException;
import net.bodz.bios.InPort;
import net.bodz.bios.Unit;

public abstract class SOSource extends SOUnit implements Source {

    @Override
    @TypeNote(Unit.class)
    public int getInPorts() {
        return 0;
    }

    @Override
    @TypeNote(Unit.class)
    public InPort getInPort(int index) {
        throw new IllegalUsageError();
    }

    @Override
    public abstract boolean pump(int timeout) throws IOException, InterruptedException;

    @Override
    public boolean pump() throws IOException {
        try {
            return pump(0);
        } catch (InterruptedException e) {
            throw new UnexpectedException(e);
        }
    }

    @Override
    public void pumpLoop(int timeout) throws IOException, InterruptedException {
        while (pump(timeout))
            ;
    }

    @Override
    public void pumpLoop() throws IOException {
        while (pump())
            ;
    }

}
