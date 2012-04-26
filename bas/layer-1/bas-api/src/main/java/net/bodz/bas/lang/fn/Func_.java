package net.bodz.bas.lang.fn;

public interface Func_<T, V> {

    T eval(@SuppressWarnings("unchecked") V... args);

}
