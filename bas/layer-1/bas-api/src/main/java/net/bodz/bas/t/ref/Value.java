package net.bodz.bas.t.ref;

import net.bodz.bas.err.ReadOnlyException;

public class Value<T>
        extends AbstractRef<T> {

    private static final long serialVersionUID = 1L;

    private final Class<? extends T> valueType;
    private final T value;

    public Value(Class<? extends T> valueType, T value) {
        this.valueType = valueType;
        this.value = value;
    }

    public static <T> Value<T> of(T val) {
        if (val == null)
            throw new NullPointerException("val");
        Class<? extends T> valType = (Class<? extends T>) val.getClass();
        return new Value<T>(valType, val);
    }

    public static <T> Value<T> nullOf(Class<T> type) {
        return new Value<T>(type, null);
    }

    @Override
    public Class<? extends T> getValueType() {
        return valueType;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public void set(T value) {
        throw new ReadOnlyException("value ref is read only.");
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Value<?>))
            return false;

        Value<?> o = (Value<?>) obj;
        return value == o.value && valueType == o.valueType;
    }

    @Override
    public int hashCode() {
        int hash = 0x1c40e21d;
        hash += System.identityHashCode(value);
        return hash;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
