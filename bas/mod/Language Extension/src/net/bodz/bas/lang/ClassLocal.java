package net.bodz.bas.lang;

import java.util.HashMap;
import java.util.Map;


public class ClassLocal<T> extends HashMap<Class<?>, T> {

    private static final long serialVersionUID = 6470297212054945897L;

    public ClassLocal() {
        super();
    }

    public ClassLocal(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public ClassLocal(int initialCapacity) {
        super(initialCapacity);
    }

    public ClassLocal(Map<? extends Class<?>, ? extends T> m) {
        super(m);
    }

    public T get() {
        Class<?> clazz = getCallerClass(2);
        return super.get(clazz);
    }

    public T set(T value) {
        Class<?> clazz = getCallerClass(2);
        return super.put(clazz, value);
    }

    protected Class<?> getCallerClass(int n) {
        Class<?> clazz = Caller.getCallerClass(n + 1);
        return clazz;
    }

    // @Diagnostic
    public Class<?> getCallerClass() {
        Class<?> clazz = getCallerClass(2);
        return clazz;
    }

}
