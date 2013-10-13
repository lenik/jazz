package net.bodz.bas.t.tree;

public interface IMutableTreeNode<node_t extends IMutableTreeNode<?>>
        extends ITreeNode<node_t> {

    @Override
    node_t getParent();

    /**
     * @return this.
     */
    <self_t extends node_t> self_t detach();

    /**
     * @return this.
     */
    <self_t extends node_t> self_t attach(node_t parent);

    /**
     * @return this.
     */
    <self_t extends node_t> self_t attach(node_t parent, String key);

    void addChild(node_t child);

    /**
     * If the specific child is duplicated in the children set, any number of them can be removed.
     */
    void removeChild(node_t child);

    void putChild(String key, node_t child);

    node_t removeChild(String key);

    void clear();

}
