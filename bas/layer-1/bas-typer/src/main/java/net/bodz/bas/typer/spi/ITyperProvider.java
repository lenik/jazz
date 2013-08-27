package net.bodz.bas.typer.spi;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.rtx.IQueryProxy;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.t.order.IPriority;

/**
 * Provide to resolve specific typers for a given class or object.
 */
@IndexedType
public interface ITyperProvider
        extends IPriority, IQueryProxy {

    /**
     * High priority.
     * <p>
     * This typer provider should be queryed first.
     * <p>
     * A high priority typers provider should be highly optimized, to resolve the type typers fast.
     */
    int PRIORITY_HIGH = -100;

    /**
     * Normal priority.
     */
    int PRIORITY_NORMAL = 0;

    /**
     * Low priority.
     */
    int PRIORITY_LOW = 100;

    /**
     * Determine the priority of this typer provider compared to the given one.
     * 
     * The priority is represented by an integer, a smaller value means higher priority.
     * 
     * @return An integer value represent the priority.
     * @see #PRIORITY_HIGH
     * @see #PRIORITY_NORMAL
     * @see #PRIORITY_LOW
     */
    @Override
    int getPriority();

    /**
     * An aggressive typers provider will include all inherited typers in the results.
     * <p>
     * For a specific typers query on the user type, the aggressive typers provider will be called
     * only once.
     * <p>
     * Correspondingly, for non-aggressive typers provider, if the concerned typers wasn't included
     * in the query results, then this provider will be later called again with the super class (or
     * interface) of the user type.
     * 
     * @return <code>true</code> If inherited typers are included in the results provided by this
     *         typers provider.
     */
    boolean isAggressive();

    /**
     * Query for specific typers about the user object type.
     * <p>
     * For specific typers on the instance object, {@link #getTyper(Class, Object, Class)} should be
     * called.
     * 
     * @param objType
     *            The user object type to be queryed, non-<code>null</code>.
     * @param typerClass
     *            Typers facet of interesting, non-<code>null</code>.
     * @return Typers implementation, <code>null</code> if the typers isn't defined.
     * @throws QueryException
     *             If exception occurred when query for specific type typers.
     * @throws NullPointerException
     *             If <code>objType</code> or <code>typerClass</code> is <code>null</code>.
     */
    <T> T getTyper(Class<?> objType, Class<T> typerClass)
            throws QueryException;

    /**
     * Query for specific typers on the object instance.
     * 
     * @param objType
     *            The user object type to be queryed, non-<code>null</code>.
     * @param obj
     *            The object instance, may be <code>null</code>.
     *            <p>
     *            If <code>obj</code> is <code>null</code>, this method is just the same as
     *            {@link #getTyper(Class, Class)}.
     * @param typerClass
     *            Typers facet of interesting, non-<code>null</code>.
     * @return Typer implementation, <code>null</code> if the typers isn't defined.
     * @throws QueryException
     *             If exception occurred when query for specific type typers.
     * @throws NullPointerException
     *             If <code>objType</code> or <code>typerClass</code> is <code>null</code>.
     */
    <T> T getTyper(Class<?> objType, Object obj, Class<T> typerClass)
            throws QueryException;

}
