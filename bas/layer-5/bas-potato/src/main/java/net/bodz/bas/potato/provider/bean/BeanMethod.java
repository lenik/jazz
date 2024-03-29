package net.bodz.bas.potato.provider.bean;

import net.bodz.bas.bean.api.IMethodDescriptor;
import net.bodz.bas.i18n.dom.StrFn;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.potato.provider.reflect.ReflectMethod;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class BeanMethod
        extends ReflectMethod {

    private final IMethodDescriptor methodDescriptor;

    public BeanMethod(BeanType type, IMethodDescriptor methodDescriptor, IElementDoc xjdoc) {
        super(type, methodDescriptor.getMethod(), xjdoc);
        this.methodDescriptor = methodDescriptor;
    }

    @Override
    public String getName() {
        return methodDescriptor.getName();
    }

    @Override
    public iString getLabel() {
        String displayName = methodDescriptor.getDisplayName();
        return StrFn.wrap(displayName);
    }

    @Override
    public iString getDescription() {
        String shortDescription = methodDescriptor.getShortDescription();
        return StrFn.wrap(shortDescription);
    }

    @Override
    public Class<?> getDeclaringClass() {
        return methodDescriptor.getMethod().getDeclaringClass();
    }

    // IPotatoParameter[] getParameters() {
    // ParameterDescriptor[] parameterDescriptors = methodDescriptor.getParameterDescriptors();
    // }

}
