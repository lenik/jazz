package net.bodz.bas.util;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.err.OutOfDomainException;
import net.bodz.mda.xjdoc.conv.ClassDocs;
import net.bodz.mda.xjdoc.model1.ArtifactDoc;

/**
 * Plugin Category
 */
public class PluginCategory {

    private final String categoryName;
    private final Class<? extends IPlugin> categoryBaseType;
    private final ArtifactDoc categoryDoc;

    protected Map<String, PluginTypeEx> registry;

    public PluginCategory(String name, Class<? extends IPlugin> baseType) {
        assert baseType != null;
        this.categoryName = name;
        this.categoryBaseType = baseType;
        this.categoryDoc = ClassDocs.loadFromResource(baseType).as(ArtifactDoc.class);
        registry = new HashMap<String, PluginTypeEx>();
    }

    public PluginCategory(Class<? extends IPlugin> baseType) {
        this(baseType.getSimpleName(), baseType);
    }

    public String getName() {
        return categoryName;
    }

    public Class<? extends IPlugin> getBaseType() {
        return categoryBaseType;
    }

    public String getDescription() {
        return categoryDoc.getTextHeader();
    }

    public Map<String, PluginTypeEx> getRegistry() {
        return registry;
    }

    public int size() {
        return registry.size();
    }

    public PluginTypeEx get(String pluginId) {
        return registry.get(pluginId);
    }

    public void register(String pluginId, PluginTypeEx typeEx) {
        assert typeEx != null;
        if (registry.containsKey(pluginId))
            throw new IllegalArgumentException("Plugin is already registered: " + pluginId);
        if (!categoryBaseType.isAssignableFrom(typeEx.getType()))
            throw new OutOfDomainException("wrong category");
        registry.put(pluginId, typeEx);
    }

    public void register(String pluginId, Class<? extends IPlugin> type) {
        register(pluginId, new PluginTypeEx(type));
    }

    public void register(String pluginId, Class<? extends IPlugin> type, Object outer) {
        register(pluginId, new PluginTypeEx(type, outer));
    }

    public void register(String pluginId, IPlugin pluginInstance) {
        register(pluginId, new PluginTypeEx(pluginInstance));
    }

    public void unregister(String pluginId) {
        registry.remove(pluginId);
    }

}
