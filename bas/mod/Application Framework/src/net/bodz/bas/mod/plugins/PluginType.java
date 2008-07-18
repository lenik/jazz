package net.bodz.bas.mod.plugins;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.annotations.ClassInfo;

/**
 * Plugin Prototype
 */
public class PluginType<T> {

    private final String                  name;
    private final Class<T>                type;
    private final ClassInfo               info;

    protected Map<String, PluginClass<T>> registry;

    public PluginType(String name, Class<T> type) {
        assert type != null;
        this.name = name;
        this.type = type;
        this.info = ClassInfo.get(type);
        registry = new HashMap<String, PluginClass<T>>();
    }

    public PluginType(Class<T> type) {
        this(type.getSimpleName(), type);
    }

    public String getName() {
        return name;
    }

    public Class<T> getType() {
        return type;
    }

    public String getDescription() {
        return info.getDoc();
    }

    public Map<String, PluginClass<T>> getRegistry() {
        return registry;
    }

    public int size() {
        return registry.size();
    }

    public PluginClass<T> get(String pluginId) {
        return registry.get(pluginId);
    }

    public void register(String pluginId, PluginClass<T> pluginClass) {
        assert pluginClass != null;
        if (registry.containsKey(pluginId))
            throw new IllegalArgumentException("plugin with id=" + pluginId
                    + " already registered");
        registry.put(pluginId, pluginClass);
    }

    public void register(String pluginId, Class<T> plugin) {
        register(pluginId, new PluginClass<T>(plugin));
    }

    public void register(String pluginId, Class<T> plugin, Object outer) {
        register(pluginId, new PluginClass<T>(plugin, outer));
    }

    public void register(String pluginId, T staticPlugin) {
        register(pluginId, new PluginClass<T>(staticPlugin));
    }

    public void unregister(String pluginId) {
        registry.remove(pluginId);
    }

}
