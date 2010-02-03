package net.bodz.bas.potato.adapter.bean;

import java.beans.MethodDescriptor;
import java.util.Locale;

import net.bodz.bas.potato.adapter.reflect.ReflectPotatoMethod;

public class BeanPotatoMethod
        extends ReflectPotatoMethod {

    private final BeanPotatoType<?> declaringPotatoType;
    private final MethodDescriptor methodDescriptor;

    /**
     *@throws NullPointerException
     *             if <code>declaringPotatoType</code> or <code>methodDescriptor</code> is
     *             <code>null</code>.
     */
    public BeanPotatoMethod(BeanPotatoType<?> declaringPotatoType, MethodDescriptor methodDescriptor) {
        super(methodDescriptor.getMethod());

        if (declaringPotatoType == null)
            throw new NullPointerException("declaringPotatoType");
        this.declaringPotatoType = declaringPotatoType;

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
