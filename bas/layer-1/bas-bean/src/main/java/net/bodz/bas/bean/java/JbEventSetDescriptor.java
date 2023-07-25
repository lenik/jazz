package net.bodz.bas.bean.java;

import java.beans.EventSetDescriptor;
import java.lang.reflect.Method;

import net.bodz.bas.bean.api.IEventSetDescriptor;

public class JbEventSetDescriptor
        extends JbFeatureDescriptor
        implements
            IEventSetDescriptor {

    EventSetDescriptor esd;

    public JbEventSetDescriptor(EventSetDescriptor esd) {
        super(esd);
        this.esd = esd;
    }

    @Override
    public Class<?> getListenerType() {
        return esd.getListenerType();
    }

    @Override
    public Method[] getListenerMethods() {
        return esd.getListenerMethods();
    }

    @Override
    public JbMethodDescriptor[] getListenerMethodDescriptors() {
        return JbMethodDescriptor.convert(esd.getListenerMethodDescriptors());

    }

    @Override
    public Method getAddListenerMethod() {
        return null;
    }

    @Override
    public Method getRemoveListenerMethod() {
        return null;
    }

    @Override
    public Method getGetListenerMethod() {
        return null;
    }

    @Override
    public boolean isUnicast() {
        return false;
    }

    @Override
    public void setUnicast(boolean unicast) {
    }

    @Override
    public boolean isInDefaultEventSet() {
        return false;
    }

    @Override
    public void setInDefaultEventSet(boolean inDefaultEventSet) {
    }

    public static JbEventSetDescriptor convert(EventSetDescriptor o) {
        if (o == null)
            return null;
        else
            return new JbEventSetDescriptor(o);
    }

    public static JbEventSetDescriptor[] convert(EventSetDescriptor[] src) {
        if (src == null)
            return null;
        JbEventSetDescriptor[] dst = new JbEventSetDescriptor[src.length];
        for (int i = 0; i < src.length; i++)
            dst[i] = convert(src[i]);
        return dst;
    }

}
