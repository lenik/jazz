package net.bodz.bas.potato.provider.bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import net.bodz.bas.bean.api.IPropertyDescriptor;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.potato.element.AbstractProperty;
import net.bodz.bas.t.event.IPropertyChangeListener;
import net.bodz.bas.t.event.IPropertyChangeSource;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class BeanProperty
        extends AbstractProperty {

    private final IPropertyDescriptor propertyDescriptor;
    // private final Method xetter;
    private final int detailLevel;
    private final int modifiers;
    private final int priority;

    private Boolean propertyChangeSource;

    /**
     * @throws NullPointerException
     *             If <code>declaringPotatoType</code> or <code>propertyDescriptor</code> is
     *             <code>null</code>.
     */
    public BeanProperty(BeanType beanType, IPropertyDescriptor propertyDescriptor, IElementDoc doc) {
        this(beanType, propertyDescriptor, doc, xetter(propertyDescriptor));
    }

    public BeanProperty(BeanType beanType, IPropertyDescriptor propertyDescriptor, IElementDoc doc, Method xetter) {
        super(beanType, propertyDescriptor.getName(), doc);

        this.propertyDescriptor = propertyDescriptor;
        // this.xetter = xetter;

        int _modifiers = xetter.getModifiers();
        this.modifiers = _modifiers;

        DetailLevel aDetailLevel = xetter.getAnnotation(DetailLevel.class);
        if (aDetailLevel != null)
            this.detailLevel = aDetailLevel.value();
        else
            this.detailLevel = FeatureDescriptorUtil.getDetailLevel(propertyDescriptor);

        Priority aPriority = xetter.getAnnotation(Priority.class);
        priority = aPriority == null ? 0 : aPriority.value();
    }

    static Method xetter(IPropertyDescriptor propertyDescriptor) {
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

    public IPropertyDescriptor getPropertyDescriptor() {
        return propertyDescriptor;
    }

    @Override
    public Class<?> getPropertyClass() {
        // XXX Generic not supported.
        // propertyDescriptor.getReadMethod();
        return propertyDescriptor.getPropertyType();
    }

    @Override
    public Type getPropertyGenericType() {
        Type type;
        Method getter = propertyDescriptor.getReadMethod();
        if (getter != null)
            type = getter.getGenericReturnType();
        else {
            Method setter = propertyDescriptor.getWriteMethod();
            if (setter != null) {
                Type[] pv = setter.getGenericParameterTypes();
                type = pv[0];
            } else
                throw new UnexpectedException();
        }

        throw new NotImplementedException();
    }

    public Method getReadMethod() {
        return propertyDescriptor.getReadMethod();
    }

    public Method getWriteMethod() {
        return propertyDescriptor.getWriteMethod();
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

    @Override
    public int getPriority() {
        return priority;
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
