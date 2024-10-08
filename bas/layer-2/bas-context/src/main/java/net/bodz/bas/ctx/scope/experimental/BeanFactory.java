package net.bodz.bas.ctx.scope.experimental;

import java.beans.Introspector;

import net.bodz.bas.ctx.scope.IScopeInstance;
import net.bodz.bas.ctx.scope.ScopedTypeInfo;
import net.bodz.bas.err.LoadException;
import net.bodz.bas.rtx.IQueryable;

public class BeanFactory
        implements
            IBeanProvider,
            IQueryable {

    @Override
    public <spec_t> spec_t query(Class<spec_t> clazz) {
        spec_t bean = get(clazz);
        return bean;
    }

    @Override
    public final <T> T get(Class<T> objectType) {
        String name = objectType.getSimpleName();
        name = Introspector.decapitalize(name);
        return getOrLoad(name, objectType);
    }

    @SuppressWarnings("unchecked")
    public <T> T getOnly(String name, Class<T> objectType) {
        ScopedTypeInfo<T> info = new ScopedTypeInfo<T>(objectType);
        IScopeInstance scopeInstance = info.scopeTeller.tell();
        return (T) scopeInstance.get(name);
    }

    public <T> T getOrLoad(String name, Class<T> objectType)
            throws LoadException {
        ScopedTypeInfo<T> info = new ScopedTypeInfo<T>(objectType);
        IScopeInstance scopeInstance = info.scopeTeller.tell();
        @SuppressWarnings("unchecked")
        T obj = (T) scopeInstance.get(name);
        if (obj == null) {
            try {
                obj = info.instantiate();
            } catch (ReflectiveOperationException e) {
                throw new LoadException(e.getMessage(), e);
            }
            scopeInstance.set(name, obj);
        }
        return obj;
    }

    private static BeanFactory instance = new BeanFactory();

    public static BeanFactory getInstance() {
        return instance;
    }

}
