package net.bodz.bas.types.util;

import net.bodz.bas.lang.ControlBreak;

public abstract class _DirectIterator<T, X extends Throwable> implements DirectIterator<T, X> {

    @Override
    public boolean isOverlapped() {
        return false;
    }

    @Override
    public T getNext() throws X, ControlBreak {
        if (next())
            return get();
        else
            throw new ControlBreak();
    }

}
