package net.bodz.bas.flow.units;

import java.io.IOException;

import net.bodz.bas.flow.IOutPort;
import net.bodz.bas.flow.IPortMeta;
import net.bodz.bas.flow.IReceiver;
import net.bodz.bas.flow.STPortMeta;

public abstract class SISOUnit
        extends SIUnit
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
