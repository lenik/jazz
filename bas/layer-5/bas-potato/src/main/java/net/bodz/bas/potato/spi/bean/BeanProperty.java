package net.bodz.bas.potato.spi.bean;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.i18n.dom.XDomainString;
import net.bodz.bas.potato.model.AbstractProperty;
import net.bodz.bas.util.event.IPropertyChangeListener;
import net.bodz.bas.util.event.IPropertyChangeSource;

public class BeanProperty
        extends AbstractProperty {

    private final PropertyDescriptor propertyDescriptor;
    private Boolean propertyChangeSource;

    /**
     * @throws NullPointerException
     *             If <code>declaringPotatoType</code> or <code>propertyDescriptor</code> is
     *             <code>null</code>.
     */
    public BeanProperty(Class<?> declaringType, PropertyDescriptor propertyDescriptor) {
        super(declaringType, propertyDescriptor.getName());
        this.propertyDescriptor = propertyDescriptor;
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
    public Class<?> getPropertyType() {
        return propertyDescriptor.getPropertyType();
    }

    @Override
    public boolean isReadable() {
        return propertyDescriptor.getReadMethod() != null;
    }

    @Override
    public boolean isWritable() {
        return propertyDescriptor.getWriteMethod() != null;
    }

    @Override
    public Object getValue(Object instance)
            throws ReflectiveOperationException {
        Method getter = propertyDescriptor.getReadMethod();
        if (getter == null)
            throw new NoSuchMethodException("No getter method");
        return getter.invoke(instance);
    }

    @Override
    public void setValue(Object instance, Object value)
            throws ReflectiveOperationException {
        Method setter = propertyDescriptor.getWriteMethod();
        if (setter == null)
            throw new NoSuchMethodException("No setter method");
        setter.invoke(instance, value);
    }

    @Override
    public boolean isPropertyChangeSource() {
        if (propertyChangeSource == null) {
            synchronized (this) {
                if (propertyChangeSource == null) {
                    Class<?> declaringType = getDeclaringClass();
                    propertyChangeSource = IPropertyChangeSource.class.isAssignableFrom(declaringType);
                }
            }
        }
        return propertyChangeSource;
    }

    @Override
    public void addPropertyChangeListener(Object instance, IPropertyChangeListener listener) {
        if (isPropertyChangeSource()) {
            IPropertyChangeSource source = (IPropertyChangeSource) instance;
            source.addPropertyChangeListener(listener);
        }
    }

    @Override
    public void addPropertyChangeListener(Object instance, String propertyName, IPropertyChangeListener listener) {
        if (isPropertyChangeSource()) {
            IPropertyChangeSource source = (IPropertyChangeSource) instance;
            source.addPropertyChangeListener(propertyName, listener);
        }
    }

    @Override
    public void removePropertyChangeListener(Object instance, IPropertyChangeListener listener) {
        if (isPropertyChangeSource()) {
            IPropertyChangeSource source = (IPropertyChangeSource) instance;
            source.removePropertyChangeListener(listener);
        }
    }

    @Override
    public void removePropertyChangeListener(Object instance, String propertyName, IPropertyChangeListener listener) {
        if (isPropertyChangeSource()) {
            IPropertyChangeSource source = (IPropertyChangeSource) instance;
            source.removePropertyChangeListener(propertyName, listener);
        }
    }

    // -o AnnotatedElement

    @Override
    public Annotation[] getAnnotations() {
        Method getter = propertyDescriptor.getReadMethod();
        if (getter == null)
            return new Annotation[0];
        else
            return getter.getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        Method getter = propertyDescriptor.getReadMethod();
        if (getter == null)
            return new Annotation[0];
        else
            return getter.getDeclaredAnnotations();
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        Method getter = propertyDescriptor.getReadMethod();
        if (getter == null)
            return null;
        else
            return getter.getAnnotation(annotationClass);
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        Method getter = propertyDescriptor.getReadMethod();
        if (getter == null)
            return false;
        else
            return getter.isAnnotationPresent(annotationClass);
    }

    @Override
    public int getModifiers() {
        Method getter = propertyDescriptor.getReadMethod();
        if (getter == null)
            return 0;
        else
            return getter.getModifiers();
    }

}
