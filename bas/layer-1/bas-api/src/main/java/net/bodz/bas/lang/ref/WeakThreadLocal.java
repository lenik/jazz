package net.bodz.bas.lang.ref;

import java.lang.ref.WeakReference;

public class WeakThreadLocal<T> {
    ThreadLocal<WeakReference<T>> tl = new ThreadLocal<WeakReference<T>>();

    public T get() {
        WeakReference<T> ref = tl.get();
        if (ref == null)
            return null;
        return ref.get();
    }

    public void set(T value) {
        if (value == null) {
            tl.set(null);
            return;
        }
        WeakReference<T> ref = new WeakReference<T>(value);
        tl.set(ref);
    }

}
