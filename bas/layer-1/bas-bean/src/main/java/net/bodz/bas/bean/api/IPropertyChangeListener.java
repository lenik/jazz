package net.bodz.bas.bean.api;

import java.util.EventListener;

public interface IPropertyChangeListener
        extends
            EventListener {

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt
     *            A PropertyChangeEvent object describing the event source and the property that has
     *            changed.
     */

    void propertyChange(IPropertyChangeEvent evt);

}
