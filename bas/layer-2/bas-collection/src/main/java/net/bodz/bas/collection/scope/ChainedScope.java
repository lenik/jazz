package net.bodz.bas.collection.scope;

public interface ChainedScope<T> {

    T current();

    void enterNew();

    void enter(T externState);

    void leave();

}
