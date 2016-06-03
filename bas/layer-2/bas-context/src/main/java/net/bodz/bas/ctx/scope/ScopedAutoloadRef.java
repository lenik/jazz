package net.bodz.bas.ctx.scope;

import net.bodz.bas.t.ref.Ref;

/**
 * Focused on scope-cluster.
 */
public class ScopedAutoloadRef<T>
        implements Ref<T> {

    private final Class<T> objectType;
    private ScopedTypeInfo<T> info;

    /**
     * Used as bean name, attribute name, var name.
     */
    private String name;

    public ScopedAutoloadRef(Class<T> objectType) {
        if (objectType == null)
            throw new NullPointerException("objectType");
        this.objectType = objectType;
        this.info = new ScopedTypeInfo<>(objectType);
        this.name = objectType.getName();
    }

    public static <T> ScopedAutoloadRef<T> of(Class<T> objectType) {
        return new ScopedAutoloadRef<>(objectType);
    }

    @Override
    public Class<T> getValueType() {
        return objectType;
    }

    @Override
    public T get() {
        IScopeInstance scopeInstance = info.scopeTeller.tell();
        if (scopeInstance == null)
            throw new OutOfScopeException();
        return (T) scopeInstance.get(name);
    }

    public T getOrLoad() {
        IScopeInstance scopeInstance = info.scopeTeller.tell();
        if (scopeInstance == null)
            throw new OutOfScopeException();
        T obj = (T) scopeInstance.get(name);
        if (obj == null) {
            obj = _load();
            scopeInstance.set(name, obj);
        }
        return obj;
    }

    protected T _load() {
        return info.instantiate();
    }

    @Override
    public void set(T value) {
        IScopeInstance scopeInstance = info.scopeTeller.tell();
        if (scopeInstance == null)
            throw new OutOfScopeException();
        scopeInstance.set(name, value);
    }

    @Override
    public void remove() {
        IScopeInstance scopeInstance = info.scopeTeller.tell();
        if (scopeInstance != null) {
            scopeInstance.remove(name);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);

        return sb.toString();
    }

}
