package net.bodz.bas.potato.spi.bean;

import java.beans.MethodDescriptor;
import java.lang.reflect.Method;

import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.i18n.dom.XDomainString;
import net.bodz.bas.potato.spi.reflect.ReflectMethod;
import net.bodz.bas.potato.spi.reflect.ReflectModifiers;

public class BeanMethod
        extends ReflectMethod {

    private final MethodDescriptor methodDescriptor;
    private final int verboseLevel;
    private final int modifiers;

    public BeanMethod(MethodDescriptor methodDescriptor) {
        super(methodDescriptor.getMethod());
        this.methodDescriptor = methodDescriptor;

        this.verboseLevel = FeatureDescriptorUtil.getVerboseLevel(methodDescriptor);

        Method method = methodDescriptor.getMethod();
        this.modifiers = ReflectModifiers.toVerboseLevel(method.getModifiers());
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

    // -o IElement

    @Override
    public int getModifiers() {
        return modifiers;
    }

    @Override
    public int getVerboseLevel() {
        return verboseLevel;
    }

}
