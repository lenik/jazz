package net.bodz.bas.mod.plugins;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import net.bodz.bas.lang.Predicate;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.types.TypeHierMap;

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

    public boolean registerCategory(String name,
            Class<? extends Plugin> baseType) {
        PluginCategory category = new PluginCategory(name, baseType);
        return registerCategory(category);
    }

    public PluginCategory getOrCreateCategory(Class<? extends Plugin> type,
            boolean strict) {
        PluginCategory pluginType;
        if (strict)
            pluginType = categories.get(type);
        else
            pluginType = categories.getParent(type);
        if (pluginType == null) {
            pluginType = new PluginCategory(type);
            categories.put(type, pluginType);
        }
        return (PluginCategory) pluginType;
    }

    public PluginCategory getOrCreateCategory(
            Class<? extends Plugin> pluginTypeClass) {
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

    public void register(String pluginId, Class<? extends Plugin> type,
            Object outer) {
        PluginCategory category = getOrCreateCategory(type);
        category.register(pluginId, type, outer);
    }

    public <T> void register(String pluginId, Plugin staticInstance) {
        Class<? extends Plugin> type = staticInstance.getClass();
        PluginCategory category = getOrCreateCategory(type);
        category.register(pluginId, staticInstance);
    }

    PluginTypeEx find(Class<?> type, final String pluginId)
            throws PluginException {
        final List<PluginTypeEx> found = new ArrayList<PluginTypeEx>();
        categories.findChildren(type,
                new Predicate<Entry<Class<?>, PluginCategory>>() {
                    @Override
                    public boolean eval(Entry<Class<?>, PluginCategory> e) {
                        PluginCategory category = e.getValue();
                        PluginTypeEx typeEx = category.get(pluginId);
                        if (typeEx != null)
                            found.add(typeEx);
                        return true;
                    }
                });
        if (found.isEmpty())
            return null;
        if (found.size() > 1) {
            StringBuffer cands = null;
            for (PluginTypeEx typeEx : found) {
                if (cands == null)
                    new StringBuffer(found.size() * 30);
                else
                    cands.append('\n');
                cands.append(typeEx);
            }
            throw new PluginException("ambiguous plugin id: " + pluginId
                    + ", candidates: \n" + cands);
        }
        return found.get(0);
    }

    // ???
    public PluginTypeEx getTypeEx(Class<?> type, String pluginId)
            throws PluginException {
        return find(type, pluginId);
    }

    public Plugin load(Class<? extends Plugin> type, String pluginId,
            Object... args) throws PluginException {
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
