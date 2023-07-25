package net.bodz.bas.bean.api;

public interface IPropertyChangeSupport {

    /**
     * Add a PropertyChangeListener to the listener list. The listener is registered for all
     * properties. The same listener object may be added more than once, and will be called as many
     * times as it is added. If <code>listener</code> is null, no exception is thrown and no action
     * is taken.
     *
     * @param listener
     *            The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(IPropertyChangeListener listener);

    /**
     * Remove a PropertyChangeListener from the listener list. This removes a PropertyChangeListener
     * that was registered for all properties. If <code>listener</code> was added more than once to
     * the same event source, it will be notified one less time after being removed. If
     * <code>listener</code> is null, or was never added, no exception is thrown and no action is
     * taken.
     *
     * @param listener
     *            The PropertyChangeListener to be removed
     */
    void removePropertyChangeListener(IPropertyChangeListener listener);

    /**
     * Returns an array of all the listeners that were added to the PropertyChangeSupport object
     * with addPropertyChangeListener().
     * <p>
     * If some listeners have been added with a named property, then the returned array will be a
     * mixture of PropertyChangeListeners and <code>IPropertyChangeListenerProxy</code>s. If the
     * calling method is interested in distinguishing the listeners then it must test each element
     * to see if it's a <code>IPropertyChangeListenerProxy</code>, perform the cast, and examine the
     * parameter.
     *
     * <pre>
     * {
     *     &#64;code
     *     PropertyChangeListener[] listeners = bean.getPropertyChangeListeners();
     *     for (int i = 0; i < listeners.length; i++) {
     *         if (listeners[i] instanceof IPropertyChangeListenerProxy) {
     *             IPropertyChangeListenerProxy proxy = (IPropertyChangeListenerProxy) listeners[i];
     *             if (proxy.getPropertyName().equals("foo")) {
     *                 // proxy is a PropertyChangeListener which was associated
     *                 // with the property named "foo"
     *             }
     *         }
     *     }
     * }
     * </pre>
     *
     * @see IPropertyChangeListenerProxy
     * @return all of the <code>PropertyChangeListeners</code> added or an empty array if no
     *         listeners have been added
     * @since 1.4
     */
    IPropertyChangeListener[] getPropertyChangeListeners();

    /**
     * Add a PropertyChangeListener for a specific property. The listener will be invoked only when
     * a call on firePropertyChange names that specific property. The same listener object may be
     * added more than once. For each property, the listener will be invoked the number of times it
     * was added for that property. If <code>propertyName</code> or <code>listener</code> is null,
     * no exception is thrown and no action is taken.
     *
     * @param propertyName
     *            The name of the property to listen on.
     * @param listener
     *            The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(String propertyName, IPropertyChangeListener listener);

    /**
     * Remove a PropertyChangeListener for a specific property. If <code>listener</code> was added
     * more than once to the same event source for the specified property, it will be notified one
     * less time after being removed. If <code>propertyName</code> is null, no exception is thrown
     * and no action is taken. If <code>listener</code> is null, or was never added for the
     * specified property, no exception is thrown and no action is taken.
     *
     * @param propertyName
     *            The name of the property that was listened on.
     * @param listener
     *            The PropertyChangeListener to be removed
     */
    void removePropertyChangeListener(String propertyName, IPropertyChangeListener listener);

    /**
     * Returns an array of all the listeners which have been associated with the named property.
     *
     * @param propertyName
     *            The name of the property being listened to
     * @return all of the <code>PropertyChangeListeners</code> associated with the named property.
     *         If no such listeners have been added, or if <code>propertyName</code> is null, an
     *         empty array is returned.
     * @since 1.4
     */
    IPropertyChangeListener[] getPropertyChangeListeners(String propertyName);

    /**
     * Reports a bound property update to listeners that have been registered to track updates of
     * all properties or a property with the specified name.
     * <p>
     * No event is fired if old and new values are equal and non-null.
     * <p>
     * This is merely a convenience wrapper around the more general
     * {@link #firePropertyChange(IPropertyChangeEvent)} method.
     *
     * @param propertyName
     *            the programmatic name of the property that was changed
     * @param oldValue
     *            the old value of the property
     * @param newValue
     *            the new value of the property
     */
    void firePropertyChange(String propertyName, Object oldValue, Object newValue);

    /**
     * Reports an integer bound property update to listeners that have been registered to track
     * updates of all properties or a property with the specified name.
     * <p>
     * No event is fired if old and new values are equal.
     * <p>
     * This is merely a convenience wrapper around the more general
     * {@link #firePropertyChange(String, Object, Object)} method.
     *
     * @param propertyName
     *            the programmatic name of the property that was changed
     * @param oldValue
     *            the old value of the property
     * @param newValue
     *            the new value of the property
     */
    void firePropertyChange(String propertyName, int oldValue, int newValue);

    /**
     * Reports a boolean bound property update to listeners that have been registered to track
     * updates of all properties or a property with the specified name.
     * <p>
     * No event is fired if old and new values are equal.
     * <p>
     * This is merely a convenience wrapper around the more general
     * {@link #firePropertyChange(String, Object, Object)} method.
     *
     * @param propertyName
     *            the programmatic name of the property that was changed
     * @param oldValue
     *            the old value of the property
     * @param newValue
     *            the new value of the property
     */
    void firePropertyChange(String propertyName, boolean oldValue, boolean newValue);

    /**
     * Fires a property change event to listeners that have been registered to track updates of all
     * properties or a property with the specified name.
     * <p>
     * No event is fired if the given event's old and new values are equal and non-null.
     *
     * @param event
     *            the {@code IPropertyChangeEvent} to be fired
     */
    void firePropertyChange(IPropertyChangeEvent event);

    /**
     * Reports a bound indexed property update to listeners that have been registered to track
     * updates of all properties or a property with the specified name.
     * <p>
     * No event is fired if old and new values are equal and non-null.
     * <p>
     * This is merely a convenience wrapper around the more general
     * {@link #firePropertyChange(IPropertyChangeEvent)} method.
     *
     * @param propertyName
     *            the programmatic name of the property that was changed
     * @param index
     *            the index of the property element that was changed
     * @param oldValue
     *            the old value of the property
     * @param newValue
     *            the new value of the property
     * @since 1.5
     */
    void fireIndexedPropertyChange(String propertyName, int index, Object oldValue, Object newValue);

    /**
     * Reports an integer bound indexed property update to listeners that have been registered to
     * track updates of all properties or a property with the specified name.
     * <p>
     * No event is fired if old and new values are equal.
     * <p>
     * This is merely a convenience wrapper around the more general
     * {@link #fireIndexedPropertyChange(String, int, Object, Object)} method.
     *
     * @param propertyName
     *            the programmatic name of the property that was changed
     * @param index
     *            the index of the property element that was changed
     * @param oldValue
     *            the old value of the property
     * @param newValue
     *            the new value of the property
     * @since 1.5
     */
    void fireIndexedPropertyChange(String propertyName, int index, int oldValue, int newValue);

    /**
     * Reports a boolean bound indexed property update to listeners that have been registered to
     * track updates of all properties or a property with the specified name.
     * <p>
     * No event is fired if old and new values are equal.
     * <p>
     * This is merely a convenience wrapper around the more general
     * {@link #fireIndexedPropertyChange(String, int, Object, Object)} method.
     *
     * @param propertyName
     *            the programmatic name of the property that was changed
     * @param index
     *            the index of the property element that was changed
     * @param oldValue
     *            the old value of the property
     * @param newValue
     *            the new value of the property
     * @since 1.5
     */
    void fireIndexedPropertyChange(String propertyName, int index, boolean oldValue, boolean newValue);

    /**
     * Check if there are any listeners for a specific property, including those registered on all
     * properties. If <code>propertyName</code> is null, only check for listeners registered on all
     * properties.
     *
     * @param propertyName
     *            the property name.
     * @return true if there are one or more listeners for the given property
     */
    boolean hasListeners(String propertyName);

}