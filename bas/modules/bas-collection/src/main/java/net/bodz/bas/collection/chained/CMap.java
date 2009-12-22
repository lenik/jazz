package net.bodz.bas.collection.chained;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CMap<K, V> extends _ChainedScope<Map<K, V>> implements Map<K, V> {

    public CMap() {
        super();
    }

    public CMap(Map<K, V> start) {
        super(start);
    }

    /** Create a HashMap by default */
    @Override
    protected Map<K, V> create() {
        return new HashMap<K, V>();
    }

    @Override
    protected Map<K, V> link(Map<K, V> _this, Map<K, V> next) {
        return new Link<K, V>(_this, next);
    }

    static class Link<Kl, Vl> extends SLink<Map<Kl, Vl>> implements Map<Kl, Vl> {

        public Link(Map<Kl, Vl> _this, Map<Kl, Vl> next) {
            super(_this, next);
        }

        public void clear() {
            _this.clear();
        }

        public Vl put(Kl key, Vl value) {
            return _this.put(key, value);
        }

        public void putAll(Map<? extends Kl, ? extends Vl> m) {
            _this.putAll(m);
        }

        public Vl remove(Object key) {
            return _this.remove(key);
        }

        public int size() {
            return _this.size();
        }

        // MERGED OPERATIONS

        public boolean containsKey(Object key) {
            return _this.containsKey(key) || next.containsKey(key);
        }

        public boolean containsValue(Object value) {
            return _this.containsValue(value) || next.containsKey(value);
        }

        public Vl get(Object key) {
            return _this.containsKey(key) ? _this.get(key) : next.get(key);
        }

        public boolean isEmpty() {
            return _this.isEmpty() && next.isEmpty();
        }

        public Set<java.util.Map.Entry<Kl, Vl>> entrySet() {
            CSet<Entry<Kl, Vl>> chain = new CSet<Entry<Kl, Vl>>(next.entrySet());
            chain.enter(_this.entrySet());
            return chain;
        }

        public Set<Kl> keySet() {
            CSet<Kl> chain = new CSet<Kl>(next.keySet());
            chain.enter(_this.keySet());
            return chain;
        }

        public Collection<Vl> values() {
            return _this.values();
        }

    }

    public void clear() {
        head.clear();
    }

    public boolean containsKey(Object key) {
        return head.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return head.containsValue(value);
    }

    public Set<java.util.Map.Entry<K, V>> entrySet() {
        return head.entrySet();
    }

    public V get(Object key) {
        return head.get(key);
    }

    public boolean isEmpty() {
        return head.isEmpty();
    }

    public Set<K> keySet() {
        return head.keySet();
    }

    public V put(K key, V value) {
        return head.put(key, value);
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        head.putAll(m);
    }

    public V remove(Object key) {
        return head.remove(key);
    }

    public int size() {
        return head.size();
    }

    public Collection<V> values() {
        return head.values();
    }

}
