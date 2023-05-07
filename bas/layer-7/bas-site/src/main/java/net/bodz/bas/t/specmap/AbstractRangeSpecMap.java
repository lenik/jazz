package net.bodz.bas.t.specmap;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public abstract class AbstractRangeSpecMap<key_t extends Comparable<key_t>, val_t>
        extends AbstractSpecMap<key_t, val_t>
        implements
            IRangeMap<key_t, val_t> {

    private static <K extends Comparable<K>> IRange<K> bottom(K key) {
        return new StartEndRange<>(key, null);
    }

    static <K extends Comparable<K>> IRange<K> range(K start, K end) {
        return new StartEndRange<>(start, end);
    }

    public abstract TreeMap<IRange<key_t>, val_t> getRangeMap();

//    public abstract SortedMap<IRange<key_t>, val_t> getRangeMapHead(IRange<key_t> toKey, boolean inclusive);

    @Override
    protected val_t _find(key_t key) {
        IRange<key_t> rangeKey = findRangeFor(key);
        if (rangeKey != null)
            return getRange(rangeKey);
        return super._find(key);
    }

    @Override
    protected void _findAll(key_t key, List<ILayerKeyValue<val_t>> list) {
//        IRange<key_t> top = top(key);
        IRange<key_t> bottom = bottom(key);
        int n = list.size();
        for (Entry<IRange<key_t>, val_t> entry : getRangeMap().headMap(bottom, true).entrySet()) {
            IRange<key_t> _key = entry.getKey();
//            if (pos.compareTo(top) < 0)
//                break;
            if (_key.contains(key))
                // insert in reversed order, so the closest entry comes first.
                list.add(n, LayerKeyValue.range(entry.getKey(), entry.getValue()));
        }
        super._findAll(key, list);
    }

    @Override
    protected SpecKeyLocation _whichKey(key_t key) {
        IRange<key_t> rangeKey = findRangeFor(key);
        if (rangeKey != null)
            return SpecKeyLocation.range(rangeKey);
        return super._whichKey(key);
    }

    @Override
    protected ILayerKeyValue<val_t> _removeFromAnyLayer(key_t key) {
        IRange<key_t> rangeKey = findRangeFor(key);
        if (rangeKey != null) {
            val_t last = removeRange(rangeKey);
            return LayerKeyValue.range(rangeKey, last);
        }
        return super._removeFromAnyLayer(key);
    }

    @Override
    public IRange<key_t> findRangeFor(key_t key) {
//        IRange<key_t> min = top(key);
        IRange<key_t> max = bottom(key);
        for (Entry<IRange<key_t>, val_t> ent = getRangeMap().floorEntry(max); // pos = max
                ent != null //
//                        && ent.getKey().compareTo(min) >= 0 //
                ; // pos >= min
                ent = getRangeMap().lowerEntry(ent.getKey())) // pos--
            if (ent.getKey().contains(key))
                return ent.getKey();
        return null;
    }

    @Override
    public boolean hasRange() {
        return !getRangeMap().isEmpty();
    }

    @Override
    public Set<IRange<key_t>> rangeKeySet() {
        return getRangeMap().keySet();
    }

    @Override
    public boolean containsRange(key_t start, key_t end) {
        return getRangeMap().containsKey(range(start, end));
    }

    @Override
    public final val_t getRange(IRange<key_t> range) {
        return getRangeMap().get(range);
    }

    @Override
    public final val_t getRange(key_t start, key_t end) {
        return getRange(range(start, end));
    }

    @Override
    public val_t putRange(key_t start, key_t end, val_t val) {
        return getRangeMap().put(range(start, end), val);
    }

    @Override
    public val_t removeRange(IRange<key_t> range) {
        return getRangeMap().remove(range);
    }

    @Override
    public final val_t removeRange(key_t start, key_t end) {
        return removeRange(range(start, end));
    }

    @Override
    public void removeAllRanges() {
        getRangeMap().clear();
    }

    @Override
    public void _accept(ISpecMapVisitor<? super key_t, ? super val_t> visitor) {
        if (hasRange()) {
            if (visitor.beginRanges()) {
                for (IRange<key_t> rangeKey : rangeKeySet()) {
                    val_t val = getRange(rangeKey);
                    if (!visitor.visitRange(rangeKey, val))
                        break;
                    if (visitor.beginValue(SpecLayer.RANGE, rangeKey)) {
                        visitor.visitValue(val);
                        visitor.endValue();
                    }
                }
                visitor.endRanges();
            }
        }
    }

}
