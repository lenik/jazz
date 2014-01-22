package net.bodz.bas.site;

import java.util.Collection;
import java.util.Set;

/**
 * TODO Annotation Equality.
 *
 * See also: RAMDirectory.
 */
public interface ITagIndex<T> {

    Collection<T> find(Set<Object> criteria);

}
