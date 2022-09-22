package net.bodz.bas.servlet.ctx;

import javax.servlet.ServletContext;

/**
 * A relative path abstraction.
 *
 * @see WebAppAnchor
 * @see PathAnchor
 * @see URLAnchor
 */
public interface IAnchor {

    boolean isDirectory();

    /**
     * Get the absolute href.
     *
     * @return The absolute href, contains the trailing slash.
     */
    @Deprecated
    String absoluteHref();

    default String toUriPath() {
        return absoluteHref();
    }

    /**

     */
    default String toWebPath() {
        ServletContext servletContext = CurrentHttpService.getServletContext();

        // empty or "/foo".
        String contextPath = servletContext.getContextPath();
        String uriPath = toUriPath();
        if (!uriPath.startsWith("/"))
            throw new IllegalArgumentException("invalid uriPath: " + uriPath);

        return PathUtils.hrefFrom(contextPath, uriPath);
    }

    /**
     * Get the href string (relative path) from other path.
     *
     * Example:
     *
     * "/foo/bar/".from("/foo/def/[xyz]") = ../bar/
     *
     * @param otherPath
     *            The other path. Must be non-<code>null</code> absolute path.
     * @return The relative path from <code>otherPath</code> to this base path. Never
     *         <code>null</code>.
     * @throws IllegalArgumentException
     *             If the other path isn't absolute.
     */
    String hrefFrom(String otherPath);

    String hrefTo(String otherPath);

    IAnchor join(String spec);

}
