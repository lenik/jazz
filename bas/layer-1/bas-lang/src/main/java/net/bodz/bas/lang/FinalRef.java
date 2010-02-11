package net.bodz.bas.lang;

import net.bodz.bas.exceptions.ReadOnlyException;

public class FinalRef<T>
        implements Ref<T> {

    private final T constantValue;

    public FinalRef(T constantValue) {
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
        if (obj instanceof FinalRef<?>) {
            Object thatValue = ((FinalRef<?>) obj).constantValue;
            return Nullables.equals(constantValue, thatValue);
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
