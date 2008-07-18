package net.bodz.bas.lang.ref;

public class SimpleRef<T> implements Ref<T> {

    private T o;

    public SimpleRef() {
    }

    public SimpleRef(T init) {
        this.o = init;
    }

    @Override
    public T get() {
        return o;
    }

    @Override
    public void set(T o) {
        this.o = o;
    }

}
