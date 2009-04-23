package net.bodz.bas.lang.ref;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @see PropertyChangeSupport
 */
public interface IPropertyChangeSupport {

    void addPropertyChangeListener(PropertyChangeListener listener);

    void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);

    void removePropertyChangeListener(PropertyChangeListener listener);

    void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);

    /* add in future: Object propertyKey? */

}
