package net.bodz.bas.potato.element;

import java.lang.annotation.Annotation;
import java.util.Map;

import net.bodz.bas.c.reflect.DecoratedAnnotatedElement;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.mda.xjdoc.model.IJavaElementDoc;
import net.bodz.mda.xjdoc.model.javadoc.IXjdocElement;

public class DecoratedPotatoElement
        extends DecoratedAnnotatedElement
        implements IPotatoElement {

    private static final long serialVersionUID = 1L;

    public DecoratedPotatoElement(IPotatoElement _orig) {
        super(_orig);
    }

    @Override
    public IPotatoElement getWrapped() {
        return (IPotatoElement) _orig; // super.getWrapped();
    }

    @Override
    public Class<?> getDeclaringClass() {
        return getWrapped().getDeclaringClass();
    }

    @Override
    public <T> T getTyper(Class<T> typerClass) {
        return getWrapped().getTyper(typerClass);
    }

    /** ⇱ Implementation Of {@link IAnnotated}. */
    /* _____________________________ */static section.iface __ANNOTATION__;

    @Override
    public Map<Class<?>, Annotation> getAnnotationMap() {
        return getWrapped().getAnnotationMap();
    }

    @Override
    public Map<Class<?>, Annotation> getDeclaredAnnotationMap() {
        return getWrapped().getDeclaredAnnotationMap();
    }

    @Override
    public void dumpAnnotations(Map<Class<?>, Annotation> map) {
        IPotatoElement wrapped = getWrapped();
        IAnnotated _wrapped = wrapped;
        _wrapped.dumpAnnotations(map);
    }

    /** ⇱ Implementation Of {@link IElement}. */
    /* _____________________________ */static section.iface __ELEMENT__;

    @Override
    public String getName() {
        return getWrapped().getName();
    }

    @Override
    public iString getLabel() {
        return getWrapped().getLabel();
    }

    @Override
    public iString getDescription() {
        return getWrapped().getDescription();
    }

    @Override
    public iString getHelpDoc() {
        return getWrapped().getHelpDoc();
    }

    @Override
    public int getVerboseLevel() {
        return getWrapped().getVerboseLevel();
    }

    @Override
    public int getModifiers() {
        return getWrapped().getModifiers();
    }

    /** ⇱ Implementation Of {@link IXjdocElement}. */
    /* _____________________________ */static section.iface __XJDOC__;

    @Override
    public IJavaElementDoc getXjdoc() {
        return getWrapped().getXjdoc();
    }

}
