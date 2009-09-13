package net.bodz.bas.types.util;

public interface DirectIterable<T, X extends Throwable> {

    /**
     * <table border="1">
     * <tr>
     * <th>allowOverlap</th>
     * <th>Returned {@link DirectIterator#isOverlapped() overlap-mode}</th>
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
    DirectIterator<? extends T, X> iterator(boolean allowOverlap);

}
