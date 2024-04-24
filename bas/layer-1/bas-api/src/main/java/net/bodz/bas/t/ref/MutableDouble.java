package net.bodz.bas.t.ref;

import java.util.Objects;

public class MutableDouble
        implements
            Ref<Double> {

    public double value;

    @Override
    public Class<? extends Double> getValueType() {
        return Double.class;
    }

    @Override
    public Double get() {
        return value;
    }

    @Override
    public void set(Double value) {
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
        MutableDouble other = (MutableDouble) obj;
        return value == other.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
