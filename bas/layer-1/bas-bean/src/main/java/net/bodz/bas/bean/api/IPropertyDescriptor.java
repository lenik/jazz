package net.bodz.bas.bean.api;

import java.lang.reflect.Method;

public interface IPropertyDescriptor
        extends
            IFeatureDescriptor {

    Class<?> getPropertyType();

    Method getReadMethod();

    Method getWriteMethod();

    void setReadMethod(Method getter)
            throws IntrospectionException;

    void setWriteMethod(Method setter)
            throws IntrospectionException;

    boolean isConstrained();

    void setConstrained(boolean constrained);

    boolean isBound();

    void setBound(boolean bound);

    Class<?> getPropertyEditorClass();

    void setPropertyEditorClass(Class<?> propertyEditorClass);

    IPropertyEditor createPropertyEditor(Object bean);

}