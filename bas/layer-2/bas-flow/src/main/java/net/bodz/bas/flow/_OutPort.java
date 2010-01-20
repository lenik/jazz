package net.bodz.bas.flow;

import net.bodz.bas.annotation.util.InheritableAnnotation;
import net.bodz.bas.annotations.MetaClass;
import net.bodz.bas.collection.util.ClassLocal;
import net.bodz.bas.exceptions.IllegalUsageError;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;

public abstract class _OutPort
        extends _Port
        implements OutPort {

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
                try {
                    meta = (PortMeta) Jdk7Reflect.newInstance(metaClass);
                } catch (ReflectiveOperationException e) {
                    throw new IllegalUsageError("Can't create instance for MetaClass: " + metaClass, e);
                }
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
