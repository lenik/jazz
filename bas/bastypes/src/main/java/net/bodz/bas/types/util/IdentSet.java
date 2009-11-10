package net.bodz.bas.types.util;

import java.util.AbstractSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Compare two objects by == operator, rather than using {@link Object#equals(Object)}.
 */
public class IdentSet extends AbstractSet<Object> implements Set<Object> {

    private static final long serialVersionUID = -6499268314160028115L;

    private final IdentityHashMap<Object, Object> ihm;

    public IdentSet() {
        ihm = new IdentityHashMap<Object, Object>();
    }

    public IdentSet(int expectedMaxSize) {
        ihm = new IdentityHashMap<Object, Object>(expectedMaxSize);
    }

    public IdentSet(Map<? extends Object, ? extends Object> m) {
        ihm = new IdentityHashMap<Object, Object>(m);
    }

    static final Object t = null;

    @Override
    public boolean isEmpty() {
        return ihm.isEmpty();
    }

    @Override
    public int size() {
        return ihm.size();
    }

    @Override
    public boolean add(Object e) {
        boolean exists = ihm.containsKey(e);
        ihm.put(e, t);
        return !exists;
    }

    @Override
    public boolean remove(Object o) {
        boolean exists = ihm.containsKey(o);
        ihm.remove(o);
        return exists;
    }

    @Override
    public void clear() {
        ihm.clear();
    }

    @Override
    public Iterator<Object> iterator() {
        return ihm.keySet().iterator();
    }

}
