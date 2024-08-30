package net.bodz.bas.t.coll;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.c.type.TypeParam;

public class EmptyContainer<E>
        implements
            IContainer<E> {

    final ContainerType containerType;
    final Class<? extends E> elementType;

    public EmptyContainer(ContainerType containerType) {
        this.containerType = containerType;
        this.elementType = TypeParam.infer1(getClass(), EmptyContainer.class, 0);
    }

    public EmptyContainer(ContainerType containerType, Class<? extends E> elementType) {
        this.containerType = containerType;
        this.elementType = elementType;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return Collections.emptyIterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Class<?> componentType = a.getClass().getComponentType();
        @SuppressWarnings("unchecked")
        T[] array = (T[]) Array.newInstance(componentType, 0);
        return array;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
    }

    @Override
    public ContainerType getType() {
        return containerType;
    }

    @Override
    public Class<? extends E> getElementType() {
        return elementType;
    }

    @Override
    public boolean isNamed() {
        return containerType == ContainerType.STRING_MAP;
    }

    @Override
    public Set<String> names() {
        return Collections.emptySet();
    }

    @Override
    public boolean isNamePresent(String name) {
        return false;
    }

    @Override
    public E get(String name) {
        return null;
    }

    @Override
    public E set(String name, E element) {
        return null;
    }

    @Override
    public boolean addNamed(E element, String name) {
        return false;
    }

    @Override
    public E removeNamed(String name) {
        return null;
    }

    @Override
    public boolean isIndexed() {
        switch (containerType) {
        case ARRAY:
        case LIST:
        case SINGLE:
            return true;
        default:
            return false;
        }
    }

    @Override
    public boolean isIndexValid(int index) {
        return false;
    }

    @Override
    public E get(int index) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int indexOf(Object o) {
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return -1;
    }

    @Override
    public void add(int index, E element) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public E set(int index, E element) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public E remove(int index) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public List<E> toList() {
        return Collections.emptyList();
    }

    @Override
    public Set<E> toSet() {
        return Collections.emptySet();
    }

    @Override
    public Map<String, E> toMap() {
        return Collections.emptyMap();
    }

}
