package net.bodz.bas.flow;

import java.io.IOException;

import net.bodz.bas.annotation.util.InheritableAnnotation;
import net.bodz.bas.annotations.MetaClass;
import net.bodz.bas.collection.util.ClassLocal;
import net.bodz.bas.exceptions.IllegalUsageError;
import net.bodz.bas.exceptions.OutOfDomainException;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;

public abstract class _InPort
        extends _Port
        implements InPort, ReceiverEx {

    private int index;

    public _InPort(Unit unit, int index) {
        super(unit);
        this.index = index;
    }

    private static ClassLocal<PortMeta> metas;
    static {
        metas = new ClassLocal<PortMeta>();
    }

    @Override
    public PortMeta getInPortMeta() {
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
    public void addSrc(OutPort srcPort)
            throws IOException {
    }

    @Override
    public void removeSrc(OutPort srcPort)
            throws IOException {
    }

}
