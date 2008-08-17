package net.bodz.bas.mod.plugins;

import net.bodz.bas.annotations.ClassInfo;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.mod.Factory;

/**
 * Plugin Implementation
 */
public class PluginTypeEx {

    private final Class<? extends Plugin> clazz;
    private final Factory<Plugin>         factory;
    private final ClassInfo               info;

    public PluginTypeEx(Factory<Plugin> factory) {
        assert factory != null;
        this.factory = factory;
        this.clazz = factory.getType();
        this.info = ClassInfo.get(clazz);
    }

    public PluginTypeEx(Class<? extends Plugin> clazz) {
        this(new Factory.Ctor<Plugin>(clazz));
    }

    public PluginTypeEx(Class<? extends Plugin> clazz, Object outer) {
        this(new Factory.Ctor<Plugin>(clazz, outer));
    }

    public PluginTypeEx(Plugin staticInstance) {
        this(new Factory.Static<Plugin>(staticInstance));
    }

    public Class<? extends Plugin> getType() {
        return clazz;
    }

    public String getDescription() {
        return info.getDoc();
    }

    public String getVersion() {
        return info.getVersionString();
    }

    public Plugin newInstance(Object... args) throws CreateException {
        return factory.create(args);
    }

    @Override
    public String toString() {
        return "Plugin(" + clazz.getName() + ")";
    }

}
