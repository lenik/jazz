package net.bodz.bas.repr.form;

import java.util.List;

import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IPropertyAccessor;
import net.bodz.bas.potato.element.IType;

public class PropertyChain
        implements
            IPropertyAccessor {

    private final String path;
    private final List<IFormProperty> propertyVector;

    public PropertyChain(String path, List<IFormProperty> propertyVector) {
        this.path = path;
        this.propertyVector = propertyVector;
    }

    public String getPath() {
        return path;
    }

    public IFormProperty getLast() {
        if (propertyVector.isEmpty())
            return null;
        else
            return propertyVector.get(propertyVector.size() - 1);
    }

    @Override
    public Class<?> getPropertyClass() {
        if (propertyVector.isEmpty())
            return null; // void.class;
        IFormProperty last = propertyVector.get(propertyVector.size() - 1);
        Class<?> valueType = last.getValueType();
        return valueType;
    }

    @Override
    public IType getPropertyType() {
        if (propertyVector.isEmpty())
            return null; // void?
        IFormProperty last = propertyVector.get(propertyVector.size() - 1);
        IProperty property = last.getProperty();
        IType propertyType = property.getPropertyType();
        return propertyType;
    }

    @Override
    public Object getValue(Object instance)
            throws ReflectiveOperationException {
        if (instance == null)
            throw new NullPointerException("null instance for path: " + path);
        Object obj = instance;
        for (IFormProperty fieldDecl : propertyVector) {
            obj = fieldDecl.getAccessor().getValue(obj);
            if (obj == null)
                return null;
        }
        return obj;
    }

    @Override
    public void setValue(Object instance, Object value)
            throws ReflectiveOperationException {
        if (instance == null)
            throw new NullPointerException("null instance for path: " + path);
        int max = propertyVector.size() - 1;
        Object obj = instance;
        for (int i = 0; i < max; i++) {
            obj = propertyVector.get(i).getAccessor().getValue(obj);
            if (obj == null)
                return;
        }
        IFormProperty lastField = propertyVector.get(max);
        lastField.getAccessor().setValue(obj, value);
    }

}
