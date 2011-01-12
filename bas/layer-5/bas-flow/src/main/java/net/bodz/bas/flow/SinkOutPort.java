package net.bodz.bas.flow;

import java.io.IOException;

import net.bodz.bas.util.exception.IllegalUsageError;

public class SinkOutPort
        extends AbstractOutPort {

    public SinkOutPort(IUnit unit, int index) {
        super(unit, index);
    }

    @Override
    public IInPort getDst() {
        return null;
    }

    @Override
    public void setDst(IReceiver dst)
            throws IOException {
        throw new IllegalUsageError();
    }

    @Override
    public void send(Object data)
            throws IOException {
        throw new IllegalUsageError();
    }

    @Override
    public void flush()
            throws IOException {
    }

}
