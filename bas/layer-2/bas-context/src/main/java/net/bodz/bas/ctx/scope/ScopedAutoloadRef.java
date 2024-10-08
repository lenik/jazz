package net.bodz.bas.ctx.scope;

import net.bodz.bas.err.LoadException;
import net.bodz.bas.t.ref.TypedRef;

/**
 * Focused on scope-cluster.
 */
public class ScopedAutoloadRef<T>
        implements TypedRef<T> {

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
        this.info = new ScopedTypeInfo<T>(objectType);
        this.name = objectType.getName();
    }

    public static <T> ScopedAutoloadRef<T> of(Class<T> objectType) {
        return new ScopedAutoloadRef<T>(objectType);
    }

    @Override
    public Class<T> getValueType() {
        return objectType;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get() {
        IScopeInstance scopeInstance = info.scopeTeller.tell();
        if (scopeInstance == null)
            throw new OutOfScopeException();
        return (T) scopeInstance.get(name);
    }

    /**
     * @throws LoadException
     */
    public T getOrLoad()
            throws LoadException {
        IScopeInstance scopeInstance = info.scopeTeller.tell();
        if (scopeInstance == null) {
            String id = info.scopeTeller.tellId();
            throw new OutOfScopeException("scope: " + id);
        }
        @SuppressWarnings("unchecked")
        T obj = (T) scopeInstance.get(name);
        if (obj == null) {
            obj = _load();
            scopeInstance.set(name, obj);
        }
        return obj;
    }

    /**
     * @throws LoadException
     */
    protected T _load()
            throws LoadException {
        try {
            return info.instantiate();
        } catch (ReflectiveOperationException e) {
            throw new LoadException(e.getMessage(), e);
        }
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
