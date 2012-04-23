package net.bodz.bas.vfs.path;

import net.bodz.bas.vfs.CurrentDirectoryColo;

public interface IPathSystem {

    int HIGH_PRIORITY = -100;
    int NORMAL_PRIORITY = 0;
    int LOW_PRIORITY = 100;

    /**
     * Set the path resolver of specific protocol, if the protocol is in use, return false.
     * 
     * @return <code>false</code> If the protocol name is in use.
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     */
    boolean addPathResolver(String protocol, IPathResolver resolver);

    /**
     * Remove a path resolver by protocol.
     * 
     * @throws NullPointerException
     *             If <code>protocol</code> is <code>null</code>.
     */
    void removePathResolver(String protocol);

    /**
     * Add a generic path resolver when no protocol is matched.
     * 
     * @param resolver
     *            A generic path resolver to be added.
     * @param priority
     *            The priority of this generic path resolver, smaller integer will be evaluated
     *            first.
     * @throws NullPointerException
     *             If <code>resolver</code> is <code>null</code>.
     * @see #LOW_PRIORITY
     * @see #NORMAL_PRIORITY
     * @see #HIGH_PRIORITY
     */
    void addGenericPathResolver(IGenericPathResolver resolver, int priority);

    /**
     * Remove a generic path resolver.
     * 
     * @param resolver
     *            A generic path resolver to be removed.
     * @throws NullPointerException
     *             If <code>resolver</code> is <code>null</code>.
     */
    void removeGenericPathResolver(IGenericPathResolver resolver);

    /**
     * Get the context path (or "current directory").
     * <p>
     * The context path is used to expand a relative path to absolute path.
     * <p>
     * USING WITH CAUTION: Context path should not be cached.
     * 
     * @return non-<code>null</code> context path, whose alignment should be absolute.
     * @see CurrentDirectoryColo
     */
    IPath getContextPath();

    /**
     * Change the context path (or "current directory").
     * <p>
     * The context path is used to expand a relative path to absolute path.
     * 
     * Change context path won't affect those already joined relative paths,
     * <p>
     * USING WITH CAUTION: Context path should not be cached.
     * 
     * @param Non
     *            -<code>null</code> context path to be set.
     */
    void setContextPath(IPath path);

    /**
     * Resolve unaligned (i.e., relative) path string within this path system.
     * 
     * @param path
     *            The path string to be resolved.
     * @return Non-<code>null</code> resolved path object.
     */
    IPath resolve(String path)
            throws BadPathException;

}
