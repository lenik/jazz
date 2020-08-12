package net.bodz.bas.c.bean;

import java.lang.reflect.Method;

import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.t.ref.AbstractRef;

import com.googlecode.openbeans.BeanInfo;
import com.googlecode.openbeans.IntrospectionException;
import com.googlecode.openbeans.Introspector;
import com.googlecode.openbeans.PropertyDescriptor;

public class BeanPropertyRef<T>
        extends AbstractRef<T> {

    private static final long serialVersionUID = 1L;

    private final Object bean;

    private final String propertyName;
    private final Class<T> propertyType;

    private final Method getter;
    private final Method setter;
    private final Object[] parameters;

    public BeanPropertyRef(Object bean, String propertyName, Class<T> propertyType, Method getter, Method setter,
            Object... parameters) {

        if (parameters != null && parameters.length == 0)
            parameters = null;

        this.bean = bean;

        this.propertyName = propertyName;
        this.propertyType = propertyType;

        this.getter = getter;
        this.setter = setter;

        this.parameters = parameters;
    }

    public String getPropertyName() {
        return propertyName;
    }

    @Override
    public Class<? extends T> getValueType() {
        return propertyType;
    }

    @Override
    public T get() {
        if (getter == null)
            throw new UnsupportedOperationException("Can't read from property. ");
        Object propertyValue;
        try {
            if (parameters == null)
                propertyValue = getter.invoke(bean);
            else
                propertyValue = getter.invoke(bean, parameters);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return propertyType.cast(propertyValue);
    }

    @Override
    public void set(T value) {
        if (setter == null)
            throw new UnsupportedOperationException("Can't write to property. ");
        try {
            setter.invoke(bean, value);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(bean);
    }

    public static <T> BeanPropertyRef<T> wrap(T bean, Class<T> propertyType, PropertyDescriptor propertyDescriptor,
            Object... parameters) {
        if (bean == null)
            throw new NullPointerException("bean");

        Class<?> beanClass = bean.getClass();
        return wrap(beanClass, bean, propertyType, propertyDescriptor, parameters);
    }

    public static <T> BeanPropertyRef<T> wrap(Class<?> beanClass, T bean, Class<T> propertyType,
            PropertyDescriptor propertyDescriptor, Object... parameters) {
        Class<?> actualType = propertyDescriptor.getPropertyType();
        if (!propertyType.isAssignableFrom(actualType))
            throw new IllegalArgumentException("Incompatible with actual property type: " + propertyType);

        String propertyName = propertyDescriptor.getName();
        Method readMethod = propertyDescriptor.getReadMethod();
        Method writeMethod = propertyDescriptor.getWriteMethod();

        BeanPropertyRef<T> propertyRef = new BeanPropertyRef<T>(bean, propertyName, propertyType, readMethod,
                writeMethod, parameters);
        return propertyRef;
    }

    public static <T> BeanPropertyRef<T> wrap(T bean, Class<T> propertyType, String propertyName, Object... parameters)
            throws IntrospectionException {
        if (bean == null)
            throw new NullPointerException("bean");
        Class<?> beanClass = bean.getClass();
        return wrap(beanClass, bean, propertyType, propertyName, parameters);
    }

    public static <T> BeanPropertyRef<T> wrap(Class<?> beanClass, T bean, Class<T> propertyType, String propertyName,
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
