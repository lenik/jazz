package net.bodz.bas.bean.openbeans;

import net.bodz.bas.bean.api.IPropertyChangeListener;

import com.googlecode.openbeans.PropertyChangeEvent;
import com.googlecode.openbeans.PropertyChangeListener;

public class ObPropertyChangeListener2
        implements
            PropertyChangeListener {

    IPropertyChangeListener orig;

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        orig.propertyChange(ObPropertyChangeEvent.convert(event));
    }

    @Override
    public int hashCode() {
        return orig.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ObPropertyChangeListener2) {
            ObPropertyChangeListener2 o = (ObPropertyChangeListener2) obj;
            return orig.equals(o.orig);
        }
        return false;
    }

    @Override
    public String toString() {
        return orig.toString();
    }

    public static PropertyChangeListener convert(IPropertyChangeListener o) {
        if (o == null)
            return null;
        else
            return new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent event) {
                    o.propertyChange(ObPropertyChangeEvent.convert(event));
                }
            };
    }

    public static PropertyChangeListener[] convert(IPropertyChangeListener[] src) {
        if (src == null)
            return null;
        PropertyChangeListener[] dst = new PropertyChangeListener[src.length];
        for (int i = 0; i < src.length; i++)
            dst[i] = convert(src[i]);
        return dst;
    }

}
