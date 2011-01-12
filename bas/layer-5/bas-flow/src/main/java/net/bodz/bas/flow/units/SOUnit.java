package net.bodz.bas.flow.units;

import java.io.IOException;

import net.bodz.bas.flow.IOutPort;
import net.bodz.bas.flow.IPortMeta;
import net.bodz.bas.flow.IReceiver;
import net.bodz.bas.flow.STPortMeta;
import net.bodz.bas.flow.IUnit;
import net.bodz.bas.flow.AbstractUnit;

// code copy-paste from SISOUnit.

public abstract class SOUnit
        extends AbstractUnit
        implements IOutPort {

    protected IReceiver dst;

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

    @Override
    public IUnit getUnit() {
        return this;
    }

    @Override
    public IPortMeta getOutPortMeta() {
        return new STPortMeta("out", Object.class);
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
