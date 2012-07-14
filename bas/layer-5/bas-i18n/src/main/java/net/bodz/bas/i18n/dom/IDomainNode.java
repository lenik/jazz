package net.bodz.bas.i18n.dom;

import java.util.Map.Entry;

public interface IDomainNode<node_t extends IDomainNode<node_t, value_t>, value_t>
        extends Iterable<Entry<String, node_t>> {

    String getDomain();

    value_t getValue();

    void setValue(value_t value);

    value_t get(String path);

    /**
     * Resolve to the node for specific path.
     * 
     * @return The resolved node, <code>null</code> if not existed.
     */
    node_t getNode(String path);

    value_t getNearest(String path);

    node_t getNearestNode(String path);

    value_t put(String path, value_t value);

    value_t pull(String path);

    node_t pullNode(String path);

    /**
     * Create nodes for specific path. If the node isn't existed, create it and initialize it with
     * <code>initialValue</code>.
     * 
     * @param initialValue
     *            The initial value for new created node. If the node is existed, initialValue is
     *            not used.
     * @return The resolved node.
     */
    node_t create(String path, value_t initialValue);

    node_t remove(String path);

    int size();

}
