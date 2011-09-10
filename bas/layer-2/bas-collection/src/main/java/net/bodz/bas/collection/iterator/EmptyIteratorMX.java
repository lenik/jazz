package net.bodz.bas.collection.iterator;

public class EmptyIteratorMX<T, X extends Exception>
        extends AbstractIteratorMX<T, X> {

    @Override
    public boolean isEnded() {
        return true;
    }

    @Override
    public T _next()
            throws X {
        return null;
    }

    private static final EmptyIteratorMX<Object, Exception> instance;
    static {
        instance = new EmptyIteratorMX<Object, Exception>();
    }

    @SuppressWarnings("unchecked")
    public static <T, X extends Exception> EmptyIteratorMX<T, X> getInstance() {
        return (EmptyIteratorMX<T, X>) instance;
    }

}
