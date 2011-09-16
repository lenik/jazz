package net.bodz.bas.closure;

public interface Func_<T, V> {

    T eval(@SuppressWarnings("unchecked") V... args);

}
