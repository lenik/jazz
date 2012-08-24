package net.bodz.bas.util.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see java.beans.PropertyChangeSupport
 */
public class PropertyChangeSupport
        implements Serializable, IPropertyChangeSource {

    private static final long serialVersionUID = 1L;

    Object source;
    List<IPropertyChangeListener> superListeners;
    Map<String, List<IPropertyChangeListener>> namedListeners;

    public PropertyChangeSupport(Object source) {
        this.source = source;
        this.superListeners = new ArrayList<IPropertyChangeListener>();
        this.namedListeners = new HashMap<>();
    }

    @Override
    public void addPropertyChangeListener(IPropertyChangeListener listener) {
        if (listener == null)
            throw new NullPointerException("listener");
        superListeners.add(listener);
    }

    @Override
    public void removePropertyChangeListener(IPropertyChangeListener listener) {
        if (listener == null)
            throw new NullPointerException("listener");
        superListeners.remove(listener);
    }

    @Override
    public synchronized void addPropertyChangeListener(String propertyName, IPropertyChangeListener listener) {
        if (propertyName == null)
            throw new NullPointerException("propertyName");
        if (listener == null)
            throw new NullPointerException("listener");

        List<IPropertyChangeListener> listeners = namedListeners.get(propertyName);
        if (listeners == null)
            listeners = new ArrayList<IPropertyChangeListener>();

        listeners.add(listener);
    }

    @Override
    public synchronized void removePropertyChangeListener(String propertyName, IPropertyChangeListener listener) {
        if (propertyName == null)
            throw new NullPointerException("propertyName");
        if (listener == null)
            throw new NullPointerException("listener");

        List<IPropertyChangeListener> listeners = namedListeners.get(propertyName);
        listeners.remove(listener);
    }

    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        PropertyChangeEvent event = new PropertyChangeEvent(source, propertyName, oldValue, newValue);

        if (superListeners != null)
            for (IPropertyChangeListener listener : superListeners)
                listener.propertyChange(event);

        List<IPropertyChangeListener> listeners = namedListeners.get(propertyName);
        if (listeners != null)
            for (IPropertyChangeListener listener : listeners)
                listener.propertyChange(event);
    }

    public boolean fireVetoablePropertyChange(String propertyName, Object oldValue, Object newValue) {
        PropertyChangeEvent event = new PropertyChangeEvent(source, propertyName, oldValue, newValue, true);

        if (superListeners != null)
            for (IPropertyChangeListener listener : superListeners)
                if (!listener.propertyChange(event))
                    return false;

        List<IPropertyChangeListener> listeners = namedListeners.get(propertyName);
        if (listeners != null)
            for (IPropertyChangeListener listener : listeners)
                if (!listener.propertyChange(event))
                    return false;

        return true;
    }

}
