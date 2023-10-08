package net.bodz.bas.t.stack;

public class NamedTypePredicate<T, R>
        implements
            TypePredicate<T, R> {

    final String name;
    final TypePredicate<T, R> impl;

    public NamedTypePredicate(String name, TypePredicate<T, R> impl) {
        this.name = name;
        this.impl = impl;
    }

    @Override
    public boolean test(T val) {
        return false;
    }

}
