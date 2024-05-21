package net.bodz.bas.t.ref;

import java.util.Objects;

public class MutableBool
        implements
            TypedRef<Boolean> {

    public boolean value;

    @Override
    public Class<? extends Boolean> getValueType() {
        return Boolean.class;
    }

    @Override
    public Boolean get() {
        return value;
    }

    @Override
    public void set(Boolean value) {
        if (value == null)
            this.value = false;
        else
            this.value = value;
    }

    @Override
    public void remove() {
        value = false;
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
        MutableBool other = (MutableBool) obj;
        return value == other.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
