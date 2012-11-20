package net.bodz.bas.db.stat;

public class Counter<T extends Number>
        implements ICounter<T> {

    private static final long serialVersionUID = 1L;

    private final ICounterDef<T> definition;
    private final String name;
    private T value;

    public Counter(String name, T initialValue) {
        this(CounterDef.getGenericDef((Class<T>) initialValue.getClass()), name, initialValue);
    }

    public Counter(ICounterDef<T> definition, String name, T initialValue) {
        if (definition == null)
            throw new NullPointerException("definition");
        if (name == null)
            throw new NullPointerException("name");
        if (initialValue == null)
            throw new NullPointerException("initialValue");
        this.definition = definition;
        this.name = name;
        this.value = initialValue;
    }

    @Override
    public ICounterDef<T> getDefinition() {
        return definition;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public synchronized void setValue(T value) {
        this.value = value;
    }

    @Override
    public synchronized void increase() {
        value = definition.incr(value);
    }

    @Override
    public synchronized void decrease() {
        value = definition.decr(value);
    }

}
