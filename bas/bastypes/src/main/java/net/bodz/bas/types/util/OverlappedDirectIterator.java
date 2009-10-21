package net.bodz.bas.types.util;

public abstract class OverlappedDirectIterator<T, X extends Throwable> extends
        _DirectIterator<T, X> {

    @Override
    public boolean isOverlapped() {
        return true;
    }

}
