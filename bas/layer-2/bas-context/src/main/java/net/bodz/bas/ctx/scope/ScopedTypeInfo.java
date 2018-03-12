package net.bodz.bas.ctx.scope;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

import net.bodz.bas.ctx.ScopeType;
import net.bodz.bas.ctx.scope.experimental.BeanFactory;
import net.bodz.bas.ctx.scope.experimental.IBeanProvider;
import net.bodz.bas.err.IllegalUsageException;

public class ScopedTypeInfo<T> {

    public Class<T> objectType;
    public Annotation aFooScope;
    public IScopeTeller scopeTeller;
    public Constructor<T> ctorWithProvider;
    public Constructor<T> ctorWithScopeId;

    public ScopedTypeInfo(Class<T> objectType) {
        this.objectType = objectType;
        aFooScope = ScopeType.fn.getConcreteAnnotation(objectType);

        if (aFooScope == null) {
            // prototype by default
            scopeTeller = LocalScopeTeller.INSTANCE;
        } else {
            scopeTeller = ScopeTeller.fn.fromScopeAnnotationType(aFooScope.annotationType());
        }

        try {
            ctorWithProvider = objectType.getConstructor(IBeanProvider.class);
        } catch (ReflectiveOperationException e) {
        }

        if (ctorWithProvider == null)
            if (aFooScope != null) {
                ScopeIdClass aScopeIdClass = aFooScope.annotationType().getAnnotation(ScopeIdClass.class);
                if (aScopeIdClass != null)
                    try {
                        ctorWithScopeId = objectType.getConstructor(aScopeIdClass.value());
                    } catch (NoSuchMethodError | NoSuchMethodException e) {
                    }
            }
    }

    public T instantiate()
            throws ReflectiveOperationException {
        IScopeInstance scopeInstance = scopeTeller.tell();
        return _instantiate(scopeInstance);
    }

    public T _instantiate(IScopeInstance scopeInstance)
            throws ReflectiveOperationException {
        if (ctorWithProvider != null)
            return ctorWithProvider.newInstance(BeanFactory.getInstance());
        if (ctorWithScopeId != null)
            return ctorWithScopeId.newInstance(scopeInstance.getIdentity());
        try {
            // if (debug)
            objectType.getConstructor();
            return objectType.newInstance();
        } catch (NoSuchMethodException e) {
            throw new IllegalUsageException("No default constructor on " + objectType);
        }
    }

}
