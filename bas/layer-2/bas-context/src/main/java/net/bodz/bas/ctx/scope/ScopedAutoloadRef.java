package net.bodz.bas.ctx.scope;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

import net.bodz.bas.c.type.SingletonUtil;
import net.bodz.bas.t.ref.Ref;

/**
 * Focused on scope-cluster.
 */
public class ScopedAutoloadRef<T>
        implements Ref<T> {

    private final Class<T> objectType;

    /**
     * Used as bean name, attribute name, var name.
     */
    private String name;

    private Constructor<T> ctorWithScopeId;
    private IScopeTeller scopeTeller;

    public ScopedAutoloadRef(Class<T> objectType) {
        if (objectType == null)
            throw new NullPointerException("objectType");
        this.objectType = objectType;
        this.name = objectType.getName();

        Annotation aFooScope = _Scope.fn.getScopeAnnotation(objectType);
        Class<?> aFooScopeCass = aFooScope == null ? null : aFooScope.annotationType();

        ScopeTeller aScopeTeller = aFooScopeCass.getAnnotation(ScopeTeller.class);
        Class<? extends IScopeTeller> scopeTellerClass = aScopeTeller == null ? null : aScopeTeller.value();
        if (scopeTellerClass != null) {
            scopeTeller = SingletonUtil.instantiateCached(scopeTellerClass);
        } else
            scopeTeller = IScopeTeller.STATIC;

        ScopeIdClass aScopeIdClass = aFooScopeCass.getAnnotation(ScopeIdClass.class);
        if (aScopeIdClass != null) {
            try {
                this.ctorWithScopeId = objectType.getConstructor(aScopeIdClass.value());
            } catch (NoSuchMethodError | NoSuchMethodException e) {
            }
        }
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
        IScopeInstance scopeInstance = scopeTeller.tell();
        if (scopeInstance == null)
            throw new OutOfScopeException();
        return (T) scopeInstance.get(name);
    }

    public T getOrLoad() {
        IScopeInstance scopeInstance = scopeTeller.tell();
        if (scopeInstance == null)
            throw new OutOfScopeException();

        T obj = (T) scopeInstance.get(name);

        if (obj == null) {
            obj = _load(scopeInstance.getIdentity());
            scopeInstance.set(name, obj);
        }
        return obj;
    }

    protected T _load(Object scopeIdentity) {
        try {
            if (ctorWithScopeId != null)
                return ctorWithScopeId.newInstance(scopeIdentity);
            else
                return objectType.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public void set(T value) {
        IScopeInstance scopeInstance = scopeTeller.tell();
        if (scopeInstance == null)
            throw new OutOfScopeException();
        scopeInstance.set(name, value);
    }

    @Override
    public void remove() {
        IScopeInstance scopeInstance = scopeTeller.tell();
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
