package net.bodz.bas.potato.model.invoke;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.potato.model.DecoratedPotatoElement;
import net.bodz.bas.potato.model.IPotatoElement;
import net.bodz.bas.potato.model.IProperty;
import net.bodz.bas.potato.ref.IRefDescriptor;

public abstract class AbstractInvocationDescriptor
        extends DecoratedPotatoElement
        implements IRefDescriptor {

    private static final long serialVersionUID = 1L;

    IRefDescriptor[] parameterDescriptors;

    public AbstractInvocationDescriptor(IPotatoElement methodElement, IRefDescriptor[] parameterDescriptors) {
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
