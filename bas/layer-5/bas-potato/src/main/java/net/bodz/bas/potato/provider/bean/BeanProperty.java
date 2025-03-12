package net.bodz.bas.potato.provider.bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.bean.api.IPropertyDescriptor;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.potato.element.AbstractProperty;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;
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
     *             If <code>declaringPotatoType</code> or <code>propertyDescriptor</code> is <code>null</code>.
     */
    public BeanProperty(BeanType beanType, IPropertyDescriptor propertyDescriptor, IElementDoc doc) {
        this(beanType, propertyDescriptor, doc, getterOrSetter(propertyDescriptor));
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

    static Method getterOrSetter(IPropertyDescriptor propertyDescriptor) {
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
    protected IProperty loadSuperProperty() {
        IType decl = getDeclaringType();
        while ((decl = decl.getSuperType()) != null) {
            IProperty prop = decl.getProperty(getName());
            if (prop != null)
                return prop;
        }
        return null;
    }

    @Override
    public Class<?> getPropertyClass() {
        // XXX Generic not supported.
        // propertyDescriptor.getReadMethod();
        return propertyDescriptor.getPropertyType();
    }

    @Override
    public Type getPropertyGenericType() {
        return propertyDescriptor.getPropertyGenericType();
    }

    @Override
    public Class<?> getDeclaringClass() {
        Method getter = propertyDescriptor.getReadMethod();
        if (getter != null)
            return getter.getDeclaringClass();
        Method setter = propertyDescriptor.getWriteMethod();
        if (setter != null)
            return setter.getDeclaringClass();
        throw new UnexpectedException();
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
        if (instance == null)
            throw new NullPointerException("instance");
        Method getter = propertyDescriptor.getReadMethod();
        if (getter == null)
            throw new NoSuchMethodException("No getter method: " + propertyDescriptor.getName());
        return getter.invoke(instance);
    }

    @Override
    public void setValue(Object instance, Object value)
            throws ReflectiveOperationException {
        if (instance == null)
            throw new NullPointerException("instance");
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

    Map<Class<?>, Annotation> annotationMap = new LinkedHashMap<>();
    Annotation[] annotations;
    boolean annotationsLoaded;

    void lazyLoadAnnotations() {
        if (! annotationsLoaded) {
            synchronized (this) {
                if (! annotationsLoaded) {
                    loadAnnotations();
                    annotationsLoaded = true;
                }
            }
        }
    }

    void loadAnnotations() {
        for (IProperty property : ancestorsToRoot(true)) {
            BeanProperty bp = (BeanProperty) property;
            IPropertyDescriptor descriptor = bp.getPropertyDescriptor();
            Method xetter = getterOrSetter(descriptor);
            for (Annotation annotation : xetter.getAnnotations()) {
                Class<? extends Annotation> aClass = annotation.annotationType();
                if (! annotationMap.containsKey(aClass)) {
                    annotationMap.put(aClass, annotation);
                }
            }
        }
        annotations = annotationMap.values().toArray(new Annotation[0]);
    }

    @Override
    public Annotation[] getAnnotations() {
        lazyLoadAnnotations();
        return annotations;
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        Method xetter = getterOrSetter(propertyDescriptor);
        if (xetter == null)
            return new Annotation[0];
        else
            return xetter.getDeclaredAnnotations();
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        lazyLoadAnnotations();
        Annotation annotation = annotationMap.get(annotationClass);
        return annotationClass.cast(annotation);
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        lazyLoadAnnotations();
        return annotationMap.containsKey(annotationClass);
    }

}
