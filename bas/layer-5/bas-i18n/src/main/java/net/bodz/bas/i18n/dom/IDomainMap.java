package net.bodz.bas.i18n.dom;

import java.util.Map;
import java.util.Set;

public interface IDomainMap<value_t> {

    value_t get(String path);

    /**
     * Resolve to the node for specific path.
     * 
     * @return The resolved node, <code>null</code> if not existed.
     */

    value_t getNearest(String path);

    value_t put(String path, value_t value);

    value_t pull(String path);

    value_t remove(String path);

    int size();

    Set<String> keySet();

    Set<Map.Entry<String, value_t>> entrySet();

}