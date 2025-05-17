package net.bodz.bas.repr.form;

import java.util.List;

import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IPropertyAccessor;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.potato.element.PropertyReadException;
import net.bodz.bas.potato.element.PropertyWriteException;

public class PropertyChain
        implements IPropertyAccessor {

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
    public Object read(Object instance)
            throws PropertyReadException {
        if (instance == null)
            throw new NullPointerException("null instance for path: " + path);
        Object obj = instance;
        for (IFormProperty fieldDecl : propertyVector) {
            obj = fieldDecl.getAccessor().read(obj);
            if (obj == null)
                return null;
        }
        return obj;
    }

    @Override
    public void write(Object instance, Object value)
            throws PropertyWriteException {
        if (instance == null)
            throw new NullPointerException("null instance for path: " + path);
        int max = propertyVector.size() - 1;
        Object obj = instance;
        for (int i = 0; i < max; i++) {
            IFormProperty property = propertyVector.get(i);
            try {
                obj = property.getAccessor().read(obj);
            } catch (PropertyReadException e) {
                throw new PropertyWriteException("Error get context from " + property.getProperty() + ": " + e.getMessage(), e);
            }
            if (obj == null)
                return;
        }
        IFormProperty lastField = propertyVector.get(max);
        lastField.getAccessor().write(obj, value);
    }

}
