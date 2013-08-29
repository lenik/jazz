package net.bodz.bas.potato.ref;

import java.util.Map;

import net.bodz.bas.i18n.dom1.DecoratedElement;
import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.rtx.QueryException;

public abstract class AbstractRefEntry<T>
        extends DecoratedElement
        implements IRefEntry<T>, Map.Entry<String, T> {

    private static final long serialVersionUID = 1L;

    public AbstractRefEntry(IElement element) {
        super(element);
    }

    @Override
    public void remove() {
        set(null);
    }

    /** ⇱ {@link java.util.Map.Entry}. */
    ;

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

    /** ⇱ Implementaton Of {@link net.bodz.bas.rtx.IQueryable} */
    ;

    @Override
    public Object query(Object specification)
            throws QueryException {
        if (specification instanceof Class<?>)
            return query((Class<?>) specification);
        if (specification instanceof String)
            return query((String) specification);
        return null;
    }

    @Override
    public Object query(String specificationId)
            throws QueryException {
        return null;
    }

    @Override
    public <S> S query(Class<S> specificationType)
            throws QueryException {

        Class<? extends T> valueType = getValueType();
        if (specificationType.isAssignableFrom(valueType)) {
            T value = getValue();
            return specificationType.cast(value);
        }

    }

}
