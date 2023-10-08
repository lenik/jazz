package net.bodz.bas.t.stack;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public interface IContextStack<E> {

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    void clear();

    default E top() {
        return top(0);
    }

    /**
     * @return the element offset <code>offset</code> from top.
     * @throws NoSuchElementException
     *             If <code>count</code> is too large.
     */
    E top(int offset);

    <item_t extends E> item_t push(item_t item);

    default E pop() {
        return pop(0);
    }

    /**
     * If there's not enough elements in the stack, nothing will change.
     *
     * @return <code>null</code> if count is 0.
     * @throws EmptyStackException
     */
    E pop(int count);

    default E _pop() {
        return _pop(0);
    }

    /**
     * @return <code>null</code> if <code>count</code> is 0, or stack goes empty.
     */
    E _pop(int count);

    /**
     * @return bottom element. <code>null</code> if stack is empty.
     */
    default E popAll() {
        E a = null;
        while (!isEmpty()) {
            a = pop();
        }
        return a;
    }

    /**
     * pop-before with look-ahead(1).
     *
     * @return <code>null</code> if no matching found.
     */
    <R extends E> R popBefore(TypePredicate<E, R> predicate, R defaultVal);

    /**
     * pop-before with look-ahead(1).
     *
     * @return <code>null</code> if no matching found.
     */
    <R extends E> R popAhead(TypePredicate<E, R> predicate, R defaultVal);

    /**
     * @return <code>null</code> if no matching found.
     */
    <R extends E> R popTo(TypePredicate<E, R> predicate, R defaultVal);

    /**
     * pop-before with look-ahead(1).
     *
     * @throws NoSuchElementException
     *             if no matching found.
     */
    default <R extends E> R popBefore(TypePredicate<E, R> predicate) {
        R element = popBefore(predicate, null);
        if (element == null)
            throw new NoSuchElementException(predicate.toString());
        return element;
    }

    /**
     * pop-before with look-ahead(1).
     *
     * @throws NoSuchElementException
     *             if no matching found.
     */
    default <R extends E> R popAhead(TypePredicate<E, R> predicate) {
        R element = popAhead(predicate, null);
        if (element == null)
            throw new NoSuchElementException(predicate.toString());
        return element;
    }

    /**
     * @throws NoSuchElementException
     *             if no matching found.
     */
    default <R extends E> R popTo(TypePredicate<E, R> predicate) {
        R element = popTo(predicate, null);
        if (element == null)
            throw new NoSuchElementException(predicate.toString());
        return element;
    }

}