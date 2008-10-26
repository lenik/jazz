package net.bodz.bas.lang.ref;

import java.beans.PropertyChangeListener;

public interface PropertyChangeSupport {

    void addPropertyChangeListener(PropertyChangeListener listener);

    void addPropertyChangeListener(String propertyName,
            PropertyChangeListener listener);

    void removePropertyChangeListener(PropertyChangeListener listener);

    void removePropertyChangeListener(String propertyName,
            PropertyChangeListener listener);

    /* add in future: Object propertyKey? */

}
