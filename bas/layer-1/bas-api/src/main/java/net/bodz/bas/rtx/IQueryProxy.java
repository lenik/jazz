package net.bodz.bas.rtx;

public interface IQueryProxy {

    /**
     * @param obj               The object under query.
     * @param specificationType The specification type to query about, the implementation should not consider any
     *                          super or derived class or interface of the specified
     *                          <code>specificationType</code> , but one may return the result which is instance
     *                          of <code>specificationType</code>. Return <code>null</code> if the specification
     *                          type doesn't available.
     * @return <code>null</code> If no available implementation exists for the
     * <code>specificationType</code>.
     * @throws NullPointerException If <code>specificationType</code> is <code>null</code>.
     * @throws ClassCastException   If the returned implementation can't be isn't instanceof specifiec
     *                              <code>specificationType</code>.
     * @throws QueryException       If query is failed.
     */
    default <T> T query(Object obj, Class<T> specificationType)
            throws QueryException {
        if (obj instanceof IQueryable) {
            IQueryable queryable = (IQueryable) obj;
            return queryable.query(specificationType);
        }
        return null;
    }

    /**
     * @param obj The object under query.
     * @return <code>null</code> If no available implementation exists for the
     * <code>specificationId</code>.
     * @throws NullPointerException If <code>specificationId</code> is <code>null</code>.
     * @throws QueryException       If query is failed.
     */
    default Object query(Object obj, String... args)
            throws QueryException {
        if (obj instanceof IQueryable) {
            IQueryable queryable = (IQueryable) obj;
            return queryable.query(args);
        }
        return null;
    }

}
