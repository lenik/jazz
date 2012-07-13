package net.bodz.bas.potato.traits;

import java.beans.FeatureDescriptor;
import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.i18n.dom.DomainString;

public abstract class AbstractElement
        implements IElement {

    private final Class<?> declaringType;
    private final String name;

    protected DomainString displayName;
    protected DomainString description;
    protected Set<String> tags;

    /**
     * @param declaringType
     *            May be <code>null</code>.
     * @param name
     *            May be <code>null</code>.
     */
    public AbstractElement(Class<?> declaringType, String name) {
        this.declaringType = declaringType;
        this.name = name;
    }

    @Override
    public Class<?> getDeclaringType() {
        return declaringType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public DomainString getDisplayName() {
        return displayName;
    }

    public void setDisplayName(DomainString displayName) {
        this.displayName = displayName;
    }

    @Override
    public DomainString getDescription() {
        return description;
    }

    public void setDescription(DomainString description) {
        this.description = description;
    }

    /**
     * @see #getFeaturePreferenceLevel(FeatureDescriptor)
     */
    @Override
    public int getPreferenceLevel() {
        return 0;
    }

    @Override
    public Set<String> getTags() {
        if (tags == null) {
            synchronized (this) {
                if (tags == null) {
                    tags = new TreeSet<String>();
                }
            }
        }
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
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
    public String toString() {
        return getName();
    }

}
