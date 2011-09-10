package net.bodz.bas.collection.iterator;

public class EmptyIteratorM<T>
        extends AbstractIteratorM<T> {

    @Override
    public boolean isEnded() {
        return true;
    }

    @Override
    public T _next()
            throws RuntimeException {
        return null;
    }

    private static final EmptyIteratorM<Object> instance = new EmptyIteratorM<Object>();

    @SuppressWarnings("unchecked")
    public static <T> EmptyIteratorM<T> getInstance() {
        return (EmptyIteratorM<T>) instance;
    }

}
