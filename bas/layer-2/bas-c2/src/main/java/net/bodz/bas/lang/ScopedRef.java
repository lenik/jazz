package net.bodz.bas.lang;

import java.util.LinkedList;

import net.bodz.bas.lang.Ref;

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

    @SuppressWarnings("unchecked")
    @Override
    public void set(Object o) {
        if (stack.isEmpty())
            throw new RuntimeException("stack underflow");
        stack.set(stack.size(), (T) o);
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
