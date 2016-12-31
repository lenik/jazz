package net.bodz.bas.t.set;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

import net.bodz.bas.c.object.IdentityHashSet;

public class StackSet<T>
        implements Set<T> {

    private final IdentityHashSet<T> idSet;
    private final Stack<T> stack;

    public StackSet() {
        idSet = new IdentityHashSet<>();
        stack = new Stack<>();
    }

    public boolean push(T elm) {
        if (idSet.add(elm)) {
            stack.push(elm);
            return true;
        } else {
            return false;
        }
    }

    public T pop() {
        T top = stack.pop();
        idSet.remove(top);
        return top;
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return idSet.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return stack.iterator();
    }

    @Override
    public Object[] toArray() {
        return stack.toArray();
    }

    @Override
    public <E> E[] toArray(E[] a) {
        return stack.toArray(a);
    }

    @Override
    public boolean add(T e) {
        return push(e);
    }

    @Override
    public boolean remove(Object o) {
        idSet.remove(o);
        return stack.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return idSet.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean status = false;
        for (T item : c)
            status |= add(item);
        return status;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        idSet.retainAll(c);
        return stack.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        idSet.removeAll(c);
        return stack.removeAll(c);
    }

    @Override
    public void clear() {
        idSet.clear();
        stack.clear();
    }

}
