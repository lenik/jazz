package net.bodz.bas.lang.ref;

import java.util.LinkedList;


/**
 * Scoped Var.
 */
public class ScopedRef<T>
        implements Ref<T> {

    protected LinkedList<T> stack = new LinkedList<T>();

    @Override
    public T get() {
        if (stack.isEmpty())
            throw new RuntimeException("stack underflow");
        return stack.getLast();
    }

    @Override
    public void set(T o) {
        if (stack.isEmpty())
            throw new RuntimeException("stack underflow");
        stack.set(stack.size(), o);
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
