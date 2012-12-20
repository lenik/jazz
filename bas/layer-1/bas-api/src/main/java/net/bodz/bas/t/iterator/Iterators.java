package net.bodz.bas.t.iterator;

import java.util.*;

import net.bodz.bas.fn.IFilter;
import net.bodz.bas.fn.ITransformer;

public class Iterators {

    public static final int defaultAppxSize = 16;

    /**
     * Get an empty iterator.
     */
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

}

class EmptyIterator<T>
        implements Iterator<T> {

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
        implements Iterator<T> {

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
        implements Iterator<T> {

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

class ConcatIterator<T>
        extends PrefetchedIterator<T> {

    private List<Iterator<T>> iterators;
    private int num;

    private int currentIteratorIndex;
    private Iterator<T> currentIterator;

    /**
     * As iterators are only for (fast-forward) iterating, i.e., it won't accept to add new
     * elements, so its safe-varargs definitely.
     */
    public ConcatIterator(List<Iterator<T>> iterators) {
        if (iterators == null)
            throw new NullPointerException("iterators");
        this.iterators = iterators;
        this.num = iterators.size();
    }

    @Override
    protected T fetch() {
        if (currentIterator == null) {
            if (currentIteratorIndex < num) {
                currentIterator = iterators.get(currentIteratorIndex++);
                return fetch();
            } else
                return end();
        }
        if (!currentIterator.hasNext()) {
            currentIterator = null;
            return fetch();
        }
        return currentIterator.next();
    }

    @Override
    public void remove() {
        currentIterator.remove();
    }

}

/**
 * Interface adapter to convert an existing {@link Enumeration} to {@link Iterator}.
 */
class EnumIterator<T>
        implements Iterator<T> {

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

    final Iterator<T> orig;
    IFilter<T> filter;

    public FilteredIterator(Iterator<T> orig, IFilter<T> filter) {
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

class TransformedIterator<S, T>
        implements Iterator<T> {

    final Iterator<S> orig;
    final ITransformer<S, T> transformer;

    public TransformedIterator(Iterator<S> orig, ITransformer<S, T> transformer) {
        this.orig = orig;
        this.transformer = transformer;
    }

    @Override
    public boolean hasNext() {
        return orig.hasNext();
    }

    @Override
    public T next() {
        S src = orig.next();
        T dst = transformer.transform(src);
        return dst;
    }

    @Override
    public void remove() {
        orig.remove();
    }

}