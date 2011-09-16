package net.bodz.bas.util.iter;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class Iterables {

    /**
     * Get an OTP iterator wrapper.
     */
    public static <T> Iterable<T> otp(Iterator<T> iterator) {
        return new OtpIteratorWrapper<T>(iterator);
    }

    public static <T> Iterable<T> otp(Enumeration<T> enumeration) {
        return new OtpEnumWrapper<T>(enumeration);
    }

    public static <T, X extends Throwable> Mitablex<T, X> otpMx(final Enumeration<T> enumeration,
            final boolean isOverlapped, final Class<X> exceptionType) {
        return new AbstractMitablex<T, X>() {

            @Override
            public Mitorx<? extends T, ? extends X> iterator(boolean allowOverlap) {
                final boolean resultOverlap = isOverlapped;
                if (isOverlapped) {
                    if (!allowOverlap)
                        throw new UnsupportedOperationException();
                }
                return Mitors.convert(enumeration, resultOverlap, exceptionType);
            }

            @Override
            public Iterator<T> iterator()
                    throws IteratorTargetException {
                return Iterators.convert(enumeration);
            }

        };
    }

    public static <T> List<T> toList(Iterable<T> iterable) {
        return Iterators.toList(iterable.iterator());
    }

    public static <T> List<T> toList(Iterable<T> iterable, int appxSize) {
        return Iterators.toList(iterable.iterator(), appxSize);
    }

    public static <T, X extends Exception> List<? extends T> toList(Mitablex<T, X> iterable)
            throws X {
        return Iterators.toList(iterable.iterator(false));
    }

    public static <T, X extends Exception> List<? extends T> toList(Mitablex<T, X> iterable, int appxSize)
            throws X {
        return Iterators.toList(iterable.iterator(false), appxSize);
    }

    public static <T> List<T> toListLimited(Iterable<T> iterable, int limit) {
        return Iterators.toListLimited(iterable.iterator(), limit);
    }

    public static <T> List<T> toListLimited(Iterable<T> iterable, int limit, int appxSize) {
        return Iterators.toListLimited(iterable.iterator(), limit, appxSize);
    }

    public static <T, X extends Exception> List<? extends T> toListLimited(Mitablex<T, X> iterable, int limit)
            throws X {
        return Iterators.toListLimited(iterable.iterator(false), limit);
    }

    public static <T, X extends Exception> List<? extends T> toListLimited(Mitablex<T, X> iterable, int limit,
            int appxSize)
            throws X {
        return Iterators.toListLimited(iterable.iterator(false), limit, appxSize);
    }

}

class OtpIteratorWrapper<T>
        implements Iterable<T> {

    private final Iterator<T> iterator;

    OtpIteratorWrapper(Iterator<T> iterator) {
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

class MitableIterable<T, X extends Throwable>
        implements Iterable<T> {

    private final Mitablex<T, X> mitable;
    private final boolean allowOverlap;

    /**
     * Same as {@link #ImmIterIterable(Mitablex, boolean)} with <code>allowOverlap</code> set to
     * <code>false</code>.
     */
    public MitableIterable(Mitablex<T, X> mx) {
        this(mx, false);
    }

    public MitableIterable(Mitablex<T, X> mx, boolean allowOverlap) {
        if (mx == null)
            throw new NullPointerException("mx");
        this.mitable = mx;
        this.allowOverlap = allowOverlap;
    }

    @Override
    public Iterator<T> iterator() {
        Mitorx<? extends T, ? extends X> mitor;
        try {
            mitor = mitable.iterator(allowOverlap);
        } catch (Exception e) {
            throw new IteratorTargetException(e.getMessage(), e);
        }
        return Iterators.convert(mitor);
    }

}
