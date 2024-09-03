package net.bodz.bas.t.coll;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import net.bodz.bas.c.java.util.Arrays;

public class ArrayContainer<E>
        extends OrderedContainer<E>
        implements
            List<E> {

    E[] array;

    public ArrayContainer() {
        super();
    }

    @SuppressWarnings("unchecked")
    public ArrayContainer(Class<? extends E> componentType) {
        super(componentType);
        this.array = (E[]) Array.newInstance(componentType, 0);
    }

    @SafeVarargs
    @SuppressWarnings("unchecked")
    public ArrayContainer(E... array) {
        super((Class<E>) array.getClass().getComponentType());
        this.array = array;
    }

    @Override
    public ContainerType getType() {
        return ContainerType.ARRAY;
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
        return index >= 0 && index < array.length;
    }

    boolean isInsertIndexValid(int index) {
        return index >= 0 && index <= array.length;
    }

    @Override
    public E get(int index) {
        return array[index];
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
        return Arrays.indexOf(array, o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return Arrays.lastIndexOf(array, o);
    }

    @Override
    public void add(int index, E element) {
        if (! isInsertIndexValid(index))
            throw new IndexOutOfBoundsException("" + index);
        E[] newArray = newArray(array.length + 1);
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index, newArray, index + 1, array.length - index);
        newArray[index] = element;
        this.array = newArray;
    }

    @Override
    public E set(int index, E element) {
        E old = array[index];
        array[index] = element;
        return old;
    }

    @Override
    public E remove(int index) {
        E[] newArray = newArray(array.length - 1);
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, newArray.length - index);
        E old = array[index];
        array = newArray;
        return old;
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public boolean isEmpty() {
        return array.length == 0;
    }

    @Override
    public boolean contains(Object o) {
        return Arrays.indexOf(array, o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return Arrays.asList(array).iterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, array.length);
    }

    @SuppressWarnings("unchecked")
    E[] newArray(int length) {
        return (E[]) Array.newInstance(elementType, length);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null)
            throw new NullPointerException("a");
        Class<?> componentType = a.getClass().getComponentType();
        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) Array.newInstance(componentType, array.length);
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    @Override
    public boolean add(E e) {
        add(array.length, e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1)
            return false;
        remove(index);
        return true;
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
        return Arrays.asList(array).containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(array.length, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        Class<E> componentType = (Class<E>) array.getClass().getComponentType();
        array = (E[]) Array.newInstance(componentType, 0);
    }

    //

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (! isInsertIndexValid(index))
            throw new IndexOutOfBoundsException("" + index);

        int d = c.size();
        E[] newArray = newArray(array.length + d);
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index, newArray, index + d, array.length - index);

        int pos = index;
        for (E el : c) {
            newArray[pos++] = el;
        }
        this.array = newArray;
        return true;
    }

    @Override
    public ListIterator<E> listIterator() {
        return Arrays.asList(array).listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return Arrays.asList(array).listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return Arrays.asList(array).subList(fromIndex, toIndex);
    }

    @Override
    public List<E> toList() {
        return Arrays.asList(array);
    }

    @Override
    public Map<String, E> toMap() {
        return new ListAsStringMap<E>(Arrays.asList(array));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + java.util.Arrays.deepHashCode(array);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ArrayContainer<?> other = (ArrayContainer<?>) obj;
        return java.util.Arrays.deepEquals(array, other.array);
    }

    @Override
    public String toString() {
        return Arrays.asList(array).toString();
    }

}
