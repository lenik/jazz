package net.bodz.bas.t.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.bodz.bas.c.string.StringPred;

public abstract class AbstractListTreeNode<node_t extends ITreeNode>
        extends AbstractMutableTreeNode<node_t> {

    private static final long serialVersionUID = 1L;

    private List<node_t> list;

    public AbstractListTreeNode(node_t parent) {
        this(parent, new ArrayList<node_t>());
    }

    public AbstractListTreeNode(node_t parent, List<node_t> list) {
        super(parent);
        if (list == null)
            throw new NullPointerException("list");
        this.list = list;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public node_t getChild(String key) {
        if (StringPred.isInteger(key)) {
            int index = Integer.parseInt(key);
            if (index >= 0 && index < list.size())
                return list.get(index);
        }
        return null;
    }

    @Override
    public Set<String> childKeySet() {
        Set<String> set = new LinkedHashSet<String>();
        int size = list.size();
        for (int index = 0; index < size; index++)
            set.add(String.valueOf(index));
        return set;
    }

    @Override
    public Collection<? extends node_t> children() {
        return list;
    }

    @Override
    public String keyOf(ITreeNode child) {
        int index = list.indexOf(child);
        if (index == -1)
            return null;
        else
            return String.valueOf(index);
    }

    @Override
    public List<String> keysOf(ITreeNode child) {
        List<String> keys = new ArrayList<String>(1);
        int size = list.size();
        for (int index = 0; index < size; index++)
            if (list.get(index) == child)
                keys.add(String.valueOf(index));
        return keys;
    }

    @Override
    public String addChild(ITreeNode child) {
        int size = list.size();
        String key = String.valueOf(size);

        @SuppressWarnings("unchecked") node_t _child = (node_t) child;
        list.add(_child);

        return key;
    }

    @Override
    public void putChild(String key, ITreeNode child) {
        ITreeNode childParent = child.getParent();
        if (childParent != null)
            throw new IllegalStateException("Child node is already attached: " + child);

        if (!StringPred.isInteger(key))
            throw new IllegalArgumentException("key isn't integer: " + key);

        int index = Integer.parseInt(key);
        if (index < 0)
            throw new IllegalArgumentException("Index must be positive: " + index);

        while (list.size() < index)
            list.add(null);

        @SuppressWarnings("unchecked") node_t _child = (node_t) child;
        list.set(index, _child);
    }

    @Override
    public node_t removeChild(String key) {
        if (StringPred.isInteger(key)) {
            int index = Integer.parseInt(key);
            if (index >= 0 && index < list.size()) {
                node_t child = list.remove(index);
                if (child.isMutable()) {
                    IMutableTreeNode _child = (IMutableTreeNode) child;
                    _child.detach();
                }
                return child;
            }
        }
        return null;
    }

}
