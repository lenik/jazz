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

    void clear();

}
