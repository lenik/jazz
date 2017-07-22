package net.bodz.bas.ctx.scope.experimental;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.ctx.scope.IScopeInstance;
import net.bodz.bas.ctx.scope.ScopedTypeInfo;
import net.bodz.bas.err.LoadException;
import net.bodz.bas.rtx.AbstractQueryable;

public class BeanFactory
        extends AbstractQueryable
        implements IBeanProvider {

    @Override
    public <spec_t> spec_t query(Class<spec_t> clazz) {
        spec_t bean = get(clazz);
        return bean;
    }

    @Override
    public final <T> T get(Class<T> objectType) {
        String name = objectType.getSimpleName();
        name = Strings.lcfirst(name);
        return getOrLoad(name, objectType);
    }

    public <T> T getOnly(String name, Class<T> objectType) {
        ScopedTypeInfo<T> info = new ScopedTypeInfo<T>(objectType);
        IScopeInstance scopeInstance = info.scopeTeller.tell();
        return (T) scopeInstance.get(name);
    }

    public <T> T getOrLoad(String name, Class<T> objectType)
            throws LoadException {
        ScopedTypeInfo<T> info = new ScopedTypeInfo<T>(objectType);
        IScopeInstance scopeInstance = info.scopeTeller.tell();
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
