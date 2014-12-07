package net.bodz.bas.flow.unit;

import java.io.IOException;

import net.bodz.bas.err.OutOfDomainException;
import net.bodz.bas.flow.stream.IReceiverEx;
import net.bodz.bas.flow.unit.metadata.IPortMetadata;
import net.bodz.bas.flow.unit.metadata.StrictPortMetadata;

public abstract class GenericUnit_1v
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
    public IPortMetadata getInPortMeta() {
        return new StrictPortMetadata("in", Object.class);
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
