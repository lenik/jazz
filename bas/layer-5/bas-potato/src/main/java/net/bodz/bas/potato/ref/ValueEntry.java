package net.bodz.bas.potato.ref;

import net.bodz.bas.err.ReadOnlyException;
import net.bodz.bas.i18n.dom1.IElement;

public class ValueEntry<T>
        extends AbstractRefEntry<T> {

    private static final long serialVersionUID = 1L;

    private final Class<? extends T> valueType;
    private final T value;

    public ValueEntry(IElement element, Class<? extends T> valueType) {
        this(element, valueType, null);
    }

    public ValueEntry(IElement element, T value) {
        this(element, (Class<? extends T>) value.getClass(), value);
    }

    public ValueEntry(IElement element, Class<? extends T> valueType, T value) {
        super(element);
        this.valueType = valueType;
        this.value = value;
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
        throw new ReadOnlyException("value entry is read-only.");
    }

    @Override
    public void remove() {
        set(null);
    }

}
