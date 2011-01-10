package net.bodz.bas.collection.iterator;

public class EmptyImmediateIterator<T>
        extends AbstractImmediateIterator<T> {

    @Override
    public boolean isEnded() {
        return true;
    }

    @Override
    public T next()
            throws RuntimeException {
        return null;
    }

    private static final EmptyImmediateIterator<Object> instance = new EmptyImmediateIterator<Object>();

    @SuppressWarnings("unchecked")
    public static <T> EmptyImmediateIterator<T> getInstance() {
        return (EmptyImmediateIterator<T>) instance;
    }

}
