package net.bodz.bas.potato.adapter.bean;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Locale;

import net.bodz.bas.exceptions.UnexpectedException;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;
import net.bodz.bas.potato.AbstarctPotatoProperty;
import net.bodz.bas.potato.PotatoException;
import net.bodz.bas.potato.PotatoTargetException;

public class BeanPotatoProperty
        extends AbstarctPotatoProperty {

    private final BeanPotatoType<?> declaringPotatoType;
    private final PropertyDescriptor propertyDescriptor;
    private final Method firstMethod;

    /**
     * @throws NullPointerException
     *             If <code>declaringPotatoType</code> or <code>propertyDescriptor</code> is
     *             <code>null</code>.
     */
    public BeanPotatoProperty(BeanPotatoType<?> declaringPotatoType, PropertyDescriptor propertyDescriptor) {
        super(propertyDescriptor.getName());

        if (declaringPotatoType == null)
            throw new NullPointerException("declaringPotatoType");
        this.declaringPotatoType = declaringPotatoType;

        this.propertyDescriptor = propertyDescriptor;

        Method method = propertyDescriptor.getReadMethod();
        if (method == null) {
            method = propertyDescriptor.getWriteMethod();
            if (method == null)
                throw new UnexpectedException("Bean property neither readable nor writable.");
        }
        firstMethod = method;
    }

    @Override
    public String getName() {
        return propertyDescriptor.getName();
    }

    @Override
    public String getDisplayName(Locale locale) {
        return propertyDescriptor.getDisplayName();
    }

    @Override
    public String getDescription(Locale locale) {
        return propertyDescriptor.getShortDescription();
    }

    @Override
    public int getPreferenceLevel() {
        return FeatureDescriptorUtil.getFeaturePreferenceLevel(propertyDescriptor);
    }

    @Override
    public BeanPotatoType<?> getDeclaringType() {
        return declaringPotatoType;
    }

    @Override
    public Class<?> getType() {
        return propertyDescriptor.getPropertyType();
    }

    @Override
    public Object get(Object instance)
            throws PotatoException {
        Method readMethod = propertyDescriptor.getReadMethod();
        if (readMethod == null)
            throw new PotatoException("No read method");
        try {
            return Jdk7Reflect.invoke(readMethod, instance);
        } catch (ReflectiveOperationException e) {
            throw new PotatoTargetException(e.getMessage(), e);
        }
    }

    @Override
    public void set(Object instance, Object value)
            throws PotatoException {
        Method writeMethod = propertyDescriptor.getWriteMethod();
        if (writeMethod == null)
            throw new PotatoException("No write method");
        try {
            Jdk7Reflect.invoke(writeMethod, instance, value);
        } catch (ReflectiveOperationException e) {
            throw new PotatoTargetException(e.getMessage(), e);
        }
    }

    @Override
    public Annotation[] getAnnotations() {
        return firstMethod.getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return firstMethod.getDeclaredAnnotations();
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        return firstMethod.getAnnotation(annotationClass);
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return firstMethod.isAnnotationPresent(annotationClass);
    }

}
