package net.bodz.bas.disp;

/**
 * Records the state of token preprocessing.
 */
public interface ITokenQueue {

    String INDEX = ""; // "<<index>>";

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
     * A trailing slash (<code>'/'</code>) should be translated into {@link #INDEX}.
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
     * A trailing slash (<code>'/'</code>) should be translated into {@link #INDEX}.
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
     * Shift out the head token.
     * 
     * @return <code>null</code> If no more token.
     */
    String peek();

    /**
     * Peek at the n-th token from the head.
     * 
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
     * @return <code>null</code> If the token doesn't exist, or it's not a long integer.
     */
    Long peekLong(int offset);

}
