package net.bodz.bas.util.variant;

import java.util.Set;

public class VariantLookupMapImpl<K>
        extends AbstractVariantLookupMap<K> {

    private final ILookupMap<K, Object> impl;

    public VariantLookupMapImpl(ILookupMap<K, Object> impl) {
        if (impl == null)
            throw new NullPointerException("impl");
        this.impl = impl;
    }

    @Override
    public Set<K> keySet() {
        return impl.keySet();
    }

    @Override
    public boolean containsKey(K key) {
        return impl.containsKey(key);
    }

    @Override
    public Object get(K key) {
        return impl.get(key);
    }

}
