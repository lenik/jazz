package net.bodz.bas.t.ref;

import java.util.Objects;

public class MutableByte
        implements
            Ref<Byte> {

    public byte value;

    @Override
    public Class<? extends Byte> getValueType() {
        return Byte.class;
    }

    @Override
    public Byte get() {
        return value;
    }

    @Override
    public void set(Byte value) {
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
        MutableByte other = (MutableByte) obj;
        return value == other.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}