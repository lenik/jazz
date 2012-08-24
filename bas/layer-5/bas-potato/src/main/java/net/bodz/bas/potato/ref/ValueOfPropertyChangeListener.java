package net.bodz.bas.potato.ref;

import net.bodz.bas.util.event.IPropertyChangeListener;
import net.bodz.bas.util.event.PropertyChangeEvent;

public class ValueOfPropertyChangeListener
        implements IPropertyChangeListener {

    final IValueChangeListener delegate;

    public ValueOfPropertyChangeListener(IValueChangeListener delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean propertyChange(PropertyChangeEvent event) {
        ValueChangeEvent _event = new ValueChangeEvent(event.getSource(), event.getOldValue(), event.getNewValue());
        _event.setVetoable(event.isVetoable());
        return delegate.valueChange(_event);
    }

    @Override
    public int hashCode() {
        int h = 0xbaefc441;
        h += delegate.hashCode();
        return h;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ValueOfPropertyChangeListener))
            return false;
        ValueOfPropertyChangeListener o = (ValueOfPropertyChangeListener) obj;
        return delegate == o.delegate;
    }

    @Override
    public String toString() {
        return delegate.toString();
    }

}
