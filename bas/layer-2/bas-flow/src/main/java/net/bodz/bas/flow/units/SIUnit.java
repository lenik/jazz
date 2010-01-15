package net.bodz.bas.flow.units;

import java.io.IOException;

import net.bodz.bas.exceptions.OutOfDomainException;
import net.bodz.bas.flow.InPort;
import net.bodz.bas.flow.OutPort;
import net.bodz.bas.flow.PortMeta;
import net.bodz.bas.flow.ReceiverEx;
import net.bodz.bas.flow.STPortMeta;
import net.bodz.bas.flow.Unit;
import net.bodz.bas.flow._Unit;
import net.bodz.bas.flow.util.RecvUtil;

public abstract class SIUnit
        extends _Unit
        implements InPort, ReceiverEx {

    @Override
    public int getInPorts() {
        return 1;
    }

    @Override
    public InPort getInPort(int portIndex) {
        if (portIndex != 0)
            throw new IndexOutOfBoundsException("in " + portIndex);
        return this;
    }

    @Override
    public Unit getUnit() {
        return this;
    }

    @Override
    public PortMeta getInPortMeta() {
        return new STPortMeta("in", Object.class);
    }

    @Override
    public int getInPortIndex() {
        return 0;
    }

    @Override
    public void addSrc(OutPort srcPort)
            throws IOException {
    }

    @Override
    public void removeSrc(OutPort srcPort)
            throws IOException {
    }

    @Override
    public void recv(Object data)
            throws IOException {
        RecvUtil.recvEx(this, data);
    }

    @Override
    public void recvNull()
            throws IOException {
        throw new NullPointerException("recvNull");
    }

    @Override
    public void recvUnknown(Object data)
            throws IOException {
        throw new OutOfDomainException("type", data.getClass());
    }

}
