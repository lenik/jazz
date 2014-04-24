package net.bodz.bas.http.ctx;

import javax.servlet.http.HttpServletRequest;

public interface IBasePath {

    /**
     * Get the absolute path.
     *
     * @return The absolute path, contains the trailing slash.
     */
    String absolute();

    /**
     * Get the relative path.
     *
     * Example:
     *
     * "/foo/bar/".from("/foo/def/") = ../bar/
     *
     * @param otherPath
     *            The other path. Must be non-<code>null</code> absolute path.
     * @return The relative path from <code>otherPath</code> to this base path. Never
     *         <code>null</code>.
     * @throws IllegalArgumentException
     *             If the other path isn't absolute.
     */
    String from(String otherPath);

    /**
     * Get the relative path.
     *
     * @param req
     *            Non-<code>null</code> {@link HttpServletRequest request} object, whose request URI
     *            is used as the other path.
     *
     * @return The relative path from the request URI to this base path. Never <code>null</code>.
     */
    String from(HttpServletRequest req);

    /**
     * Get the relative href, based on the thread local request.
     *
     * @see CurrentHttpService#getRequest()
     */
    String fromCurrentRequest();

}
