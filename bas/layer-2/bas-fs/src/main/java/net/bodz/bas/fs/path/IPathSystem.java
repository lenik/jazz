package net.bodz.bas.fs.path;


public interface IPathSystem {

    int HIGH_PRIORITY = -100;
    int NORMAL_PRIORITY = 0;
    int LOW_PRIORITY = 100;

    /**
     * Set path resolver for a named protocol, if that protocol is already exists, then it's
     * overwrited.
     * 
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     */
    void setPathResolver(String protocol, IPathResolver resolver);

    /**
     * @throws NullPointerException
     *             If <code>protocol</code> is <code>null</code>.
     */
    void unsetPathResolver(String protocol);

    /**
     * @throws NullPointerException
     *             If <code>resolver</code> is <code>null</code>.
     */
    void addGenericPathResolver(IPathResolver resolver, int priority);

    /**
     * @throws NullPointerException
     *             If <code>resolver</code> is <code>null</code>.
     */
    void addGenericPathResolver(IPathResolver resolver);

    /**
     * @throws NullPointerException
     *             If <code>resolver</code> is <code>null</code>.
     */
    void removeGenericPathResolver(IPathResolver resolver);

    /**
     * The default context path is used to expand a relative path to absolute path.
     * 
     * @return non-<code>null</code> context path, whose alignment should be absolute.
     */
    IPath getDefaultContext();

    /**
     * @return non-<code>null</code> path.
     * @throws NullPointerException
     *             If <code>path</code> is <code>null</code>.
     */
    IPath resolve(String path)
            throws PathException;

}
