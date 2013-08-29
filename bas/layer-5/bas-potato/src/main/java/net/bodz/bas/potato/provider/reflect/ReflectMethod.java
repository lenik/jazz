package net.bodz.bas.potato.provider.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.bodz.bas.potato.element.AbstractMethod;
import net.bodz.bas.potato.element.IParameter;
import net.bodz.mda.xjdoc.model.IJavaElementDoc;

public class ReflectMethod
        extends AbstractMethod {

    private final Method method;

    private final int modifiers;
    private final int verboseLevel;

    /**
     * @throws NullPointerException
     *             If <code>method</code> is <code>null</code>.
     */
    public ReflectMethod(Method method, IJavaElementDoc xjdoc) {
        super(method.getDeclaringClass(), method.getName());
        this.method = method;

        int _modifiers = method.getModifiers();
        this.modifiers = ReflectModifiers.toElementModifiers(_modifiers);
        this.verboseLevel = ReflectModifiers.toVerboseLevel(_modifiers);

        setXjdoc(xjdoc);
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
    protected IParameter createParameter(int index) {
        return null;
    }

    @Override
    public Object invoke(Object instance, Object... parameters)
            throws ReflectiveOperationException {
        return method.invoke(instance, parameters);
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
