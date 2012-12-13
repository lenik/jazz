package net.bodz.bas.t.ref;

public class Var<T>
        extends AbstractRef<T> {

    private static final long serialVersionUID = 1L;

    private final Class<? extends T> valueType;
    private T value;

    @SuppressWarnings("unchecked")
    public Var(Class<?> valueType) {
        this.valueType = (Class<? extends T>) valueType;
    }

    public Var(Class<?> valueType, T init) {
        this(valueType);
        this.value = init;
    }

    public Var(T init) {
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
        if (!(obj instanceof Var<?>))
            return false;
        Var<?> o = (Var<?>) obj;
        return value == o.value;
    }

    @Override
    public int hashCode() {
        int hash = 0x7ad9ca01;
        hash += System.identityHashCode(value);
        return hash;
    }

}
