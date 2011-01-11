package net.bodz.bas.type.traits;

import java.util.Collection;

import net.bodz.bas.type.ITypeTraits;

public interface IAttributes {

    /**
     * @return Never <code>null</code>, returns empty {@link Collection} if no public annotation
     *         available.
     */
    Collection<String> getAttributeNames();

    /**
     * @return <code>null</code> if specified id doesn't exist.
     */
    Object getAttribute(String attributeName);

    /**
     * @return <code>null</code> if no type info available.
     */
    ITypeTraits<?> getAttributeTraits(String attributeName);

}
