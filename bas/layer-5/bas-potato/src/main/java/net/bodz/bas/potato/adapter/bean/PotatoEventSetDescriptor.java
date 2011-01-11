package net.bodz.bas.potato.adapter.bean;

import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.util.EventListener;

import net.bodz.bas.potato.IPotatoEvent;

public class PotatoEventSetDescriptor
        extends EventSetDescriptor {

    public PotatoEventSetDescriptor(IPotatoEvent potatoEvent)
            throws IntrospectionException {
        super(Object.class, potatoEvent.getName(), EventListener.class, "onChange");
        FeatureDescriptorUtil.initFeatureDescriptorFromPotatoElement(this, potatoEvent);
    }

}
