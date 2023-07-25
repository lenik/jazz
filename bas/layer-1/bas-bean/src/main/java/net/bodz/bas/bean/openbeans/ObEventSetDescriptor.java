package net.bodz.bas.bean.openbeans;

import java.lang.reflect.Method;

import net.bodz.bas.bean.api.IEventSetDescriptor;

import com.googlecode.openbeans.EventSetDescriptor;

public class ObEventSetDescriptor
        extends ObFeatureDescriptor
        implements
            IEventSetDescriptor {

    EventSetDescriptor esd;

    public ObEventSetDescriptor(EventSetDescriptor esd) {
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
    public ObMethodDescriptor[] getListenerMethodDescriptors() {
        return ObMethodDescriptor.convert(esd.getListenerMethodDescriptors());

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

    public static ObEventSetDescriptor convert(EventSetDescriptor o) {
        if (o == null)
            return null;
        else
            return new ObEventSetDescriptor(o);
    }

    public static ObEventSetDescriptor[] convert(EventSetDescriptor[] src) {
        if (src == null)
            return null;
        ObEventSetDescriptor[] dst = new ObEventSetDescriptor[src.length];
        for (int i = 0; i < src.length; i++)
            dst[i] = convert(src[i]);
        return dst;
    }

}
