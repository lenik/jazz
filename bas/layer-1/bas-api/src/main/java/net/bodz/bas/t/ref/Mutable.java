package net.bodz.bas.t.ref;

public class Mutable<T>
        implements
            Ref<T> {

    public T value;

    @Override
    public Class<? extends T> getValueType() {
        if (value == null)
            return null;
        @SuppressWarnings("unchecked")
        Class<? extends T> type = (Class<? extends T>) value.getClass();
        return type;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public void set(T value) {
        this.value = value;
    }

    @Override
    public void remove() {
        value = null;
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

}
