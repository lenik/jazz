package net.bodz.bas.servlet.ctx;

public class PathUtils {

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

    public static boolean parentWith(String path, String parent) {
        if (path == null)
            throw new NullPointerException("path");
        if (parent == null)
            throw new NullPointerException("parent");

        if (path.startsWith(parent)) {
            int len = path.length();
            int parentLen = parent.length();
            if (len == parentLen)
                return true;
            if (path.charAt(parentLen) == '/')
                return true;
        }
        return false;
    }

    public static String pathAfter(String path, String parent) {
        if (path == null)
            throw new NullPointerException("path");
        if (parent == null)
            throw new NullPointerException("parent");

        if (path.startsWith(parent)) {
            int len = path.length();
            int parentLen = parent.length();
            if (len == parentLen)
                return null;
            if (path.charAt(parentLen) == '/')
                return path.substring(parentLen + 1);
        }
        return path;
    }

}
