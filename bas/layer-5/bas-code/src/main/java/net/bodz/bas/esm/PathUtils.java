package net.bodz.bas.esm;

public class PathUtils {

    public static boolean matchRelative(String contextPath, String relativePath) {
        if (contextPath == null || relativePath == null)
            return contextPath == relativePath;
        int lastSlash = contextPath.lastIndexOf('/');
        int pos;
        if (lastSlash == -1)
            pos = 0;
        else
            pos = lastSlash + 1;
        return _matchRelative(contextPath, pos, relativePath);
    }

    static boolean _matchRelative(String contextPath, int pos, String relativePath) {
        int slash = relativePath.indexOf('/');
        String head;
        String tail;
        if (slash == -1) {
            head = relativePath;
            tail = null;
        } else {
            head = relativePath.substring(0, slash);
            tail = relativePath.substring(slash + 1);
        }

        switch (head) {
        case ".":
            return _matchRelative(contextPath, pos, tail);

        case "..":
            int lastSlash = contextPath.lastIndexOf('/', pos - 2);
            if (lastSlash == -1)
                if (pos == 0)
                    return false;
                else
                    pos = 0;
            else
                pos = lastSlash + 1;
            return _matchRelative(contextPath, pos, tail);

        default:
            int nextSlash = contextPath.indexOf('/', pos);
            String contextHead = nextSlash == -1 //
                    ? contextPath.substring(pos)
                    : contextPath.substring(pos, nextSlash);
            if (! contextHead.equals(head))
                return false;
            if (tail == null)
                return true;
            return _matchRelative(contextPath, nextSlash + 1, tail);
        }
    }

    static void test(String contextPath, String relativePath) {
        boolean result = matchRelative(contextPath, relativePath);
        System.out.printf("matchRelative(%s, %s): %s\n", contextPath, relativePath, result);
    }

    public static void main(String[] args) {
        test("foo/bar", "./bar");
        test("foo/bar", "../bar");
        test("foo/bar", "bar");
        test("foo/bar", "");
        test("foo/bar", "foo/bar");
        test("foo/bar", "../foo");
        test("foo/bar", "../foo/bar");
    }

}
