package net.bodz.bios;

import java.io.IOException;

import net.bodz.bas.lang.err.IllegalUsageError;

public class SinkOutPort extends _OutPort {

    public SinkOutPort(Unit unit, int index) {
        super(unit, index);
    }

    @Override
    public InPort getDst() {
        return null;
    }

    @Override
    public void setDst(Receiver dst) throws IOException {
        throw new IllegalUsageError();
    }

    @Override
    public void send(Object data) throws IOException {
        throw new IllegalUsageError();
    }

    @Override
    public void flush() throws IOException {
    }

}
