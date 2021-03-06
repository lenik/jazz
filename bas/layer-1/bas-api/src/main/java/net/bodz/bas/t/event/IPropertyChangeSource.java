package net.bodz.bas.t.event;

import net.bodz.bas.meta.decl.EventSource;

/**
 * Property change event source.
 * 
 * WARNING: To support listener-wrappers, an implementation should use maps which utilized the
 * equality methods ( {@link Object#hashCode()}, {@link Object#equals(Object)}), rather then
 * identity equality, to store listeners.
 * 
 * @see com.googlecode.openbeans.PropertyChangeSupport
 */
public interface IPropertyChangeSource
        extends EventSource {

    void addPropertyChangeListener(IPropertyChangeListener listener);

    void addPropertyChangeListener(String propertyName, IPropertyChangeListener listener);

    void removePropertyChangeListener(IPropertyChangeListener listener);

    void removePropertyChangeListener(String propertyName, IPropertyChangeListener listener);

    /* add in future: Object propertyKey? */

}
