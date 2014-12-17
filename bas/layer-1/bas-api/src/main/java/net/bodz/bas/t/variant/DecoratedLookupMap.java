package net.bodz.bas.t.variant;

import java.util.Set;

import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedLookupMap<K, V>
        extends AbstractDecorator<ILookupMap<K, V>>
        implements ILookupMap<K, V> {

    private static final long serialVersionUID = 1L;

    public DecoratedLookupMap(ILookupMap<K, V> _orig) {
        super(_orig);
    }

    @Override
    public Set<K> keySet() {
        return getWrapped().keySet();
    }

    @Override
    public boolean containsKey(Object key) {
        return getWrapped().containsKey(key);
    }

    @Override
    public V get(Object key) {
        return getWrapped().get(key);
    }

}
