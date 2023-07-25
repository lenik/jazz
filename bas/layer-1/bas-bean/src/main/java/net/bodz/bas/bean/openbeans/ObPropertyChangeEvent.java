package net.bodz.bas.bean.openbeans;

import java.util.EventObject;

import net.bodz.bas.bean.api.IPropertyChangeEvent;

import com.googlecode.openbeans.PropertyChangeEvent;

public class ObPropertyChangeEvent
        extends EventObject
        implements
            IPropertyChangeEvent {

    private static final long serialVersionUID = 1L;

    PropertyChangeEvent pce;

    public ObPropertyChangeEvent(PropertyChangeEvent pce) {
        super(pce.getSource());
        this.pce = pce;
    }

    @Override
    public String getPropertyName() {
        return pce.getPropertyName();
    }

    @Override
    public Object getNewValue() {
        return pce.getNewValue();
    }

    @Override
    public Object getOldValue() {
        return pce.getOldValue();
    }

    @Override
    public Object getPropagationId() {
        return pce.getPropagationId();
    }

    @Override
    public void setPropagationId(Object propagationId) {
        pce.setPropagationId(propagationId);
    }

    public static ObPropertyChangeEvent convert(PropertyChangeEvent o) {
        if (o == null)
            return null;
        else
            return new ObPropertyChangeEvent(o);
    }

    public static ObPropertyChangeEvent[] convert(PropertyChangeEvent[] src) {
        if (src == null)
            return null;
        ObPropertyChangeEvent[] dst = new ObPropertyChangeEvent[src.length];
        for (int i = 0; i < src.length; i++)
            dst[i] = convert(src[i]);
        return dst;
    }

    public static PropertyChangeEvent convert(IPropertyChangeEvent o) {
        if (o == null)
            return null;
        else
            return new PropertyChangeEvent(o.getSource(), o.getPropertyName(), o.getOldValue(), o.getNewValue());
    }

    public static PropertyChangeEvent[] convert(IPropertyChangeEvent[] src) {
        if (src == null)
            return null;
        PropertyChangeEvent[] dst = new PropertyChangeEvent[src.length];
        for (int i = 0; i < src.length; i++)
            dst[i] = convert(src[i]);
        return dst;
    }

}
