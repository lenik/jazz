package net.bodz.bas.potato.provider.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.bodz.bas.potato.element.AbstractProperty;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.t.event.IPropertyChangeListener;
import net.bodz.bas.t.event.IPropertyChangeSource;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class ReflectProperty
        extends AbstractProperty {

    private final Field field;

    private final int modifiers;
    private final int verboseLevel;

    private PropertyChangeSourceMode propertyChangeSourceMode;

    public ReflectProperty(Field field, IElementDoc xjdoc) {
        super(field.getDeclaringClass(), field.getName());
        this.field = field;

        int _modifiers = field.getModifiers();
        this.modifiers = _modifiers;
        this.verboseLevel = ReflectModifiers.toVerboseLevel(_modifiers);

        if (xjdoc == null)
            throw new NullPointerException("xjdoc");
        setXjdoc(xjdoc);
    }

    /** ⇱ Implementation Of {@link IProperty}. */
    /* _____________________________ */static section.iface __PROPERTY__;

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

    private synchronized void analyzePropertyChangeSourceMode() {
        if (propertyChangeSourceMode == null) {
            Class<?> declaringType = getDeclaringClass();
            if (IPropertyChangeSource.class.isAssignableFrom(declaringType))
                propertyChangeSourceMode = PropertyChangeSourceMode.INTERFACE;
            // PropertyChangeSourceMode.BEAN
            else
                propertyChangeSourceMode = PropertyChangeSourceMode.UNSUPPORTED;
        }
    }

    @Override
    public boolean isPropertyChangeSource() {
        analyzePropertyChangeSourceMode();
        return propertyChangeSourceMode != PropertyChangeSourceMode.UNSUPPORTED;
    }

    @Override
    public void addPropertyChangeListener(Object instance, IPropertyChangeListener listener) {
        addPropertyChangeListener(instance, null, listener);
    }

    @Override
    public void addPropertyChangeListener(Object instance, String propertyName, IPropertyChangeListener listener) {
        analyzePropertyChangeSourceMode();
        switch (propertyChangeSourceMode) {
        case INTERFACE:
            IPropertyChangeSource source = (IPropertyChangeSource) instance;
            if (propertyName == null)
                source.addPropertyChangeListener(listener);
            else
                source.addPropertyChangeListener(propertyName, listener);
            break;
        case BEAN:
            // TODO Not implemented.
            break;
        case UNSUPPORTED:
        default:
        }
    }

    @Override
    public void removePropertyChangeListener(Object instance, IPropertyChangeListener listener) {
        removePropertyChangeListener(instance, null, listener);
    }

    @Override
    public void removePropertyChangeListener(Object instance, String propertyName, IPropertyChangeListener listener) {
        analyzePropertyChangeSourceMode();
        switch (propertyChangeSourceMode) {
        case INTERFACE:
            IPropertyChangeSource source = (IPropertyChangeSource) instance;
            if (propertyName == null)
                source.removePropertyChangeListener(listener);
            else
                source.removePropertyChangeListener(propertyName, listener);
            break;
        case BEAN:
            // TODO Not implemented.
            break;
        case UNSUPPORTED:
        default:
        }
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.i18n.dom1.IElement}. */
    /* _____________________________ */static section.iface __ELEMENT__;

    @Override
    public int getModifiers() {
        return modifiers;
    }

    @Override
    public int getVerboseLevel() {
        return verboseLevel;
    }

    /** ⇱ Implementaton Of {@link java.lang.reflect.AnnotatedElement}. */
    /* _____________________________ */static section.iface __ANNOTATION__;

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
