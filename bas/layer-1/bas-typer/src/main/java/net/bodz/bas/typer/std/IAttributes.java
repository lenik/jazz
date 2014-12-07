package net.bodz.bas.typer.std;

import java.util.Collection;

public interface IAttributes {

    /**
     * @return Never <code>null</code>, returns empty {@link Collection} if no public annotation
     *         available.
     */
    Collection<String> getAttributeNames();

    /**
     * @return <code>null</code> if specified id doesn't exist.
     */
    <T> T getAttribute(String name);

    <T> T getAttribute(String name, T defaultValue);

    /**
     * @return <code>null</code> if no type info available.
     */
    ITyperFamily<?> getAttributeTypers(String attributeName);

}
