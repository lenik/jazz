package net.bodz.bas.flow;

import net.bodz.bas.annotation.util.InheritableAnnotation;
import net.bodz.bas.annotations.MetaClass;
import net.bodz.bas.collection.util.ClassLocal;
import net.bodz.bas.type.util.Types;

public abstract class _OutPort extends _Port implements OutPort {

    private int index;

    public _OutPort(Unit unit, int index) {
        super(unit);
        this.index = index;
    }

    private static ClassLocal<PortMeta> metas;
    static {
        metas = new ClassLocal<PortMeta>();
    }

    @Override
    public PortMeta getOutPortMeta() {
        Class<? extends _Port> clazz = getClass();
        PortMeta meta = metas.get(clazz);
        if (meta == null) {
            Class<?> metaClass = (Class<?>) InheritableAnnotation.getValue(clazz, MetaClass.class);
            if (metaClass != null)
                meta = (PortMeta) Types.newInstance(metaClass);
            else
                meta = createPortMeta();
            metas.put(clazz, meta);
        }
        return meta;
    }

    protected PortMeta createPortMeta() {
        return new STPortMeta(getName(), Object.class);
    }

    @Override
    public int getOutPortIndex() {
        return index;
    }

}
