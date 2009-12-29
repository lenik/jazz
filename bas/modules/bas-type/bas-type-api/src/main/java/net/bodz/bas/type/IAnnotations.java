package net.bodz.bas.type;

import java.util.Collection;

public interface IAnnotations {

    /**
     * @return Never <code>null</code>, returns empty {@link Collection} if no public annotation
     *         available.
     */
    Collection<IAnnotationEntry> getPublicAnnotations();

    /**
     * @return <code>null</code> if specified id doesn't exist.
     */
    IAnnotationEntry getAnnotation(String id);

}
