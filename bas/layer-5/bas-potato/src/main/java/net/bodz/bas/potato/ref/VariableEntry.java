package net.bodz.bas.potato.ref;

import net.bodz.bas.i18n.dom1.IElement;

public class VariableEntry<T>
        extends AbstractRefEntry<T> {

    private static final long serialVersionUID = 1L;

    private final Class<? extends T> valueType;
    private T value;

    public VariableEntry(IElement element, Class<? extends T> valueType) {
        this(element, valueType, null);
    }

    public VariableEntry(IElement element, T initialValue) {
        this(element, (Class<? extends T>) initialValue.getClass(), initialValue);
    }

    public VariableEntry(IElement element, Class<? extends T> valueType, T initialValue) {
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
