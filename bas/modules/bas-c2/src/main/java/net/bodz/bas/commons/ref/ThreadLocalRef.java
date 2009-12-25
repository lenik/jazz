package net.bodz.bas.commons.ref;

public class ThreadLocalRef<T> implements Ref<T> {

    private ThreadLocal<T> local = new ThreadLocal<T>();

    @Override
    public T get() {
        return local.get();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void set(Object o) {
        local.set((T) o);
    }

}
