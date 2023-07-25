package net.bodz.bas.bean.java;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import net.bodz.bas.bean.api.IPropertyChangeListener;

public class JbPropertyChangeListener2
        implements
            PropertyChangeListener {

    IPropertyChangeListener orig;

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        orig.propertyChange(JbPropertyChangeEvent.convert(event));
    }

    @Override
    public int hashCode() {
        return orig.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof JbPropertyChangeListener2) {
            JbPropertyChangeListener2 o = (JbPropertyChangeListener2) obj;
            return orig.equals(o.orig);
        }
        return false;
    }

    @Override
    public String toString() {
        return orig.toString();
    }

}
