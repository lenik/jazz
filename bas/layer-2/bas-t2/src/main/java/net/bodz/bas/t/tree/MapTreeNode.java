package net.bodz.bas.t.tree;

import java.util.Map;

import net.bodz.bas.err.CreateException;

public class MapTreeNode
        extends AbstractMapTreeNode<ITreeNode> {

    private static final long serialVersionUID = 1L;

    public MapTreeNode() {
        super();
    }

    public MapTreeNode(Map<String, ITreeNode> map) {
        super(map);
    }

    @Override
    protected ITreeNode newChild()
            throws CreateException {
        return new MapTreeNode();
    }

}
