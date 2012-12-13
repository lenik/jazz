package net.bodz.bas.t.iterator.immed;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.err.IteratorTargetException;
import net.bodz.bas.t.iterator.Iterators;

public class Mitables {

    public static <T, X extends Exception> List<? extends T> toList(IMitablex<T, X> iterable)
            throws X {
        return Mitors.toList(iterable.iterator(false));
    }

    public static <T, X extends Exception> List<? extends T> toList(IMitablex<T, X> iterable, int appxSize)
            throws X {
        return Mitors.toList(iterable.iterator(false), appxSize);
    }

    public static <T, X extends Exception> List<? extends T> toListLimited(IMitablex<T, X> iterable, int limit)
            throws X {
        return Mitors.toListLimited(iterable.iterator(false), limit);
    }

    public static <T, X extends Exception> List<? extends T> toListLimited(IMitablex<T, X> iterable, int limit,
            int appxSize)
            throws X {
        return Mitors.toListLimited(iterable.iterator(false), limit, appxSize);
    }

    public static <T, X extends Throwable> IMitablex<T, X> otpMx(final Enumeration<T> enumeration,
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

    public static <T> Iterable<T> convert(IMitablex<T, ?> mx) {
        return new MitableIterable<T>(mx);
    }

}

class MitableIterable<T>
        implements Iterable<T> {

    private final IMitablex<T, ?> mitable;
    private final boolean allowOverlap;

    /**
     * Same as {@link #ImmIterIterable(IMitablex, boolean)} with <code>allowOverlap</code> set to
     * <code>false</code>.
     */
    public MitableIterable(IMitablex<T, ?> mx) {
        this(mx, false);
    }

    public MitableIterable(IMitablex<T, ?> mx, boolean allowOverlap) {
        if (mx == null)
            throw new NullPointerException("mx");
        this.mitable = mx;
        this.allowOverlap = allowOverlap;
    }

    @Override
    public Iterator<T> iterator() {
        Mitorx<? extends T, ?> mitor;
        try {
            mitor = mitable.iterator(allowOverlap);
        } catch (Exception e) {
            throw new IteratorTargetException(e.getMessage(), e);
        }
        return Mitors.convert(mitor);
    }

}
