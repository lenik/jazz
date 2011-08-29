package net.bodz.bas.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;
import net.bodz.bas.util.Ref;

public class PropertyRef<PropertyType>
        implements Ref<PropertyType> {

    private final Object bean;

    private final String propertyName;
    private final Class<PropertyType> propertyType;

    private final Method readMethod;
    private final Method writeMethod;
    private final Object[] parameters;

    public PropertyRef(Object bean, String propertyName, Class<PropertyType> propertyType, Method readMethod,
            Method writeMethod, Object... parameters) {
        if (propertyType == null)
            throw new NullPointerException("propertyType");
        if (parameters != null && parameters.length == 0)
            parameters = null;
        this.bean = bean;
        this.propertyName = propertyName;
        this.propertyType = propertyType;
        this.readMethod = readMethod;
        this.writeMethod = writeMethod;
        this.parameters = parameters;
    }

    public String getPropertyName() {
        return propertyName;
    }

    @Override
    public PropertyType get() {
        if (readMethod == null)
            throw new UnsupportedOperationException("Can't read from property. ");
        Object propertyValue;
        try {
            if (parameters == null)
                propertyValue = Jdk7Reflect.invoke(readMethod, bean);
            else
                propertyValue = Jdk7Reflect.invoke(readMethod, bean, parameters);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return propertyType.cast(propertyValue);
    }

    @Override
    public void set(PropertyType value) {
        if (writeMethod == null)
            throw new UnsupportedOperationException("Can't write to property. ");
        try {
            Jdk7Reflect.invoke(writeMethod, bean, value);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(bean);
    }

    public static <T> PropertyRef<T> wrap(T bean, Class<T> propertyType, PropertyDescriptor propertyDescriptor,
            Object... parameters) {
        if (bean == null)
            throw new NullPointerException("bean");

        Class<?> beanClass = (Class<?>) bean.getClass();
        return wrap(beanClass, bean, propertyType, propertyDescriptor, parameters);
    }

    public static <T> PropertyRef<T> wrap(Class<?> beanClass, T bean, Class<T> propertyType,
            PropertyDescriptor propertyDescriptor, Object... parameters) {
        Class<?> actualType = propertyDescriptor.getPropertyType();
        if (!propertyType.isAssignableFrom(actualType))
            throw new IllegalArgumentException("Incompatible with actual property type: " + propertyType);

        String propertyName = propertyDescriptor.getName();
        Method readMethod = propertyDescriptor.getReadMethod();
        Method writeMethod = propertyDescriptor.getWriteMethod();

        PropertyRef<T> propertyRef = new PropertyRef<T>(bean, propertyName, propertyType, readMethod, writeMethod,
                parameters);
        return propertyRef;
    }

    public static <T> PropertyRef<T> wrap(T bean, Class<T> propertyType, String propertyName, Object... parameters)
            throws IntrospectionException {
        if (bean == null)
            throw new NullPointerException("bean");
        Class<?> beanClass = (Class<?>) bean.getClass();
        return wrap(beanClass, bean, propertyType, propertyName, parameters);
    }

    public static <T> PropertyRef<T> wrap(Class<?> beanClass, T bean, Class<T> propertyType, String propertyName,
            Object... parameters)
            throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
        for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
            String name = descriptor.getName();
            if (name.equals(propertyName)) {
                return wrap(beanClass, bean, propertyType, descriptor, parameters);
            }
        }
        throw new NoSuchKeyException(propertyName);
    }

}
