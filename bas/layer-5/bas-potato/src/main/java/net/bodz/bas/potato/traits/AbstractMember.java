package net.bodz.bas.potato.traits;

import java.beans.FeatureDescriptor;
import java.lang.annotation.Annotation;
import java.util.Locale;

import net.bodz.bas.meta.info.DisplayName;
import net.bodz.bas.meta.info.Doc;
import net.bodz.bas.meta.info.Tags;

import org.apache.commons.lang.ArrayUtils;

public abstract class AbstractMember
        implements IMember {

    private final Class<?> declaringType;
    private final String name;

    protected String displayName;
    protected String description;
    protected String[] tags;

    /**
     * @param declaringType
     *            May be <code>null</code>.
     * @param name
     *            May be <code>null</code>.
     */
    public AbstractMember(Class<?> declaringType, String name) {
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
    public String getDisplayName(Locale locale) {
        if (displayName == null) {
            synchronized (this) {
                if (displayName == null) {
                    DisplayName aDisplayName = getAnnotation(DisplayName.class);
                    if (aDisplayName != null) {
                        displayName = aDisplayName.value();
                        if (displayName == null)
                            displayName = getName();
                    }
                }
            }
        }
        return displayName;
    }

    @Override
    public String getDescription(Locale locale) {
        if (description == null) {
            synchronized (this) {
                if (description == null) {
                    Doc aDoc = getAnnotation(Doc.class);
                    if (aDoc != null)
                        description = aDoc.value();
                }
            }
        }
        return description;
    }

    /**
     * @see #getFeaturePreferenceLevel(FeatureDescriptor)
     */
    @Override
    public int getPreferenceLevel() {
        return 0;
    }

    @Override
    public String[] getTags() {
        if (tags == null) {
            synchronized (this) {
                if (tags == null) {
                    Tags aTags = getAnnotation(Tags.class);
                    if (aTags != null) {
                        tags = aTags.value();
                        if (tags == null)
                            tags = ArrayUtils.EMPTY_STRING_ARRAY;
                    }
                }
            }
        }
        return tags;
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
