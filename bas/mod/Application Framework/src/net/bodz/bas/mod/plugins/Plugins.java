package net.bodz.bas.mod.plugins;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;

import net.bodz.bas.mod.CreateException;
import net.bodz.bas.types.util.Comparators;
import net.bodz.bas.types.util.Types;

public class Plugins {

    protected TreeMap<Class<?>, PluginType<?>> types;

    public Plugins() {
        types = new TreeMap<Class<?>, PluginType<?>>(Comparators.TYPE_HIER);
    }

    public <T> boolean registerPluginType(PluginType<T> pluginType) {
        Class<T> pluginTypeClass = pluginType.getType();
        if (types.containsKey(pluginTypeClass))
            return false;
        types.put(pluginTypeClass, pluginType);
        return true;
    }

    public <T> boolean registerPluginType(String name, Class<T> pluginTypeClass) {
        PluginType<T> pluginType = new PluginType<T>(name, pluginTypeClass);
        return registerPluginType(pluginType);
    }

    @SuppressWarnings("unchecked")
    public <T> PluginType<T> getPluginType(Class<T> pluginTypeClass,
            boolean strict) {
        Entry<Class<?>, PluginType<?>> supersome = types
                .floorEntry(pluginTypeClass);
        if (supersome != null) {
            Class<?> supersomeClass = supersome.getKey();
            if (supersomeClass.isAssignableFrom(pluginTypeClass))
                return (PluginType<T>) supersome.getValue();
        }
        PluginType<T> pluginType = new PluginType<T>(pluginTypeClass);
        types.put(pluginTypeClass, pluginType);
        return pluginType;
    }

    public <T> PluginType<T> getPluginType(Class<T> pluginTypeClass) {
        return getPluginType(pluginTypeClass, false);
    }

    public PluginType<?> getPluginType(String pluginTypeName) {
        assert pluginTypeName != null;
        for (PluginType<?> type : types.values()) {
            if (pluginTypeName.equals(type.getName()))
                return type;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> void register(String pluginId, PluginClass<T> pluginClass) {
        Class<T> pluginTypeClass = (Class<T>) pluginClass.getType();
        PluginType<T> pluginType = getPluginType(pluginTypeClass);
        pluginType.register(pluginId, pluginClass);
    }

    public <T> void register(String pluginId, Class<T> pluginClass) {
        Class<T> pluginTypeClass = pluginClass;
        PluginType<T> pluginType = getPluginType(pluginTypeClass);
        pluginType.register(pluginId, pluginClass);
    }

    public <T> void register(String pluginId, Class<T> pluginClass, Object outer) {
        Class<T> pluginTypeClass = pluginClass;
        PluginType<T> pluginType = getPluginType(pluginTypeClass);
        pluginType.register(pluginId, pluginClass, outer);
    }

    @SuppressWarnings("unchecked")
    public <T> void register(String pluginId, T staticPlugin) {
        Class<T> pluginTypeClass = (Class<T>) staticPlugin.getClass();
        PluginType<T> pluginType = getPluginType(pluginTypeClass);
        pluginType.register(pluginId, staticPlugin);
    }

    PluginClass<?> find(Class<?> pluginTypeClass, String pluginId)
            throws PluginException {
        Entry<Class<?>, PluginType<?>>[] derivations = Types.findDerivations(
                types, pluginTypeClass);
        if (derivations == null)
            return null;
        List<PluginClass<?>> found = new ArrayList<PluginClass<?>>();
        for (Entry<Class<?>, PluginType<?>> e : derivations) {
            PluginType<?> pluginType = e.getValue();
            PluginClass<?> pc = pluginType.get(pluginId);
            if (pc != null)
                found.add(pc);
        }
        if (found.isEmpty())
            return null;
        if (found.size() > 1) {
            StringBuffer cands = null;
            for (PluginClass<?> pc : found) {
                if (cands == null)
                    new StringBuffer(found.size() * 30);
                else
                    cands.append('\n');
                cands.append(pc);
            }
            throw new PluginException("ambiguous plugin id: " + pluginId
                    + ", candidates: \n" + cands);
        }
        return found.get(0);
    }

    public PluginClass<?> getPluginClass(Class<?> pluginTypeClass,
            String pluginId) throws PluginException {
        return find(pluginTypeClass, pluginId);
    }

    @SuppressWarnings("unchecked")
    public <T> T load(Class<T> pluginTypeClass, String pluginId, Object... args)
            throws PluginException {
        PluginClass<T> pclass = (PluginClass<T>) find(pluginTypeClass, pluginId);
        if (pclass == null)
            return null;
        try {
            return pclass.newInstance(args);
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
