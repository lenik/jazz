package net.bodz.bas.potato.spi.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

import net.bodz.bas.potato.model.AbstractConstructor;

public class ReflectConstructor
        extends AbstractConstructor {

    private final Constructor<?> ctor;
    private final int verboseLevel;
    private final int modifiers;

    /**
     * @throws NullPointerException
     *             If <code>method</code> is <code>null</code>.
     */
    public ReflectConstructor(Constructor<?> ctor) {
        super(ctor.getDeclaringClass());
        this.ctor = ctor;

        int _modifiers = ctor.getModifiers();
        this.verboseLevel = ReflectModifiers.toVerboseLevel(_modifiers);
        this.modifiers = ReflectModifiers.toElementModifiers(_modifiers);
    }

    @Override
    public Class<?>[] getParameterTypes() {
        return ctor.getParameterTypes();
    }

    @Override
    public Object newInstance(Object... parameters)
            throws ReflectiveOperationException {
        return ctor.newInstance(ctor, parameters);
    }

    // -o IElement

    @Override
    public int getModifiers() {
        return modifiers;
    }

    @Override
    public int getVerboseLevel() {
        return verboseLevel;
    }

    // -o AnnotatedElement

    @Override
    public Annotation[] getAnnotations() {
        return ctor.getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return ctor.getDeclaredAnnotations();
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        return ctor.getAnnotation(annotationClass);
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return ctor.isAnnotationPresent(annotationClass);
    }

}
