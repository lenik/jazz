package net.bodz.bas.reflect.query;

public interface INamePredicate {

    /**
     * @return <code>null</code> If no more predicate
     */
    INamePredicate next();

    /**
     * @param name
     *            Never <code>null</code>.
     */
    boolean test(String name);

}
