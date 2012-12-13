package net.bodz.bas.t.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.t.factory.ICreator;
import net.bodz.bas.t.factory.NewInstanceCreator;

public class ListTreeNode
        extends AbstractMutableTreeNode {

    private static final long serialVersionUID = 1L;

    private List<ITreeNode> list;
    private ICreator<ITreeNode> creator;

    public ListTreeNode(ICreator<ITreeNode> creator) {
        this(new ArrayList<ITreeNode>(), creator);
    }

    public ListTreeNode(List<ITreeNode> list, Class<? extends ITreeNode> nodeType) {
        if (list == null)
            throw new NullPointerException("list");
        if (nodeType == null)
            throw new NullPointerException("nodeType");
        this.list = list;
        this.creator = new NewInstanceCreator<ITreeNode>(nodeType);
    }

    public ListTreeNode(List<ITreeNode> list, ICreator<ITreeNode> creator) {
        if (list == null)
            throw new NullPointerException("list");
        if (creator == null)
            throw new NullPointerException("creator");
        this.list = list;
        this.creator = creator;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public ITreeNode getChild(String key) {
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
    public Collection<ITreeNode> children() {
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
    public ITreeNode newChild()
            throws CreateException {
        return creator.create();
    }

    @Override
    public String addChild(ITreeNode child) {
        int size = list.size();
        list.add(child);
        return String.valueOf(size);
    }

    @Override
    public void putChild(String key, ITreeNode child) {
        if (!StringPred.isInteger(key))
            throw new IllegalArgumentException("key isn't integer: " + key);

        int index = Integer.parseInt(key);
        if (index < 0)
            throw new IllegalArgumentException("Index must be positive: " + index);

        while (list.size() < index)
            list.add(null);

        list.set(index, child);
    }

    @Override
    public ITreeNode removeChild(String key) {
        if (StringPred.isInteger(key)) {
            int index = Integer.parseInt(key);
            if (index >= 0 && index < list.size())
                return list.remove(index);
        }
        return null;
    }

}
