package net.bodz.bas.t.ref;

public interface Ref<T> {

    /**
     * Get the declared class of target value.
     */
    Class<? extends T> getValueType();

    T get();

    /**
     * @throws ClassCastException
     *             If value's type is illegal.
     */
    void set(T value);

    void remove();

}
