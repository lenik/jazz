package net.bodz.bas.t.record;

import java.lang.reflect.Method;

import net.bodz.bas.bean.api.IBeanInfo;
import net.bodz.bas.bean.api.IPropertyDescriptor;
import net.bodz.bas.bean.api.IntrospectionException;
import net.bodz.bas.bean.api.Introspectors;
import net.bodz.bas.meta.decl.NotNull;

public class BasicColumnType<T, E>
        implements IColumnType<T, E> {

    final String name;
    String description;
    final Class<E> type;
    final IColumnGetter<T, E> getter;
    final IColumnSetter<T, E> setter;

    BasicColumnType(String name, Class<E> type, IColumnGetter<T, E> getter, IColumnSetter<T, E> setter) {
        this.name = name;
        this.type = type;
        this.getter = getter;
        this.setter = setter;
    }

    public static <T, E> BasicColumnType<T, E> ofProperty(String name, Class<E> type, @NotNull IColumnGetter<T, E> getter, @NotNull IColumnSetter<T, E> setter) {
        return new BasicColumnType<>(name, type, getter, setter);
    }

    /** read-only property */
    public static <T, E> BasicColumnType<T, E> ofProperty(String name, Class<E> type, @NotNull IColumnGetter<T, E> getter) {
        return new BasicColumnType<>(name, type, getter, null);
    }

    /** write-only property */
    public static <T, E> BasicColumnType<T, E> ofSetter(String name, Class<E> type, @NotNull IColumnSetter<T, E> setter) {
        return new BasicColumnType<>(name, type, null, setter);
    }

    public static <T, E> BasicColumnType<T, E> ofProperty(Class<T> beanClass, String propertyName) {
        IBeanInfo beanInfo = null;
        try {
            beanInfo = Introspectors.getBeanInfo(beanClass);
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }

        IPropertyDescriptor property = beanInfo.getPropertyDescriptor(propertyName);
        if (property == null)
            throw new IllegalArgumentException("invalid property name: " + propertyName);

        String description = property.getShortDescription();

        @SuppressWarnings("unchecked")
        Class<E> type = (Class<E>) property.getPropertyType();

        IColumnGetter<T, E> getter = null;
        Method readMethod = property.getReadMethod();
        if (readMethod != null)
            getter = context -> {
                try {
                    @SuppressWarnings("unchecked")
                    E val = (E) readMethod.invoke(context);
                    return val;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            };

        IColumnSetter<T, E> setter = null;
        Method writeMethod = property.getWriteMethod();
        if (writeMethod != null)
            setter = (context, val) -> {
                try {
                    writeMethod.invoke(context, val);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            };

        return new BasicColumnType<>(propertyName, type, getter, setter);
    }

    @NotNull
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    @Override
    public Class<E> getColumnType() {
        return type;
    }

    @Override
    public E get(T context) {
        if (getter != null)
            return getter.get(context);
        else
            return null;
    }

    @Override
    public void set(T context, E value) {
        if (setter != null)
            setter.set(context, value);
    }

}
