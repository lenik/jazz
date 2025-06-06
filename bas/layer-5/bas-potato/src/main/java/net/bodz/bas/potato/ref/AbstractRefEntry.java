package net.bodz.bas.potato.ref;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Map;

import net.bodz.bas.i18n.dom1.DecoratedElement;
import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.typer.Typers;

public abstract class AbstractRefEntry<T>
        extends DecoratedElement<IElement>
        implements IRefEntry<T>,
                   Map.Entry<String, T> {

    private static final long serialVersionUID = 1L;

    private final AnnotatedElement annotatedElement;

    public AbstractRefEntry(IElement element, AnnotatedElement annotatedElement) {
        super(element);
        if (annotatedElement == null)
            throw new NullPointerException("annotatedElement");
        this.annotatedElement = annotatedElement;
    }

    @Override
    public void remove() {
        set(null);
    }

    /**
     * ⇱ Implementation Of {@link AnnotatedElement}.
     */
    /* _____________________________ */static section.iface __ANNOTATED__;

    @Override
    public boolean isAnnotationPresent(@NotNull Class<? extends Annotation> annotationClass) {
        return annotatedElement.isAnnotationPresent(annotationClass);
    }

    @Override
    public <A extends Annotation> A getAnnotation(@NotNull Class<A> annotationClass) {
        return annotatedElement.getAnnotation(annotationClass);
    }

    @NotNull
    @Override
    public Annotation[] getAnnotations() {
        return annotatedElement.getAnnotations();
    }

    @NotNull
    @Override
    public Annotation[] getDeclaredAnnotations() {
        return annotatedElement.getDeclaredAnnotations();
    }

    /**
     * ⇱ {@link java.util.Map.Entry}.
     */
    /* _____________________________ */static section.iface __ENTRY__;

    @Override
    public final String getKey() {
        return getName();
    }

    @Override
    public final T getValue() {
        return get();
    }

    @Override
    public final T setValue(T value) {
        T oldValue = get();
        set(value);
        return oldValue;
    }

    /**
     * ⇱ Implementaton Of {@link net.bodz.bas.rtx.IQueryable}
     */
    /* _____________________________ */static section.iface __QUERY__;

    @Override
    public <spec_t> spec_t query(Class<spec_t> specificationType)
            throws QueryException {
        Class<?> valueType = getValueType();

        // if (IStdTyper.class.isAssignableFrom(specificationType))
        spec_t typer = Typers.getTyper(valueType, specificationType);
        if (typer != null)
            return typer;

        return null;
    }

    @Override
    public Object query(String... args)
            throws QueryException {
        return null;
    }

}
