package net.bodz.bas.potato.adapter.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.bodz.bas.potato.AbstractPotatoMethod;
import net.bodz.bas.potato.IPotatoType;
import net.bodz.bas.potato.PotatoException;
import net.bodz.bas.potato.PotatoTargetException;

public class ReflectPotatoMethod
        extends AbstractPotatoMethod {

    private final IPotatoType<?> declaringPotatoType;

    private final Method method;

    /**
     * @throws NullPointerException
     *             If <code>declaringPotatoType</code> or <code>method</code> is <code>null</code>.
     */
    public ReflectPotatoMethod(IPotatoType<?> declaringPotatoType, Method method) {
        super(method.getName());

        if (declaringPotatoType == null)
            throw new NullPointerException("declaringPotatoType");
        this.declaringPotatoType = declaringPotatoType;

        this.method = method;
    }

    @Override
    public IPotatoType<?> getDeclaringType() {
        return declaringPotatoType;
    }

    @Override
    public Class<?> getReturnType() {
        return method.getReturnType();
    }

    @Override
    public Class<?>[] getParameterTypes() {
        return method.getParameterTypes();
    }

    @Override
    public Object invoke(Object instance, Object... parameters)
            throws PotatoException, PotatoTargetException {
        try {
            return method.invoke(instance, parameters);
        } catch (Exception e) {
            throw new PotatoTargetException(e.getMessage(), e);
        }
    }

    @Override
    public Annotation[] getAnnotations() {
        return method.getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return method.getDeclaredAnnotations();
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        return method.getAnnotation(annotationClass);
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return method.isAnnotationPresent(annotationClass);
    }

}
