package net.bodz.bas.t.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.err.OutOfDomainException;

/**
 * Segment is always trimmed, and can't be:
 * <ul>
 * <li>empty string</li>
 * </ul>
 */
public class PathEntries
        implements Comparable<PathEntries>, Serializable {

    private static final long serialVersionUID = -7257088263065852283L;

    public static final String ENTRY_SEPARATOR = "/";
    private String separator = "/";

    public static final int ABSOLUTE = -1;
    private final int parentCount;

    private final String[] entries;

    /**
     * @param path
     *            will be canonicalized.
     */
    public PathEntries(String path) {
        this(ENTRY_SEPARATOR, path, true);
    }

    /**
     * Canonicalization:
     * <ul>
     * <li>empty entries are removed, so any trailing / are ignored.
     * <li>., .. are reduced when possible.
     * </ul>
     * XXX - escapes...
     */
    public PathEntries(String separator, String path, boolean canonicalize) {
        this(path.startsWith(separator) ? ABSOLUTE : 0, //
                (path.startsWith(separator) ? path.substring(1) : path).split(separator), //
                canonicalize);
    }

    public PathEntries(PathEntries parent, String child) {
        this(parent, new PathEntries(child));
    }

    public PathEntries(PathEntries parent, PathEntries child) {
        if (parent == null || child.isAbsolute()) {
            parentCount = child.parentCount;
            entries = child.entries;
        } else {
            int reduce = Math.min(parent.entries.length, child.parentCount);
            int pn = parent.entries.length - reduce;
            entries = new String[pn + child.entries.length];
            if (parent.isAbsolute())
                // if (child.parentCount > 0) throw new IllegalStateException();
                parentCount = ABSOLUTE;
            else
                parentCount = parent.parentCount + child.parentCount - reduce;
            System.arraycopy(parent.entries, 0, entries, 0, pn);
            System.arraycopy(child.entries, 0, entries, pn, child.entries.length);
        }
    }

    PathEntries(int parentCount, String[] entries, boolean canonicalize) {
        if (entries == null)
            throw new NullPointerException("entries");
        if (parentCount < -1)
            throw new OutOfDomainException("parentCount", parentCount, -1);
        if (canonicalize) {
            List<String> entryList = new ArrayList<String>(entries.length);
            for (int i = 0; i < entries.length; i++) {
                String s = entries[i];
                assert s != null : "path entry[" + i + "] is null";
                if (s.isEmpty() || ".".equals(s))
                    continue;
                if ("..".equals(s)) {
                    if (entryList.isEmpty())
                        if (isAbsolute())
                            ; // throw new IllegalStateException();
                        else
                            parentCount++;
                    else
                        entryList.remove(entryList.size() - 1);
                    continue;
                }
                // normalize(s)?...
                entryList.add(s);
            }
            entries = entryList.toArray(new String[0]);
        }
        this.parentCount = parentCount;
        this.entries = entries;
    }

    public boolean isAbsolute() {
        return parentCount == ABSOLUTE;
    }

    public boolean isRelative() {
        return !isAbsolute();
    }

    public int getParentCount() {
        return parentCount;
    }

    public PathEntries getCanonical() {
        return new PathEntries(parentCount, entries, true);
    }

    public PathEntries getParent() {
        if (entries.length > 0)
            return new PathEntries(parentCount, Arrays.copyOf(entries, entries.length - 1), false);
        else if (isAbsolute())
            // return this; // root/.. = root
            return null; // root/.. = null
        else
            return new PathEntries(parentCount + 1, entries, false);
    }

    public String getPath() {
        StringBuilder buf = new StringBuilder(entries.length * 30);
        if (parentCount == ABSOLUTE)
            buf.append(separator);
        else
            for (int i = 0; i < parentCount; i++) {
                buf.append("..");
                buf.append(separator);
            }
        for (int i = 0; i < entries.length; i++) {
            if (i != 0)
                buf.append(separator);
            buf.append(entries[i]);
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
        h += 0xf05a497c * parentCount;
        h += Arrays.hashCode(entries);
        return h;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PathEntries))
            return false;
        PathEntries p = (PathEntries) obj;
        if (parentCount != p.parentCount)
            return false;
        return Arrays.equals(entries, p.entries);
    }

    @Override
    public int compareTo(PathEntries o) {
        if (o == null)
            return 1;
        if (parentCount != o.parentCount)
            return parentCount - o.parentCount;
        int n = Math.min(entries.length, o.entries.length);
        for (int i = 0; i < n; i++) {
            int c = entries[i].compareTo(o.entries[i]);
            if (c != 0)
                return c;
        }
        return entries.length - o.entries.length;
    }

}
