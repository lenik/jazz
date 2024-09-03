package net.bodz.bas.t.coll;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.type.TypeParam;

public class SingleContainer<E>
        extends OrderedContainer<E>
        implements
            IContainer<E> {

    E value;
    final Class<? extends E> type;
    final OverflowAction overflowAction;

    public SingleContainer() {
        this(OverflowAction.ERROR);
    }

    public SingleContainer(OverflowAction overflowAction) {
        this.overflowAction = overflowAction;
        this.type = TypeParam.infer1(getClass(), SingleContainer.class, 0);
    }

    public SingleContainer(Class<? extends E> elementType) {
        this(elementType, OverflowAction.ERROR);
    }

    public SingleContainer(Class<? extends E> type, OverflowAction overflowAction) {
        this.type = type;
        this.overflowAction = overflowAction;
    }

    public static <T> SingleContainer<T> of(T obj) {
        SingleContainer<T> container = new SingleContainer<T>();
        container.set(0, obj);
        return container;
    }

    @Override
    public ContainerType getType() {
        return ContainerType.SINGLE;
    }

    public OverflowAction getOverflowAction() {
        return overflowAction;
    }

    @Override
    public Class<? extends E> getElementType() {
        return type;
    }

    public E get() {
        return value;
    }

    public void set(E value) {
        this.value = value;
    }

    public SingleContainer<E> value(E value) {
        this.value = value;
        return this;
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
        return index >= 0 && index < size();
    }

    boolean isInsertIndexValid(int index) {
        return index >= 0 && index <= size();
    }

    @Override
    public E get(int index) {
        if (! isIndexValid(index))
            throw new IndexOutOfBoundsException("" + index);
        return value;
    }

    @Override
    public int indexOf(Object o) {
        if (value == null)
            return -1;
        if (Nullables.equals(value, o))
            return 0;
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (value == null)
            return -1;
        if (Nullables.equals(value, o))
            return 0;
        return -1;
    }

    @Override
    public void add(int index, E element) {
        if (! isInsertIndexValid(index))
            throw new IndexOutOfBoundsException("" + index);
        if (value == null) {
            value = element;
        } else {
            switch (overflowAction) {
            case ERROR:
                throw new IllegalStateException("overflow");
            case REPLACE:
                value = element;
                break;
            case IGNORE:
            default:
            }
        }
    }

    @Override
    public E set(int index, E element) {
        if (! isIndexValid(index))
            throw new IndexOutOfBoundsException("" + index);
        E old = value;
        value = element;
        return old;
    }

    @Override
    public E remove(int index) {
        if (! isIndexValid(index))
            throw new IndexOutOfBoundsException("" + index);
        E old = value;
        value = null;
        return old;
    }

    @Override
    public int size() {
        return value == null ? 0 : 1;
    }

    @Override
    public boolean isEmpty() {
        return value == null;
    }

    @Override
    public boolean contains(Object o) {
        return Nullables.equals(value, o);
    }

    @Override
    public Iterator<E> iterator() {
        if (value == null)
            return Collections.emptyIterator();
        else
            return Arrays.asList(value).iterator();
    }

    @Override
    public Object[] toArray() {
        if (value == null)
            return new Object[0];
        else
            return new Object[] { value };
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        Class<T> componentType = (Class<T>) a.getClass().getComponentType();
        if (value == null)
            return (T[]) Array.newInstance(componentType, 0);

        T[] array = (T[]) Array.newInstance(componentType, 1);
        array[0] = (T) value;
        return array;
    }

    @Override
    public boolean add(E e) {
        if (value == null) {
            value = e;
            return true;
        } else {
            switch (overflowAction) {
            case ERROR:
                throw new IllegalStateException("overflow");
            case REPLACE:
                value = e;
                return true;
            case IGNORE:
            default:
                return false;
            }
        }
    }

    @Override
    public boolean remove(Object o) {
        if (Nullables.equals(value, o)) {
            value = null;
            return true;
        } else
            return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (value == null)
            return true;
        else
            return c.contains(value);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean changed = false;
        for (E el : c) {
            changed |= add(el);
        }
        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object el : c) {
            changed |= remove(el);
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (value != null) {
            if (! c.contains(value)) {
                value = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        value = null;
    }

    @Override
    public List<E> toList() {
        if (value == null)
            return Collections.emptyList();
        else
            return Arrays.asList(value);
    }

    @Override
    public Set<E> toSet() {
        if (value == null)
            return Collections.emptySet();
        LinkedHashSet<E> set = new LinkedHashSet<>();
        set.add(value);
        return set;
    }

    @Override
    public Map<String, E> toMap() {
        Map<String, E> map = new HashMap<>();
        if (value != null)
            map.put("0", value);
        return map;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, type);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SingleContainer<?> other = (SingleContainer<?>) obj;
        return Objects.equals(value, other.value) && Objects.equals(type, other.type);
    }

    @Override
    public String toString() {
        return Arrays.asList(toArray()).toString();
    }

}
