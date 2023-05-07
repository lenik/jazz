package net.bodz.bas.t.specmap;

import java.util.Map;
import java.util.Set;

public abstract class AbstractSpecMap<key_t, val_t>
        extends AbstractSpecMapBase<key_t, val_t> {

    public abstract Map<key_t, val_t> getMap();

    @Override
    public boolean hasTop() {
        return !getMap().isEmpty();
    }

    @Override
    public Set<key_t> topKeySet() {
        return getMap().keySet();
    }

    @Override
    public boolean containsTop(key_t key) {
        return getMap().containsKey(key);
    }

    @Override
    public val_t getTop(key_t key) {
        return getMap().get(key);
    }

    @Override
    public val_t putTop(key_t key, val_t value) {
        return getMap().put(key, value);
    }

    @Override
    public val_t removeTop(key_t key) {
        return getMap().remove(key);
    }

    @Override
    public void removeAllTops() {
        getMap().clear();
    }

}
