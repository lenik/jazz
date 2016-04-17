package net.bodz.bas.potato.provider.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.potato.element.AbstractConstructor;
import net.bodz.bas.potato.element.IConstructor;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class ReflectConstructor
        extends AbstractConstructor {

    private final Constructor<?> ctor;

    private final int modifiers;
    private final int detailLevel;
    private final int priority;

    /**
     * @throws NullPointerException
     *             If <code>method</code> is <code>null</code>.
     */
    public ReflectConstructor(Constructor<?> ctor, IElementDoc doc) {
        super(ctor.getDeclaringClass(), doc);
        this.ctor = ctor;

        int _modifiers = ctor.getModifiers();
        this.modifiers = _modifiers;
        this.detailLevel = ReflectModifiers.toDetailLevel(_modifiers);

        Priority aPriority = ctor.getAnnotation(Priority.class);
        priority = aPriority == null ? 0 : aPriority.value();
    }

    /** ⇱ Implementation Of {@link IConstructor}. */
    /* _____________________________ */static section.iface __CTOR__;

    @Override
    public Class<?>[] getParameterTypes() {
        return ctor.getParameterTypes();
    }

    @Override
    public Object newInstance(Object... parameters)
            throws ReflectiveOperationException {
        return ctor.newInstance(ctor, parameters);
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
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return ctor.isAnnotationPresent(annotationClass);
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        return ctor.getAnnotation(annotationClass);
    }

    @Override
    public Annotation[] getAnnotations() {
        return ctor.getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return ctor.getDeclaredAnnotations();
    }

}
