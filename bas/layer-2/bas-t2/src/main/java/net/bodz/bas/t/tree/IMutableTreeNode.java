package net.bodz.bas.t.tree;

public interface IMutableTreeNode
        extends ITreeNode {

    @Override
    ITreeNode getParent();

    ITreeNode detach();

    void attach(ITreeNode parent, String key);

    String addChild(ITreeNode child);

    void putChild(String key, ITreeNode child);

    ITreeNode removeChild(String key);

    /**
     * Resolve the node by path.
     * 
     * @param path
     *            Path of the node to be resolved. Can't be <code>null</code>.
     * @return The resolved node. All missing nodes along the path should be created implicitly. If
     *         path is empty, returns this node.
     */
    ITreeNode resolve(String path);

    void clear();

}
