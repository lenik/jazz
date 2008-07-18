package net.bodz.bas.mod.plugins;

import net.bodz.bas.annotations.ClassInfo;
import net.bodz.bas.mod.CreateException;
import net.bodz.bas.mod.Factory;

/**
 * Plugin Implementation
 */
public class PluginClass<T> {

    private final Class<? extends T> clazz;
    private final Factory<T>         factory;
    private final ClassInfo          info;

    public PluginClass(Factory<T> factory) {
        assert factory != null;
        this.factory = factory;
        this.clazz = factory.getType();
        this.info = ClassInfo.get(clazz);
    }

    public PluginClass(Class<? extends T> clazz) {
        this(new Factory.Ctor<T>(clazz));
    }

    public PluginClass(Class<? extends T> clazz, Object outer) {
        this(new Factory.Ctor<T>(clazz, outer));
    }

    public PluginClass(T _static) {
        this(new Factory.Static<T>(_static));
    }

    public Class<? extends T> getType() {
        return clazz;
    }

    public String getDescription() {
        return info.getDoc();
    }

    public String getVersion() {
        return info.getVersionString();
    }

    public T newInstance(Object... args) throws CreateException {
        return factory.create(args);
    }

    @Override
    public String toString() {
        return "Plugin(" + clazz.getName() + ")";
    }

}
