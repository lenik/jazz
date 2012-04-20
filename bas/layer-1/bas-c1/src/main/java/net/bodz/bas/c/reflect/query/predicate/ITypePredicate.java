package net.bodz.bas.c.reflect.query.predicate;

public interface ITypePredicate {

    /**
     * @param type
     *            Never <code>null</code>.
     */
    boolean evaluate(Class<?> type);

}
