package net.bodz.bas.mod.plugins;

import net.bodz.bas.a.ClassInfo;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.mod.Factory;
import net.bodz.bas.nls.AppNLS;

/**
 * Plugin Implementation
 */
public class PluginTypeEx {

    private final Class<? extends Plugin> clazz;
    private final Factory<Plugin>         factory;
    private final ClassInfo               info;

    @SuppressWarnings("unchecked")
    public PluginTypeEx(Factory<Plugin> factory) {
        assert factory != null;
        this.factory = factory;
        Class<?> type = factory.getType();
        if (!Plugin.class.isAssignableFrom(type))
            throw new IllegalArgumentException(AppNLS
                    .getString("PluginTypeEx.wrongFactoryWhichCreate") //$NON-NLS-1$
                    + type);
        this.clazz = (Class<? extends Plugin>) type;
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
        return (Plugin) factory.create(args);
    }

    @Override
    public String toString() {
        return String.format("Plugin(%s)", clazz.getName());
    }

}
