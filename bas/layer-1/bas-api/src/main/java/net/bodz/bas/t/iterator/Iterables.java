package net.bodz.bas.t.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.fn.IFilter;
import net.bodz.bas.t.factory.IFactory;
import net.bodz.bas.t.factory.Instantiator;

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
                List<Iterator<T>> iterators = new ArrayList<Iterator<T>>(iterables.size());

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

    public static <T> Iterable<T> create(IFactory<? extends Iterator<T>> iteratorCreator) {
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

    public static <T> Set<T> toSet(Iterable<T> iterable) {
        return Iterators.toSet(iterable.iterator());
    }

    public static <T> Set<T> toSet(Iterable<T> iterable, int appxSize) {
        return Iterators.toSet(iterable.iterator(), appxSize);
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

    public static <T> Iterable<T> filter(Iterable<? extends T> iterable, IFilter<? super T> filter) {
        return new FilteredIterable<T>(iterable, filter);
    }

    public static <S, T> Iterable<T> transform(Iterable<S> iterable, Function<S, T> transformer) {
        return new TransformedIterable<S, T>(iterable, transformer);
    }

}

class CreateIterable<T>
        implements Iterable<T> {

    final IFactory<? extends Iterator<T>> iteratorCreator;

    public CreateIterable(Class<? extends Iterator<T>> iteratorClass) {
        this(new Instantiator<Iterator<T>>(iteratorClass));
    }

    public CreateIterable(IFactory<? extends Iterator<T>> iteratorCreator) {
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

    final Iterable<? extends T> orig;
    final IFilter<? super T> filter;

    public FilteredIterable(Iterable<? extends T> orig, IFilter<? super T> filter) {
        this.orig = orig;
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<? extends T> iterator = orig.iterator();
        return new FilteredIterator<T>(iterator, filter);
    }

}

class TransformedIterable<S, T>
        implements Iterable<T> {

    final Iterable<? extends S> orig;
    final Function<S, T> transformer;

    public TransformedIterable(Iterable<? extends S> orig, Function<S, T> transformer) {
        this.orig = orig;
        this.transformer = transformer;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<? extends S> iterator = orig.iterator();
        return new TransformedIterator<S, T>(iterator, transformer);
    }

}