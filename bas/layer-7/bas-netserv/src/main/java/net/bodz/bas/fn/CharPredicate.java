package net.bodz.bas.fn;

import java.util.Objects;
import java.util.function.Predicate;

import net.bodz.bas.meta.decl.NotNull;

@FunctionalInterface
public interface CharPredicate
        extends Predicate<Character> {

    @Override
    default boolean test(Character character) {
        if (character == null)
            return false;
        return test((char) character);
    }

    boolean test(char t);

    @NotNull
    default CharPredicate negate() {
        return (t) -> !test(t);
    }

    default CharPredicate or(CharPredicate other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) || other.test(t);
    }

    static CharPredicate isEqual(char target) {
        return ch -> target == ch;
    }

    static CharPredicate not(CharPredicate target) {
        return (CharPredicate) target.negate();
    }

}
