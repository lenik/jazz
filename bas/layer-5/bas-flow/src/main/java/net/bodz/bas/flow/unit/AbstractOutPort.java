package net.bodz.bas.flow.unit;

import net.bodz.bas.c.type.ClassLocal;
import net.bodz.bas.c.type.ClassLocals;
import net.bodz.bas.flow.unit.metadata.IPortMeta;
import net.bodz.bas.flow.unit.metadata.PortMetaEntryLoader;

public abstract class AbstractOutPort
        extends AbstractPort
        implements IOutPort {

    private int index;

    public AbstractOutPort(IUnit unit, int index) {
        super(unit);
        this.index = index;
    }

    private static ClassLocal<IPortMeta> metas = ClassLocals.createMap(//
            IPortMeta.class.getCanonicalName(), PortMetaEntryLoader.INSTANCE);

    @Override
    public IPortMeta getOutPortMeta() {
        Class<? extends AbstractPort> clazz = getClass();
        IPortMeta meta = metas.load(clazz);
        return meta;
    }

    @Override
    public int getOutPortIndex() {
        return index;
    }

}
