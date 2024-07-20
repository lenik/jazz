package net.bodz.bas.t.tree;

import java.util.List;

import net.bodz.bas.t.list.ArrayStack;
import net.bodz.bas.t.list.IStack;

public abstract class PathNodeVisitor<node_t extends ITreeNode<node_t>>
        implements
            ITreeNodeVisitor<node_t> {

    IStack<String> path = new ArrayStack<>();

    @Override
    public final void node(node_t node) {
        topDown(path, node);

        for (String key : node.childKeySet()) {
            path.push(key);

            node_t child = node.getChild(key);
            child.accept(this);

            path.pop();
        }

        bottomUp(path, node);
    }

    protected void topDown(List<String> path, node_t node) {
    }

    protected void bottomUp(List<String> path, node_t node) {
    }

}
