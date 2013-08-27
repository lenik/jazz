package net.bodz.bas.typer.std;

import java.util.Collection;

public interface IAttributes {

    int typerIndex = 70680192; // IAttributes

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
    ITyperFamily<?> getAttributeTypers(String attributeName);

}
