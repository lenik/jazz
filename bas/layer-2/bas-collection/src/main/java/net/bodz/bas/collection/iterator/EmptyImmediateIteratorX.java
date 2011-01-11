package net.bodz.bas.collection.iterator;

public class EmptyImmediateIteratorX<T, X extends Exception>
        extends AbstractImmediateIteratorX<T, X> {

    @Override
    public boolean isEnded() {
        return true;
    }

    @Override
    public T next()
            throws X {
        return null;
    }

    private static final EmptyImmediateIteratorX<Object, Exception> instance;
    static {
        instance = new EmptyImmediateIteratorX<Object, Exception>();
    }

    @SuppressWarnings("unchecked")
    public static <T, X extends Exception> EmptyImmediateIteratorX<T, X> getInstance() {
        return (EmptyImmediateIteratorX<T, X>) instance;
    }

}
