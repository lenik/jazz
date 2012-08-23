package net.bodz.bas.util.event;

public interface IPropertyChangeListener {

    /**
     * This method gets called when a bound property is changed.
     * 
     * @param event
     *            A PropertyChangeEvent object describing the event source and the property that has
     *            changed.
     * @return <code>false</code> to reject the change. However, it's only useful if the change
     *         event is vetoable.
     * @see PropertyChangeEvent#isVetoable()
     */
    boolean propertyChange(PropertyChangeEvent event);

}
