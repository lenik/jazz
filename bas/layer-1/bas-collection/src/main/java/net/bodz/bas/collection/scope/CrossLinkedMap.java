package net.bodz.bas.collection.scope;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

public class CrossLinkedMap<K, V>
        extends AbstractMap<K, V>
        implements IStackable {

    private Map<K, AbstractCrossNode<V>> map;

    public CrossLinkedMap() {

    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public void enter() {

    }

    @Override
    public void leave() {

    }

}
