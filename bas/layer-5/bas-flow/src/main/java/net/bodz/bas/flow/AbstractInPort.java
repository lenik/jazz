package net.bodz.bas.flow;

import java.io.IOException;

import net.bodz.bas.c.type.ClassLocal;
import net.bodz.bas.c.type.ClassLocals;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.err.OutOfDomainException;
import net.bodz.bas.meta.stereo.MetaClass;
import net.bodz.bas.util.Nullables;

public abstract class AbstractInPort
        extends AbstractPort
        implements IInPort, IReceiverEx {

    private int index;

    public AbstractInPort(IUnit unit, int index) {
        super(unit);
        this.index = index;
    }

    private static ClassLocal<IPortMeta> metas = ClassLocals.createMap(//
            IPortMeta.class.getCanonicalName(), entryLoader);

    @Override
    public IPortMeta getInPortMeta() {
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
