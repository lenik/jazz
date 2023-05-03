package net.bodz.bas.t.specmap;

import java.util.Map;
import java.util.Set;

import net.bodz.bas.repr.form.SortOrder;

public class DefaultSpecMap<key_t, val_t>
        extends AbstractSpecMap<key_t, val_t> {

    private Map<key_t, val_t> _map;

    public DefaultSpecMap() {
        this(SortOrder.NONE);
    }

    public DefaultSpecMap(SortOrder order) {
        this(order.newMap());
    }

    public DefaultSpecMap(Map<key_t, val_t> map) {
        this._map = map;
    }

    @Override
    public boolean hasTop() {
        return !_map.isEmpty();
    }

    @Override
    public Set<key_t> topKeySet() {
        return _map.keySet();
    }

    @Override
    public boolean containsTop(key_t key) {
        return _map.containsKey(key);
    }

    @Override
    public val_t getTop(key_t key) {
        return _map.get(key);
    }

    @Override
    public val_t putTop(key_t key, val_t value) {
        return _map.put(key, value);
    }

    @Override
    public val_t removeTop(key_t key) {
        return _map.remove(key);
    }

    @Override
    public void removeAllTops() {
        _map.clear();
    }

}
