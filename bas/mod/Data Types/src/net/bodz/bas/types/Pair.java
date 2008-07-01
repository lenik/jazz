package net.bodz.bas.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Pair<TK, TV> implements Map<TK, TV>, Entry<TK, TV>, Serializable {

    private static final long serialVersionUID = -7265744237177039061L;

    public TK                 first;
    public TV                 second;

    public Pair() {
    }

    public Pair(TK first) {
        this.first = first;
    }

    public Pair(TK first, TV second) {
        this.first = first;
        this.second = second;
    }

    public TK getFirst() {
        return first;
    }

    public void setFirst(TK first) {
        this.first = first;
    }

    public TV getSecond() {
        return second;
    }

    public void setSecond(TV second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (!(object instanceof Pair))
            return false;
        Pair<?, ?> objectPair = (Pair<?, ?>) object;
        if (first == null) {
            if (objectPair.first != null)
                return false;
        } else if (!first.equals(objectPair.first))
            return false;
        if (second == null) {
            if (objectPair.second != null)
                return false;
        } else if (!second.equals(objectPair.second))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        if (first != null)
            hash += first.hashCode();
        if (second != null)
            hash += second.hashCode();
        return hash;
    }

    // Map Interface

    public void clear() {
        first = null;
    }

    public boolean containsKey(Object key) {
        if (key == null)
            throw new NullPointerException("null key");
        return key.equals(first);
    }

    public boolean containsValue(Object value) {
        if (first == null)
            return false;
        if (second == null)
            return second == value;
        return second.equals(value);
    }

    public Set<java.util.Map.Entry<TK, TV>> entrySet() {
        Set<java.util.Map.Entry<TK, TV>> set = new HashSet<java.util.Map.Entry<TK, TV>>();
        if (first != null)
            set.add(this);
        return set;
    }

    public TV get(Object key) {
        if (key == null)
            throw new NullPointerException("null key");
        if (key.equals(first))
            return second;
        return null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public Set<TK> keySet() {
        Set<TK> s = new HashSet<TK>();
        if (first != null)
            s.add(first);
        return s;
    }

    public TV put(TK key, TV value) {
        if (key == null)
            throw new NullPointerException("null key");
        first = key;
        second = value;
        return null;
    }

    public void putAll(Map<? extends TK, ? extends TV> t) {
        // assert t != null;
        Iterator<? extends TK> it = t.keySet().iterator();
        if (it.hasNext()) {
            first = it.next();
            second = t.get(first);
        }
    }

    public TV remove(Object key) {
        if (key == null)
            throw new NullPointerException("Null key");
        if (key.equals(first)) {
            first = null;
            return second;
        }
        return null;
    }

    public int size() {
        if (first == null)
            return 0;
        return 1;
    }

    public Collection<TV> values() {
        List<TV> list = new ArrayList<TV>();
        if (first != null)
            list.add(second);
        return list;
    }

    // Map.Entry

    public TK getKey() {
        return first;
    }

    public TV getValue() {
        return second;
    }

    public TV setValue(TV value) {
        TV old = second;
        second = value;
        return old;
    }

}
