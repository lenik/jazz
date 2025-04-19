package net.bodz.bas.t.record;

/**
 * @see net.bodz.bas.bean.api.IPropertyChangeSupport
 */
public interface IColumnChangeSupport {

    /**
     * Add a ColumnChangeListener to the listener list. The listener is registered for all properties. The same listener
     * object may be added more than once, and will be called as many times as it is added. If
     * <code>listener</code> is null, no exception is thrown and no action is taken.
     *
     * @param listener The ColumnChangeListener to be added
     */
    void addColumnChangeListener(IColumnChangeListener listener);

    /**
     * Remove a ColumnChangeListener from the listener list. This removes a ColumnChangeListener that was registered for
     * all columns. If <code>listener</code> was added more than once to the same event source, it will be notified one
     * less time after being removed. If
     * <code>listener</code> is null, or was never added, no exception is thrown and no action is
     * taken.
     *
     * @param listener The ColumnChangeListener to be removed
     */
    void removeColumnChangeListener(IColumnChangeListener listener);

    /**
     * Returns an array of all the listeners that were added to the ColumnChangeSupport object with
     * addColumnChangeListener().
     * <p>
     * If some listeners have been added with a named column, then the returned array will be a mixture of
     * ColumnChangeListeners and <code>IColumnChangeListenerProxy</code>s. If the calling method is interested in
     * distinguishing the listeners then it must test each element to see if it's a
     * <code>IColumnChangeListenerProxy</code>, perform the cast, and examine the parameter.
     *
     * <pre>
     * {
     *     &#64;code
     *     ColumnChangeListener[] listeners = bean.getColumnChangeListeners();
     *     for (int i = 0; i < listeners.length; i++) {
     *         if (listeners[i] instanceof IColumnChangeListenerProxy) {
     *             IColumnChangeListenerProxy proxy = (IColumnChangeListenerProxy) listeners[i];
     *             if (proxy.getColumnName().equals("foo")) {
     *                 // proxy is a ColumnChangeListener which was associated
     *                 // with the column named "foo"
     *             }
     *         }
     *     }
     * }
     * </pre>
     *
     * @return all of the <code>ColumnChangeListeners</code> added or an empty array if no listeners have been added
     * @see IColumnChangeListenerProxy
     * @since 1.4
     */
    IColumnChangeListener[] getColumnChangeListeners();

    /**
     * Add a ColumnChangeListener for a specific column. The listener will be invoked only when a call on
     * fireColumnChange names that specific column. The same listener object may be added more than once. For each
     * column, the listener will be invoked the number of times it was added for that column. If
     * <code>column</code> or <code>listener</code> is null, no exception is thrown and no action is taken.
     *
     * @param column   The name of the column to listen on.
     * @param listener The ColumnChangeListener to be added
     */
    void addColumnChangeListener(IColumnType<?, ?> column, IColumnChangeListener listener);

    /**
     * Remove a ColumnChangeListener for a specific column. If <code>listener</code> was added more than once to the
     * same event source for the specified column, it will be notified one less time after being removed. If
     * <code>column</code> is null, no exception is thrown and no action is taken. If <code>listener</code> is
     * null, or was never added for the specified column, no exception is thrown and no action is taken.
     *
     * @param column   The name of the column that was listened on.
     * @param listener The ColumnChangeListener to be removed
     */
    void removeColumnChangeListener(IColumnType<?, ?> column, IColumnChangeListener listener);

    /**
     * Returns an array of all the listeners which have been associated with the named column.
     *
     * @param column The name of the column being listened to
     * @return all of the <code>ColumnChangeListeners</code> associated with the named column. If no such listeners have
     * been added, or if <code>column</code> is null, an empty array is returned.
     * @since 1.4
     */
    IColumnChangeListener[] getColumnChangeListeners(IColumnType<?, ?> column);

    /**
     * Reports a bound column update to listeners that have been registered to track updates of all columns or a column
     * with the specified name.
     * <p>
     * No event is fired if old and new values are equal and non-null.
     * <p>
     * This is merely a convenience wrapper around the more general {@link #fireColumnChange(ColumnChangeEvent)}
     * method.
     *
     * @param column   the programmatic name of the column that was changed
     * @param oldValue the old value of the column
     * @param newValue the new value of the column
     */
    void fireColumnChange(IColumnType<?, ?> column, Object oldValue, Object newValue);

    /**
     * Reports an integer bound column update to listeners that have been registered to track updates of all columns or
     * a column with the specified name.
     * <p>
     * No event is fired if old and new values are equal.
     * <p>
     * This is merely a convenience wrapper around the more general
     * {@link #fireColumnChange(IColumnType, Object, Object)} method.
     *
     * @param column   the programmatic name of the column that was changed
     * @param oldValue the old value of the column
     * @param newValue the new value of the column
     */
    void fireColumnChange(IColumnType<?, ?> column, int oldValue, int newValue);

    /**
     * Reports a boolean bound column update to listeners that have been registered to track updates of all columns or a
     * column with the specified name.
     * <p>
     * No event is fired if old and new values are equal.
     * <p>
     * This is merely a convenience wrapper around the more general
     * {@link #fireColumnChange(IColumnType, Object, Object)} method.
     *
     * @param column   the programmatic name of the column that was changed
     * @param oldValue the old value of the column
     * @param newValue the new value of the column
     */
    void fireColumnChange(IColumnType<?, ?> column, boolean oldValue, boolean newValue);

    /**
     * Fires a column change event to listeners that have been registered to track updates of all columns or a column
     * with the specified name.
     * <p>
     * No event is fired if the given event's old and new values are equal and non-null.
     *
     * @param event the {@code ColumnChangeEvent} to be fired
     */
    void fireColumnChange(ColumnChangeEvent event);

    /**
     * Reports a bound indexed column update to listeners that have been registered to track updates of all columns or a
     * column with the specified name.
     * <p>
     * No event is fired if old and new values are equal and non-null.
     * <p>
     * This is merely a convenience wrapper around the more general {@link #fireColumnChange(ColumnChangeEvent)}
     * method.
     *
     * @param column   the programmatic name of the column that was changed
     * @param index    the index of the column element that was changed
     * @param oldValue the old value of the column
     * @param newValue the new value of the column
     * @since 1.5
     */
    void fireIndexedColumnChange(IColumnType<?, ?> column, int index, Object oldValue, Object newValue);

    /**
     * Reports an integer bound indexed column update to listeners that have been registered to track updates of all
     * columns or a column with the specified name.
     * <p>
     * No event is fired if old and new values are equal.
     * <p>
     * This is merely a convenience wrapper around the more general
     * {@link #fireIndexedColumnChange(IColumnType, int, Object, Object)} method.
     *
     * @param column   the programmatic name of the column that was changed
     * @param index    the index of the column element that was changed
     * @param oldValue the old value of the column
     * @param newValue the new value of the column
     * @since 1.5
     */
    void fireIndexedColumnChange(IColumnType<?, ?> column, int index, int oldValue, int newValue);

    /**
     * Reports a boolean bound indexed column update to listeners that have been registered to track updates of all
     * columns or a column with the specified name.
     * <p>
     * No event is fired if old and new values are equal.
     * <p>
     * This is merely a convenience wrapper around the more general
     * {@link #fireIndexedColumnChange(IColumnType, int, Object, Object)} method.
     *
     * @param column   the programmatic name of the column that was changed
     * @param index    the index of the column element that was changed
     * @param oldValue the old value of the column
     * @param newValue the new value of the column
     * @since 1.5
     */
    void fireIndexedColumnChange(IColumnType<?, ?> column, int index, boolean oldValue, boolean newValue);

    /**
     * Check if there are any listeners for a specific column, including those registered on all columns. If
     * <code>column</code> is null, only check for listeners registered on all columns.
     *
     * @param column the column name.
     * @return true if there are one or more listeners for the given column
     */
    boolean hasListeners(IColumnType<?, ?> column);

}
