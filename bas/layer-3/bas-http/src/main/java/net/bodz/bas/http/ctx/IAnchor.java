package net.bodz.bas.http.ctx;

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
    String absoluteHref();

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

    IAnchor join(String spec);

    class fn {

        public static String hrefFrom(String fromPath, String destPath) {
            if (fromPath == null)
                throw new NullPointerException("otherPath");
            if (!fromPath.startsWith("/"))
                throw new IllegalArgumentException("Not absolute: " + fromPath);

            // Remove the "basename" at first.
            String from_ = fromPath.substring(0, fromPath.lastIndexOf('/') + 1);

            StringBuilder sb = new StringBuilder(80);
            while (true) {
                if (destPath.startsWith(from_))
                    break;

                int slash = from_.lastIndexOf('/', from_.length() - 2);
                if (slash == -1)
                    break;

                from_ = from_.substring(0, slash + 1);
                sb.append("../");
            }

            String remaining = destPath.substring(from_.length());
            sb.append(remaining);
            return sb.toString();
        }

    }

}
