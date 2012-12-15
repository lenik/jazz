package net.bodz.bas.t.tree;

import java.util.*;
import java.util.Map.Entry;

import net.bodz.bas.c.object.ObjectInfo;

public abstract class AbstractMapTreeNode<node_t extends ITreeNode>
        extends AbstractMutableTreeNode<node_t> {

    private static final long serialVersionUID = 1L;

    private Map<String, node_t> map;

    public AbstractMapTreeNode(node_t parent) {
        this(parent, new TreeMap<String, node_t>());
    }

    public AbstractMapTreeNode(node_t parent, Map<String, node_t> map) {
        super(parent);
        if (map == null)
            throw new NullPointerException("map");
        this.map = map;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public node_t getChild(String key) {
        return map.get(key);
    }

    @Override
    public Set<String> childKeySet() {
        return map.keySet();
    }

    @Override
    public Collection<node_t> children() {
        return map.values();
    }

    @Override
    public String keyOf(ITreeNode child) {
        for (Entry<String, node_t> entry : map.entrySet())
            if (entry.getValue() == child)
                return entry.getKey();
        return null;
    }

    @Override
    public List<String> keysOf(ITreeNode child) {
        List<String> keys = new ArrayList<String>(1);
        for (Entry<String, node_t> entry : map.entrySet())
            if (entry.getValue() == child)
                keys.add(entry.getKey());
        return keys;
    }

    @Override
    public String addChild(ITreeNode child) {

        String id = ObjectInfo.getSimpleId(child);
        return id;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void putChild(String key, ITreeNode child) {
        map.put(key, (node_t) child);
    }

    @Override
    public node_t removeChild(String key) {
        return map.remove(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

}
