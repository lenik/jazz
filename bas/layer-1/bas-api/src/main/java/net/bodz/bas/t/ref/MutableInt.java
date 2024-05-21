package net.bodz.bas.t.ref;

import java.util.Objects;

public class MutableInt
        implements
            TypedRef<Integer> {

    public int value;

    @Override
    public Class<? extends Integer> getValueType() {
        return Integer.class;
    }

    @Override
    public Integer get() {
        return value;
    }

    @Override
    public void set(Integer value) {
        if (value == null)
            this.value = 0;
        else
            this.value = value;
    }

    @Override
    public void remove() {
        value = 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MutableInt other = (MutableInt) obj;
        return value == other.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
