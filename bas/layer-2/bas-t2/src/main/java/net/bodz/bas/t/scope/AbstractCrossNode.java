package net.bodz.bas.t.scope;

public abstract class AbstractCrossNode<T> {

    AbstractCrossNode<T> xPrev;
    AbstractCrossNode<T> yNext;

    public abstract T get();

    public abstract void set(T value);

}
