package net.bodz.bas.site.vhost.registry;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import net.bodz.bas.repr.form.SortOrder;

public class NumberRegistry<T> {

    final boolean nullable;
    final boolean allowOverlappedRange;

    T _default;
    private Map<Integer, T> map;
    private SortedMap<Integer, SortedMap<Integer, T>> _startEndMap;

    SortOrder order;

    public NumberRegistry() {
        this(SortOrder.NONE);
    }

    public NumberRegistry(SortOrder order) {
        nullable = isNullable();
        allowOverlappedRange = isAllowOverlappedRange();
        map = order.newMap();
        _startEndMap = new TreeMap<>();
        this.order = order;
    }

    public boolean isNullable() {
        return true;
    }

    public boolean isAutoCompact() {
        return true;
    }

    public boolean isAllowOverlappedRange() {
        return false;
    }

    public boolean containsHex(String hex) {
        if (hex == null)
            throw new NullPointerException("hex");
        int num = Integer.parseInt(hex, 16);
        return contains(num);
    }

    public boolean contains(int num) {
        if (_default != null)
            return true;
        if (map.containsKey(num))
            return true;
        return rangeContains(num);
    }

    public boolean rangeContains(int num) {
        synchronized (_startEndMap) {
            SortedMap<Integer, SortedMap<Integer, T>> headMap = _startEndMap.headMap(num + 1);
            for (Integer start : headMap.keySet())
                if (!headMap.get(start).tailMap(num + 1).isEmpty())
                    return true;
        }
        return false;
    }

    public T findHex(String hex) {
        int num = Integer.parseInt(hex, 16);
        return find(num);
    }

    public T find(int num) {
        T obj = map.get(num);
        if (obj != null)
            return obj;
        else if (nullable && map.containsKey(num))
            return null;

        synchronized (_startEndMap) {
            SortedMap<Integer, SortedMap<Integer, T>> headMap = _startEndMap.headMap(num + 1);
            for (Integer start : headMap.keySet()) {
                SortedMap<Integer, T> tailMap = headMap.get(start).tailMap(num + 1);
                if (!tailMap.isEmpty()) {
                    Integer firstEnd = tailMap.firstKey();
                    return tailMap.get(firstEnd);
                }
            }
        }
        return _default;
    }

    public void setDefault(T obj) {
        _default = obj;
    }

    public void set(int num, T obj) {
        map.put(num, obj);
    }

    public SortedMap<Integer, T> endMap(int start) {
        synchronized (_startEndMap) {
            SortedMap<Integer, T> endMap = endMap(start);
            if (endMap == null) {
                if (!allowOverlappedRange && rangeContains(start))
                    return null;
                endMap = new TreeMap<>();
                _startEndMap.put(start, endMap);
            }
            return endMap;
        }
    }

    public boolean setStartEndRange(int start, int end, T obj) {
        SortedMap<Integer, T> map = endMap(start);
        if (map == null)
            return false;
        if (!allowOverlappedRange && !map.isEmpty())
            return false;
        map.put(end, obj);
        return true;
    }

    public final boolean setFromToRange(int from, int to, T obj) {
        return setStartEndRange(from, to + 1, obj);
    }

    public boolean addDefault(T obj) {
        if (obj == null && !nullable)
            throw new NullPointerException("obj");
        if (_default != null)
            return false;
        _default = obj;
        return true;
    }

    public boolean add(int num, T obj) {
        if (obj == null && !nullable)
            throw new NullPointerException("obj");
        if (map.containsKey(num))
            return false;
        map.put(num, obj);
        return true;
    }

    public boolean addStartEndRange(int start, int end, T obj) {
        if (obj == null && !nullable)
            throw new NullPointerException("obj");
        SortedMap<Integer, T> endMap = endMap(start);
        if (endMap == null)
            return false; // start overlap
        if (endMap.containsKey(end))
            return false; // duplicate
        if (!allowOverlappedRange && endMap.isEmpty())
            return false; // end overlap
        endMap.put(end, obj);
        return true;
    }

    public final boolean addFromToRange(int from, int to, T obj) {
        return addStartEndRange(from, to + 1, obj);
    }

    public T remove(int num) {
        return map.remove(num);
    }

    public T removeStartEndRange(int start, int end) {
        synchronized (_startEndMap) {
            SortedMap<Integer, T> endMap = _startEndMap.get(start);
            if (endMap == null)
                return null;
            T last = endMap.remove(end);
            if (isAutoCompact() && endMap.isEmpty())
                _startEndMap.remove(start);
            return last;
        }
    }

    public final T removeFromToRange(int from, int to) {
        return removeStartEndRange(from, to + 1);
    }

}
