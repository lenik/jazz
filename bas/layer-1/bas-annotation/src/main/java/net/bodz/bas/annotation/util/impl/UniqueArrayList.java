package net.bodz.bas.annotation.util.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UniqueArrayList<E>
        extends ArrayList<E>
        implements IDiscardableList<E> {

    private static final long serialVersionUID = 1L;

    private final Set<E> pool = new HashSet<E>();

    public UniqueArrayList() {
        super();
    }

    public UniqueArrayList(Collection<? extends E> c) {
        super(c);
    }

    public UniqueArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public boolean add(E e) {
        if (!pool.add(e))
            return false;
        return super.add(e);
    }

    @Override
    public void add(int index, E element) {
        if (!pool.add(element))
            return;
        super.add(index, element);
    }

    @Override
    public boolean addAtPreferredIndex(int index, E element) {
        if (!pool.add(element))
            return false;
        super.add(index, element);
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        pool.addAll(c);
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        pool.addAll(c);
        return super.addAll(index, c);
    }

    @Override
    public E remove(int index) {
        E o = super.remove(index);
        pool.remove(o);
        return o;
    }

    @Override
    public boolean remove(Object o) {
        pool.remove(o);
        return super.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        pool.removeAll(c);
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        pool.retainAll(c);
        return super.retainAll(c);
    }

    @Override
    public void clear() {
        pool.clear();
        super.clear();
    }

}
