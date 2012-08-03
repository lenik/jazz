package net.bodz.bas.util;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.meta.build.AppClassDoc;
import net.bodz.bas.model.IFactory;
import net.bodz.mda.xjdoc.conv.ClassDocs;

/**
 * Plugin Implementation
 */
public class PluginTypeEx {

    private final Class<? extends IPlugin> clazz;
    private final IFactory<IPlugin> factory;
    private final AppClassDoc classDoc;

    @SuppressWarnings("unchecked")
    public PluginTypeEx(IFactory<IPlugin> factory) {
        assert factory != null;
        this.factory = factory;
        Class<?> type = factory.getType();
        if (!IPlugin.class.isAssignableFrom(type))
            throw new IllegalArgumentException("wrong factory which create " + type);
        this.clazz = (Class<? extends IPlugin>) type;
        this.classDoc = ClassDocs.loadFromResource(clazz).decorate(AppClassDoc.class);
    }

    public PluginTypeEx(Class<? extends IPlugin> clazz) {
        this(new Factories.Ctor<IPlugin>(clazz));
    }

    public PluginTypeEx(Class<? extends IPlugin> clazz, Object outer) {
        this(new Factories.Ctor<IPlugin>(clazz, outer));
    }

    public PluginTypeEx(IPlugin staticInstance) {
        this(new IFactory.Static<IPlugin>(staticInstance));
    }

    public Class<? extends IPlugin> getType() {
        return clazz;
    }

    public String getDescription() {
        return classDoc.getTextBody();
    }

    public String getVersion() {
        return classDoc.getReleaseDescription().getVersion().toString();
    }

    public IPlugin newInstance(Object... args)
            throws CreateException {
        return factory.create(args);
    }

    @Override
    public String toString() {
        return String.format("Plugin(%s)", clazz.getName());
    }

}
