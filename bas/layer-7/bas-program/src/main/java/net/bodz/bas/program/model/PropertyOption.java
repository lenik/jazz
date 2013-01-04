package net.bodz.bas.program.model;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.provider.bean.BeanProperty;
import net.bodz.mda.xjdoc.model.MethodDoc;

public class PropertyOption
        extends AbstractOption {

    Class<?> beanClass;
    PropertyDescriptor propertyDescriptor;

    public PropertyOption(PropertyDescriptor propertyDescriptor, MethodDoc getterDoc) {
        super("property:" + propertyDescriptor.getName(), //
                propertyDescriptor.getName(), //
                getterDoc, //
                findPropertyGenericType(propertyDescriptor));
        this.beanClass = propertyDescriptor.getReadMethod().getDeclaringClass();
        this.propertyDescriptor = propertyDescriptor;
    }

    static Type findPropertyGenericType(PropertyDescriptor propertyDescriptor) {
        Method getter = propertyDescriptor.getReadMethod();
        if (getter != null)
            return getter.getGenericReturnType();

        Method setter = propertyDescriptor.getWriteMethod();
        if (setter == null)
            throw new UnexpectedException("Neither getter or setter is defined for property " + propertyDescriptor);

        Type[] paramTypes = setter.getGenericParameterTypes();
        return paramTypes[0];
    }

    @Override
    public IProperty property() {
        return new BeanProperty(beanClass, propertyDescriptor, getXjdoc());
    }

}
