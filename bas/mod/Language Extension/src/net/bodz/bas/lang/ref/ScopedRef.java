package net.bodz.bas.lang.ref;

import java.util.LinkedList;

public class ScopedRef<T> extends Ref<T> {

    protected LinkedList<T> stack;

    public ScopedRef() {
        stack = new LinkedList<T>();
    }

    @Override
    protected T peek() {
        if (stack.isEmpty())
            throw new RuntimeException("stack underflow");
        return stack.getLast();
    }

    @Override
    protected void put(T o) {
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
