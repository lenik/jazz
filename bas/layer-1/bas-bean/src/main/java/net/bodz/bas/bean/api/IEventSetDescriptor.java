package net.bodz.bas.bean.api;

import java.lang.reflect.Method;

public interface IEventSetDescriptor
        extends
            IFeatureDescriptor {

    Class<?> getListenerType();

    Method[] getListenerMethods();

    IMethodDescriptor[] getListenerMethodDescriptors();

    Method getAddListenerMethod();

    Method getRemoveListenerMethod();

    Method getGetListenerMethod();

    boolean isUnicast();

    void setUnicast(boolean unicast);

    boolean isInDefaultEventSet();

    void setInDefaultEventSet(boolean inDefaultEventSet);

}