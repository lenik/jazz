package net.bodz.bas.fn;

import net.bodz.bas.err.ReadOnlyException;
import net.bodz.bas.t.ref.Ref;

public class Constant<T>
        implements IEvaluable<T>, Ref<T> {

    private final Class<T> valueType;
    private final T value;

    public Constant(Class<T> valueType, T value) {
        if (valueType == null)
            throw new NullPointerException("valueType");
        this.valueType = valueType;
        this.value = value;
    }

    public Constant(T value) {
        if (value == null)
            throw new NullPointerException("value");
        this.value = value;
        this.valueType = (Class<T>) value.getClass();
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
