package net.bodz.bas.fn;

public interface IFilterX<T, X extends Exception> {

    boolean accept(T o)
            throws X;

    TrueFilter<Object> TRUE = new TrueFilter<Object>();
    FalseFilter<Object> FALSE = new FalseFilter<Object>();

}

class TrueFilter<T>
        implements IFilter<T> {

    @Override
    public boolean accept(T o)
            throws RuntimeException {
        return true;
    }

}

class FalseFilter<T>
        implements IFilter<T> {

    @Override
    public boolean accept(T o)
            throws RuntimeException {
        return false;
    }

}
