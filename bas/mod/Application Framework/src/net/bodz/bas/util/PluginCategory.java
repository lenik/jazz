package net.bodz.bas.util;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.a.ClassInfo;
import net.bodz.bas.lang.err.OutOfDomainException;
import net.bodz.bas.nls.AppNLS;

/**
 * Plugin Category
 */
public class PluginCategory {

    private final String                  categoryName;
    private final Class<? extends Plugin> categoryBaseType;
    private final ClassInfo               categoryTypeInfo;

    protected Map<String, PluginTypeEx>   registry;

    public PluginCategory(String name, Class<? extends Plugin> baseType) {
        assert baseType != null;
        this.categoryName = name;
        this.categoryBaseType = baseType;
        this.categoryTypeInfo = ClassInfo.get(baseType);
        registry = new HashMap<String, PluginTypeEx>();
    }

    public PluginCategory(Class<? extends Plugin> baseType) {
        this(baseType.getSimpleName(), baseType);
    }

    public String getName() {
        return categoryName;
    }

    public Class<? extends Plugin> getBaseType() {
        return categoryBaseType;
    }

    public String getDescription() {
        return categoryTypeInfo.getDoc();
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
            throw new IllegalArgumentException(String.format(
                    AppNLS.getString("PluginCategory.pluginAlreadyRegistered_s"), pluginId)); //$NON-NLS-1$
        if (!categoryBaseType.isAssignableFrom(typeEx.getType()))
            throw new OutOfDomainException(AppNLS
                    .getString("PluginCategory.wrongCategory")); //$NON-NLS-1$
        registry.put(pluginId, typeEx);
    }

    public void register(String pluginId, Class<? extends Plugin> type) {
        register(pluginId, new PluginTypeEx(type));
    }

    public void register(String pluginId, Class<? extends Plugin> type,
            Object outer) {
        register(pluginId, new PluginTypeEx(type, outer));
    }

    public void register(String pluginId, Plugin pluginInstance) {
        register(pluginId, new PluginTypeEx(pluginInstance));
    }

    public void unregister(String pluginId) {
        registry.remove(pluginId);
    }

}
