package net.bodz.bas.collection.set;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class OrderPreservedSet<E>
        implements Set<E>, Serializable {

    private static final long serialVersionUID = 111818316100018739L;

    private List<E> list;

    protected OrderPreservedSet(List<E> list) {
        if (list == null)
            throw new NullPointerException("list");
        this.list = list;
    }

    @Override
    public boolean add(E e) {
        if (list.contains(e))
            return false;
        list.add(e);
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean dirty = false;
        for (E e : c)
            dirty |= (add(e));
        return dirty;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean all = true;
        for (Object e : c)
            all &= contains(e);
        return all;
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

}
