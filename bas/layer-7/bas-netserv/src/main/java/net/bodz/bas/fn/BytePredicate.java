package net.bodz.bas.fn;

import java.util.Objects;
import java.util.function.Predicate;

import net.bodz.bas.meta.decl.NotNull;

@FunctionalInterface
public interface BytePredicate
        extends Predicate<Byte> {

    @Override
    default boolean test(Byte aByte) {
        if (aByte == null)
            return false;
        return test((byte) aByte);
    }

    boolean test(byte t);

    @NotNull
    @Override
    default BytePredicate negate() {
        return (t) -> !test(t);
    }

    default BytePredicate or(BytePredicate other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) || other.test(t);
    }

    static BytePredicate isEqual(byte target) {
        return ch -> target == ch;
    }

    static BytePredicate not(BytePredicate target) {
        return (BytePredicate) target.negate();
    }

}
