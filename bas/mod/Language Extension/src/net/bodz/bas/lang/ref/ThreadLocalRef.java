package net.bodz.bas.lang.ref;

public class ThreadLocalRef<T> implements Ref<T> {

    private ThreadLocal<T> local = new ThreadLocal<T>();

    @Override
    public T get() {
        return local.get();
    }

    @Override
    public void set(T o) {
        local.set(o);
    }

}
