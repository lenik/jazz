package net.bodz.bas.potato.adapter.bean;

import java.beans.MethodDescriptor;
import java.beans.ParameterDescriptor;
import java.lang.reflect.Method;

import net.bodz.bas.potato.IPotatoMethod;

public class PotatoMethodDescriptor
        extends MethodDescriptor {

    public PotatoMethodDescriptor(IPotatoMethod potatoMethod, Method method, ParameterDescriptor[] parameterDescriptors) {
        super(method, null);
        FeatureDescriptorUtil.initFeatureDescriptorFromPotatoElement(this, potatoMethod);
    }

}
