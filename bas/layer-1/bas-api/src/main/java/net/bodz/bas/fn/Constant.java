package net.bodz.bas.fn;

import net.bodz.bas.err.ReadOnlyException;
import net.bodz.bas.t.ref.TypedRef;

public class Constant<T>
        implements
            IEvaluable<T>,
            TypedRef<T> {

    private final Class<? extends T> valueType;
    private final T value;

    public Constant(Class<T> valueType, T value) {
        if (valueType == null)
            throw new NullPointerException("valueType");
        this.valueType = valueType;
        this.value = value;
    }

    @SuppressWarnings("unchecked")
    public Constant(T value) {
        if (value == null)
            throw new NullPointerException("value");
        this.value = value;
        this.valueType = (Class<? extends T>) value.getClass();
    }

    public static <T> Constant<T> of(T value) {
        return new Constant<T>(value);
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
        throw new ReadOnlyException();
    }

    @Override
    public void remove() {
        throw new ReadOnlyException();
    }

    @Override
    public T eval()
            throws EvalException {
        return value;
    }

}
