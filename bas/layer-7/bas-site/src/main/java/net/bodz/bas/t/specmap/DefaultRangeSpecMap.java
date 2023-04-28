package net.bodz.bas.t.specmap;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import net.bodz.bas.repr.form.SortOrder;

public class DefaultRangeSpecMap<key_t extends Comparable<key_t>, val_t>
        extends DefaultSpecMap<key_t, val_t>
        implements
            IRangeMap<key_t, val_t> {

    TreeMap<IRange<key_t>, val_t> rangeMap;

    public DefaultRangeSpecMap() {
        super(SortOrder.ASCENDING);
        rangeMap = new TreeMap<>(RangeComparator.getInstance());
    }

//    private static <K extends Comparable<K>> IRange<K> top(K key) {
//        return new StartEndRange<>(key, key);
//    }

    private static <K extends Comparable<K>> IRange<K> bottom(K key) {
        return new StartEndRange<>(key, null);
    }

    static <K extends Comparable<K>> IRange<K> range(K start, K end) {
        return new StartEndRange<>(start, end);
    }

    @Override
    protected val_t _find(key_t key) {
        IRange<key_t> rangeKey = findRangeFor(key);
        if (rangeKey != null)
            return rangeMap.get(rangeKey);
        return super._find(key);
    }

    @Override
    protected void _findAll(key_t key, List<ILayerKeyValue<val_t>> list) {
//        IRange<key_t> top = top(key);
        IRange<key_t> bottom = bottom(key);
        int n = list.size();
        for (Entry<IRange<key_t>, val_t> entry : rangeMap.headMap(bottom, true).entrySet()) {
            IRange<key_t> pos = entry.getKey();
//            if (pos.compareTo(top) < 0)
//                break;
            if (pos.contains(key))
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
            val_t last = rangeMap.remove(rangeKey);
            return LayerKeyValue.range(rangeKey, last);
        }
        return super._removeFromAnyLayer(key);
    }

    @Override
    public IRange<key_t> findRangeFor(key_t key) {
//        IRange<key_t> min = top(key);
        IRange<key_t> max = bottom(key);
        for (Entry<IRange<key_t>, val_t> ent = rangeMap.floorEntry(max); // pos = max
                ent != null //
//                        && ent.getKey().compareTo(min) >= 0 //
                ; // pos >= min
                ent = rangeMap.lowerEntry(ent.getKey())) // pos--
            if (ent.getKey().contains(key))
                return ent.getKey();
        return null;
    }

    @Override
    public Set<IRange<key_t>> rangeKeySet() {
        return rangeMap.keySet();
    }

    @Override
    public boolean containsRange(key_t start, key_t end) {
        return rangeMap.containsKey(range(start, end));
    }

    @Override
    public val_t getRange(key_t start, key_t end) {
        return rangeMap.get(range(start, end));
    }

    @Override
    public val_t putRange(key_t start, key_t end, val_t val) {
        return rangeMap.put(range(start, end), val);
    }

    @Override
    public val_t removeRange(key_t start, key_t end) {
        return rangeMap.remove(range(start, end));
    }

    @Override
    public void removeAllRanges() {
        rangeMap.clear();
    }

    @Override
    public void _accept(ISpecMapVisitor<? super key_t, ? super val_t> visitor) {
        if (!rangeMap.isEmpty()) {
            if (visitor.beginRanges()) {
                for (IRange<key_t> rangeKey : rangeMap.keySet()) {
                    val_t val = rangeMap.get(rangeKey);
                    if (!visitor.visitRange(rangeKey, val))
                        break;
                    visitor.visitValue(val);
                }
                visitor.endRanges();
            }
        }
    }

}
