package net.bodz.bas.potato.ref;

import net.bodz.bas.err.ReadOnlyException;
import net.bodz.bas.i18n.dom.StrFn;
import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.i18n.dom1.MutableElement;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.t.tree.IPathInfo;

public class ValueEntry<T>
        extends AbstractRefEntry<T> {

    private static final long serialVersionUID = 1L;

    private final Class<? extends T> valueType;
    private final T value;

    public ValueEntry(Class<? extends T> valueType) {
        this(valueType, null);
    }

    @SuppressWarnings("unchecked")
    public ValueEntry(T value) {
        this((Class<? extends T>) value.getClass(), value);
    }

    public ValueEntry(Class<? extends T> valueType, T value) {
        this(PotatoTypes.getInstance().loadType(valueType), valueType, value);
    }

    public ValueEntry(IElement element, Class<? extends T> valueType, T value) {
        super(element, valueType);
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
    public boolean isReadOnly() {
        return true;
    }

    @Override
    public void set(T value) {
        throw new ReadOnlyException("value entry is read-only.");
    }

    @Override
    public IPathInfo getParent() {
        return null;
    }

    @Override
    public String getLocalPath() {
        return null;
    }

    public static <T> ValueEntry<T> wrap(T obj) {
        if (obj == null)
            throw new NullPointerException("obj");
        @SuppressWarnings("unchecked")
        Class<T> valueType = (Class<T>) obj.getClass();
        return wrap(valueType, obj);
    }

    public static <T> ValueEntry<T> wrap(Class<T> valueType, T obj) {
        MutableElement element = new MutableElement();
        element.setLabel(StrFn.wrap("noname"));
        return new ValueEntry<T>(element, valueType, obj);
    }

}
