package net.bodz.bas.t.specmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.io.BCharOut;
import net.bodz.bas.repr.form.SortOrder;

public class DefaultSpecMap<key_t, val_t>
        implements
            ISpecMap<key_t, val_t> {

    boolean hasDefaultVal;
    val_t defaultVal;

    Map<key_t, val_t> map;

    protected final boolean nullable;
    protected final boolean allowOverlappedRange;

    public DefaultSpecMap() {
        this(SortOrder.NONE);
    }

    public DefaultSpecMap(SortOrder order) {
        this(order.newMap());
    }

    public DefaultSpecMap(Map<key_t, val_t> map) {
        this.map = map;
        nullable = isNullable();
        allowOverlappedRange = isAllowOverlappedRange();
    }

    public boolean isNullable() {
        return true;
    }

    public boolean isAllowOverlappedRange() {
        return false;
    }

    @Override
    public val_t find(key_t key) {
        val_t val = map.get(key);
        if (val != null)
            return val;
        else if (nullable && map.containsKey(key))
            return null;
        return _find(key);
    }

    protected val_t _find(key_t key) {
        return defaultVal;
    }

    @Override
    public final List<ILayerKeyValue<val_t>> findAll(key_t key) {
        List<ILayerKeyValue<val_t>> list = new ArrayList<>();
        findAll(key, list);
        return list;
    }

    protected void findAll(key_t key, List<ILayerKeyValue<val_t>> list) {
        if (map.containsKey(key))
            list.add(LayerKeyValue.top(key, map.get(key)));
        _findAll(key, list);
    }

    protected void _findAll(key_t key, List<ILayerKeyValue<val_t>> list) {
        if (hasDefaultVal)
            list.add(LayerKeyValue._default(defaultVal));
    }

    @Override
    public SpecKeyLocation whichKey(key_t key) {
        if (map.containsKey(key))
            return SpecKeyLocation.top(key);
        return _whichKey(key);
    }

    protected SpecKeyLocation _whichKey(key_t key) {
        if (hasDefaultVal)
            return SpecKeyLocation._default();
        return null;
    }

    @Override
    public SpecLayer whichLayer(key_t key) {
        SpecKeyLocation keyLoc = whichKey(key);
        if (keyLoc == null)
            return null;
        else
            return keyLoc.layer;
    }

    @Override
    public ILayerKeyValue<val_t> removeFromLayer(SpecLayer layer, Object key) {
        if (layer == SpecLayer.TOP) {
            val_t val = map.remove(key);
            return new LayerKeyValue<>(layer, key, val);
        }
        if (layer == SpecLayer.DEFAULT) {
            hasDefaultVal = false;
            val_t last = defaultVal;
            defaultVal = null;
            return new LayerKeyValue<>(layer, null, last);
        }
        return null;
    }

    @Override
    public ILayerKeyValue<val_t> removeFromAnyLayer(key_t key) {
        if (map.containsKey(key)) {
            val_t val = map.remove(key);
            return LayerKeyValue.top(key, val);
        }
        return _removeFromAnyLayer(key);
    }

    protected ILayerKeyValue<val_t> _removeFromAnyLayer(key_t key) {
        if (hasDefaultVal) {
            hasDefaultVal = false;
            val_t last = defaultVal;
            defaultVal = null;
            return LayerKeyValue._default(last);
        }
        return null;
    }

    @Override
    public Set<key_t> keySet() {
        return map.keySet();
    }

    @Override
    public boolean containsKey(key_t key) {
        return map.containsKey(key);
    }

    @Override
    public val_t get(key_t key) {
        return map.get(key);
    }

    @Override
    public val_t put(key_t key, val_t value) {
        return map.put(key, value);
    }

    @Override
    public val_t remove(key_t key) {
        return map.remove(key);
    }

    @Override
    public void removeAllTops() {
        map.clear();
    }

    @Override
    public boolean containsDefault() {
        return hasDefaultVal;
    }

    @Override
    public val_t getDefault() {
        if (hasDefaultVal)
            return defaultVal;
        else
            return null;
    }

    @Override
    public void setDefault(val_t value) {
        this.defaultVal = value;
        this.hasDefaultVal = true;
    }

    @Override
    public val_t removeDefault() {
        hasDefaultVal = false;
        val_t last = defaultVal;
        defaultVal = null;
        return last;
    }

    @Override
    public final void accept(ISpecMapVisitor<? super key_t, ? super val_t> visitor) {
        if (!map.isEmpty()) {
            if (visitor.beginTops()) {
                for (key_t key : map.keySet()) {
                    val_t val = map.get(key);
                    if (!visitor.visitTop(key, val))
                        break;
                    visitor.visitValue(val);
                }
                visitor.endTops();
            }
        }
        _accept(visitor);
        if (hasDefaultVal) {
            visitor.visitDefault(defaultVal);
            visitor.visitValue(defaultVal);
        }
    }

    protected void _accept(ISpecMapVisitor<? super key_t, ? super val_t> visitor) {
    }

    @Override
    public String toString() {
        BCharOut buf = new BCharOut();
        SpecMapDumper<key_t, val_t> dumper = new SpecMapDumper<>(buf.indented());
        accept(dumper);
        return buf.toString();
    }

}
