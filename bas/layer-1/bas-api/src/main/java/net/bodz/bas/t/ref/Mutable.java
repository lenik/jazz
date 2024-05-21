package net.bodz.bas.t.ref;

public class Mutable<T>
        implements
            Ref<T> {

    public T value;

    @Override
    public T get() {
        return value;
    }

    @Override
    public void set(T value) {
        this.value = value;
    }

    @Override
    public void remove() {
        value = null;
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

}
