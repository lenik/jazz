package net.bodz.bas.reflect.query;

public interface IParametersPredicate {
    /**
     * @return <code>null</code> If no more predicate
     */
    IParametersPredicate next();

    int getParameterCount();

    /**
     * @param parameterTypes
     *            Never <code>null</code>.
     */
    boolean test(Class<?>[] parameterTypes);

}
