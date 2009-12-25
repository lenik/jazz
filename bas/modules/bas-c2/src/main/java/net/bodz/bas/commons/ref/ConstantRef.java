package net.bodz.bas.commons.ref;

import net.bodz.bas.api.exceptions.ReadOnlyException;
import net.bodz.bas.commons.util.Objects;

public class ConstantRef<T>
        implements Ref<T> {

    private final T constantValue;

    public ConstantRef(T constantValue) {
        this.constantValue = constantValue;
    }

    @Override
    public T get() {
        return constantValue;
    }

    @Override
    public void set(T value) {
        throw new ReadOnlyException();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ConstantRef) {
            Object thatValue = ((ConstantRef<?>) obj).constantValue;
            return Objects.equals(constantValue, thatValue);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 0x7ad9ca01;
        if (constantValue != null)
            hash += constantValue.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return "Constant: " + constantValue;
    }

}
