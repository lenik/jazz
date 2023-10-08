package net.bodz.bas.t.stack;

import java.util.function.Predicate;

@FunctionalInterface
public interface TypePredicate<T, R>
        extends
            Predicate<T> {

    @Override
    boolean test(T val);

}
