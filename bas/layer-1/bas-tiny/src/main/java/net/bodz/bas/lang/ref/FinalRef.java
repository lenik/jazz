package net.bodz.bas.lang.ref;

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
        throw new RuntimeException("Can't set on a final reference");
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FinalRef<?>))
            return false;
        Object thatValue = ((FinalRef<?>) obj).constantValue;
        if (constantValue != thatValue) {
            if (constantValue == null || thatValue == null)
                return false;
            if (!constantValue.equals(thatValue))
                return false;
        }
        return true;
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
