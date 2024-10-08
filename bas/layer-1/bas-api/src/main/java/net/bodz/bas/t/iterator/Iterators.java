package net.bodz.bas.t.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Function;

import net.bodz.bas.fn.IFilter;
import net.bodz.bas.t.coll.TransformedIterator;

public class Iterators {

    public static final int defaultAppxSize = 16;

    /**
     * Get an empty iterator.
     *
     * @deprecated See {@link Collections#emptyIterator()}.
     */
    @Deprecated
    public static <T> Iterator<T> empty() {
        @SuppressWarnings("unchecked")
        Iterator<T> empty = (Iterator<T>) EmptyIterator.EMPTY;
        return empty;
    }

    /**
     * One time iterator.
     *
     * @param object
     *            The object to repeat.
     */
    public static <T> Iterator<T> once(T object) {
        return repeat(object, 1);
    }

    /**
     * Repeat specified times.
     *
     * @param object
     *            The object to repeat.
     * @param count
     *            Count of times to repeat.
     */
    public static <T> Iterator<T> repeat(T object, int count) {
        return new RepeatIterator<T>(object, count);
    }

    @SafeVarargs
    public static <T> Iterator<T> iterate(T... array) {
        return new ArrayIterator<T>(array, 0);
    }

    public static <T> Iterator<T> iterate(T[] array, int start) {
        return new ArrayIterator<T>(array, start);
    }

    @SafeVarargs
    public static <T> Iterator<T> concat(Iterator<T>... iterators) {
        return new ConcatIterator<T>(Arrays.asList(iterators));
    }

    public static <T> Iterator<T> concat(List<Iterator<T>> iterators) {
        return new ConcatIterator<T>(iterators);
    }

    /**
     * Convert an enumeration to a standard iterator.
     */
    public static <T> Iterator<T> convert(Enumeration<? extends T> enm) {
        return new EnumIterator<T>(enm);
    }

    public static <T> Set<T> toSet(Iterator<T> iterator) {
        return toSet(iterator, defaultAppxSize);
    }

    public static <T> Set<T> toSet(Iterator<T> iterator, int appxSize) {
        Set<T> set = new HashSet<T>(appxSize);
        while (iterator.hasNext()) {
            T o = iterator.next();
            set.add(o);
        }
        return set;
    }

    public static <T> Set<T> toSet(Enumeration<T> enm) {
        return toSet(enm, defaultAppxSize);
    }

    public static <T> Set<T> toSet(Enumeration<T> enm, int appxSize) {
        Set<T> set = new HashSet<T>(appxSize);
        while (enm.hasMoreElements()) {
            T o = enm.nextElement();
            set.add(o);
        }
        return set;
    }

    public static <T> List<T> toList(Iterator<T> iterator) {
        return toList(iterator, defaultAppxSize);
    }

    public static <T> List<T> toList(Iterator<T> iterator, int appxSize) {
        List<T> list = new ArrayList<T>(appxSize);
        while (iterator.hasNext()) {
            T o = iterator.next();
            list.add(o);
        }
        return list;
    }

    public static <T> List<T> toList(Enumeration<T> enm) {
        return toList(enm, defaultAppxSize);
    }

    public static <T> List<T> toList(Enumeration<T> enm, int appxSize) {
        List<T> list = new ArrayList<T>(appxSize);
        while (enm.hasMoreElements()) {
            T o = enm.nextElement();
            list.add(o);
        }
        return list;
    }

    public static <T> List<T> toListLimited(Iterator<T> iterator, int limit) {
        return toListLimited(iterator, limit, defaultAppxSize);
    }

    public static <T> List<T> toListLimited(Iterator<T> iterator, int limit, int appxSize) {
        List<T> list = new ArrayList<T>(appxSize);
        while (limit-- > 0 && iterator.hasNext()) {
            T o = iterator.next();
            list.add(o);
        }
        return list;
    }

    public static <T> Iterator<T> filter(Iterator<T> iterator, IFilter<T> filter) {
        return new FilteredIterator<T>(iterator, filter);
    }

    public static <S, T> Iterator<T> transform(Iterator<? extends S> iterable, Function<S, T> transformer) {
        return new TransformedIterator<S, T>(iterable, transformer);
    }

    public static <S, T> Iterator<T> transform(Enumeration<? extends S> enm, Function<S, T> transformer) {
        return new TransformedEnmIterator<S, T>(enm, transformer);
    }

}

class EmptyIterator<T>
        implements
            Iterator<T> {

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    static final EmptyIterator<?> EMPTY = new EmptyIterator<Object>();

}

/**
 * Repeat object self to iterator.
 */
class RepeatIterator<T>
        implements
            Iterator<T> {

    private final T object;
    private final int count;

    private int index;

    public RepeatIterator(T object, int count) {
        this.object = object;
        this.count = count;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < count;
    }

    @Override
    public T next() {
        if (index >= count)
            throw new NoSuchElementException();
        index++;
        return object;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}

class ArrayIterator<T>
        implements
            Iterator<T> {

    private final T[] array;
    private int next;

    public ArrayIterator(T[] array, int start) {
        if (array == null)
            throw new NullPointerException("array");
        this.array = array;
        this.next = start;
    }

    @Override
    public boolean hasNext() {
        return next < array.length;
    }

    @Override
    public T next() {
        if (next >= array.length)
            throw new NoSuchElementException();
        T item = array[next++];
        return item;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}

/**
 * Interface adapter to convert an existing {@link Enumeration} to {@link Iterator}.
 */
class EnumIterator<T>
        implements
            Iterator<T> {

    private final Enumeration<? extends T> enumration;

    public EnumIterator(Enumeration<? extends T> enumration) {
        if (enumration == null)
            throw new NullPointerException("enumration");
        this.enumration = enumration;
    }

    @Override
    public boolean hasNext() {
        return enumration.hasMoreElements();
    }

    @Override
    public T next() {
        return enumration.nextElement();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}

class FilteredIterator<T>
        extends PrefetchedIterator<T> {

    private final Iterator<? extends T> orig;
    private IFilter<? super T> filter;

    public FilteredIterator(Iterator<? extends T> orig, IFilter<? super T> filter) {
        if (orig == null)
            throw new NullPointerException("orig");
        if (filter == null)
            throw new NullPointerException("filter");
        this.orig = orig;
        this.filter = filter;
    }

    @Override
    protected T fetch() {
        while (orig.hasNext()) {
            T obj = orig.next();
            if (filter.accept(obj))
                return obj;
        }
        return end();
    }

}

class TransformedEnmIterator<S, T>
        implements
            Iterator<T> {

    final Enumeration<? extends S> orig;
    final Function<S, T> transformer;

    public TransformedEnmIterator(Enumeration<? extends S> orig, Function<S, T> transformer) {
        this.orig = orig;
        this.transformer = transformer;
    }

    @Override
    public boolean hasNext() {
        return orig.hasMoreElements();
    }

    @Override
    public T next() {
        S src = orig.nextElement();
        T dst = transformer.apply(src);
        return dst;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}