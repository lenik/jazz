package net.bodz.bas.fn.legacy;

public interface Func_<T, V> {

    T eval(@SuppressWarnings("unchecked") V... args);

}
