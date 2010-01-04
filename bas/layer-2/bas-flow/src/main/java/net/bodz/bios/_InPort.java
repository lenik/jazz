package net.bodz.bios;

import java.io.IOException;

import net.bodz.bas.arch.ClassLocal;
import net.bodz.bas.commons.annotations.MetaClass;
import net.bodz.bas.commons.exceptions.OutOfDomainException;
import net.bodz.bas.commons.util.Annotations;
import net.bodz.bas.commons.util.Types;

public abstract class _InPort extends _Port implements InPort, ReceiverEx {

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
            Class<?> metaClass = (Class<?>) Annotations.getValue(clazz, MetaClass.class);
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
    public int getInPortIndex() {
        return index;
    }

    @Override
    public abstract void recv(Object data) throws IOException;

    @Override
    public void recvNull() throws IOException {
        throw new NullPointerException("recvNull"); //$NON-NLS-1$
    }

    @Override
    public void recvUnknown(Object data) throws IOException {
        throw new OutOfDomainException("type", data.getClass()); //$NON-NLS-1$
    }

    @Override
    public void addSrc(OutPort srcPort) throws IOException {
    }

    @Override
    public void removeSrc(OutPort srcPort) throws IOException {
    }

}
