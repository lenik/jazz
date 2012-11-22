package net.bodz.bas.util;

import net.bodz.bas.cli.plugin.ICLIPlugin;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.model.IFactory;
import net.bodz.mda.xjdoc.conv.ClassDocs;
import net.bodz.mda.xjdoc.model1.ArtifactDoc;

/**
 * Plugin Implementation
 */
public class PluginTypeEx {

    private final Class<? extends ICLIPlugin> clazz;
    private final IFactory<ICLIPlugin> factory;
    private final ArtifactDoc artifactDoc;

    @SuppressWarnings("unchecked")
    public PluginTypeEx(IFactory<ICLIPlugin> factory) {
        assert factory != null;
        this.factory = factory;
        Class<?> type = factory.getType();
        if (!ICLIPlugin.class.isAssignableFrom(type))
            throw new IllegalArgumentException("wrong factory which create " + type);
        this.clazz = (Class<? extends ICLIPlugin>) type;
        this.artifactDoc = ClassDocs.loadFromResource(clazz).as(ArtifactDoc.class);
    }

    public PluginTypeEx(Class<? extends ICLIPlugin> clazz) {
        this(new Factories.Ctor<ICLIPlugin>(clazz));
    }

    public PluginTypeEx(Class<? extends ICLIPlugin> clazz, Object outer) {
        this(new Factories.Ctor<ICLIPlugin>(clazz, outer));
    }

    public PluginTypeEx(ICLIPlugin staticInstance) {
        this(new IFactory.Static<ICLIPlugin>(staticInstance));
    }

    public Class<? extends ICLIPlugin> getType() {
        return clazz;
    }

    public String getDescription() {
        return artifactDoc.getText().getHeadPar();
    }

    public String getVersion() {
        return artifactDoc.getReleaseDescription().getVersion().toString();
    }

    public ICLIPlugin newInstance(Object... args)
            throws CreateException {
        return factory.create(args);
    }

    @Override
    public String toString() {
        return String.format("Plugin(%s)", clazz.getName());
    }

}
