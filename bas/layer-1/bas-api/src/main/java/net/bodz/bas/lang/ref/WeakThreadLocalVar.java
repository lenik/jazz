package net.bodz.bas.lang.ref;

import java.lang.ref.WeakReference;

public class WeakThreadLocalVar<T>
        extends AbstractRef<T> {

    private static final long serialVersionUID = 1L;

    final Class<? extends T> valueType;

    ThreadLocal<WeakReference<T>> tl = new ThreadLocal<WeakReference<T>>();

    @SuppressWarnings("unchecked")
    public WeakThreadLocalVar(Class<?> valueType) {
        this.valueType = (Class<? extends T>) valueType;
    }

    @Override
    public Class<? extends T> getValueType() {
        return valueType;
    }

    @Override
    public T get() {
        WeakReference<T> ref = tl.get();
        if (ref == null)
            return null;
        else
            return ref.get();
    }

    @Override
    public void set(T value) {
        if (value == null) {
            tl.set(null);
            return;
        } else {
            WeakReference<T> ref = new WeakReference<T>(value);
            tl.set(ref);
        }
    }

    @Override
    public void remove() {
        tl.remove();
    }

}
