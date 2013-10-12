package net.bodz.bas.potato.provider.reflect;

import java.lang.annotation.Annotation;

import net.bodz.bas.potato.ITypeProvider;
import net.bodz.bas.potato.element.*;
import net.bodz.mda.xjdoc.model.ClassDoc;

public abstract class AbstractReflectType
        extends AbstractType {

    private final Class<?> clazz;

    MutablePropertyMap propertyMap = new MutablePropertyMap();
    MutableMethodMap methodMap = new MutableMethodMap();
    MutableConstructorMap constructorMap = new MutableConstructorMap();
    IEventMap eventMap = NullEventMap.getInstance();

    private final int verboseLevel;

    /**
     * @param infoset
     *            Selection bits.
     * @see ITypeProvider#PROPERTIES
     * @see ITypeProvider#METHODS
     * @see ITypeProvider#CONSTRUCTORS
     * @see ITypeProvider#EVENTS
     */
    public AbstractReflectType(Class<?> clazz, int infoset, ClassDoc classDoc) {
        super(clazz.getDeclaringClass(), clazz.getName());

        this.clazz = clazz;

        int _modifiers = clazz.getModifiers();
        this.verboseLevel = ReflectModifiers.toVerboseLevel(_modifiers);

        setXjdoc(classDoc);
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

    /** ⇱ Implementaton Of {@link java.lang.reflect.AnnotatedElement}. */
    ;

    @Override
    public Annotation[] getAnnotations() {
        return clazz.getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return clazz.getDeclaredAnnotations();
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        return clazz.getAnnotation(annotationClass);
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return clazz.isAnnotationPresent(annotationClass);
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.i18n.dom1.IElement}. */
    ;

    @Override
    public int getVerboseLevel() {
        return verboseLevel;
    }

}
