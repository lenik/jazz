package net.bodz.bas.t.tree;

import net.bodz.bas.err.CreateException;

public interface IMutableTreeNode
        extends ITreeNode {

    /**
     * Re-attach the parent node.
     * 
     * @param parent
     *            The new parent.
     */
    void setParent(ITreeNode parent);

    ITreeNode newChild()
            throws CreateException;

    String addChild(ITreeNode child);

    void putChild(String key, ITreeNode child);

    ITreeNode removeChild(String key);

    ITreeNode resolve(String path);

    void clear();

}
