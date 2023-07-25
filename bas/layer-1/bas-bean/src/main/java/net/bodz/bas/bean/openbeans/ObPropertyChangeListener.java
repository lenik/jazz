package net.bodz.bas.bean.openbeans;

import net.bodz.bas.bean.api.IPropertyChangeEvent;
import net.bodz.bas.bean.api.IPropertyChangeListener;

import com.googlecode.openbeans.PropertyChangeEvent;
import com.googlecode.openbeans.PropertyChangeListener;

public class ObPropertyChangeListener
        implements
            IPropertyChangeListener {

    PropertyChangeListener pcl;

    public ObPropertyChangeListener(PropertyChangeListener pcl) {
        if (pcl == null)
            throw new NullPointerException("pcl");
        this.pcl = pcl;
    }

    @Override
    public void propertyChange(IPropertyChangeEvent evt) {
        PropertyChangeEvent _pce = ObPropertyChangeEvent.convert(evt);
        pcl.propertyChange(_pce);
    }

    public static ObPropertyChangeListener convert(PropertyChangeListener o) {
        if (o == null)
            return null;
        else
            return new ObPropertyChangeListener(o);
    }

    public static ObPropertyChangeListener[] convert(PropertyChangeListener[] src) {
        if (src == null)
            return null;
        ObPropertyChangeListener[] dst = new ObPropertyChangeListener[src.length];
        for (int i = 0; i < src.length; i++)
            dst[i] = convert(src[i]);
        return dst;
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
