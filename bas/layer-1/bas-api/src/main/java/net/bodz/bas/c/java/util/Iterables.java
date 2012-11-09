package net.bodz.bas.c.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.model.ICreator;
import net.bodz.bas.model.IFilter;
import net.bodz.bas.model.ITransformer;
import net.bodz.bas.model.NewInstanceCreator;

public class Iterables {

    public static <T> Iterable<T> once(T object) {
        return repeat(object, 1);
    }

    public static <T> Iterable<T> repeat(final T object, final int count) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return Iterators.repeat(object, count);
            }
        };
    }

    public static <T> Iterable<T> concat(final List<Iterable<T>> iterables) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                List<Iterator<T>> iterators = new ArrayList<>(iterables.size());

                for (Iterable<T> iterable : iterables)
                    iterators.add(iterable.iterator());

                return Iterators.concat(iterators);
            }
        };
    }

    @SafeVarargs
    public static <T> Iterable<T> concat(Iterable<T>... iterables) {
        return concat(Arrays.asList(iterables));
    }

    public static <T> Iterable<T> create(ICreator<? extends Iterator<T>> iteratorCreator) {
        return new CreateIterable<T>(iteratorCreator);
    }

    public static <T> Iterable<T> create(Class<? extends Iterator<T>> iteratorClass) {
        return new CreateIterable<T>(iteratorClass);
    }

    /**
     * Get an OTP iterator wrapper.
     */
    public static <T> Iterable<T> otp(Iterator<T> iterator) {
        return new OtpIteratorIterable<T>(iterator);
    }

    public static <T> Iterable<T> otp(Enumeration<T> enumeration) {
        return new OtpEnumWrapper<T>(enumeration);
    }

    public static <T> List<T> toList(Iterable<T> iterable) {
        return Iterators.toList(iterable.iterator());
    }

    public static <T> List<T> toList(Iterable<T> iterable, int appxSize) {
        return Iterators.toList(iterable.iterator(), appxSize);
    }

    public static <T> List<T> toListLimited(Iterable<T> iterable, int limit) {
        return Iterators.toListLimited(iterable.iterator(), limit);
    }

    public static <T> List<T> toListLimited(Iterable<T> iterable, int limit, int appxSize) {
        return Iterators.toListLimited(iterable.iterator(), limit, appxSize);
    }

    public static <T> Iterable<T> filter(Iterable<T> iterable, IFilter<T> filter) {
        return new FilteredIterable<>(iterable, filter);
    }

    public static <S, T> Iterable<T> transform(Iterable<S> iterable, ITransformer<S, T> transformer) {
        return new TransformedIterable<>(iterable, transformer);
    }

}

class CreateIterable<T>
        implements Iterable<T> {

    final ICreator<? extends Iterator<T>> iteratorCreator;

    public CreateIterable(Class<? extends Iterator<T>> iteratorClass) {
        this(new NewInstanceCreator<Iterator<T>>(iteratorClass));
    }

    public CreateIterable(ICreator<? extends Iterator<T>> iteratorCreator) {
        this.iteratorCreator = iteratorCreator;
    }

    @Override
    public Iterator<T> iterator() {
        try {
            return iteratorCreator.create();
        } catch (CreateException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}

class OtpIteratorIterable<T>
        implements Iterable<T> {

    private final Iterator<T> iterator;

    OtpIteratorIterable(Iterator<T> iterator) {
        if (iterator == null)
            throw new NullPointerException("iterator");
        this.iterator = iterator;
    }

    @Override
    public Iterator<T> iterator() {
        if (iterator == null)
            throw new IllegalStateException("Already iterated");
        return iterator;
    }

}

class OtpEnumWrapper<T>
        implements Iterable<T> {

    private Enumeration<T> enumeration;

    OtpEnumWrapper(Enumeration<T> enumerator) {
        if (enumerator == null)
            throw new NullPointerException("enumeration");
        this.enumeration = enumerator;
    }

    @Override
    public Iterator<T> iterator() {
        if (enumeration == null)
            throw new IllegalStateException("Already iterated");
        Iterator<T> iterator = Iterators.convert(enumeration);
        enumeration = null;
        return iterator;
    }

}

class FilteredIterable<T>
        implements Iterable<T> {

    final Iterable<T> orig;
    final IFilter<T> filter;

    public FilteredIterable(Iterable<T> orig, IFilter<T> filter) {
        this.orig = orig;
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> iterator = orig.iterator();
        return new FilteredIterator<T>(iterator, filter);
    }

}

class TransformedIterable<S, T>
        implements Iterable<T> {

    final Iterable<S> orig;
    final ITransformer<S, T> transformer;

    public TransformedIterable(Iterable<S> orig, ITransformer<S, T> transformer) {
        this.orig = orig;
        this.transformer = transformer;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<S> iterator = orig.iterator();
        return new TransformedIterator<S, T>(iterator, transformer);
    }

}