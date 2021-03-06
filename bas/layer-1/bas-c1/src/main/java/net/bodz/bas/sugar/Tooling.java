package net.bodz.bas.sugar;

import java.lang.reflect.Constructor;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.t.factory.IFactory;

public final class Tooling
        implements IToChain {

    final Object hostObject;

    public Tooling(Object hostObject) {
        if (hostObject == null)
            throw new NullPointerException("hostObject");
        this.hostObject = hostObject;
    }

    @Override
    public Tooling clone() {
        return this; // new Tooling(hostObject);
    }

    public static Tooling on(Object hostObject) {
        return new Tooling(hostObject);
    }

    public <F extends IFactory<? extends T>, T> T _for(F factory)
            throws ToolsInstantiationException {
        if (factory == null)
            throw new NullPointerException("factory");
        T tools;
        try {
            tools = factory.create();
        } catch (CreateException e) {
            throw new ToolsInstantiationException(e.getMessage(), e);
        }
        return tools;
    }

    public <T> T getWrapper(Class<T> wrapperClass) {
        Class<?> hostClass = hostObject.getClass();
        SimpleConstructorMap ctorMap = SimpleConstructorMap.getInstance(wrapperClass);
        Constructor<?> ctor = ctorMap.getOrLoad(hostClass);
        if (ctor == null)
            throw new IllegalUsageException(String.format( //
                    "No suitable constructor for %s found in the wrapper class %s.", //
                    hostClass.getName(), wrapperClass.getName()));
        try {
            @SuppressWarnings("unchecked")
            T instance = (T) ctor.newInstance(hostObject);
            return instance;
        } catch (ReflectiveOperationException e) {
            throw new IllegalUsageException("Exception thrown from the wrapper constructor", e);
        }
    }

    public <T> T to(Class<T> clazz) {
        return getWrapper(clazz);
    }

}