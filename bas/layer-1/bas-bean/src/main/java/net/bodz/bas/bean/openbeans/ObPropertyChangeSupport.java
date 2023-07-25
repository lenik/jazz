package net.bodz.bas.bean.openbeans;

import net.bodz.bas.bean.api.IPropertyChangeEvent;
import net.bodz.bas.bean.api.IPropertyChangeListener;
import net.bodz.bas.bean.api.IPropertyChangeSupport;

import com.googlecode.openbeans.PropertyChangeListener;
import com.googlecode.openbeans.PropertyChangeSupport;

public class ObPropertyChangeSupport
        implements
            IPropertyChangeSupport {

    PropertyChangeSupport pcs;

    public ObPropertyChangeSupport(PropertyChangeSupport pcs) {
        this.pcs = pcs;
    }

    @Override
    public int hashCode() {
        return pcs.hashCode();
    }

    @Override
    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        pcs.firePropertyChange(propertyName, oldValue, newValue);
    }

    @Override
    public void fireIndexedPropertyChange(String propertyName, int index, Object oldValue, Object newValue) {
        pcs.fireIndexedPropertyChange(propertyName, index, oldValue, newValue);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(propertyName, listener);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public boolean equals(Object obj) {
        return pcs.equals(obj);
    }

    @Override
    public IPropertyChangeListener[] getPropertyChangeListeners(String propertyName) {
        return ObPropertyChangeListener.convert(pcs.getPropertyChangeListeners(propertyName));
    }

    @Override
    public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {
        pcs.firePropertyChange(propertyName, oldValue, newValue);
    }

    @Override
    public void fireIndexedPropertyChange(String propertyName, int index, boolean oldValue, boolean newValue) {
        pcs.fireIndexedPropertyChange(propertyName, index, oldValue, newValue);
    }

    @Override
    public void firePropertyChange(String propertyName, int oldValue, int newValue) {
        pcs.firePropertyChange(propertyName, oldValue, newValue);
    }

    @Override
    public void fireIndexedPropertyChange(String propertyName, int index, int oldValue, int newValue) {
        pcs.fireIndexedPropertyChange(propertyName, index, oldValue, newValue);
    }

    @Override
    public boolean hasListeners(String propertyName) {
        return pcs.hasListeners(propertyName);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    @Override
    public IPropertyChangeListener[] getPropertyChangeListeners() {
        return ObPropertyChangeListener.convert(pcs.getPropertyChangeListeners());
    }

    public void firePropertyChange(com.googlecode.openbeans.PropertyChangeEvent event) {
        pcs.firePropertyChange(event);
    }

    @Override
    public String toString() {
        return pcs.toString();
    }

    @Override
    public void addPropertyChangeListener(IPropertyChangeListener listener) {
        pcs.addPropertyChangeListener(ObPropertyChangeListener.convert(listener));
    }

    @Override
    public void removePropertyChangeListener(IPropertyChangeListener listener) {
        pcs.removePropertyChangeListener(ObPropertyChangeListener.convert(listener));
    }

    @Override
    public void addPropertyChangeListener(String propertyName, IPropertyChangeListener listener) {
        pcs.addPropertyChangeListener(propertyName, ObPropertyChangeListener.convert(listener));
    }

    @Override
    public void removePropertyChangeListener(String propertyName, IPropertyChangeListener listener) {
        pcs.removePropertyChangeListener(propertyName, ObPropertyChangeListener.convert(listener));
    }

    @Override
    public void firePropertyChange(IPropertyChangeEvent event) {
        pcs.firePropertyChange(ObPropertyChangeEvent.convert(event));
    }

    public static ObPropertyChangeSupport convert(PropertyChangeSupport o) {
        if (o == null)
            return null;
        else
            return new ObPropertyChangeSupport(o);
    }

    public static ObPropertyChangeSupport[] convert(PropertyChangeSupport[] src) {
        if (src == null)
            return null;
        ObPropertyChangeSupport[] dst = new ObPropertyChangeSupport[src.length];
        for (int i = 0; i < src.length; i++)
            dst[i] = convert(src[i]);
        return dst;
    }

}
