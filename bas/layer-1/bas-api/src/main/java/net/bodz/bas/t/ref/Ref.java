package net.bodz.bas.t.ref;

public interface Ref<T> {

    default boolean isReadOnly() {
        return false;
    }

    T get();

    /**
     * @throws ClassCastException
     *             If value's type is illegal.
     */
    void set(T value);

    void remove();

}
