package net.bodz.bas.lang;

public interface IQueryable {

    Object query(String specificationId);

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
     */
    <T> T query(Class<T> specificationType);

}
