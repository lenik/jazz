package net.bodz.bas.fs.path;

public interface IPathSystem {

    int HIGH_ORDER = -100;
    int NORMAL_ORDER = 0;
    int LOW_ORDER = 100;

    /**
     * @throws NullPointerException
     *             If <code>resolver</code> is <code>null</code>.
     */
    void addPathResolver(IPathResolver resolver, int order);

    /**
     * @throws NullPointerException
     *             If <code>resolver</code> is <code>null</code>.
     */
    void addPathResolver(IPathResolver resolver);

    /**
     * @throws NullPointerException
     *             If <code>resolver</code> is <code>null</code>.
     */
    void removePathResolver(IPathResolver resolver);

    /**
     * @return non-<code>null</code> path.
     * @throws NullPointerException
     *             If <code>path</code> is <code>null</code>.
     */
    IPath resolve(String path)
            throws PathException;

}
