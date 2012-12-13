package net.bodz.bas.util;

import java.util.Map.Entry;

import net.bodz.bas.c.type.TypePoMap;
import net.bodz.bas.cli.plugin.ICLIPlugin;
import net.bodz.bas.err.CreateException;

public class Plugins {

    /**
     * base-type -> category
     */
    protected TypePoMap<PluginCategory> categories;

    public Plugins() {
        categories = new TypePoMap<PluginCategory>();
    }

    public boolean addCategory(PluginCategory category) {
        Class<?> baseType = category.getBaseType();
        if (categories.containsKey(baseType))
            return false;
        categories.put(baseType, category);
        return true;
    }

    public boolean addCategory(String name, Class<? extends ICLIPlugin> baseType) {
        PluginCategory category = new PluginCategory(name, baseType);
        return addCategory(category);
    }

    public PluginCategory getOrCreateCategory(Class<? extends ICLIPlugin> type, boolean strict) {
        PluginCategory pluginType;
        if (strict)
            pluginType = categories.get(type);
        else
            pluginType = categories.meet(type);
        if (pluginType == null) {
            pluginType = new PluginCategory(type);
            categories.put(type, pluginType);
        }
        return (PluginCategory) pluginType;
    }

    public PluginCategory getOrCreateCategory(Class<? extends ICLIPlugin> pluginTypeClass) {
        return getOrCreateCategory(pluginTypeClass, false);
    }

    public PluginCategory getCategory(String categoryName) {
        assert categoryName != null;
        for (PluginCategory category : categories.values()) {
            if (categoryName.equals(category.getName()))
                return category;
        }
        return null;
    }

    public void register(String pluginId, PluginTypeEx typeEx) {
        Class<? extends ICLIPlugin> type = typeEx.getType();
        PluginCategory category = getOrCreateCategory(type);
        category.register(pluginId, typeEx);
    }

    public void register(String pluginId, Class<? extends ICLIPlugin> type) {
        PluginCategory category = getOrCreateCategory(type);
        category.register(pluginId, type);
    }

    public void register(String pluginId, Class<? extends ICLIPlugin> type, Object outer) {
        PluginCategory category = getOrCreateCategory(type);
        category.register(pluginId, type, outer);
    }

    public <T> void register(String pluginId, ICLIPlugin staticInstance) {
        Class<? extends ICLIPlugin> type = staticInstance.getClass();
        PluginCategory category = getOrCreateCategory(type);
        category.register(pluginId, staticInstance);
    }

    PluginTypeEx find(Class<?> type, final String pluginId)
            throws PluginException {
        PluginTypeEx found = null;
        StringBuilder errmsg = null;
        for (Entry<Class<?>, PluginCategory> e : categories.joinEntries(type)) {
            PluginCategory category = e.getValue();
            PluginTypeEx typeEx = category.get(pluginId);
            if (typeEx != null) {
                if (found == null) {
                    found = typeEx;
                } else {
                    if (errmsg == null) {
                        errmsg = new StringBuilder();
                        errmsg.append("ambiguous plugin id: " + pluginId);
                        errmsg.append(", candidates: \n");
                        errmsg.append(found);
                    }
                    errmsg.append('\n');
                    errmsg.append(typeEx);
                }
            }
        }
        if (errmsg != null)
            throw new PluginException(errmsg.toString());
        return found;
    }

    // ???
    public PluginTypeEx getTypeEx(Class<?> type, String pluginId)
            throws PluginException {
        return find(type, pluginId);
    }

    public ICLIPlugin load(Class<? extends ICLIPlugin> type, String pluginId, Object... args)
            throws PluginException {
        PluginTypeEx typeEx = find(type, pluginId);
        if (typeEx == null)
            return null;
        try {
            return typeEx.newInstance(args);
        } catch (CreateException e) {
            throw new PluginException(e.getMessage(), e);
        }
    }

    // public <T> T load(Class<T> pluginTypeClass, String pluginId, String...
    // args)
    // throws PluginException {
    // return load(pluginTypeClass, pluginId, (Object[]) args);
    // }

}
