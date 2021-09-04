package net.bodz.bas.t.set;

import java.util.Stack;

import net.bodz.bas.err.IllegalUsageException;

public class FramedMarks
        implements
            IMarkset {

    Stack<IMarkset> stack;

    public FramedMarks() {
        stack = new Stack<>();
        enter();
    }

    protected IMarkset createMarkset() {
        return new Marks();
    }

    public void enter() {
        stack.push(createMarkset());
    }

    public void leave() {
        if (stack.size() <= 1)
            throw new IllegalUsageException("No more frame to leave!");
        stack.pop();
    }

    @Override
    public boolean containsMark(Object o) {
        for (IMarkset marks : stack)
            if (marks.containsMark(o))
                return true;
        return false;
    }

    @Override
    public boolean addMark(Object o) {
        if (containsMark(o))
            return false;
        IMarkset top = stack.peek();
        top.addMark(o);
        return true;
    }

    @Override
    public boolean removeMark(Object o) {
        IMarkset top = stack.peek();
        return top.removeMark(o);
    }

    @Override
    public void clearMarks() {
        IMarkset top = stack.peek();
        top.clearMarks();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(stack.size() * 100);
        for (IMarkset marks : stack) {
            sb.append(marks);
            sb.append("\n");
        }
        return sb.toString();
    }

}
