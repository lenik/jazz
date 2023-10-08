package net.bodz.bas.t.stack;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.function.Predicate;

public class ContextStack<E>
        implements
            IContextStack<E> {

    private final Stack<E> stack;

    public ContextStack() {
        this(new Stack<>());
    }

    public ContextStack(Stack<E> stack) {
        if (stack == null)
            throw new NullPointerException("stack");
        this.stack = stack;
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
    public void clear() {
        stack.clear();
    }

    @Override
    public E top() {
        return stack.lastElement();
    }

    @Override
    public E top(int offset) {
        synchronized (stack) {
            int n = stack.size();
            if (offset < 0 || offset >= n)
                throw new IndexOutOfBoundsException();
            int index = n - offset - 1;
            return stack.elementAt(index);
        }
    }

    @Override
    public <item_t extends E> item_t push(item_t item) {
        stack.push(item);
        return item;
    }

    @Override
    public E pop() {
        return stack.pop();
    }

    @Override
    public E pop(int count) {
        if (count == 0)
            return null;
        synchronized (stack) {
            int size = stack.size();
            if (count > size)
                throw new EmptyStackException();
            for (int i = 1; i < count; i++)
                stack.pop();
            E top = stack.pop();
            return top;
        }
    }

    @Override
    public E _pop() {
        synchronized (stack) {
            if (stack.isEmpty())
                return null;
            else
                return stack.pop();
        }
    }

    @Override
    public E _pop(int n) {
        if (n == 0)
            return null;
        synchronized (stack) {
            int size = stack.size();
            if (n > size) {
                stack.clear();
                return null;
            }
            for (int i = 1; i < n; i++)
                stack.pop();
            E top = stack.pop();
            return top;
        }
    }

    @Override
    public E popAll() {
        synchronized (stack) {
            E bottom = stack.get(0);
            stack.clear();
            return bottom;
        }
    }

    <R extends E> R _popMany(//
            TypePredicate<E, R> predicate, boolean popMatching, boolean raiseException) {
        if (stack.isEmpty())
            if (raiseException)
                throw new NoSuchElementException(predicate.toString());
            else
                return null;

        int n = stack.size();
        for (int i = n - 1; i >= 0; i--) {
            E node = stack.get(i);
            if (predicate.test(node)) {
                @SuppressWarnings("unchecked")
                R matching = (R) node;

                int j;
                if (popMatching)
                    j = i; // remove the matching element from the stack
                else
                    j = i + 1; // keep the matching element in the stack
                for (; j < n; j++)
                    stack.pop();
                return matching;
            }
        }
        if (raiseException)
            throw new NoSuchElementException(predicate.toString());
        else
            return null;
    }

    @Override
    public <R extends E> R popBefore(TypePredicate<E, R> predicate, R defaultVal) {
        int offset = offsetFromTop(predicate);
        if (offset == -1)
            return defaultVal;
        @SuppressWarnings("unchecked")
        R bot = (R) pop(offset); // if offset=0, pop nothing.
        return bot;
    }

    @Override
    public <R extends E> R popAhead(TypePredicate<E, R> predicate, R defaultVal) {
        int offset = offsetFromTop(predicate);
        if (offset == -1)
            return defaultVal;
        pop(offset);
        @SuppressWarnings("unchecked")
        R top = (R) top();
        return top;
    }

    @Override
    public <R extends E> R popTo(TypePredicate<E, R> predicate, R defaultVal) {
        int offset = offsetFromTop(predicate);
        if (offset == -1)
            return defaultVal;
        pop(offset);
        @SuppressWarnings("unchecked")
        R top = (R) pop();
        return top;
    }

    public synchronized <R extends E> //
    R findFromTop(TypePredicate<E, R> predicate) {
        int offset = offsetFromTop(predicate);
        if (offset == -1)
            return null;
        int index = size() - offset - 1;
        @SuppressWarnings("unchecked")
        R element = (R) stack.get(index);
        return element;
    }

    public int offsetFromTop(E example) {
        int n = stack.size();
        for (int i = n - 1; i >= 0; i--) {
            E node = stack.get(i);
            if (node == example)
                return n - i - 1;
        }
        return -1;
    }

    public int offsetFromTop(Predicate<E> predicate) {
        int n = stack.size();
        for (int i = n - 1; i >= 0; i--) {
            E node = stack.get(i);
            if (predicate.test(node))
                return n - i - 1;
        }
        return -1;
    }

    public void popBefore(E element) {
        int offset = offsetFromTop(element);
        pop(offset);
    }

    public void popAhead(E element) {
        popBefore(element);
    }

    public void popTo(E element) {
        int offset = offsetFromTop(element);
        pop(offset + 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (E node : stack)
            sb.append(node + "\n");
        return sb.toString();
    }

}
