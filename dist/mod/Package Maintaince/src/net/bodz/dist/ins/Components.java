package net.bodz.dist.ins;

import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;

import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TextMap.TreeTextMap;
import net.bodz.dist.nls.PackNLS;

/**
 * @test ComponentsTest
 */
public class Components extends TreeTextMap<IComponent> {

    private static final long serialVersionUID = -3086334384020221224L;

    public static Components collect(IComponent start) {
        Components components = new Components();
        collectChildren(components, start);
        return components;
    }

    static void collectChildren(TextMap<IComponent> map, IComponent start) {
        Collection<IComponent> children = start.getChildren();
        if (children == null)
            return;
        String startId = start.getId();
        String prefix;
        if (startId == null) {
            prefix = ""; //$NON-NLS-1$
            start.setId(prefix);
        } else
            prefix = startId + "."; //$NON-NLS-1$
        for (IComponent child : children) {
            String id = child.getId();
            if (id == null) {
                String name = child.getName();
                if (name == null)
                    id = searchUnusedKey(map, prefix, 1);
                else {
                    id = prefix + name;
                    if (map.containsKey(id))
                        id = searchUnusedKey(map, id + "_" + start, 1); //$NON-NLS-1$
                }
                child.setId(id);
            }
            map.put(id, child);
            collectChildren(map, child);
        }
    }

    static String searchUnusedKey(TextMap<?> map, String s, int startIndex) {
        String key = null;
        return key;
    }

    public void importRegistry(Map<String, Object> registry) {
        importRegistry(registry, false);
    }

    /**
     * @param strict
     *            error if registry contains any id of non-existing component
     * @throws NoSuchElementException
     */
    public void importRegistry(Map<String, Object> registry, boolean strict) {
        for (Map.Entry<String, Object> e : registry.entrySet()) {
            String id = e.getKey();
            Object data = e.getValue();
            IComponent component = get(id);
            if (component != null)
                component.setRegistryData(data);
            else if (strict) {
                throw new NoSuchElementException(String.format(PackNLS
                        .getString("Components.compNotDefined"), id)); //$NON-NLS-1$
            }
        }
    }

    public TextMap<Object> exportRegistry() {
        TreeTextMap<Object> registry = new TreeTextMap<Object>();
        for (Map.Entry<String, IComponent> e : entrySet()) {
            String id = e.getKey();
            IComponent component = e.getValue();
            Object data = component.getRegistryData();
            if (data != null)
                registry.put(id, data);
        }
        return registry;
    }

}
