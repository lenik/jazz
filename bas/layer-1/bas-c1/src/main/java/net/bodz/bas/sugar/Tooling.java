package net.bodz.bas.sugar;

import java.lang.reflect.Constructor;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.model.IFactory;

public final class Tooling {

    final Object hostObject;

    public Tooling(Object hostObject) {
        if (hostObject == null)
            throw new NullPointerException("hostObject");
        this.hostObject = hostObject;
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
        Constructor<?> ctor = ctorMap.load(hostClass);
        if (ctor == null)
            throw new IllegalUsageException(String.format( //
                    "No suitable constructor for %s found in the wrapper class %s.", //
                    hostClass.getName(), wrapperClass.getName()));
        try {
            T instance = (T) ctor.newInstance(hostObject);
            return instance;
        } catch (ReflectiveOperationException e) {
            throw new IllegalUsageException("Exception thrown from the wrapper constructor", e);
        }
    }

    public <T> T _for(Class<T> toolsType) {
        return getWrapper(toolsType);
    }

}