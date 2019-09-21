package net.bodz.bas.t.factory;

import java.lang.reflect.Constructor;

import net.bodz.bas.err.CreateException;

/**
 * New instance from class name.
 */
public class ScriptingInstantiator
        extends AbstractFactory<Object> {

    private final ClassLoader loader;
    private final String name;

    public ScriptingInstantiator(ClassLoader loader, String name) {
        if (loader == null)
            throw new NullPointerException("classLoader");
        if (name == null)
            throw new NullPointerException("className");
        this.loader = loader;
        this.name = name;
    }

    @Override
    public Class<? extends Object> getType() {
        return Object.class;
    }

    @Override
    public Object _create(Class<?>[] argTypes, Object... args)
            throws CreateException {
        try {
            Class<?> clazz = Class.forName(name, true, loader);
            if (argTypes == null)
                return clazz.newInstance();
            else {
                Constructor<?> ctor = clazz.getConstructor(argTypes);
                return ctor.newInstance(args);
            }
        } catch (ClassNotFoundException e) {
            throw new CreateException(e);
        } catch (Exception e) {
            throw new CreateException(e);
        }
    }

}
