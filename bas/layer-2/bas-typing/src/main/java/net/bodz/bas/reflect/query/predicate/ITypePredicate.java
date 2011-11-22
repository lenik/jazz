package net.bodz.bas.reflect.query.predicate;

public interface ITypePredicate {

    /**
     * @param type
     *            Never <code>null</code>.
     */
    boolean evaluate(Class<?> type);

}
