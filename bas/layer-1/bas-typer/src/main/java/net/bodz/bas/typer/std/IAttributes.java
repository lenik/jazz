package net.bodz.bas.typer.std;

import java.util.Collection;

public interface IAttributes
        extends IStdTyper {

    int typerIndex = 0x3d9ce22c; // IAttributes

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
