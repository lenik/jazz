package net.bodz.bas.t.ref;

import java.util.Objects;

public class MutableShort
        implements
            TypedRef<Short> {

    public short value;

    @Override
    public Class<? extends Short> getValueType() {
        return Short.class;
    }

    @Override
    public Short get() {
        return value;
    }

    @Override
    public void set(Short value) {
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
        MutableShort other = (MutableShort) obj;
        return value == other.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
