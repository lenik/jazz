package net.bodz.bas.t.catalog;

import java.util.*;
import java.util.regex.Pattern;

public class PatternMap<V>
        implements
            Map<String, V> {

    Map<String, V> map = new HashMap<>();
    Map<String, Pattern> _cache = new HashMap<>();

    public PatternMap() {
    }

    public Iterable<String> getMatchedKeys(String keyText) {
        Set<String> set = new HashSet<>();
        for (String regex : map.keySet()) {
            Pattern pattern = compile(regex);
            if (pattern.matcher(keyText).matches())
                set.add(regex);
        }
        return set;
    }

    public List<V> getMatchedValues(String keyText) {
        List<V> values = new ArrayList<>();
        for (String regex : getMatchedKeys(keyText))
            values.add(get(regex));
        return values;
    }

    synchronized Pattern compile(String regex) {
        Pattern pattern = _cache.get(regex);
        if (pattern == null) {
            pattern = Pattern.compile(regex);
            _cache.put(regex, pattern);
        }
        return pattern;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public V put(String key, V value) {
        V old = map.put(key, value);
        _cache.remove(key);
        return old;
    }

    @Override
    public V remove(Object key) {
        V old = map.remove(key);
        _cache.remove(key);
        return old;
    }

    @Override
    public void putAll(Map<? extends String, ? extends V> m) {
        for (String key : m.keySet())
            _cache.remove(key);
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
        _cache.clear();
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<String, V>> entrySet() {
        return map.entrySet();
    }

}
