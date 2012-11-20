package net.bodz.bas.db.stat;

public class SetUpCounter<T extends Number>
        implements ICounter<T> {

    private static final long serialVersionUID = 1L;

    private final ICounter<T> parent;
    private T value;

    public SetUpCounter(ICounter<T> parent, T initialValue) {
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
        this.value = value;
        parent.setValue(value);
    }

    @Override
    public void increase() {
        value = getDefinition().incr(value);
        parent.setValue(value);
    }

    @Override
    public void decrease() {
        value = getDefinition().decr(value);
        parent.setValue(value);
    }

}
