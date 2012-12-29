package net.bodz.bas.potato.ref;

import net.bodz.bas.err.ReadOnlyException;
import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.potato.PotatoLoader;

public class ValueEntry<T>
        extends AbstractRefEntry<T> {

    private static final long serialVersionUID = 1L;

    private final Class<? extends T> valueType;
    private final T value;

    public ValueEntry(Class<? extends T> valueType) {
        this(valueType, null);
    }

    public ValueEntry(T value) {
        this((Class<? extends T>) value.getClass(), value);
    }

    public ValueEntry(Class<? extends T> valueType, T value) {
        this(PotatoLoader.getType(valueType), valueType, value);
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

}
