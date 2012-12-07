package net.bodz.bas.potato.ref;

public class Variable<T>
        extends AbstractRefEntry<T> {

    private static final long serialVersionUID = 1L;

    protected final Class<? extends T> valueType;
    private T value;

    public Variable(String name, Class<? extends T> valueType) {
        this(name, valueType, null);
    }

    public Variable(String name, T initialValue) {
        this(name, (Class<? extends T>) initialValue.getClass(), initialValue);
    }

    public Variable(String name, Class<? extends T> valueType, T initialValue) {
        super(name);
        this.valueType = valueType;
        this.value = initialValue;
    }

    @Override
    public IRefDescriptor getDescriptor() {
        return new VariableDescriptor(name, valueType);
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

}
