package net.bodz.bas.flow;

import java.io.IOException;

import net.bodz.bas.c.type.ClassLocal;
import net.bodz.bas.c.type.ClassLocals;
import net.bodz.bas.err.OutOfDomainException;

public abstract class AbstractInPort
        extends AbstractPort
        implements IInPort, IReceiverEx {

    private int index;

    public AbstractInPort(IUnit unit, int index) {
        super(unit);
        this.index = index;
    }

    private static ClassLocal<IPortMeta> metas = ClassLocals.createMap(//
            IPortMeta.class.getCanonicalName(), PortMetaEntryLoader.INSTANCE);

    @Override
    public IPortMeta getInPortMeta() {
        Class<? extends AbstractPort> clazz = getClass();
        IPortMeta meta = metas.get(clazz);
        return meta;
    }

    @Override
    public int getInPortIndex() {
        return index;
    }

    @Override
    public abstract void recv(Object data)
            throws IOException;

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

    @Override
    public void addSrc(IOutPort srcPort)
            throws IOException {
    }

    @Override
    public void removeSrc(IOutPort srcPort)
            throws IOException {
    }

}
