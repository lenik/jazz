package net.bodz.bas.t.variant;

public class MutableVariant
        extends AbstractVariant {

    private Object value;

    public MutableVariant() {
    }

    public MutableVariant(Object value) {
        this.value = value;
    }

    public static MutableVariant wrap(Object value) {
        return new MutableVariant(value);
    }

    @Override
    public Object get() {
        return value;
    }

    public void set(Object value) {
        this.value = value;
    }

}
