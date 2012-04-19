package net.bodz.bas.flow;

import net.bodz.bas.collection.util.ClassLocal;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.meta.tags.MetaClass;
import net.bodz.bas.util.Nullables;

public abstract class AbstractOutPort
        extends AbstractPort
        implements IOutPort {

    private int index;

    public AbstractOutPort(IUnit unit, int index) {
        super(unit);
        this.index = index;
    }

    private static ClassLocal<IPortMeta> metas;
    static {
        metas = new ClassLocal<IPortMeta>();
    }

    @Override
    public IPortMeta getOutPortMeta() {
        Class<? extends AbstractPort> clazz = getClass();
        IPortMeta meta = metas.get(clazz);
        if (meta == null) {
            Class<?> metaClass = Nullables.getAnnotation(clazz, MetaClass.class).value();
            if (metaClass != null)
                try {
                    meta = (IPortMeta) metaClass.newInstance();
                } catch (ReflectiveOperationException e) {
                    throw new IllegalUsageError("Can't create instance for MetaClass: " + metaClass, e);
                }
            else
                meta = createPortMeta();
            metas.put(clazz, meta);
        }
        return meta;
    }

    protected IPortMeta createPortMeta() {
        return new STPortMeta(getName(), Object.class);
    }

    @Override
    public int getOutPortIndex() {
        return index;
    }

}
