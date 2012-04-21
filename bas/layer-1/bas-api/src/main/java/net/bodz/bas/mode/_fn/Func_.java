package net.bodz.bas.mode._fn;

public interface Func_<T, V> {

    T eval(@SuppressWarnings("unchecked") V... args);

}
