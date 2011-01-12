package net.bodz.bas.vfs.path;

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
    void addPathResolver(String protocol, IPathResolver resolver);

    /**
     * @throws NullPointerException
     *             If <code>protocol</code> is <code>null</code>.
     */
    void removePathResolver(String protocol);

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
     * Get the context path.
     * <p>
     * The context path is used to expand a relative path to absolute path.
     * <p>
     * You should avoid to use context path whenever possible, because it may be changed.
     * 
     * @return non-<code>null</code> context path, whose alignment should be absolute.
     */
    IPath getContextPath();

    /**
     * Change the context path.
     * <p>
     * The context path is used to expand a relative path to absolute path.
     * 
     * Change context path won't affect those already joined relative paths,
     * <p>
     * <i>Warning: You should know what you are doing, before change the context path. </i>
     * 
     * @throws NullPointerException
     *             If <code>path</code> is <code>null</code>.
     */
    void setContextPath(IPath path);

    /**
     * @return non-<code>null</code> path.
     * @throws NullPointerException
     *             If <code>path</code> is <code>null</code>.
     */
    IPath resolve(String path)
            throws BadPathException;

}
