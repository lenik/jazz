package net.bodz.bas.t.tree;

import java.util.Arrays;

public interface IMutableTreeNode<node_t extends IMutableTreeNode<node_t>>
        extends
            ITreeNode<node_t> {

    @Override
    node_t getParent();

    /**
     * @return this.
     */
    <self_t extends node_t> self_t detach();

    /**
     * @return this.
     */
    <self_t extends node_t> self_t attach(node_t parent, String key);

    node_t addNewChild(String key);

//    void addChild(node_t child);

    /**
     * If the specific child is duplicated in the children set, any number of them can be removed.
     */
    void removeChild(node_t child);

    void putChild(String key, node_t child);

    node_t removeChild(String key);

    node_t getOrCreateChild(String key);

    /**
     * Get or create the node by path.
     *
     * @param path
     *            Path of the node to be resolved. Can't be <code>null</code>.
     * @return The resolved node. If the <code>path</code> is empty, returns this node. All missing
     *         nodes along the path should be created implicitly. If it's failed to create any
     *         missing node, <code>null</code> is returned.
     *
     */
    node_t findOrCreatePath(String path);

    /**
     * Get or create the node by path.
     *
     * @param path
     *            Path of the node to be resolved. Can't be <code>null</code>.
     * @return The resolved node. If the <code>path</code> is empty, returns this node. All missing
     *         nodes along the path should be created implicitly. If it's failed to create any
     *         missing node, <code>null</code> is returned.
     *
     */
    node_t findOrCreate(Iterable<String> path);

    default node_t findOrCreate(String... path) {
        return findOrCreate(Arrays.asList(path));
    }

    void clear();

}
