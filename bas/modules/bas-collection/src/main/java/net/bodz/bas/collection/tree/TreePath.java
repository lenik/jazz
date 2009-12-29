package net.bodz.bas.collection.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.exceptions.OutOfDomainException;

/**
 * Segment is always trimmed, and can't be:
 * <ul>
 * <li>empty string</li>
 * </ul>
 * 
 * @test {@link TreePathTest}
 */
public class TreePath implements Comparable<TreePath>, Serializable {

    private static final long serialVersionUID = -7257088263065852283L;

    public static final String FS = "/";

    public static final int ABSOLUTE = -1;
    private final int uplevel;

    private final String[] segments;

    /**
     * @param path
     *            will be canonicalized.
     */
    public TreePath(String path) {
        this(path, true);
    }

    /**
     * Canonicalization:
     * <ul>
     * <li>empty segments are removed, so any trailing / are ignored.
     * <li>., .. are reduced when possible.
     * </ul>
     * XXX - escapes...
     */
    public TreePath(String path, boolean canonicalize) {
        this(path.startsWith(FS) ? ABSOLUTE : 0, //
                (path.startsWith(FS) ? path.substring(1) : path).split(FS), //
                canonicalize);
    }

    public TreePath(TreePath parent, String child) {
        this(parent, new TreePath(child));
    }

    public TreePath(TreePath parent, TreePath child) {
        if (parent == null || child.isAbsolute()) {
            uplevel = child.uplevel;
            segments = child.segments;
        } else {
            int reduce = Math.min(parent.segments.length, child.uplevel);
            int pn = parent.segments.length - reduce;
            segments = new String[pn + child.segments.length];
            if (parent.isAbsolute())
                // if (child.uplevel > 0) throw new IllegalStateException();
                uplevel = ABSOLUTE;
            else
                uplevel = parent.uplevel + child.uplevel - reduce;
            System.arraycopy(parent.segments, 0, segments, 0, pn);
            System.arraycopy(child.segments, 0, segments, pn, child.segments.length);
        }
    }

    TreePath(int uplevel, String[] segments, boolean canonicalize) {
        if (segments == null)
            throw new NullPointerException("segments");
        if (uplevel < -1)
            throw new OutOfDomainException("uplevel", uplevel, -1);
        if (canonicalize) {
            List<String> cv = new ArrayList<String>(segments.length);
            for (int i = 0; i < segments.length; i++) {
                String s = segments[i];
                assert s != null : "path segment[" + i + "] is null";
                if (s.isEmpty() || ".".equals(s))
                    continue;
                if ("..".equals(s)) {
                    if (cv.isEmpty())
                        if (isAbsolute())
                            ; // throw new IllegalStateException();
                        else
                            uplevel++;
                    else
                        cv.remove(cv.size() - 1);
                    continue;
                }
                // normalize(s)?...
                cv.add(s);
            }
            segments = cv.toArray(new String[0]);
        }
        this.uplevel = uplevel;
        this.segments = segments;
    }

    public boolean isAbsolute() {
        return uplevel == ABSOLUTE;
    }

    public boolean isRelative() {
        return !isAbsolute();
    }

    public int getUpLevel() {
        return uplevel;
    }

    public TreePath getCanonical() {
        return new TreePath(uplevel, segments, true);
    }

    public TreePath getParent() {
        if (segments.length > 0)
            return new TreePath(uplevel, Arrays.copyOf(segments, segments.length - 1), false);
        else if (isAbsolute())
            // return this; // root/.. = root
            return null; // root/.. = null
        else
            return new TreePath(uplevel + 1, segments, false);
    }

    public String getPath() {
        StringBuffer buf = new StringBuffer(segments.length * 30);
        if (uplevel == ABSOLUTE)
            buf.append(FS);
        else
            for (int i = 0; i < uplevel; i++) {
                buf.append("..");
                buf.append(FS);
            }
        for (int i = 0; i < segments.length; i++) {
            if (i != 0)
                buf.append(FS);
            buf.append(segments[i]);
        }
        return buf.toString();
    }

    @Override
    public String toString() {
        return getPath();
    }

    @Override
    public int hashCode() {
        int h = 0x5f51d01c;
        h += 0xf05a497c * uplevel;
        h += Arrays.hashCode(segments);
        return h;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TreePath))
            return false;
        TreePath p = (TreePath) obj;
        if (uplevel != p.uplevel)
            return false;
        return Arrays.equals(segments, p.segments);
    }

    @Override
    public int compareTo(TreePath o) {
        if (o == null)
            return 1;
        if (uplevel != o.uplevel)
            return uplevel - o.uplevel;
        int n = Math.min(segments.length, o.segments.length);
        for (int i = 0; i < n; i++) {
            int c = segments[i].compareTo(o.segments[i]);
            if (c != 0)
                return c;
        }
        return segments.length - o.segments.length;
    }

}
