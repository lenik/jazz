package net.bodz.bas.aspect.typeinfo;

import java.util.Collection;

public interface Annotations {

    /**
     * @return Never <code>null</code>, returns empty {@link Collection} if no public annotation
     *         available.
     */
    Collection<AnnotationEntry> getPublicAnnotations();

    /**
     * @return <code>null</code> if specified id doesn't exist.
     */
    AnnotationEntry getAnnotation(String id);

}
