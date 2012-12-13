package net.bodz.bas.t.ref;

public class ThreadLocalVar<T>
        extends AbstractRef<T> {

    private static final long serialVersionUID = 1L;

    final Class<? extends T> valueType;

    private ThreadLocal<T> local = new ThreadLocal<T>();

    @SuppressWarnings("unchecked")
    public ThreadLocalVar(Class<?> valueType) {
        this.valueType = (Class<? extends T>) valueType;
    }

    @Override
    public Class<? extends T> getValueType() {
        return valueType;
    }

    @Override
    public T get() {
        return local.get();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void set(Object o) {
        local.set((T) o);
    }

    @Override
    public void remove() {
        local.remove();
    }

}
