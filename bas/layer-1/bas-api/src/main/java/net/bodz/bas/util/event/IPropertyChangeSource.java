package net.bodz.bas.util.event;

/**
 * @see java.beans.PropertyChangeSupport
 */
public interface IPropertyChangeSource {

    void addPropertyChangeListener(IPropertyChangeListener listener);

    void addPropertyChangeListener(String propertyName, IPropertyChangeListener listener);

    void removePropertyChangeListener(IPropertyChangeListener listener);

    void removePropertyChangeListener(String propertyName, IPropertyChangeListener listener);

    /* add in future: Object propertyKey? */

}
