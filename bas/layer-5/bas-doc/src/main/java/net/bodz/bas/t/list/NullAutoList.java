package net.bodz.bas.t.list;

import java.util.function.Supplier;

public class NullAutoList<E>
        extends NullList<E>
        implements
            IAutoList<E> {

    @Override
    public Supplier<E> getFactory() {
        return () -> {
            throw new UnsupportedOperationException();
        };
    }

    @Override
    public E getFirst() {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public E getLast() {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public E clearExcept(int retainIndex) {
        return null;
    }

    @Override
    public boolean clearExcept(int retainStart, int retainEnd) {
        return false;
    }

    @Override
    public E clearExcept(int index, Supplier<E> defaultItemFactory) {
        if (defaultItemFactory != null)
            throw new UnsupportedOperationException();
        return null;
    }

    @Override
    public <item_t extends E> item_t prepend(item_t item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <item_t extends E> item_t append(item_t item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <item_t extends E> item_t insert(int index, item_t item) {
        throw new UnsupportedOperationException();
    }

    static NullAutoList<Object> INSTANCE = new NullAutoList<>();

    @SuppressWarnings("unchecked")
    public static <E> NullAutoList<E> getInstance() {
        return (NullAutoList<E>) INSTANCE;
    }

}
