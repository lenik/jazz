package net.bodz.bas.t.coll;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import net.bodz.bas.c.string.StringPred;

public class ListAsStringMap<E>
        extends
        AbstractMap<String, E> {

    List<E> list;

    public ListAsStringMap(List<E> list) {
        if (list == null)
            throw new NullPointerException("list");
        this.list = list;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Collection<E> values() {
        return list;
    }

    @Override
    public Set<Entry<String, E>> entrySet() {
        return new ListAsStringEntries<E>(list);
    }

    @Override
    public boolean containsKey(Object key) {
        if (key instanceof String) {
            String skey = (String) key;
            if (StringPred.isDecimal(skey)) {
                int index = Integer.parseInt(skey);
                return index >= 0 && index < list.size();
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return list.contains(value);
    }

    @Override
    public E get(Object key) {
        if (key instanceof String) {
            String skey = (String) key;
            if (StringPred.isDecimal(skey)) {
                int index = Integer.parseInt(skey);
                return list.get(index);
            }
        }
        return null;
    }

    @Override
    public E put(String key, E value) {
        if (StringPred.isDecimal(key)) {
            int index = Integer.parseInt(key);
            return list.set(index, value);
        }
        throw new IllegalArgumentException("bad key: " + key);
    }

    @Override
    public E remove(Object key) {
        if (key == null)
            throw new NullPointerException("key");
        if (key instanceof String) {
            String skey = (String) key;
            if (StringPred.isDecimal(skey)) {
                int index = Integer.parseInt(skey);
                return list.remove(index);
            }
        }
        return null;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(list);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ListAsStringMap<?> other = (ListAsStringMap<?>) obj;
        return Objects.equals(list, other.list);
    }

    @Override
    public String toString() {
        return list.toString();
    }

}
