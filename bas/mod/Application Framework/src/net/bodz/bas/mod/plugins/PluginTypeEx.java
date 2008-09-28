package net.bodz.bas.mod.plugins;

import net.bodz.bas.a.ClassInfo;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.mod.Factory;

/**
 * Plugin Implementation
 */
public class PluginTypeEx {

    private final Class<? extends Plugin> clazz;
    private final Factory                 factory;
    private final ClassInfo               info;

    @SuppressWarnings("unchecked")
    public PluginTypeEx(Factory factory) {
        assert factory != null;
        this.factory = factory;
        Class<?> type = factory.getType();
        if (!Plugin.class.isAssignableFrom(type))
            throw new IllegalArgumentException("wrong factory which create "
                    + type);
        this.clazz = (Class<? extends Plugin>) type;
        this.info = ClassInfo.get(clazz);
    }

    public PluginTypeEx(Class<? extends Plugin> clazz) {
        this(new Factory.Ctor(clazz));
    }

    public PluginTypeEx(Class<? extends Plugin> clazz, Object outer) {
        this(new Factory.Ctor(clazz, outer));
    }

    public PluginTypeEx(Plugin staticInstance) {
        this(new Factory.Static(staticInstance));
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
        return (Plugin) factory.create(args);
    }

    @Override
    public String toString() {
        return "Plugin(" + clazz.getName() + ")";
    }

}
