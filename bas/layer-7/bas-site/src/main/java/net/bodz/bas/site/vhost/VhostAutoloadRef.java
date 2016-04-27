package net.bodz.bas.site.vhost;

import java.lang.reflect.Constructor;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.repr.path.INoPathRef;
import net.bodz.bas.t.ref.Ref;

public abstract class VhostAutoloadRef<T>
        implements Ref<T>, INoPathRef {

    private final Class<T> objectType;
    private final Constructor<T> ctor_vhost;
    private String attributeName;

    public VhostAutoloadRef(Class<T> objectType) {
        if (objectType == null)
            throw new NullPointerException("objectType");
        this.objectType = objectType;
        try {
            this.ctor_vhost = objectType.getConstructor(IVirtualHost.class);
        } catch (ReflectiveOperationException e) {
            throw new IllegalUsageException(String.format("Can't construct a %s with virtual host.", objectType), e);
        }
        this.attributeName = objectType.getName();
    }

    @Override
    public Class<T> getValueType() {
        return objectType;
    }

    @Override
    public Object getTarget() {
        return get();
    }

    @Override
    public T get() {
        IVirtualHost vhost = CurrentVirtualHost.getVirtualHost();

        T obj = vhost.getAttribute(attributeName);
        if (obj == null) {
            try {
                obj = ctor_vhost.newInstance(vhost);
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
            vhost.setAttribute(attributeName, obj);
        }
        return obj;
    }

    @Override
    public void set(T value) {
        IVirtualHost vhost = CurrentVirtualHost.getVirtualHost();
        vhost.setAttribute(attributeName, value);
    }

    @Override
    public void remove() {
        IVirtualHost vhost = CurrentVirtualHost.getVirtualHost();
        vhost.removeAttribute(attributeName);
    }

}
