package net.bodz.bas.potato.provider.bean;

import java.beans.MethodDescriptor;

import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.i18n.dom.XDomainString;
import net.bodz.bas.potato.provider.reflect.ReflectMethod;
import net.bodz.mda.xjdoc.model.IJavaElementDoc;

public class BeanMethod
        extends ReflectMethod {

    private final MethodDescriptor methodDescriptor;

    public BeanMethod(MethodDescriptor methodDescriptor, IJavaElementDoc xjdoc) {
        super(methodDescriptor.getMethod(), xjdoc);
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

    // IPotatoParameter[] getParameters() {
    // ParameterDescriptor[] parameterDescriptors = methodDescriptor.getParameterDescriptors();
    // }

}
