package net.bodz.bas.collection.hierarchical;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;


public class DirMap<T> extends HierMap<String, T> {

    private static final long serialVersionUID = 4662707552493624475L;

    public DirMap() {
        super();
    }

    public DirMap(Comparator<? super String> comparator) {
        super(comparator);
    }

    public DirMap(Map<? extends String, ? extends T> m) {
        super(m);
    }

    public DirMap(SortedMap<String, ? extends T> m) {
        super(m);
    }

    static String getCanonicalPath(String path) {
        return path;
    }

    static boolean _derives(String dir, String path) {
        dir = getCanonicalPath(dir);
        path = getCanonicalPath(path);
        if (!path.startsWith(dir))
            return false;
        int p = dir.length();
        if (path.length() == p)
            return true;
        if (path.charAt(p) != '/')
            return false;
        return true;
    }

    /**
     * @return parent dir of path
     */
    static String _shrink(String path) {
        path = getCanonicalPath(path);
        int p = path.lastIndexOf('/');
        if (p == -1)
            return null;
        return path.substring(0, p);
    }

    @Override
    public boolean derives(String sup, String sub) {
        return _derives(sup, sub);
    }

    @Override
    public String shrink(String e) {
        return _shrink(e);
    }

}
