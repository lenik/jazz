package net.bodz.lily.entity;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedList<It>
        extends AbstractDecorator<List<It>>
        implements List<It> {

    private static final long serialVersionUID = 1L;

    public DecoratedList(List<It> _orig) {
        super(_orig);
    }

    @Override
    public int size() {
        return getWrapped().size();
    }

    @Override
    public boolean isEmpty() {
        return getWrapped().isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return getWrapped().contains(o);
    }

    @Override
    public Iterator<It> iterator() {
        return getWrapped().iterator();
    }

    @Override
    public Object[] toArray() {
        return getWrapped().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return getWrapped().toArray(a);
    }

    @Override
    public boolean add(It e) {
        return getWrapped().add(e);
    }

    @Override
    public boolean remove(Object o) {
        return getWrapped().remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return getWrapped().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends It> c) {
        return getWrapped().addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends It> c) {
        return getWrapped().addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return getWrapped().removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return getWrapped().retainAll(c);
    }

    @Override
    public void clear() {
        getWrapped().clear();
    }

    @Override
    public boolean equals(Object o) {
        return getWrapped().equals(o);
    }

    @Override
    public int hashCode() {
        return getWrapped().hashCode();
    }

    @Override
    public It get(int index) {
        return getWrapped().get(index);
    }

    @Override
    public It set(int index, It element) {
        return getWrapped().set(index, element);
    }

    @Override
    public void add(int index, It element) {
        getWrapped().add(index, element);
    }

    @Override
    public It remove(int index) {
        return getWrapped().remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return getWrapped().indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return getWrapped().lastIndexOf(o);
    }

    @Override
    public ListIterator<It> listIterator() {
        return getWrapped().listIterator();
    }

    @Override
    public ListIterator<It> listIterator(int index) {
        return getWrapped().listIterator(index);
    }

    @Override
    public List<It> subList(int fromIndex, int toIndex) {
        return getWrapped().subList(fromIndex, toIndex);
    }

}
