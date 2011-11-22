package net.bodz.bas.reflect.query.predicate;

public interface IStringPredicate {

    /**
     * @param name
     *            Never <code>null</code>.
     */
    boolean evaluate(String name);

}
