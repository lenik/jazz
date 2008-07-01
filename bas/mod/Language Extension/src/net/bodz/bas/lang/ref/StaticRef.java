package net.bodz.bas.lang.ref;

public class StaticRef<T> extends Ref<T> {

    private T o;

    @Override
    protected T peek() {
        return o;
    }

    @Override
    protected void put(T o) {
        this.o = o;
    }

}
