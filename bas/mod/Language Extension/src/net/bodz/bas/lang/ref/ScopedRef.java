package net.bodz.bas.lang.ref;

import java.util.LinkedList;

/**
 * Scoped Var.
 */
public class ScopedRef<T> implements Ref<T> {

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

    public void begin() {
        stack.push(null);
    }

    public T end() {
        if (stack.isEmpty())
            throw new RuntimeException("stack underflow");
        return stack.pop();
    }

}
