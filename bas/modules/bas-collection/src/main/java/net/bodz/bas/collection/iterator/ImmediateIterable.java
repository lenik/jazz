package net.bodz.bas.collection.iterator;

public interface ImmediateIterable<T, X extends Throwable> {

    /**
     * <table border="1">
     * <tr>
     * <th>allowOverlap</th>
     * <th>Returned {@link ImmediateIterator#isOverlapped() overlap-mode}</th>
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
    ImmediateIterator<? extends T, X> iterator(boolean allowOverlap);

    /**
     * Default overlap is disallowed.
     * 
     * @see #iterator(boolean)
     */
    ImmediateIterator<? extends T, X> iterator();

}
