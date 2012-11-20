package net.bodz.bas.potato.ref;

public class ConstVariableDescriptor
        extends VariableDescriptor {

    private static final long serialVersionUID = 1L;

    public ConstVariableDescriptor(String name, Class<?> valueType) {
        super(name, valueType);
    }

    @Override
    public boolean isWritable() {
        return false;
    }

}
