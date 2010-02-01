package net.bodz.bas.reflect.query;

public interface ITypePredicate {

    /**
     * @return <code>null</code> If no next.
     */
    ITypePredicate next();

    /**
     * @param type
     *            Never <code>null</code>.
     */
    boolean test(Class<?> type);

}
