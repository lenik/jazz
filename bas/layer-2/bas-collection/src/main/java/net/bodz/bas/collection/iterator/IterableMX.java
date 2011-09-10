package net.bodz.bas.collection.iterator;

public interface IterableMX<T, X extends Throwable>
        extends IterableX<T, X> {

    /**
     * <table border="1">
     * <tr>
     * <th>allowOverlap</th>
     * <th>Returned {@link IteratorMX#isOverlapped() overlap-mode}</th>
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
     * 
     * @throws UnsupportedOperationException
     *             If specified overlap-mode isn't allowed.
     */
    IteratorMX<? extends T, ? extends X> iterator(boolean allowOverlap);

    /**
     * Default overlap is allowed..
     * 
     * @see #iterator(boolean)
     */
    @Override
    IteratorX<T, X> iteratorX();

}
