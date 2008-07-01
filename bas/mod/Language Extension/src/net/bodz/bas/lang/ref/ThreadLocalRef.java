package net.bodz.bas.lang.ref;

public class ThreadLocalRef<T> extends Ref<T> {

    private ThreadLocal<T> tl;

    public ThreadLocalRef() {
        tl = new ThreadLocal<T>();
    }

    @Override
    protected T peek() {
        return tl.get();
    }

    @Override
    protected void put(T o) {
        tl.set(o);
    }

}
