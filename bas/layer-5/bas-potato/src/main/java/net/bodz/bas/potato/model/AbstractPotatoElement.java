package net.bodz.bas.potato.model;

import java.lang.annotation.Annotation;
import java.util.Map;

import net.bodz.mda.xjdoc.model1.AbstractArtifactElement;
import net.bodz.mda.xjdoc.model1.ArtifactDoc;

public abstract class AbstractPotatoElement
        extends AbstractArtifactElement
        implements IPotatoElement {

    private String name;
    private final Class<?> declaringClass;

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
    public ArtifactDoc getArtifactDoc() {
        // TODO ClassDocs.loadFromResource(getClass()).as(ArtifactDoc.class);
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class<?> getDeclaringClass() {
        return declaringClass;
    }

    private static final Annotation[] EMPTY_ANNOTATION = new Annotation[0];

    @Override
    public Annotation[] getAnnotations() {
        return EMPTY_ANNOTATION;
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return EMPTY_ANNOTATION;
    }

    /**
     * {@inheritDoc}
     * 
     * @return The first occurance in {@link #getAnnotations()} which is instance of the specified
     *         <code>annotationClass</code>.
     */
    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        for (Annotation a : getAnnotations())
            if (annotationClass.isInstance(a))
                return annotationClass.cast(a);
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @return <code>true</code> If any annotation of in {@link #getAnnotations()} is instance of
     *         the specified <code>annotationClass</code> exists.
     */
    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        for (Annotation a : getAnnotations())
            if (annotationClass.isInstance(a))
                return true;
        return false;
    }

    @Override
    public void findAnnotations(Map<Class<? extends Annotation>, Annotation> map) {
        Annotation[] annotations = getAnnotations();
        for (Annotation annotation : annotations)
            map.put(annotation.getClass(), annotation);
    }

    @Override
    public String toString() {
        return getName();
    }

}
