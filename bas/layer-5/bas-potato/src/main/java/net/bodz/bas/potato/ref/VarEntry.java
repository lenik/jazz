package net.bodz.bas.potato.ref;

import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.potato.PotatoLoader;

public class VarEntry<T>
        extends AbstractRefEntry<T> {

    private static final long serialVersionUID = 1L;

    private final Class<? extends T> valueType;
    private T value;

    public VarEntry(Class<? extends T> valueType) {
        this(valueType, null);
    }

    public VarEntry(T initialValue) {
        this((Class<? extends T>) initialValue.getClass(), initialValue);
    }

    public VarEntry(Class<? extends T> valueType, T initialValue) {
        this(PotatoLoader.getType(valueType), valueType, initialValue);
    }

    public VarEntry(IElement element, Class<? extends T> valueType, T initialValue) {
        super(element);
        this.valueType = valueType;
        this.value = initialValue;
    }

    @Override
    public Class<? extends T> getValueType() {
        return valueType;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public void set(T value) {
        this.value = valueType.cast(value);
    }

    @Override
    public void remove() {
        this.value = null;
    }

}