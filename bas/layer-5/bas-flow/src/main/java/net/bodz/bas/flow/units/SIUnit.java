package net.bodz.bas.flow.units;

import java.io.IOException;

import net.bodz.bas.err.OutOfDomainException;
import net.bodz.bas.flow.*;
import net.bodz.bas.flow.util.RecvUtil;

public abstract class SIUnit
        extends AbstractUnit
        implements IInPort, IReceiverEx {

    @Override
    public int getInPorts() {
        return 1;
    }

    @Override
    public IInPort getInPort(int portIndex) {
        if (portIndex != 0)
            throw new IndexOutOfBoundsException("in " + portIndex);
        return this;
    }

    @Override
    public IUnit getUnit() {
        return this;
    }

    @Override
    public IPortMeta getInPortMeta() {
        return new STPortMeta("in", Object.class);
    }

    @Override
    public int getInPortIndex() {
        return 0;
    }

    @Override
    public void addSrc(IOutPort srcPort)
            throws IOException {
    }

    @Override
    public void removeSrc(IOutPort srcPort)
            throws IOException {
    }

    /**
     * XXX - how to handle exceptions?
     */
    @Override
    public void recv(Object data)
            throws IOException {
        try {
            RecvUtil.recvEx(this, data);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
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
