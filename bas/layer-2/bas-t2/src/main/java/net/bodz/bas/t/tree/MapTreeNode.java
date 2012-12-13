package net.bodz.bas.t.tree;

import java.util.*;
import java.util.Map.Entry;

import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.t.factory.ICreator;
import net.bodz.bas.t.factory.NewInstanceCreator;

public class MapTreeNode
        extends AbstractMutableTreeNode {

    private static final long serialVersionUID = 1L;

    private Map<String, ITreeNode> map;
    private ICreator<ITreeNode> creator;

    public MapTreeNode(ICreator<ITreeNode> creator) {
        this(new TreeMap<String, ITreeNode>(), creator);
    }

    public MapTreeNode(Map<String, ITreeNode> map, Class<? extends ITreeNode> nodeType) {
        if (map == null)
            throw new NullPointerException("map");
        if (nodeType == null)
            throw new NullPointerException("nodeType");
        this.map = map;
        this.creator = new NewInstanceCreator<ITreeNode>(nodeType);
    }

    public MapTreeNode(Map<String, ITreeNode> map, ICreator<ITreeNode> creator) {
        if (map == null)
            throw new NullPointerException("map");
        if (creator == null)
            throw new NullPointerException("creator");
        this.map = map;
        this.creator = creator;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public ITreeNode getChild(String key) {
        return map.get(key);
    }

    @Override
    public Set<String> childKeySet() {
        return map.keySet();
    }

    @Override
    public Collection<ITreeNode> children() {
        return map.values();
    }

    @Override
    public String keyOf(ITreeNode child) {
        for (Entry<String, ITreeNode> entry : map.entrySet())
            if (entry.getValue() == child)
                return entry.getKey();
        return null;
    }

    @Override
    public List<String> keysOf(ITreeNode child) {
        List<String> keys = new ArrayList<String>(1);
        for (Entry<String, ITreeNode> entry : map.entrySet())
            if (entry.getValue() == child)
                keys.add(entry.getKey());
        return keys;
    }

    @Override
    public ITreeNode newChild()
            throws CreateException {
        return creator.create();
    }

    @Override
    public String addChild(ITreeNode child) {
        
        String id = ObjectInfo.getSimpleId(child);
        return id;
    }

    @Override
    public void putChild(String key, ITreeNode child) {
        map.put(key, child);
    }

    @Override
    public ITreeNode removeChild(String key) {
        return map.remove(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

}
