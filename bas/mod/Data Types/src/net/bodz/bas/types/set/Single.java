package net.bodz.bas.types.set;

import net.bodz.bas.types.util.DirectIterator;
import net.bodz.bas.types.util.Objects;

public class Single<T> extends _Set<T> {

    T value;

    public Single(T value) {
        this.value = value;
    }

    @Override
    public boolean isFinal() {
        return true;
    }

    @Override
    public boolean isFinite() {
        return true;
    }

    @Override
    public T first() {
        return value;
    }

    @Override
    public T last() {
        return value;
    }

    @Override
    public boolean contains(T x) {
        return Objects.equals(value, x);
    }

    @Override
    public Set<T> toFinal() {
        return new Single<T>(value);
    }

    @Override
    public DirectIterator<T, RuntimeException> iterator() {
        return null;
    }

}
