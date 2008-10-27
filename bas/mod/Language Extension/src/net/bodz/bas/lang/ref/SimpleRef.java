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

    @SuppressWarnings("unchecked")
    @Override
    public void set(Object o) {
        this.o = (T) o;
    }

}
