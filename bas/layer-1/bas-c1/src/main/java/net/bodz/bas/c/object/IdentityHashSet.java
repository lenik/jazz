package net.bodz.bas.c.object;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Compare two objects by == operator, rather than using {@link Object#equals(Object)}.
 */
public class IdentityHashSet<T>
        extends AbstractSet<T>
        implements Set<T>, Serializable {

    private static final long serialVersionUID = 1L;

    public static final Object existedValue = Boolean.TRUE;

    private final IdentityHashMap<T, Object> ihm;

    public IdentityHashSet() {
        ihm = new IdentityHashMap<T, Object>();
    }

    public IdentityHashSet(int expectedMaxSize) {
        ihm = new IdentityHashMap<T, Object>(expectedMaxSize);
    }

    public IdentityHashSet(Set<? extends T> m) {
        ihm = new IdentityHashMap<T, Object>();
        addAll(m);
    }

    @Override
    public boolean isEmpty() {
        return ihm.isEmpty();
    }

    @Override
    public int size() {
        return ihm.size();
    }

    @Override
    public boolean contains(Object o) {
        return ihm.containsKey(o);
    }

    @Override
    public boolean add(T e) {
        if (ihm.containsKey(e))
            return false;
        ihm.put(e, existedValue);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!ihm.containsKey(o))
            return false;
        ihm.remove(o);
        return true;
    }

    @Override
    public void clear() {
        ihm.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return ihm.keySet().iterator();
    }

}
