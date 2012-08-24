package net.bodz.swt.viz.invoke;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.potato.ref.IRefDescriptor;
import net.bodz.bas.potato.traits.DecoratedElement;
import net.bodz.bas.potato.traits.IElement;
import net.bodz.bas.potato.traits.IProperty;

public abstract class AbstractInvocationDescriptor
        extends DecoratedElement
        implements IRefDescriptor {

    private static final long serialVersionUID = 1L;

    IRefDescriptor[] parameterDescriptors;

    public AbstractInvocationDescriptor(IElement methodElement, IRefDescriptor[] parameterDescriptors) {
        super(methodElement);
        this.parameterDescriptors = parameterDescriptors;
    }

    public abstract MethodSignature getSignature();

    @Override
    public IProperty getDeclaringProperty() {
        return null;
    }

    @Override
    public boolean isReadable() {
        return true;
    }

    @Override
    public boolean isWritable() {
        return false;
    }

    @Override
    public boolean isValueChangeSource() {
        return false;
    }

    public int getParameterCount() {
        return parameterDescriptors.length;
    }

    public IRefDescriptor[] getParameterDescriptors() {
        return parameterDescriptors;
    }

    public void setParameterDescriptors(IRefDescriptor[] parameterDescriptors) {
        this.parameterDescriptors = parameterDescriptors;
    }

    public IRefDescriptor getParameterDescriptor(int index) {
        return parameterDescriptors[index];
    }

    public void setParameterDescriptor(int index, IRefDescriptor descriptor) {
        parameterDescriptors[index] = descriptor;
    }

    public abstract boolean isNonVoid();

}
