package net.bodz.bas.flow.unit;

import java.io.IOException;

import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.flow.stream.IReceiver;
import net.bodz.bas.flow.unit.metadata.IPortMetadata;
import net.bodz.bas.flow.unit.metadata.StrictPortMetadata;

public abstract class GenericUnit_z1
        extends AbstractSourceUnit
        implements IOutPort {

    IReceiver dst;

    @Override
    public int getInPorts() {
        return 0;
    }

    @Override
    public IInPort getInPort(int index) {
        throw new IllegalUsageError();
    }

    @Override
    public int getOutPorts() {
        return 1;
    }

    @Override
    public IOutPort getOutPort(int portIndex) {
        if (portIndex != 0)
            throw new IndexOutOfBoundsException("out " + portIndex);
        return this;
    }

    /** ⇱ Implementaton Of {@link IOutPort}. */
    /* _____________________________ */static section.iface __OUT__;

    @Override
    public IUnit getUnit() {
        return this;
    }

    @Override
    public IPortMetadata getOutPortMeta() {
        return new StrictPortMetadata("out", Object.class);
    }

    @Override
    public int getOutPortIndex() {
        return 0;
    }

    @Override
    public IReceiver getDst() {
        return dst;
    }

    @Override
    public void setDst(IReceiver dst)
            throws IOException {
        this.dst = dst;
    }

    @Override
    public void send(Object data)
            throws IOException {
        if (dst != null)
            dst.recv(data);
    }

}
