package net.bodz.bas.potato.ref;

public interface IValueChangeListener {

    /**
     * This method gets called when the value of a ref is changed.
     * 
     * @param event
     *            A {@link ValueChangeEvent} object describing the event source and the value that
     *            has changed.
     * @return <code>false</code> to reject the change. However, it's only useful if the change
     *         event is vetoable.
     * @see ValueChangeEvent#isVetoable()
     */
    boolean valueChange(ValueChangeEvent event);

}
