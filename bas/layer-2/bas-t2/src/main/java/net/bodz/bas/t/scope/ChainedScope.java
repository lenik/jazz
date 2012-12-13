package net.bodz.bas.t.scope;

public interface ChainedScope<T> {

    T current();

    void enterNew();

    void enter(T externState);

    void leave();

}
