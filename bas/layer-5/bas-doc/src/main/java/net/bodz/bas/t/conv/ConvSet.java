package net.bodz.bas.t.conv;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ConvSet<E, E_actual>
        implements
            Set<E> {

    IConverter<E, E_actual> converter;
    Set<E_actual> actuals;

    public ConvSet(IConverter<E, E_actual> converter, Set<E_actual> set) {
        this.converter = converter;
        this.actuals = set;
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        actuals.forEach((Object t) -> {
            @SuppressWarnings("unchecked")
            E_actual ae = (E_actual) t;
            E e = converter.restore(ae);
            action.accept(e);
        });
    }

    @Override
    public int size() {
        return actuals.size();
    }

    @Override
    public boolean isEmpty() {
        return actuals.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        E_actual actual = converter._convert(o);
        return actuals.contains(actual);
    }

    @Override
    public Iterator<E> iterator() {
        return new ConvIterator<>(converter, actuals.iterator());
    }

    @Override
    public Object[] toArray() {
        Object[] array = actuals.toArray();
        for (int i = 0; i < array.length; i++) {
            @SuppressWarnings("unchecked")
            E_actual actual = (E_actual) array[i];
            E e = converter.restore(actual);
            array[i] = e;
        }
        return array;
    }

    // T super E
    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] ev) {
        Object[] actualV = actuals.toArray();
        if (ev.length < actualV.length) {
            Class<?> componentType = ev.getClass().getComponentType();
            ev = (T[]) Array.newInstance(componentType, actualV.length);
        }

        for (int i = 0; i < actualV.length; i++) {
            E_actual actual = (E_actual) actualV[i];
            E e = converter.restore(actual);
            ev[i] = (T) e;
        }
        return ev;
    }

    @Override
    public boolean add(E e) {
        E_actual actual = converter.convert(e);
        return actuals.add(actual);
    }

    @Override
    public boolean remove(Object o) {
        E_actual actual = converter._convert(o);
        return actuals.remove(actual);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            E_actual actual = converter._convert(e);
            if (!actuals.contains(actual))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean dirty = false;
        for (Object e : c) {
            E_actual actual = converter._convert(e);
            dirty |= actuals.add(actual);
        }
        return dirty;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return actuals.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean dirty = false;
        for (Object e : c) {
            E_actual actual = converter._convert(e);
            dirty |= actuals.remove(actual);
        }
        return dirty;
    }

    @Override
    public void clear() {
        actuals.clear();
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return actuals.removeIf((Object actual) -> {
            E e = converter._restore(actual);
            return filter.test(e);
        });
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ConvSet<?, ?> other = (ConvSet<?, ?>) obj;
        return actuals.equals(other.actuals);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((actuals == null) ? 0 : actuals.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return actuals.toString();
    }

}
