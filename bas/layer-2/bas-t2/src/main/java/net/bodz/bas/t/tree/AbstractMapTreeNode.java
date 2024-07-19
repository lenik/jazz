package net.bodz.bas.t.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.bodz.bas.c.java.util.TextMap;
import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.repr.form.SortOrder;

public abstract class AbstractMapTreeNode<node_t extends IMutableTreeNode<node_t>>
        extends AbstractMutableTreeNode<node_t> {

    private static final long serialVersionUID = 1L;

    private final SortOrder order;
    private Map<String, node_t> childMap;

    public AbstractMapTreeNode() {
        this(SortOrder.NONE);
    }

    public AbstractMapTreeNode(SortOrder order) {
        if (order == null)
            throw new NullPointerException("order");
        this.order = order;
        this.childMap = order.newMap();
    }

    protected AbstractMapTreeNode(SortOrder order, Map<String, node_t> map) {
        this(order);
        if (map == null)
            throw new NullPointerException("map");
        this.childMap = map;
    }

    protected Map<String, node_t> createMap() {
        return order.newMap();
    }

    @Override
    public int size() {
        return childMap.size();
    }

    @Override
    public node_t getChild(String key) {
        return childMap.get(key);
    }

    @Override
    public Set<String> childKeySet() {
        return childMap.keySet();
    }

    @Override
    public Collection<node_t> getChildren() {
        return childMap.values();
    }

    @Override
    public String keyOf(ITreeNode<?> child) {
        for (Entry<String, node_t> entry : childMap.entrySet())
            if (entry.getValue() == child)
                return entry.getKey();
        return null;
    }

    @Override
    public List<String> keysOf(ITreeNode<?> child) {
        List<String> keys = new ArrayList<String>(1);
        for (Entry<String, node_t> entry : childMap.entrySet())
            if (entry.getValue() == child)
                keys.add(entry.getKey());
        return keys;
    }

    void _addChild(node_t child) {
        String prefix = ObjectInfo.getSimpleId(child);
        String key = TextMap.fn.searchUnusedKey(childMap.keySet(), prefix);
        putChild(key, child);
    }

    @Override
    public void removeChild(node_t child) {
        Iterator<Entry<String, node_t>> iterator = childMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, node_t> entry = iterator.next();
            if (entry.getValue() == child)
                iterator.remove();
        }
    }

    @Override
    public void putChild(String key, node_t child) {
        childMap.put(key, child);
    }

    @Override
    public node_t removeChild(String key) {
        return childMap.remove(key);
    }

    @Override
    public void clear() {
        childMap.clear();
    }

}
