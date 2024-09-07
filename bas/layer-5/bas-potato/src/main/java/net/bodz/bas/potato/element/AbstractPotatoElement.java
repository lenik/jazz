package net.bodz.bas.potato.element;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.annotation.AnnotationUtil;
import net.bodz.bas.c.type.SingletonUtil;
import net.bodz.bas.meta.lang.typer;
import net.bodz.bas.potato.util.TyperOverriders;
import net.bodz.bas.typer.std.ITyperFamily;
import net.bodz.mda.xjdoc.model.IElementDoc;
import net.bodz.mda.xjdoc.model.javadoc.ExternXjdocElement;
import net.bodz.mda.xjdoc.model.javadoc.IXjdocElement;

public abstract class AbstractPotatoElement
        extends ExternXjdocElement
        implements
            IPotatoElement {

    private String name;
    private IType declaringType;

    /**
     * @param declaringType
     *            May be <code>null</code>.
     * @param name
     *            May be <code>null</code>.
     */
    public AbstractPotatoElement(IType declaringType, String name, IElementDoc doc) {
        super(doc);
        this.name = name;
        this.declaringType = declaringType;
    }

    @Override
    public IType getDeclaringType() {
        return declaringType;
    }

    @Override
    public <T> T getTyper(Class<T> typerClass) {
        Class<? extends Annotation> overriderAnnotationClass = TyperOverriders.getOverriderAnnotationClass(typerClass);
        if (overriderAnnotationClass != null) {
            Annotation aTyperImplClass = getAnnotation(overriderAnnotationClass);
            if (aTyperImplClass != null) {
                Class<T> typerImplClass = AnnotationUtil.getValue(aTyperImplClass);
                T typerImpl = SingletonUtil.instantiateCached(typerImplClass);
                return typerImpl;
            }
        }

        typer.family _typerFamilyClass = getAnnotation(typer.family.class);
        if (_typerFamilyClass == null)
            return null;

        Class<? extends ITyperFamily<?>> typerFamilyClass = _typerFamilyClass.value();
        ITyperFamily<?> typerFamily;
        typerFamily = SingletonUtil.callGetInstance(typerFamilyClass);

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
        Map<Class<?>, Annotation> map = new HashMap<Class<?>, Annotation>();
        dumpAnnotations(map);
        return map;
    }

    @Override
    public Map<Class<?>, Annotation> getDeclaredAnnotationMap() {
        Map<Class<?>, Annotation> map = new HashMap<Class<?>, Annotation>();
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
