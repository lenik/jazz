package net.bodz.bas.potato.ref;

public class ConstVariableDescriptor
        extends VariableDescriptor {

    public ConstVariableDescriptor(String name, Class<?> valueType) {
        super(name, valueType);
    }

    @Override
    public boolean isWritable() {
        return false;
    }

}
