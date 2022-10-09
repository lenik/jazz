package net.bodz.bas.repr.path;

import net.bodz.bas.err.ParseException;

/**
 * Records the state of token preprocessing.
 *
 * When the dispatch started, the token queue contains tokens to be dispatched, and after dispatch
 * is completed, all processed tokens are consumed, rest only the unprocessed tokens.
 */
public interface ITokenQueue
        extends
            Cloneable {

    String ATTRIBUTE_KEY = ITokenQueue.class.getName();

    ITokenQueue clone();

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
    boolean isEmpty();

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
    Integer shiftInt();

    /**
     * Shift out the head token as a long.
     *
     * If the head token isn't long, shift doesn't happen and <code>null</code> is returned.
     *
     * @return <code>null</code> If no more token, or the head token isn't a long number.
     */
    Long shiftLong();

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
            v[i] = peekAt(i);
        return v;
    }

    /**
     * Peek at the n-th token from the head.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    String peekAt(int offset);

    /**
     * Peek at the head token as integer.
     *
     * @return <code>null</code> If no more token, or the head token isn't a int number.
     */
    Integer peekInt();

    default int[] peekInt(int n)
            throws ParseException {
        String[] sv = peek(n);
        if (sv == null)
            return null;
        int[] iv = new int[n];
        for (int i = 0; i < n; i++)
            try {
                iv[i] = Integer.parseInt(sv[i]);
            } catch (NumberFormatException e) {
                throw new ParseException(e);
            }
        return iv;
    }

    /**
     * Peek at the n-th token from the head as int.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist, or it's not a int integer.
     */
    Integer peekIntAt(int offset);

    /**
     * Peek at the head token as long.
     *
     * @return <code>null</code> If no more token, or the head token isn't a long number.
     */
    Long peekLong();

    default long[] peekLong(int n)
            throws ParseException {
        String[] sv = peek(n);
        if (sv == null)
            return null;
        long[] lv = new long[n];
        for (int i = 0; i < n; i++)
            try {
                lv[i] = Long.parseLong(sv[i]);
            } catch (NumberFormatException e) {
                throw new ParseException(e);
            }
        return lv;
    }

    /**
     * Peek at the n-th token from the head as long.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist, or it's not a long integer.
     */
    Long peekLongAt(int offset);

    boolean isStopped();

    void stop();

}
