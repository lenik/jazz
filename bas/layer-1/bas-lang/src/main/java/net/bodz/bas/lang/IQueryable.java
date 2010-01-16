package net.bodz.bas.lang;

public interface IQueryable {

    /**
     * @return <code>null</code> If no available implementation exists for the
     *         <code>specificationId</code>.
     * @throws NullPointerException
     *             If <code>specificationId</code> is <code>null</code>.
     * @throws QueryException
     *             If query is failed.
     */
    Object query(String specificationId)
            throws QueryException;

    /**
     * The returnd value should be exactly the same to the return value of {@link #query(String)}
     * with <code>id</code> equals to {@link Class#getName()}.
     * 
     * @param specificationType
     *            The specification type to query about, the implementation should not consider any
     *            super or derived class or interface of the specified
     *            <code>specificationType</code> , but one may return the result which is instance
     *            of <code>specificationType</code>. Return <code>null</code> if the specification
     *            type doesn't available.
     * @return <code>null</code> If no available implementation exists for the
     *         <code>specificationType</code>.
     * @throws NullPointerException
     *             If <code>specificationType</code> is <code>null</code>.
     * @throws ClassCastException
     *             If the returned implementation can't be isn't instanceof specifiec
     *             <code>specificationType</code>.
     * @throws QueryException
     *             If query is failed.
     */
    <T> T query(Class<T> specificationType)
            throws QueryException;

}
