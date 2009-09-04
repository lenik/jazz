package net.bodz.dist.ins;

import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;

import net.bodz.bas.types.HashTextMap;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TreeTextMap;
import net.bodz.dist.ins.nls.PackNLS;

/**
 * @test {@link ComponentsTest}
 */
public class Components extends TreeTextMap<Component> {

    private static final long serialVersionUID = -3086334384020221224L;

    private TextMap<String>   nextMap;

    public Components() {
        nextMap = new HashTextMap<String>();
    }

    public Map<String, String> getNextMap() {
        return nextMap;
    }

    public void importRegistry(Map<String, Object> registry) {
        importRegistry(registry, false);
    }

    /**
     * Import component data from registry map.
     * 
     * @param strict
     *            error if registry contains any id of non-existing component
     * @throws NoSuchElementException
     */
    public void importRegistry(Map<String, Object> registry, boolean strict) {
        for (Map.Entry<String, Object> e : registry.entrySet()) {
            String id = e.getKey();
            Object data = e.getValue();
            Component component = get(id);
            if (component != null)
                component.setRegistryData(data);
            else if (strict) {
                throw new NoSuchElementException(String.format(PackNLS
                        .getString("Components.compNotDefined"), id)); //$NON-NLS-1$
            }
        }
    }

    /**
     * Export component data to a registry map.
     * 
     * @return Registry {@link Map} contains all component data.
     */
    public TextMap<Object> exportRegistry() {
        TreeTextMap<Object> registry = new TreeTextMap<Object>();
        for (Map.Entry<String, Component> e : entrySet()) {
            String id = e.getKey();
            Component component = e.getValue();
            Object data = component.getRegistryData();
            if (data != null)
                registry.put(id, data);
        }
        return registry;
    }

    /**
     * Preprocess the component tree, fill undertermined values and analyse the necessary
     * information.
     * 
     * @param start
     *            the root of component tree to start to collect.
     */
    public static Components collect(Component start) {
        Components components = new Components();
        TreePrep treePrep = new TreePrep(components, components.nextMap);
        treePrep.iterate(start);
        return components;
    }

    private static final String DOT = ".";

    static class TreePrep {

        final TextMap<Component> idMap;
        final TextMap<String>    nextMap;

        public TreePrep(TextMap<Component> idMap, TextMap<String> seqMap) {
            this.idMap = idMap;
            this.nextMap = seqMap;
        }

        /**
         * @return last id.
         */
        public String iterate(Component start) {
            assert start != null;
            String startId = start.getId();
            String prefix;
            if (startId == null) {
                // this must be root Component.
                prefix = ""; //$NON-NLS-1$
                startId = "";
                start.setId(startId);
            } else
                prefix = startId + DOT; //$NON-NLS-1$

            String prevId = startId;

            Collection<? extends Component> children = start.getChildren();
            if (children != null) {
                int index = 0;
                for (Component child : children) {
                    index++;
                    if (child == null)
                        throw new NullPointerException("child[" + index + "]"); //$NON-NLS-1$ //$NON-NLS-2$
                    String id = child.getId();
                    if (id == null) {
                        String name = child.getName();
                        if (name == null)
                            // 1, 2, 3, ...
                            id = searchUnusedKey(idMap, prefix, 1);
                        else {
                            id = prefix + name;
                            if (idMap.containsKey(id))
                                // x_1, x_2, ...
                                id = searchUnusedKey(idMap, id + "_", 1); //$NON-NLS-1$
                        }
                        assert id != null;
                        child.setId(id);
                    } else if (idMap.containsKey(id)) {
                        String mesg = String.format(PackNLS
                                .getString("Components.duplicatedId_sss"), id, idMap.get(id), //$NON-NLS-1$
                                child);
                        throw new IllegalStateException(mesg);
                    }
                    idMap.put(id, child);
                    nextMap.put(prevId, id);
                    prevId = iterate(child);
                }
            }
            String lastId = prevId;
            return lastId;
        }

    }

    static String searchUnusedKey(TextMap<?> map, String s, int startIndex) {
        while (true) {
            if (startIndex >= Integer.MAX_VALUE)
                throw new RuntimeException(PackNLS.getString("Components.indexUsedOut") + s); //$NON-NLS-1$
            String key = s + startIndex;
            if (!map.containsKey(key))
                return key;
            startIndex++;
        }
    }

}
