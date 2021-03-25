package net.bodz.bas.ctx.scope;

import net.bodz.bas.err.LazyLoadException;

public class BeansOfScope {

    IScopeInstance scopeInstance;

    public BeansOfScope(IScopeInstance scopeInstance) {
        this.scopeInstance = scopeInstance;
    }

    public boolean contains(String name) {
        return scopeInstance.contains(name);
    }

    public Object get(String name) {
        return scopeInstance.get(name);
    }

    public void set(String name, Object value) {
        scopeInstance.set(name, value);
    }

    public void remove(String name) {
        scopeInstance.remove(name);
    }

    public String defaultName(Class<?> clazz) {
        return clazz.getName();
    }

    public <T> T getOrLoad(Class<T> clazz) {
        String name = defaultName(clazz);
        @SuppressWarnings("unchecked")
        T obj = (T) scopeInstance.get(name);
        if (obj == null) {
            // scopeInstance.getIdentity();
            // ScopeIdClass.fn.from();
            try {
                obj = clazz.newInstance();
            } catch (ReflectiveOperationException e) {
                throw new LazyLoadException(e.getMessage(), e);
            }
            scopeInstance.set(name, obj);
        }
        return obj;
    }

    public static BeansOfScope echo(Object obj) {
        IScopeInstance scopeInstance = IScopeInstance.fn.echo(obj);
        return new BeansOfScope(scopeInstance);
    }

}
