package net.bodz.bas.rtx;

public interface IQueryable {

    /**
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
    <spec_t> spec_t query(Class<spec_t> specificationType)
            throws QueryException;

    /**
     * @return <code>null</code> If got nothing.
     * @throws QueryException
     *             If query is failed.
     */
    Object query(String... args)
            throws QueryException;

}
