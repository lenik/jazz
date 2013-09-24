package net.bodz.bas.potato.provider.bean;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.potato.element.AbstractProperty;
import net.bodz.bas.potato.provider.reflect.ReflectModifiers;
import net.bodz.bas.t.event.IPropertyChangeListener;
import net.bodz.bas.t.event.IPropertyChangeSource;
import net.bodz.mda.xjdoc.model.IJavaElementDoc;

public class BeanProperty
        extends AbstractProperty {

    private final PropertyDescriptor propertyDescriptor;
    private final int verboseLevel;
    private final int modifiers;

    private Boolean propertyChangeSource;

    /**
     * @throws NullPointerException
     *             If <code>declaringPotatoType</code> or <code>propertyDescriptor</code> is
     *             <code>null</code>.
     */
    public BeanProperty(Class<?> beanClass, PropertyDescriptor propertyDescriptor, IJavaElementDoc xjdoc) {
        super(beanClass, propertyDescriptor.getName());
        this.propertyDescriptor = propertyDescriptor;

        Method getter = propertyDescriptor.getReadMethod();

        int _modifiers = getter.getModifiers();
        this.modifiers = getter == null ? 0 : ReflectModifiers.toVerboseLevel(_modifiers);
        this.verboseLevel = FeatureDescriptorUtil.getVerboseLevel(propertyDescriptor);

        setXjdoc(xjdoc);
    }

    @Override
    public String getName() {
        return propertyDescriptor.getName();
    }

    @Override
    public iString getLabel() {
        String displayName = propertyDescriptor.getDisplayName();
        return iString.fn.val(displayName);
    }

    @Override
    public iString getDescription() {
        String shortDescription = propertyDescriptor.getShortDescription();
        return iString.fn.val(shortDescription);
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

    /** ⇱ Implementaton Of {@link net.bodz.bas.i18n.dom1.IElement}. */
    ;

    @Override
    public int getModifiers() {
        return modifiers;
    }

    @Override
    public int getVerboseLevel() {
        return verboseLevel;
    }

    /** ⇱ Implementaton Of {@link java.lang.reflect.AnnotatedElement}. */
    ;
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

}
