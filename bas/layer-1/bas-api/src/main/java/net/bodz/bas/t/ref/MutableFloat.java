package net.bodz.bas.t.ref;

import java.util.Objects;

public class MutableFloat
        implements
            TypedRef<Float> {

    public float value;

    @Override
    public Class<? extends Float> getValueType() {
        return Float.class;
    }

    @Override
    public Float get() {
        return value;
    }

    @Override
    public void set(Float value) {
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
        MutableFloat other = (MutableFloat) obj;
        return value == other.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
