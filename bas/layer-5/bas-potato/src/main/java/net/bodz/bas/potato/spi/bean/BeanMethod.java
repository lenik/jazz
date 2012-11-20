package net.bodz.bas.potato.spi.bean;

import java.beans.MethodDescriptor;

import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.i18n.dom.XDomainString;
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
    public DomainString getDisplayName() {
        String displayName = methodDescriptor.getDisplayName();
        return XDomainString.of(displayName);
    }

    @Override
    public DomainString getDescription() {
        String shortDescription = methodDescriptor.getShortDescription();
        return XDomainString.of(shortDescription);
    }

    @Override
    public int getUserLevel() {
        return FeatureDescriptorUtil.getFeatureUserLevel(methodDescriptor);
    }

    // IPotatoParameter[] getParameters() {
    // ParameterDescriptor[] parameterDescriptors = methodDescriptor.getParameterDescriptors();
    // }

}
