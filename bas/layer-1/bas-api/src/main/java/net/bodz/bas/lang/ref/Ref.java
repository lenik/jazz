package net.bodz.bas.lang.ref;

public interface Ref<T> {

    /**
     * Get the declared class of target value.
     */
    Class<? extends T> getValueType();

    T get();

    void set(T value);

    void remove();

}
