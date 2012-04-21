package net.bodz.bas.util;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.meta.build.ClassInfo;
import net.bodz.bas.mode.fn.IFactory;

/**
 * Plugin Implementation
 */
public class PluginTypeEx {

    private final Class<? extends Plugin> clazz;
    private final IFactory<Plugin> factory;
    private final ClassInfo info;

    @SuppressWarnings("unchecked")
    public PluginTypeEx(IFactory<Plugin> factory) {
        assert factory != null;
        this.factory = factory;
        Class<?> type = factory.getType();
        if (!Plugin.class.isAssignableFrom(type))
            throw new IllegalArgumentException("wrong factory which create " + type);
        this.clazz = (Class<? extends Plugin>) type;
        this.info = ClassInfo.get(clazz);
    }

    public PluginTypeEx(Class<? extends Plugin> clazz) {
        this(new Factories.Ctor<Plugin>(clazz));
    }

    public PluginTypeEx(Class<? extends Plugin> clazz, Object outer) {
        this(new Factories.Ctor<Plugin>(clazz, outer));
    }

    public PluginTypeEx(Plugin staticInstance) {
        this(new IFactory.Static<Plugin>(staticInstance));
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

    public Plugin newInstance(Object... args)
            throws CreateException {
        return (Plugin) factory.create(args);
    }

    @Override
    public String toString() {
        return String.format("Plugin(%s)", clazz.getName());
    }

}
