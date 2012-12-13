package net.bodz.bas.t.tree.legacy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayTreeNode
        implements ITreeNode<ArrayTreeNode> {

    private Object var;

    private Object[] array;
    private List<ArrayTreeNode> children;

    public ArrayTreeNode(Object var) {
        this.var = var;
        if (var != null)
            if (var.getClass().isArray())
                array = (Object[]) var;
    }

    public Object get() {
        return var;
    }

    @Override
    public List<? extends ArrayTreeNode> getChildren() {
        if (children == null) {
            if (array == null) {
                List<ArrayTreeNode> empty = Collections.emptyList();
                children = empty;
            } else {
                children = new ArrayList<ArrayTreeNode>(array.length);
                for (int i = 0; i < array.length; i++) {
                    ArrayTreeNode child = new ArrayTreeNode(array[i]);
                    children.add(child);
                }
            }
        }
        return children;
    }

    @Override
    public String toString() {
        if (array != null) {
            return "array(" + array.length + ")";
        }
        return String.valueOf(var);
    }

}
