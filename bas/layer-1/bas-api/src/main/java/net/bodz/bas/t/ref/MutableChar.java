package net.bodz.bas.t.ref;

import java.util.Objects;

public class MutableChar
        implements
            Ref<Character> {

    public char value;

    @Override
    public Class<? extends Character> getValueType() {
        return Character.class;
    }

    @Override
    public Character get() {
        return value;
    }

    @Override
    public void set(Character value) {
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
        MutableChar other = (MutableChar) obj;
        return value == other.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
