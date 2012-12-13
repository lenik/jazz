package net.bodz.bas.t.iterator.immed;

/**
 * Immediate Iterator.
 */
public interface Mitorx<T, X extends Throwable> {

    /**
     * An overlapped iterator will return overlapped iterated values (the iterated value may refer
     * to other important values in the running context), so you must deep-copy the iterated value
     * if you want to keep it for future re-use, rather than by reference only.
     * <p>
     * Notice: This method should be overrided at the same time when you modify
     * {@link #deoverlap(Object)}.
     * 
     * @return <code>true</code> If this immediate-iterator is overlapped.
     */
    boolean isOverlapped();

    /**
     * Copy the overlapped object.
     * <p>
     * Notice: This method should be overrided at the same time when you modify
     * {@link #isOverlapped()}.
     * 
     * @param obj
     *            A pre-iterated value returned by this immediate-iterator.
     * @return The same value if this iterator is not overlapped, otherwise returns a
     *         detached/cloned/etc value, so future iteration will not modify it.
     */
    T deoverlap(T obj);

    /**
     * Get the next item immediately.
     * 
     * @return The last iterated value, at the same time <code>null</code> is returned if the
     *         iteration reaches the end. For <code>null</code> value iterated, the caller must
     *         distinguish by call to {@link #isEnded()}.
     */
    T _next()
            throws X;

    /**
     * If last {@link #_next()} call returns <code>null</code>, it can be the <code>null</code> item
     * iterated, or the end of iteration.
     * 
     * <p>
     * If last {@link #_next()} call returns non-<code>null</code> value, the iteration MUST NOT be
     * ended, so the test of {@link #isEnded()} is only needed if the last call to {@link #_next()}
     * returns <code>null</code>.
     * 
     * @return <code>true</code> if the iteration reaches the end and last <code>null</code> value
     *         returned by {@link #_next()} is meaningless.
     * @throws IllegalStateException
     *             the end state may be unknown until the first call to the {@link #_next()}.
     */
    boolean isEnded();

    /**
     * Skip to next number of items, so as to save the extra cost for building the item value
     * object.
     * 
     * The semantics of {@link #skip(int)} SHOULD be no difference than:
     * 
     * <pre>
     * int actualSkipped = 0;
     * for (int i = 0; i &lt; n; i++) {
     *     if (next() == null &amp;&amp; isEnded())
     *         break;
     *     actualSkipped++;
     * }
     * </pre>
     * 
     * This call act as `immediately', that is, if the actually number of items skipped is less then
     * the specified, the iteration must have been reached to the end.
     * 
     * @return Number of items actaully skipped.
     */
    int skip(int n)
            throws X;

    /**
     * Remember the current iteration state for future reset.
     * 
     * @return The duplicated iterator with the same iteration state like current position pointer.
     */
    Mitorx<T, X> mark();

}
