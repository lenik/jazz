package net.bodz.bas.t.ref;

import java.util.Objects;

public class MutableLong
        implements
            Ref<Long> {

    public long value;

    @Override
    public Class<? extends Long> getValueType() {
        return Long.class;
    }

    @Override
    public Long get() {
        return value;
    }

    @Override
    public void set(Long value) {
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
        MutableLong other = (MutableLong) obj;
        return value == other.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
