package net.bodz.bas.t.ref;

public interface TypedRef<T>
        extends
            Ref<T> {

    /**
     * Get the declared class of target value.
     */
    Class<? extends T> getValueType();

}
