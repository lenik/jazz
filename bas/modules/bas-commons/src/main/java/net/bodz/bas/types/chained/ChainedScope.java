package net.bodz.bas.types.chained;

public interface ChainedScope<T> {

    T current();

    void enterNew();

    void enter(T scope);

    void leave();

}
