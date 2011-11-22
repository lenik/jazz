package net.bodz.bas.reflect.query.predicate;

public interface ITypeVectorPredicate {

    /**
     * Evaluate the type vector.
     * 
     * @param tv
     *            Non-<code>null</code> array of {@link Class}.
     * @return <code>true</code> or <code>false</code>.
     */
    boolean evaluate(Class<?>[] tv);

}
