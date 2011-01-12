package net.bodz.bas.util;

import java.util.Map.Entry;

import net.bodz.bas.collection.preorder.TypeHierMap;
import net.bodz.bas.util.exception.CreateException;

public class Plugins {

    /**
     * base-type -> category
     */
    protected TypeHierMap<PluginCategory> categories;

    public Plugins() {
        categories = new TypeHierMap<PluginCategory>();
    }

    public boolean registerCategory(PluginCategory category) {
        Class<?> baseType = category.getBaseType();
        if (categories.containsKey(baseType))
            return false;
        categories.put(baseType, category);
        return true;
    }

    public boolean registerCategory(String name, Class<? extends Plugin> baseType) {
        PluginCategory category = new PluginCategory(name, baseType);
        return registerCategory(category);
    }

    public PluginCategory getOrCreateCategory(Class<? extends Plugin> type, boolean strict) {
        PluginCategory pluginType;
        if (strict)
            pluginType = categories.get(type);
        else
            pluginType = categories.floor(type);
        if (pluginType == null) {
            pluginType = new PluginCategory(type);
            categories.put(type, pluginType);
        }
        return (PluginCategory) pluginType;
    }

    public PluginCategory getOrCreateCategory(Class<? extends Plugin> pluginTypeClass) {
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
        Class<? extends Plugin> type = typeEx.getType();
        PluginCategory category = getOrCreateCategory(type);
        category.register(pluginId, typeEx);
    }

    public void register(String pluginId, Class<? extends Plugin> type) {
        PluginCategory category = getOrCreateCategory(type);
        category.register(pluginId, type);
    }

    public void register(String pluginId, Class<? extends Plugin> type, Object outer) {
        PluginCategory category = getOrCreateCategory(type);
        category.register(pluginId, type, outer);
    }

    public <T> void register(String pluginId, Plugin staticInstance) {
        Class<? extends Plugin> type = staticInstance.getClass();
        PluginCategory category = getOrCreateCategory(type);
        category.register(pluginId, staticInstance);
    }

    PluginTypeEx find(Class<?> type, final String pluginId) throws PluginException {
        PluginTypeEx found = null;
        StringBuffer errmsg = null;
        for (Entry<Class<?>, PluginCategory> e : categories.ceilingEntries(type)) {
            PluginCategory category = e.getValue();
            PluginTypeEx typeEx = category.get(pluginId);
            if (typeEx != null) {
                if (found == null) {
                    found = typeEx;
                } else {
                    if (errmsg == null) {
                        errmsg = new StringBuffer();
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
    public PluginTypeEx getTypeEx(Class<?> type, String pluginId) throws PluginException {
        return find(type, pluginId);
    }

    public Plugin load(Class<? extends Plugin> type, String pluginId, Object... args) throws PluginException {
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
