package net.bodz.bas.potato.ref;

public class Variable<T>
        extends AbstractRefEntry<T> {

    private static final long serialVersionUID = 1L;

    private T value;

    public Variable(String name) {
        super(name, null); // XXX VariableProperty.
    }

    public Variable(String name, T initialValue) {
        this(name);
        this.value = initialValue;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public void set(T value) {
        this.value = value;
    }

}
