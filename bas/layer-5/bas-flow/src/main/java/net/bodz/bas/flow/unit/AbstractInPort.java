package net.bodz.bas.flow.unit;

import java.io.IOException;

import net.bodz.bas.c.type.LazyTypeMap;
import net.bodz.bas.c.type.TypeMapRegistry;
import net.bodz.bas.err.OutOfDomainException;
import net.bodz.bas.flow.stream.IReceiverEx;
import net.bodz.bas.flow.unit.metadata.IPortMetadata;
import net.bodz.bas.flow.unit.metadata.PortMetadataEntryLoader;

public abstract class AbstractInPort
        extends AbstractPort
        implements IInPort, IReceiverEx {

    private int index;

    public AbstractInPort(IUnit unit, int index) {
        super(unit);
        this.index = index;
    }

    private static LazyTypeMap<IPortMetadata> clsPortMetadata = TypeMapRegistry.createMap(//
            IPortMetadata.class.getCanonicalName(), PortMetadataEntryLoader.INSTANCE);

    @Override
    public IPortMetadata getInPortMeta() {
        Class<? extends AbstractPort> clazz = getClass();
        IPortMetadata meta = clsPortMetadata.getOrLoad(clazz);
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
