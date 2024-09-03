package net.bodz.bas.t.coll;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.UnexpectedException;

public class SetContainer<E>
        extends OrderedContainer<E>
        implements
            Set<E> {

    final Set<E> set;
    final Class<? extends E> elementType;

    public SetContainer(Class<? extends E> elementType) {
        this(new LinkedHashSet<>(), elementType);
    }

    public SetContainer(Set<E> set, Class<? extends E> elementType) {
        if (set == null)
            throw new NullPointerException("set");
        this.set = set;
        this.elementType = elementType;
    }

    @Override
    public ContainerType getType() {
        return ContainerType.SET;
    }

    @Override
    public Class<? extends E> getElementType() {
        return null;
    }

    @Override
    public boolean isNamed() {
        return false;
    }

    @Override
    public boolean isIndexed() {
        return false;
    }

    @Override
    public boolean isIndexValid(int index) {
        return index >= 0 && index < set.size();
    }

    @Override
    public E get(int index) {
        if (isIndexValid(index))
            throw new IndexOutOfBoundsException("" + index);
        int i = 0;
        for (E el : this) {
            if (i == index)
                return el;
            i++;
        }
        throw new IndexOutOfBoundsException("" + index);
    }

    @Override
    public int indexOf(Object o) {
        int i = 0;
        for (E el : this) {
            if (Nullables.equals(el, o))
                return i;
            i++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int i = 0;
        int lastMatch = -1;
        for (E el : this) {
            if (Nullables.equals(el, o))
                lastMatch = i;
            i++;
        }
        return lastMatch;
    }

    @Override
    public void add(int index, E element) {
        add(element);
    }

    @Override
    public E set(int index, E element) {
        E removed = remove(index);
        add(index, element);
        return removed;
    }

    @Override
    public E remove(int index) {
        if (! isIndexValid(index))
            throw new IndexOutOfBoundsException("" + index);
        int i = 0;
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            E el = iterator.next();
            if (i == index) {
                iterator.remove();
                return el;
            }
            i++;
        }
        throw new UnexpectedException();
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }

    @Override
    public Object[] toArray() {
        return set.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return set.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return set.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return set.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return set.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return set.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return set.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return set.retainAll(c);
    }

    @Override
    public void clear() {
        set.clear();
    }

    @Override
    public List<E> toList() {
        return new ArrayList<>(set);
    }

    @Override
    public Set<E> toSet() {
        return set;
    }

    @Override
    public boolean equals(Object o) {
        return set.equals(o);
    }

    @Override
    public int hashCode() {
        return set.hashCode();
    }

    @Override
    public String toString() {
        return set.toString();
    }

}
