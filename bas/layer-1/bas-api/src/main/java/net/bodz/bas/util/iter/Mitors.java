package net.bodz.bas.util.iter;

import java.util.Enumeration;

import net.bodz.bas.util.arch.ICreatorX;

public class Mitors {

    @SuppressWarnings("unchecked")
    public static <T, X extends Throwable> Mitorx<T, X> empty() {
        return (Mitorx<T, X>) EmptyMitor.INSTANCE;
    }

    public static <T, X extends Throwable> Mitorx<T, X> lazyCreate(ICreatorX<? extends T, ? extends X> creator) {
        return Mitors.<T, X> lazyCreate(creator, 1, (Object[]) null);
    }

    public static <T, X extends Throwable> Mitorx<T, X> lazyCreate(ICreatorX<? extends T, ? extends X> creator,
            int count) {
        return Mitors.<T, X> lazyCreate(creator, count, (Object[]) null);
    }

    public static <T, X extends Throwable> Mitorx<T, X> lazyCreate(ICreatorX<? extends T, ? extends X> creator,
            Object... createParameters) {
        return Mitors.<T, X> lazyCreate(creator, 1, createParameters);
    }

    public static <T, X extends Throwable> Mitorx<T, X> lazyCreate(ICreatorX<T, X> creator, int count,
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

}

class EmptyMitor<T>
        extends AbstractMitor<T> {

    @Override
    public boolean isEnded() {
        return true;
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

    private final ICreatorX<? extends T, ? extends X> creator;
    private final Object[] createParameters;

    private final int count;
    private int createdCount;

    public FactoryMitor(ICreatorX<? extends T, ? extends X> creator, int count, Object... createParameters) {
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
