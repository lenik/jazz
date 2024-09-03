package net.bodz.bas.t.coll;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class ListContainer<E>
        extends
        OrderedContainer<E>
        implements
        List<E> {

    final List<E> list;
    final Class<? extends E> elementType;

    public ListContainer(Class<? extends E> elementType) {
        this(new ArrayList<>(), elementType);
    }

    public ListContainer(List<E> list, Class<? extends E> elementType) {
        if (list == null)
            throw new NullPointerException("list");
        this.list = list;
        this.elementType = elementType;
    }

    @Override
    public ContainerType getType() {
        return ContainerType.LIST;
    }

    @Override
    public Class<? extends E> getElementType() {
        return elementType;
    }

    @Override
    public boolean isNamed() {
        return false;
    }

    @Override
    public boolean isIndexed() {
        return true;
    }

    @Override
    public boolean isIndexValid(int index) {
        return index >= 0 && index < list.size();
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E getFirst() {
        return super.getFirst();
    }

    @Override
    public E getLast() {
        return super.getLast();
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
    }

    @Override
    public E set(int index, E element) {
        return list.set(index, element);
    }

    @Override
    public E remove(int index) {
        return list.remove(index);
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
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return list.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public E removeFirst() {
        return super.removeFirst();
    }

    @Override
    public E removeLast() {
        return super.removeLast();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return list.addAll(c);
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
    public void clear() {
        list.clear();
    }

    //

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return list.addAll(index, c);
    }

    @Override
    public ListIterator<E> listIterator() {
        return list.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return list.listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

    @Override
    public List<E> toList() {
        return list;
    }

    @Override
    public Map<String, E> toMap() {
        return new ListAsStringMap<E>(list);
    }

    @Override
    public boolean equals(Object o) {
        return list.equals(o);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    @Override
    public String toString() {
        if (list == null)
            return "()";
        else
            return list.toString();
    }

}
