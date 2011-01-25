package net.bodz.bas.ui;

public interface Var<T> {

    Class<T> getType();

    T get();

    void set(T val);

}
