package net.bodz.bas.potato.provider.bean;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.potato.element.AbstractProperty;
import net.bodz.bas.t.event.IPropertyChangeListener;
import net.bodz.bas.t.event.IPropertyChangeSource;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class BeanProperty
        extends AbstractProperty {

    private final PropertyDescriptor propertyDescriptor;
    // private final Method xetter;
    private final int detailLevel;
    private final int modifiers;

    private Boolean propertyChangeSource;

    /**
     * @throws NullPointerException
     *             If <code>declaringPotatoType</code> or <code>propertyDescriptor</code> is
     *             <code>null</code>.
     */
    public BeanProperty(Class<?> beanClass, PropertyDescriptor propertyDescriptor, IElementDoc doc) {
        this(beanClass, propertyDescriptor, doc, xetter(propertyDescriptor));
    }

    public BeanProperty(Class<?> beanClass, PropertyDescriptor propertyDescriptor, IElementDoc doc, Method xetter) {
        super(xetter.getDeclaringClass(), propertyDescriptor.getName(), doc);

        this.propertyDescriptor = propertyDescriptor;
        // this.xetter = xetter;

        int _modifiers = xetter.getModifiers();
        this.modifiers = _modifiers;

        DetailLevel aDetailLevel = xetter.getAnnotation(DetailLevel.class);
        if (aDetailLevel != null)
            this.detailLevel = aDetailLevel.value();
        else
            this.detailLevel = FeatureDescriptorUtil.getDetailLevel(propertyDescriptor);
    }

    static Method xetter(PropertyDescriptor propertyDescriptor) {
        Method xetter = propertyDescriptor.getReadMethod();
        if (xetter == null)
            xetter = propertyDescriptor.getWriteMethod();
        assert xetter != null;
        return xetter;
    }

    @Override
    public String getName() {
        return propertyDescriptor.getName();
    }

    public PropertyDescriptor getPropertyDescriptor() {
        return propertyDescriptor;
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
            throw new NoSuchMethodException("No getter method: " + propertyDescriptor.getName());
        return getter.invoke(instance);
    }

    @Override
    public void setValue(Object instance, Object value)
            throws ReflectiveOperationException {
        Method setter = propertyDescriptor.getWriteMethod();
        if (setter == null)
            throw new NoSuchMethodException("No setter method: " + propertyDescriptor.getName());
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
    /* _____________________________ */static section.iface __ELEMENT__;

    @Override
    public int getModifiers() {
        return modifiers;
    }

    @Override
    public int getDetailLevel() {
        return detailLevel;
    }

    /** ⇱ Implementaton Of {@link java.lang.reflect.AnnotatedElement}. */
    /* _____________________________ */static section.iface __ANNOTATION__;

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
