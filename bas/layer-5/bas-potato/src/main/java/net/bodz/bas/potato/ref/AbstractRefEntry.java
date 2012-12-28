package net.bodz.bas.potato.ref;

import java.util.Map;

import net.bodz.bas.i18n.dom1.DecoratedElement;
import net.bodz.bas.i18n.dom1.IElement;

public abstract class AbstractRefEntry<T>
        extends DecoratedElement
        implements IRefEntry<T>, Map.Entry<String, T> {

    private static final long serialVersionUID = 1L;

    public AbstractRefEntry(IElement element) {
        super(element);
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
    public boolean isValueChangeSource() {
        return false;
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
