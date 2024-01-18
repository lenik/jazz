package net.bodz.lily.criterion;

import java.lang.reflect.Method;
import java.util.List;

import net.bodz.bas.bean.api.IBeanInfo;
import net.bodz.bas.bean.api.IPropertyDescriptor;
import net.bodz.bas.bean.api.IntrospectionException;
import net.bodz.bas.bean.api.Introspectors;

public class FieldTypeInferrer
        implements
            ITypeInferrer {

    Class<?> context;

    boolean beanProperties = true;
    boolean fields;
    boolean allFields;

    boolean errorCheck = true;

    public FieldTypeInferrer(Class<?> context) {
        this.context = context;
    }

    @Override
    public Class<?> getFieldType(List<String> fieldNameStack) {
        Class<?> context = this.context;

        StringBuilder prefix = new StringBuilder();

        for (String fieldName : fieldNameStack) {
            IBeanInfo beanInfo;
            try {
                beanInfo = Introspectors.getBeanInfo(context);
            } catch (IntrospectionException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

            IPropertyDescriptor property = getProperty(beanInfo, fieldName);
            if (property == null)
                if (errorCheck)
                    throw new IllegalArgumentException("no property " + fieldName + " in " + prefix + ": " + context);
                else
                    return null;

            Method getter = property.getReadMethod();
            if (getter == null)
                if (errorCheck)
                    throw new IllegalArgumentException("property " + fieldName + " is not readdable");
                else
                    return null;

            context = getter.getReturnType();
            if (prefix.length() != 0)
                prefix.append(".");
            prefix.append(fieldName);
        }
        return context;
    }

    IPropertyDescriptor getProperty(IBeanInfo beanInfo, String name) {
        for (IPropertyDescriptor property : beanInfo.getPropertyDescriptors()) {
            if (name.equals(property.getName()))
                return property;
        }
        return null;
    }

}
