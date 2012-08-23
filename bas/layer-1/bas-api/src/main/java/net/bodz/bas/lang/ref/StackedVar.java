package net.bodz.bas.lang.ref;

import java.util.LinkedList;

/**
 * Scoped Var.
 */
public class StackedVar<T>
        extends AbstractRef<T> {

    private static final long serialVersionUID = 1L;

    protected final Class<? extends T> valueType;
    protected LinkedList<T> stack = new LinkedList<T>();

    @SuppressWarnings("unchecked")
    public StackedVar(Class<?> valueType) {
        this.valueType = (Class<? extends T>) valueType;
    }

    @Override
    public Class<? extends T> getValueType() {
        return valueType;
    }

    @Override
    public T get() {
        if (stack.isEmpty())
            throw new RuntimeException("stack underflow");
        return stack.getLast();
    }

    @Override
    public void set(T value) {
        if (stack.isEmpty())
            throw new RuntimeException("stack underflow");
        stack.set(stack.size(), value);
    }

    public void begin(T init) {
        stack.push(init);
    }

    public final void begin() {
        T defaultValue = create();
        begin(defaultValue);
    }

    public T end() {
        if (stack.isEmpty())
            throw new RuntimeException("stack underflow");
        return stack.pop();
    }

    /**
     * Create a default value for new frame.
     * 
     * By default this function returns <code>null</code>.
     */
    protected T create() {
        return null;
    }

}
