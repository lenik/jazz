package net.bodz.bas.potato.spi.bean;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.i18n.dom.XDomainString;
import net.bodz.bas.potato.traits.AbstractProperty;

public class BeanProperty
        extends AbstractProperty {

    private final PropertyDescriptor propertyDescriptor;
    private final Method firstMethod;

    /**
     * @throws NullPointerException
     *             If <code>declaringPotatoType</code> or <code>propertyDescriptor</code> is
     *             <code>null</code>.
     */
    public BeanProperty(Class<?> declaringType, PropertyDescriptor propertyDescriptor) {
        super(declaringType, propertyDescriptor.getName());

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
    public DomainString getDisplayName() {
        String displayName = propertyDescriptor.getDisplayName();
        return XDomainString.of(displayName);
    }

    @Override
    public DomainString getDescription() {
        String shortDescription = propertyDescriptor.getShortDescription();
        return XDomainString.of(shortDescription);
    }

    @Override
    public int getPreferenceLevel() {
        return FeatureDescriptorUtil.getFeaturePreferenceLevel(propertyDescriptor);
    }

    @Override
    public Class<?> getType() {
        return propertyDescriptor.getPropertyType();
    }

    @Override
    public Object get(Object instance)
            throws ReflectiveOperationException {
        Method readMethod = propertyDescriptor.getReadMethod();
        if (readMethod == null)
            throw new NoSuchMethodException("No read method");
        return readMethod.invoke(instance);
    }

    @Override
    public void set(Object instance, Object value)
            throws ReflectiveOperationException {
        Method writeMethod = propertyDescriptor.getWriteMethod();
        if (writeMethod == null)
            throw new NoSuchMethodException("No write method");
        writeMethod.invoke(instance, value);
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
