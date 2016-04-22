package net.bodz.bas.t.ref;

public class SimpleVar<T>
        extends AbstractRef<T> {

    private static final long serialVersionUID = 1L;

    private final Class<? extends T> valueType;
    private T value;

    @SuppressWarnings("unchecked")
    public SimpleVar(Class<?> valueType) {
        this.valueType = (Class<? extends T>) valueType;
    }

    public SimpleVar(Class<?> valueType, T init) {
        this(valueType);
        this.value = init;
    }

    public SimpleVar(T init) {
        this(init.getClass());
        this.value = init;
    }

    @Override
    public Class<? extends T> getValueType() {
        return valueType;
    }

    @Override
    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SimpleVar<?>))
            return false;
        SimpleVar<?> o = (SimpleVar<?>) obj;
        return value == o.value;
    }

    @Override
    public int hashCode() {
        int hash = 0x7ad9ca01;
        hash += System.identityHashCode(value);
        return hash;
    }

}
