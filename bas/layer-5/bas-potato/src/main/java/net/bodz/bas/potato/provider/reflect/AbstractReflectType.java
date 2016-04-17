package net.bodz.bas.potato.provider.reflect;

import java.lang.annotation.Annotation;

import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.potato.ITypeProvider;
import net.bodz.bas.potato.element.*;
import net.bodz.mda.xjdoc.model.IClassDoc;

public abstract class AbstractReflectType
        extends AbstractType {

    private final Class<?> clazz;
    MutablePropertyMap propertyMap;
    MutableMethodMap methodMap;
    MutableConstructorMap constructorMap;
    IEventMap eventMap;

    private final int modifiers;
    private final int detailLevel;
    private final int priority;

    /**
     * @param infoset
     *            Selection bits. See
     * @see ITypeProvider#PROPERTIES
     * @see ITypeProvider#METHODS
     * @see ITypeProvider#CONSTRUCTORS
     * @see ITypeProvider#EVENTS
     */
    public AbstractReflectType(Class<?> clazz, int infoset, IClassDoc doc) {
        super(clazz, clazz.getName(), doc);

        this.clazz = clazz;

        int _modifiers = clazz.getModifiers();
        this.modifiers = _modifiers;

        DetailLevel aDetailLevel = clazz.getAnnotation(DetailLevel.class);
        if (aDetailLevel != null)
            this.detailLevel = aDetailLevel.value();
        else
            this.detailLevel = ReflectModifiers.toDetailLevel(_modifiers);

        Priority aPriority = clazz.getAnnotation(Priority.class);
        priority = aPriority == null ? 0 : aPriority.value();

        propertyMap = new MutablePropertyMap(false);
        methodMap = new MutableMethodMap(false);
        constructorMap = new MutableConstructorMap();
        eventMap = NullEventMap.getInstance();
    }

    /** ⇱ Implementation Of {@link IType}. */
    /* _____________________________ */static section.iface __TYPE__;

    @Override
    public Class<?> getType() {
        return clazz;
    }

    @Override
    public IPropertyMap getPropertyMap() {
        return propertyMap;
    }

    @Override
    public IMethodMap getMethodMap() {
        return methodMap;
    }

    @Override
    public IConstructorMap getConstructorMap() {
        return constructorMap;
    }

    @Override
    public IEventMap getEventMap() {
        return eventMap;
    }

    /** ⇱ Implementaton Of {@link IAnnotated}. */
    /* _____________________________ */static section.iface __ANNOTATION__;

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return clazz.isAnnotationPresent(annotationClass);
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        return clazz.getAnnotation(annotationClass);
    }

    @Override
    public Annotation[] getAnnotations() {
        return clazz.getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return clazz.getDeclaredAnnotations();
    }

    /** ⇱ Implementaton Of {@link IElement}. */
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

}
