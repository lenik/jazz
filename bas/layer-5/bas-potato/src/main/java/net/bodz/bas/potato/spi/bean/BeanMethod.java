package net.bodz.bas.potato.spi.bean;

import java.beans.MethodDescriptor;

import net.bodz.bas.potato.spi.reflect.ReflectMethod;

public class BeanMethod
        extends ReflectMethod {

    private final MethodDescriptor methodDescriptor;

    public BeanMethod(MethodDescriptor methodDescriptor) {
        super(methodDescriptor.getMethod());
        this.methodDescriptor = methodDescriptor;
    }

    @Override
    public String getName() {
        return methodDescriptor.getName();
    }

    @Override
    public String getDisplayName() {
        return methodDescriptor.getDisplayName();
    }

    @Override
    public String getDescription() {
        return methodDescriptor.getShortDescription();
    }

    @Override
    public int getPreferenceLevel() {
        return FeatureDescriptorUtil.getFeaturePreferenceLevel(methodDescriptor);
    }

    // IPotatoParameter[] getParameters() {
    // ParameterDescriptor[] parameterDescriptors = methodDescriptor.getParameterDescriptors();
    // }

}
