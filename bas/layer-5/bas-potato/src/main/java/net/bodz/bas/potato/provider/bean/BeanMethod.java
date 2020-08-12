package net.bodz.bas.potato.provider.bean;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.potato.provider.reflect.ReflectMethod;
import net.bodz.mda.xjdoc.model.IElementDoc;

import com.googlecode.openbeans.MethodDescriptor;

public class BeanMethod
        extends ReflectMethod {

    private final MethodDescriptor methodDescriptor;

    public BeanMethod(MethodDescriptor methodDescriptor, IElementDoc xjdoc) {
        super(methodDescriptor.getMethod(), xjdoc);
        this.methodDescriptor = methodDescriptor;
    }

    @Override
    public String getName() {
        return methodDescriptor.getName();
    }

    @Override
    public iString getLabel() {
        String displayName = methodDescriptor.getDisplayName();
        return iString.fn.val(displayName);
    }

    @Override
    public iString getDescription() {
        String shortDescription = methodDescriptor.getShortDescription();
        return iString.fn.val(shortDescription);
    }

    // IPotatoParameter[] getParameters() {
    // ParameterDescriptor[] parameterDescriptors = methodDescriptor.getParameterDescriptors();
    // }

}
