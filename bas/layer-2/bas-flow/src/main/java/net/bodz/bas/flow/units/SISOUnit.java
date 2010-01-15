package net.bodz.bas.flow.units;

import java.io.IOException;

import net.bodz.bas.flow.OutPort;
import net.bodz.bas.flow.PortMeta;
import net.bodz.bas.flow.Receiver;
import net.bodz.bas.flow.STPortMeta;

public abstract class SISOUnit
        extends SIUnit
        implements OutPort {

    protected Receiver dst;

    @Override
    public int getOutPorts() {
        return 1;
    }

    @Override
    public OutPort getOutPort(int portIndex) {
        if (portIndex != 0)
            throw new IndexOutOfBoundsException("out " + portIndex);
        return this;
    }

    @Override
    public PortMeta getOutPortMeta() {
        return new STPortMeta("out", Object.class);
    }

    @Override
    public int getOutPortIndex() {
        return 0;
    }

    @Override
    public Receiver getDst() {
        return dst;
    }

    @Override
    public void setDst(Receiver dst)
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
