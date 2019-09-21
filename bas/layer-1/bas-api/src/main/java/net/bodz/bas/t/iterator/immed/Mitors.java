package net.bodz.bas.t.iterator.immed;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import net.bodz.bas.err.Err;
import net.bodz.bas.err.IteratorTargetException;
import net.bodz.bas.t.factory.IFactoryX;
import net.bodz.bas.t.iterator.Iterators;

public class Mitors {

    public static final int defaultAppxSize = Iterators.defaultAppxSize;

    @SuppressWarnings("unchecked")
    public static <T, X extends Throwable> Mitorx<T, X> empty() {
        return (Mitorx<T, X>) EmptyMitor.INSTANCE;
    }

    public static <T, X extends Exception> List<T> toList(Mitorx<T, X> iterator)
            throws X {
        return toList(iterator, defaultAppxSize);
    }

    public static <T, X extends Exception> List<T> toList(Mitorx<T, X> iterator, int appxSize)
            throws X {
        List<T> list = new ArrayList<T>(appxSize);
        T o;
        while ((o = iterator._next()) != null || !iterator.isEnded())
            list.add(iterator.deoverlap(o));
        return list;
    }

    public static <T, X extends Exception> List<T> toListLimited(Mitorx<T, X> iterator, int limit)
            throws X {
        return toListLimited(iterator, limit, defaultAppxSize);
    }

    public static <T, X extends Exception> List<T> toListLimited(Mitorx<T, X> iterator, int limit, int appxSize)
            throws X {
        List<T> list = new ArrayList<T>(appxSize);
        T o;
        while (limit-- > 0 && ((o = iterator._next()) != null || !iterator.isEnded()))
            list.add(iterator.deoverlap(o));
        return list;
    }

    public static <T, X extends Throwable> Mitorx<T, X> lazyCreate(IFactoryX<? extends T, ? extends X> creator) {
        return Mitors.<T, X> lazyCreate(creator, 1, (Object[]) null);
    }

    public static <T, X extends Throwable> Mitorx<T, X> lazyCreate(IFactoryX<? extends T, ? extends X> creator,
            int count) {
        return Mitors.<T, X> lazyCreate(creator, count, (Object[]) null);
    }

    public static <T, X extends Throwable> Mitorx<T, X> lazyCreate(IFactoryX<? extends T, ? extends X> creator,
            Object... createParameters) {
        return Mitors.<T, X> lazyCreate(creator, 1, createParameters);
    }

    public static <T, X extends Throwable> Mitorx<T, X> lazyCreate(IFactoryX<T, X> creator, int count,
            Object... createParameters) {
        return new FactoryMitor<T, X>(creator, count, createParameters);
    }

    public static <T> Mitor<T> convert(Enumeration<T> enumeration, boolean isOverlapped) {
        return new EnumMitor<T>(enumeration, isOverlapped);
    }

    public static <T, X extends Throwable> Mitorx<T, X> convert(Enumeration<T> enumeration, boolean isOverlapped,
            Class<X> exceptionType) {
        return new EnumMitorx<T, X>(enumeration, isOverlapped, exceptionType);
    }

    /**
     * Convert an immediate iterator (Mitor) to a standard iterator.
     * 
     * Whether the converted iterator is overlapped is undetermined.
     */
    public static <T> Iterator<T> convert(Mitorx<? extends T, ?> mitor) {
        MitorIterator<T> iterator = new MitorIterator<T>(mitor);
        return iterator;
    }

}

class EmptyMitor<T>
        extends AbstractMitor<T> {

    public EmptyMitor() {
        end();
    }

    @Override
    public T _next()
            throws RuntimeException {
        return null;
    }

    public static final EmptyMitor<Object> INSTANCE = new EmptyMitor<Object>();

}

class FactoryMitor<T, X extends Throwable>
        extends AbstractMitorx<T, X> {

    private final IFactoryX<? extends T, ? extends X> creator;
    private final Object[] createParameters;

    private final int count;
    private int createdCount;

    public FactoryMitor(IFactoryX<? extends T, ? extends X> creator, int count, Object... createParameters) {
        if (creator == null)
            throw new NullPointerException("creator");
        if (count < 0)
            throw new IllegalArgumentException("Count is negative: " + count);
        this.creator = creator;
        this.count = count;
        this.createParameters = createParameters;
    }

    @Override
    public T _next()
            throws X {
        if (createdCount < count) {
            T instance;
            if (createParameters == null)
                instance = creator.create();
            else
                instance = creator.create(createParameters);
            createdCount++;
            return instance;
        }
        return end();
    }

}

class EnumMitor<T>
        extends AbstractMitor<T> {

    private final Enumeration<T> enumration;
    private final boolean isOverlapped;

    public EnumMitor(Enumeration<T> enumration, boolean isOverlapped) {
        if (enumration == null)
            throw new NullPointerException("enumration");
        this.enumration = enumration;
        this.isOverlapped = isOverlapped;
    }

    @Override
    public boolean isOverlapped() {
        return isOverlapped;
    }

    @Override
    public T _next() {
        if (enumration.hasMoreElements())
            return enumration.nextElement();
        else
            return end();
    }

}

class EnumMitorx<T, X extends Throwable>
        extends AbstractMitorx<T, X> {

    private final Enumeration<T> enumration;
    private final boolean isOverlapped;
    private final Class<X> exceptionType;

    public EnumMitorx(Enumeration<T> enumration, boolean isOverlapped, Class<X> exceptionType) {
        if (enumration == null)
            throw new NullPointerException("enumration");
        if (exceptionType == null)
            throw new NullPointerException("exceptionType");
        this.enumration = enumration;
        this.isOverlapped = isOverlapped;
        this.exceptionType = exceptionType;
    }

    @Override
    public boolean isOverlapped() {
        return isOverlapped;
    }

    @Override
    public T _next()
            throws X {
        try {
            if (enumration.hasMoreElements())
                return enumration.nextElement();
            else
                return end();
        } catch (IteratorTargetException e) {
            throw exceptionType.cast(e.getCause());
        }
    }

}

class MitorIterator<T>
        implements Iterator<T> {

    private final Mitorx<? extends T, ?> mitor;

    private static final int UNKNOWN = 0;
    private static final int ITERATED = 1;
    private static final int ENDED = 2;
    private int state = UNKNOWN;

    private T lastIteratedValue;

    public MitorIterator(IMitablex<T, ?> mitable, boolean allowOverlap) {
        if (mitable == null)
            throw new NullPointerException("mitable");
        this.mitor = mitable.iterator(allowOverlap);
    }

    public MitorIterator(Mitorx<? extends T, ?> mitor) {
        if (mitor == null)
            throw new NullPointerException("mitor");
        this.mitor = mitor;
    }

    @Override
    public boolean hasNext() {
        switch (state) {
        case UNKNOWN:
        default:
            try {
                lastIteratedValue = mitor._next();
            } catch (Throwable e) {
                throw Err.throwOrWrap(IteratorTargetException.class, e);
            }
            if (lastIteratedValue == null)
                if (mitor.isEnded()) {
                    state = ENDED;
                    return false;
                }
            state = ITERATED;
        case ITERATED:
            return true;
        case ENDED:
            return false;
        }
    }

    @Override
    public T next() {
        switch (state) {
        case UNKNOWN:
        default:
            try {
                lastIteratedValue = mitor._next();
            } catch (Throwable e) {
                throw Err.throwOrWrap(IteratorTargetException.class, e);
            }
            if (lastIteratedValue == null)
                if (mitor.isEnded()) {
                    state = ENDED;
                    throw new NoSuchElementException();
                }
            return lastIteratedValue;
        case ITERATED:
            state = UNKNOWN;
            return lastIteratedValue;
        case ENDED:
            throw new NoSuchElementException();
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
