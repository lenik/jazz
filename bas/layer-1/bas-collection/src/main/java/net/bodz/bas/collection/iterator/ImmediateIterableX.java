package net.bodz.bas.collection.iterator;

public interface ImmediateIterableX<T, X extends Exception>
        extends IterableX<T, X> {

    /**
     * <table border="1">
     * <tr>
     * <th>allowOverlap</th>
     * <th>Returned {@link ImmediateIteratorX#isOverlapped() overlap-mode}</th>
     * <th>Overlapping</th>
     * </tr>
     * <tr>
     * <td><code>true</code></td>
     * <td><code>true</code></td>
     * <td>Enabled</td>
     * </tr>
     * <tr>
     * <td><code>true</code></td>
     * <td><code>false</code></td>
     * <td>Not supported and disabled</td>
     * </tr>
     * <tr>
     * <td><code>false</code></td>
     * <td><code>true</code></td>
     * <td>Unexpected!</td>
     * </tr>
     * <tr>
     * <td><code>false</code></td>
     * <td><code>false</code></td>
     * <td>Disabled</td>
     * </tr>
     * </table>
     */
    ImmediateIteratorX<? extends T, ? extends X> iterator(boolean allowOverlap)
            throws X;

    /**
     * Default overlap is dis-allowed.
     * 
     * @see #iterator(boolean)
     * @throws IteratorTargetException
     */
    @Override
    IteratorX<T, X> iterator()
            throws IteratorTargetException;

}
