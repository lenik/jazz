package net.bodz.bas.potato.provider.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.potato.element.AbstractMethod;
import net.bodz.bas.potato.element.IParameter;
import net.bodz.bas.potato.element.IType;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class ReflectMethod
        extends AbstractMethod {

    private final Method method;
    private final int parameterCount;

    private final int modifiers;
    private final int detailLevel;
    private final int priority;

    /**
     * @throws NullPointerException
     *             If <code>method</code> is <code>null</code>.
     */
    public ReflectMethod(IType type, Method method, IElementDoc doc) {
        super(type, method.getName(), doc);
        this.method = method;
        this.parameterCount = method.getParameterTypes().length;

        int _modifiers = method.getModifiers();
        this.modifiers = _modifiers;

        // TODO InheritableAnnotations...
        DetailLevel aDetailLevel = method.getAnnotation(DetailLevel.class);
        if (aDetailLevel != null)
            this.detailLevel = aDetailLevel.value();
        else
            this.detailLevel = ReflectModifiers.toDetailLevel(_modifiers);

        Priority aPriority = method.getAnnotation(Priority.class);
        priority = aPriority == null ? 0 : aPriority.value();
    }

    @Override
    public Class<?> getDeclaringClass() {
        return method.getDeclaringClass();
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Class<?> getReturnType() {
        return method.getReturnType();
    }

    @Override
    public int getParameterCount() {
        return parameterCount;
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
