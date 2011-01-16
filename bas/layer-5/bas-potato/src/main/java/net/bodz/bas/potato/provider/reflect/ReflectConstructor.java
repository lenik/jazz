package net.bodz.bas.potato.provider.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;
import net.bodz.bas.potato.traits.AbstractConstructor;

public class ReflectConstructor
        extends AbstractConstructor {

    private final Constructor<?> ctor;

    /**
     * @throws NullPointerException
     *             If <code>method</code> is <code>null</code>.
     */
    public ReflectConstructor(Constructor<?> ctor) {
        super(ctor.getDeclaringClass());
        this.ctor = ctor;
    }

    @Override
    public Class<?>[] getParameterTypes() {
        return ctor.getParameterTypes();
    }

    @Override
    public Object newInstance(Object... parameters)
            throws ReflectiveOperationException {
        return Jdk7Reflect.newInstance(ctor, parameters);
    }

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
