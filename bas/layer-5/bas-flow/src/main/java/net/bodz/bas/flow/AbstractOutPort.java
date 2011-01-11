package net.bodz.bas.flow;

import net.bodz.bas.annotations.MetaClass;
import net.bodz.bas.collection.util.ClassLocal;
import net.bodz.bas.exceptions.IllegalUsageError;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;
import net.bodz.bas.lang.Nullables;

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
                    meta = (IPortMeta) Jdk7Reflect.newInstance(metaClass);
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
