package net.bodz.bas.repr.path;

import java.lang.reflect.Array;

import net.bodz.bas.err.ParseException;

/**
 * Records the state of token preprocessing.
 *
 * When the dispatch started, the token queue contains tokens to be dispatched, and after dispatch
 * is completed, all processed tokens are consumed, rest only the unprocessed tokens.
 */
public interface IForwardOnlyTokenQueue
        extends
            Cloneable {

    String ATTRIBUTE_KEY = IForwardOnlyTokenQueue.class.getName();

    IForwardOnlyTokenQueue clone();

    /**
     * Get the available count of remaining tokens.
     *
     * @return Count of the remaining tokens.
     */
    int available();

    /**
     * Join the remaining tokens to a path string.
     *
     * @return The joined remaining path, without leading slash('/').
     */
    String getRemainingPath();

    /**
     * Whether there is a trailing slash.
     *
     * @return <code>true</code> if there is a trailing slash.
     * @see net.bodz.bas.vfs.path.IPath#isEntered()
     */
    boolean isEntered();

    /**
     * Test if the queue is empty.
     *
     * @return <code>true</code> If the queue is empty.
     */
    boolean isDone();

    /**
     * Skip a number of tokens.
     *
     * @param n
     *            Number of tokens to skip.
     */
    void skip(int n);

    /**
     * Shift out a number of head tokens.
     *
     * @return A token array consists of n tokens.
     * @throws IndexOutOfBoundsException
     *             If <code>n</code> is out of range.
     */
    String[] shift(int n);

    /**
     * Same as shift(available).
     */
    String[] shiftAll();

    /**
     * Shift out the head token.
     *
     * @return <code>null</code> If no more token.
     */
    String shift();

    /**
     * Shift out the head token as an int.
     *
     * If the head token isn't int, shift doesn't happen and <code>null</code> is returned.
     *
     * @return <code>null</code> If no more token, or the head token isn't an int number.
     */
    Integer shiftInt()
            throws ParseException;

    int shiftInt(int fallback);

    /**
     * Shift out the head token as a long.
     *
     * If the head token isn't long, shift doesn't happen and <code>null</code> is returned.
     *
     * @return <code>null</code> If no more token, or the head token isn't a long number.
     */
    Long shiftLong()
            throws ParseException;

    long shiftLong(int fallback);

    /**
     * Peek at the head token.
     *
     * @return <code>null</code> If no more token.
     */
    String peek();

    default String[] peek(int n) {
        if (available() < n)
            return null;
        String[] v = new String[n];
        for (int i = 0; i < n; i++)
            v[i] = peekAhead(i);
        return v;
    }

    /**
     * Peek at the n-th token from the head.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    String peekAhead(int offset);

    /**
     * Peek at the head token as integer.
     *
     * @return <code>null</code> If no more token.
     */
    Integer peekInt()
            throws ParseException;

    int peekInt(int fallback);

    /**
     * Peek at the n-th token from the head as int.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist, or it's not a int integer.
     */
    Integer peekIntAhead(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as int.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist, or it's not a int integer.
     */
    int peekIntAhead(int offset, int fallback);

    /**
     * Peek at the head token as long.
     *
     * @return <code>null</code> If no more token.
     */
    Long peekLong()
            throws ParseException;

    long peekLong(long fallback);

    /**
     * Peek at the n-th token from the head as long.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    Long peekLongAhead(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as long.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     */
    long peekLongAhead(int offset, long fallback);

    default int[] peekInts(int n)
            throws ParseException {
        if (n > available())
            throw new TokenUnderflowException("available: " + available());
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = peekIntAhead(i);
        }
        return array;
    }

    default int[] peekInts(int n, int fallback) {
        int[] array = new int[n];
        int max = available();
        if (n > max)
            n = max;
        for (int i = 0; i < n; i++) {
            array[i] = peekIntAhead(i, fallback);
        }
        return array;
    }

    default long[] peekLongs(int n)
            throws ParseException {
        if (n > available())
            throw new TokenUnderflowException("available: " + available());
        long[] array = new long[n];
        for (int i = 0; i < n; i++) {
            array[i] = peekLongAhead(i);
        }
        return array;
    }

    default long[] peekLongs(int n, long fallback)
            throws ParseException {
        long[] array = new long[n];
        int max = available();
        if (n > max)
            n = max;
        for (int i = 0; i < n; i++) {
            array[i] = peekLongAhead(i, fallback);
        }
        return array;
    }

    default <E extends Enum<E>> E peek(Class<E> enumType)
            throws ParseException {
        return peekAhead(enumType, 0);
    }

    <E extends Enum<E>> E peekAhead(Class<E> enumType, int offset)
            throws ParseException;

    default <E extends Enum<E>> E peek(Class<E> enumType, E fallback) {
        return peekAhead(enumType, 0, fallback);
    }

    <E extends Enum<E>> E peekAhead(Class<E> enumType, int offset, E fallback);

    default <E extends Enum<E>> E[] peek(Class<E> enumType, int n)
            throws ParseException {
        if (n > available())
            throw new TokenUnderflowException("available: " + available());

        @SuppressWarnings("unchecked")
        E[] array = (E[]) Array.newInstance(enumType, n);

        for (int i = 0; i < n; i++) {
            array[i] = peekAhead(enumType, i);
        }
        return array;
    }

    default <E extends Enum<E>> E[] peek(Class<E> enumType, int n, E fallback) {
        if (n > available())
            throw new TokenUnderflowException("available: " + available());

        @SuppressWarnings("unchecked")
        E[] array = (E[]) Array.newInstance(enumType, n);

        for (int i = 0; i < n; i++) {
            array[i] = peekAhead(enumType, i, fallback);
        }
        return array;
    }

    boolean isStopped();

    void stop();

}
