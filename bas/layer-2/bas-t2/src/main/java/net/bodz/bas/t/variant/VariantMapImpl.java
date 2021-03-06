package net.bodz.bas.t.variant;

import java.util.Set;

public class VariantMapImpl<K>
        extends AbstractVariantMap<K> {

    private final ILookupMap<K, Object> lookupMap;

    public VariantMapImpl(ILookupMap<K, Object> lookupMap) {
        if (lookupMap == null)
            throw new NullPointerException("lookupMap");
        this.lookupMap = lookupMap;
    }

    @Override
    public Set<K> keySet() {
        return lookupMap.keySet();
    }

    @Override
    public boolean containsKey(Object key) {
        return lookupMap.containsKey(key);
    }

    @Override
    public Object get(Object key) {
        return lookupMap.get(key);
    }

}
