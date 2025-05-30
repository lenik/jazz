package net.bodz.bas.potato.provider.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.potato.element.AbstractProperty;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.potato.element.PropertyReadException;
import net.bodz.bas.potato.element.PropertyWriteException;
import net.bodz.bas.t.event.IPropertyChangeListener;
import net.bodz.bas.t.event.IPropertyChangeSource;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class FieldProperty
        extends AbstractProperty {

    private final Field field;

    private final int modifiers;
    private final int detailLevel;
    private final int priority;

    private PropertyChangeSourceMode propertyChangeSourceMode;

    public FieldProperty(IType type, Field field, IElementDoc doc) {
        super(type, field.getName(), doc);
        this.field = field;

        int _modifiers = field.getModifiers();
        this.modifiers = _modifiers;

        DetailLevel aDetailLevel = field.getAnnotation(DetailLevel.class);
        if (aDetailLevel != null)
            this.detailLevel = aDetailLevel.value();
        else
            this.detailLevel = ReflectModifiers.toDetailLevel(_modifiers);

        Priority aPriority = field.getAnnotation(Priority.class);
        priority = aPriority == null ? 0 : aPriority.value();
    }

    /**
     * ⇱ Implementation Of {@link IProperty}.
     */
    /* _____________________________ */static section.iface __PROPERTY__;

    @Override
    protected IProperty loadSuperProperty() {
        return null;
    }

    @Override
    public Class<?> getPropertyClass() {
        return field.getType();
    }

    @Override
    public Type getPropertyGenericType() {
        return field.getGenericType();
    }

    public Field getField() {
        return field;
    }

    @Override
    public Class<?> getDeclaringClass() {
        return field.getDeclaringClass();
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
    public Object read(Object instance)
            throws PropertyReadException {
        try {
            return field.get(instance);
        } catch (IllegalAccessException e) {
            throw new PropertyReadException(e);
        }
    }

    @Override
    public void write(Object instance, Object value)
            throws PropertyWriteException {
        try {
            field.set(instance, value);
        } catch (IllegalAccessException e) {
            throw new PropertyWriteException(e);
        }
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

    /**
     * ⇱ Implementaton Of {@link net.bodz.bas.i18n.dom1.IElement}.
     */
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

    /**
     * ⇱ Implementaton Of {@link java.lang.reflect.AnnotatedElement}.
     */
    /* _____________________________ */static section.iface __ANNOTATION__;

    @NotNull
    @Override
    public Annotation[] getAnnotations() {
        return field.getAnnotations();
    }

    @NotNull
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
