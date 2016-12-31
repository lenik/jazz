package net.bodz.bas.t.set;

import java.util.Stack;

import net.bodz.bas.err.IllegalUsageException;

public class FramedMarks
        implements IMarks {

    Stack<Marks> stack;

    public FramedMarks() {
        stack = new Stack<>();
        enter();
    }

    public void enter() {
        stack.push(new Marks());
    }

    public void leave() {
        if (stack.size() <= 1)
            throw new IllegalUsageException("No more frame to leave!");
        stack.pop();
    }

    @Override
    public boolean contains(Object o) {
        for (Marks marks : stack)
            if (marks.contains(o))
                return true;
        return false;
    }

    @Override
    public boolean add(Object o) {
        if (contains(o))
            return false;
        Marks top = stack.peek();
        top.add(o);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Marks top = stack.peek();
        return top.remove(o);
    }

    @Override
    public void clear() {
        Marks top = stack.peek();
        top.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(stack.size() * 100);
        for (Marks marks : stack) {
            sb.append(marks);
            sb.append("\n");
        }
        return sb.toString();
    }

}
