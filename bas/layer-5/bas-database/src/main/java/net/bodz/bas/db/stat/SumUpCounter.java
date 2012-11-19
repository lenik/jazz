package net.bodz.bas.db.stat;

public class SumUpCounter<T>
        implements ICounter<T> {

    private static final long serialVersionUID = 1L;

    private final ICounter<T> parent;
    private T value;

    public SumUpCounter(ICounter<T> parent, T initialValue) {
        if (parent == null)
            throw new NullPointerException("parent");
        if (initialValue == null)
            throw new NullPointerException("initialValue");
        this.parent = parent;
        this.value = initialValue;
    }

    @Override
    public ICounterDef<T> getDefinition() {
        return parent.getDefinition();
    }

    @Override
    public String getName() {
        return parent.getName();
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        ICounterDef<T> definition = getDefinition();
        T delta = definition.sub(value, this.value);
        this.value = value;
        // parent.increase(delta);
        T parentValue = parent.getValue();
        parentValue = definition.add(parentValue, delta);
        parent.setValue(parentValue);
    }

    @Override
    public void increase() {
        value = getDefinition().incr(value);
        parent.increase();
    }

    @Override
    public void decrease() {
        value = getDefinition().decr(value);
        parent.decrease();
    }

}
