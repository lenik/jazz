package net.bodz.bas.potato.provider.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.bodz.bas.potato.element.AbstractProperty;
import net.bodz.bas.t.event.IPropertyChangeListener;
import net.bodz.bas.t.event.IPropertyChangeSource;
import net.bodz.mda.xjdoc.model.IJavaElementDoc;

public class ReflectProperty
        extends AbstractProperty {

    private final Field field;

    private final int modifiers;
    private final int verboseLevel;

    private Boolean propertyChangeSource;

    public ReflectProperty(Field field, IJavaElementDoc xjdoc) {
        super(field.getDeclaringClass(), field.getName());
        this.field = field;

        int _modifiers = field.getModifiers();
        this.modifiers = ReflectModifiers.toElementModifiers(_modifiers);
        this.verboseLevel = ReflectModifiers.toVerboseLevel(_modifiers);

        setXjdoc(xjdoc);
    }

    @Override
    public Class<?> getPropertyType() {
        return field.getType();
    }

    @Override
    public boolean isReadable() {
        // int modifiers = field.getModifiers();
        // if (Modifier.isPrivate(modifiers))
        // return false;
        return true;
    }

    @Override
    public boolean isWritable() {
        int modifiers = field.getModifiers();
        if (Modifier.isFinal(modifiers))
            return false;
        // if (Modifier.isPrivate(modifiers)) return false;
        // if (field.isAnnotationPresent(ReadOnly.class)) return false;
        return true;
    }

    @Override
    public Object getValue(Object instance)
            throws ReflectiveOperationException {
        return field.get(instance);
    }

    @Override
    public void setValue(Object instance, Object value)
            throws ReflectiveOperationException {
        field.set(instance, value);
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
        return field.getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return field.getDeclaredAnnotations();
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        return field.getAnnotation(annotationClass);
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return field.isAnnotationPresent(annotationClass);
    }

}
