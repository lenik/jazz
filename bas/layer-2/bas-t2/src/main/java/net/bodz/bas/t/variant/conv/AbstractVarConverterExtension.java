package net.bodz.bas.t.variant.conv;

import java.util.Collections;
import java.util.Map;

import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.fn.ITransformer;

public abstract class AbstractVarConverterExtension<T>
        implements IVarConverterExtension<T> {

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public Map<Class<?>, ITransformer<Object, T>> getFromMap() {
        return Collections.emptyMap();
    }

    @Override
    public Map<Class<?>, ITransformer<T, Object>> getToMap() {
        return Collections.emptyMap();
    }

    @Override
    public boolean canConvertFrom(Class<?> type) {
        return false;
    }

    @Override
    public boolean canConvertTo(Class<?> type) {
        return false;
    }

    @Override
    public T convertFrom(Class<?> type, Object obj)
            throws TypeConvertException {
        throw new IllegalArgumentException("Can't convert from this type: " + type);
    }

    @Override
    public <U> U convertTo(T value, Class<U> type)
            throws TypeConvertException {
        throw new IllegalArgumentException("Can't convert to this type: " + type);
    }

}
