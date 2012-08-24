package net.bodz.bas.potato.ref;

import java.util.Map;

import net.bodz.bas.lang.ref.AbstractRef;

public abstract class AbstractRefEntry<T>
        extends AbstractRef<T>
        implements IRefEntry<T>, Map.Entry<String, T> {

    private static final long serialVersionUID = 1L;

    protected String name;

    public AbstractRefEntry(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // -o Map.Entry

    @Override
    public final String getKey() {
        return getName();
    }

    @Override
    public final T getValue() {
        return get();
    }

    @Override
    public final T setValue(T value) {
        T oldValue = get();
        set(value);
        return oldValue;
    }

    @Override
    public void addValueChangeListener(IValueChangeListener listener) {
        // throw new UnsupportedOperationException();
    }

    @Override
    public void removeValueChangeListener(IValueChangeListener listener) {
        // throw new UnsupportedOperationException();
    }

}
