package net.bodz.bas.potato.element;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.type.SingletonUtil;
import net.bodz.bas.meta.lang.typer;
import net.bodz.bas.typer.std.ITyperFamily;
import net.bodz.mda.xjdoc.model.IJavaElementDoc;
import net.bodz.mda.xjdoc.model.javadoc.AbstractXjdocElement;
import net.bodz.mda.xjdoc.model.javadoc.IXjdocElement;

public abstract class AbstractPotatoElement
        extends AbstractXjdocElement
        implements IPotatoElement {

    private String name;
    private Class<?> declaringClass;

    /**
     * @param declaringType
     *            May be <code>null</code>.
     * @param name
     *            May be <code>null</code>.
     */
    public AbstractPotatoElement(Class<?> declaringType, String name) {
        this.name = name;
        this.declaringClass = declaringType;
    }

    @Override
    public Class<?> getDeclaringClass() {
        return declaringClass;
    }

    protected void setDeclaringClass(Class<?> declaringClass) {
        this.declaringClass = declaringClass;
    }

    @Override
    public <T> T getTyper(Class<T> typerClass) {
        typer.family _typerFamilyClass = getAnnotation(typer.family.class);
        if (_typerFamilyClass == null)
            return null;

        Class<? extends ITyperFamily<?>> typerFamilyClass = _typerFamilyClass.value();
        ITyperFamily<?> typerFamily;
        try {
            typerFamily = SingletonUtil.callGetInstance(typerFamilyClass);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        T typer = typerFamily.query(typerClass);
        return typer;
    }

    @Override
    public String toString() {
        return getName();
    }

    /** ⇱ Implementation Of {@link IXjdocElement}. */
    /* _____________________________ */static section.iface __XJDOC__;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setXjdoc(IJavaElementDoc xjdoc) {
        super.setXjdoc(xjdoc);
    }

    /** ⇱ Implementation Of {@link IAnnotated}. */
    /* _____________________________ */static section.iface __ANNOTATED__;

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        for (Annotation a : getAnnotations())
            if (annotationClass.isInstance(a))
                return true;
        return false;
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        for (Annotation a : getAnnotations())
            if (annotationClass.isInstance(a))
                return annotationClass.cast(a);
        return null;
    }

    @Override
    public Map<Class<?>, Annotation> getAnnotationMap() {
        Map<Class<?>, Annotation> map = new HashMap<>();
        dumpAnnotations(map);
        return map;
    }

    @Override
    public Map<Class<?>, Annotation> getDeclaredAnnotationMap() {
        Map<Class<?>, Annotation> map = new HashMap<>();
        for (Annotation annotation : getDeclaredAnnotations())
            map.put(annotation.getClass(), annotation);
        return map;
    }

    @Override
    public void dumpAnnotations(Map<Class<?>, Annotation> map) {
        Annotation[] annotations = getAnnotations();
        for (Annotation annotation : annotations)
            map.put(annotation.getClass(), annotation);
    }

}
