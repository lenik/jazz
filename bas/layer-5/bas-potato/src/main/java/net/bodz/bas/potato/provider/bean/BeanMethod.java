package net.bodz.bas.potato.provider.bean;

import java.beans.MethodDescriptor;
import java.util.Locale;

import net.bodz.bas.potato.provider.reflect.ReflectMethod;

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
    public String getDisplayName(Locale locale) {
        return methodDescriptor.getDisplayName();
    }

    @Override
    public String getDescription(Locale locale) {
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
