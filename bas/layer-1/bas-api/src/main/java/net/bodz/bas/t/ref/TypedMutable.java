package net.bodz.bas.t.ref;

public class TypedMutable<T>
        extends Mutable<T>
        implements
            TypedRef<T> {

    Class<T> valueType;

    public TypedMutable(Class<T> valueType) {
        this.valueType = valueType;
    }

    @Override
    public Class<? extends T> getValueType() {
        return valueType;
    }

}
