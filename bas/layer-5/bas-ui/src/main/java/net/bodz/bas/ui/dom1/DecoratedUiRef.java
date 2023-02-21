package net.bodz.bas.ui.dom1;

import java.lang.annotation.Annotation;

import net.bodz.bas.potato.element.IAnnotated;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.t.tree.IPathInfo;

public abstract class DecoratedUiRef<T>
        extends DecoratedUiElement
        implements
            IUiRef<T> {

    private static final long serialVersionUID = 1L;

    protected DecoratedUiRef(IUiRef<T> _orig) {
        super(_orig);
    }

    @SuppressWarnings("unchecked")
    @Override
    public IUiRef<T> getWrapped() {
        return (IUiRef<T>) _orig;
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
    public boolean isReadOnly() {
        return getWrapped().isReadOnly();
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
    public <spec_t> spec_t query(Class<spec_t> specificationType)
            throws QueryException {
        return getWrapped().query(specificationType);
    }

    @Override
    public Object query(String... args)
            throws QueryException {
        return getWrapped().query(args);
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
