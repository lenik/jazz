package net.bodz.bas.gui.dom1;

import java.lang.annotation.Annotation;

import net.bodz.bas.potato.element.IAnnotated;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.t.tree.IPathInfo;

public abstract class DecoratedGUIRefEntry<T>
        extends DecoratedGUIElement
        implements IGUIRefEntry<T> {

    private static final long serialVersionUID = 1L;

    protected DecoratedGUIRefEntry(IGUIRefEntry<T> _orig) {
        super(_orig);
    }

    @SuppressWarnings("unchecked")
    @Override
    public IGUIRefEntry<T> getWrapped() {
        return (IGUIRefEntry<T>) _orig;
    }

    /** ⇱ Implementation Of {@link Ref}. */
    /* _____________________________ */static section.iface __REF__;

    @Override
    public Class<? extends T> getValueType() {
        return getWrapped().getValueType();
    }

    @Override
    public T get() {
        return getWrapped().get();
    }

    @Override
    public void set(T value) {
        getWrapped().set(value);
    }

    @Override
    public void remove() {
        getWrapped().remove();
    }

    /** ⇱ Implementation Of {@link IPathInfo}. */
    /* _____________________________ */static section.iface __PATH_INFO__;

    @Override
    public IPathInfo getParent() {
        return getWrapped().getParent();
    }

    @Override
    public String getLocalPath() {
        return getWrapped().getLocalPath();
    }

    /** ⇱ Implementation Of {@link IQueryable}. */
    /* _____________________________ */static section.iface __QUERYABLE__;

    @Override
    public Object query(Object specification)
            throws QueryException {
        return getWrapped().query(specification);
    }

    @Override
    public Object query(String specificationId)
            throws QueryException {
        return getWrapped().query(specificationId);
    }

    @Override
    public <spec_t> spec_t query(Class<spec_t> specificationType)
            throws QueryException {
        return getWrapped().query(specificationType);
    }

    /** ⇱ Implementation Of {@link IAnnotated}. */
    /* _____________________________ */static section.iface __ANNOTATED__;

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return getWrapped().isAnnotationPresent(annotationClass);
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        return getWrapped().getAnnotation(annotationClass);
    }

    @Override
    public Annotation[] getAnnotations() {
        return getWrapped().getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return getWrapped().getDeclaredAnnotations();
    }

}
