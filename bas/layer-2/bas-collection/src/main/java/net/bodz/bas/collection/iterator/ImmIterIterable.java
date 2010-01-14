package net.bodz.bas.collection.iterator;

public class ImmIterIterable<T, X extends Exception>
        implements IterableX<T, X> {

    private final ImmediateIterableX<T, X> immIterable;

    public ImmIterIterable(ImmediateIterableX<T, X> immIterable) {
        if (immIterable == null)
            throw new NullPointerException("immIterable");
        this.immIterable = immIterable;
    }

    @Override
    public IteratorX<T, X> iterator() {
        ImmediateIteratorX<? extends T, ? extends X> immIterator;
        try {
            immIterator = immIterable.iterator();
        } catch (Exception e) {
            throw new IteratorTargetException(e.getMessage(), e);
        }
        return new ImmIterIterator<T, X>(immIterator);
    }

}
