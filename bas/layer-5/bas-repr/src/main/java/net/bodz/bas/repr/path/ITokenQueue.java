package net.bodz.bas.repr.path;

/**
 * Records the state of token preprocessing.
 *
 * When the dispatch started, the token queue contains tokens to be dispatched, and after dispatch
 * is completed, all processed tokens are consumed, rest only the unprocessed tokens.
 */
public interface ITokenQueue {

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

    /**
     * Peek at the n-th token from the head.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    String peek(int offset);

    /**
     * Peek at the head token as integer.
     *
     * @return <code>null</code> If no more token, or the head token isn't a int number.
     */
    Integer peekInt();

    /**
     * Peek at the n-th token from the head as int.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist, or it's not a int integer.
     */
    Integer peekInt(int offset);

    /**
     * Peek at the head token as long.
     *
     * @return <code>null</code> If no more token, or the head token isn't a long number.
     */
    Long peekLong();

    /**
     * Peek at the n-th token from the head as long.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist, or it's not a long integer.
     */
    Long peekLong(int offset);

    boolean isStopped();

    void stop();

}
