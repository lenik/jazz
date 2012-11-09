package net.bodz.bas.potato.ref;

import net.bodz.bas.potato.model.IProperty;
import net.bodz.bas.potato.model.IType;
import net.bodz.bas.potato.model.SimpleElement;

public class VariableDescriptor
        extends SimpleElement
        implements IRefDescriptor {

    Class<?> valueType;
    IType potatoType;

    public VariableDescriptor(String name, Class<?> valueType) {
        super.setName(name);
        this.valueType = valueType;
    }

    @Override
    public IProperty getDeclaringProperty() {
        return null;
    }

    @Override
    public Class<?> getValueType() {
        return valueType;
    }

    @Override
    public IType getPotatoType() {
        return potatoType;
    }

    @Override
    public boolean isReadable() {
        return true;
    }

    @Override
    public boolean isWritable() {
        return true;
    }

    @Override
    public boolean isValueChangeSource() {
        return false;
    }

}