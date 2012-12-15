package net.bodz.bas.vfs.path;

import java.util.Arrays;

import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.vfs.path.align.IPathAlignment;
import net.bodz.bas.vfs.path.align.ParentAlignment;

public class RelativePath
        extends MultiEntryPath {

    private static final long serialVersionUID = 1L;

    // int parents;
    IPathAlignment alignment;

    public RelativePath(String[] entries, boolean entered) {
        this(0, entries, entered);
    }

    public RelativePath(int parents, String[] entries, boolean entered) {
        super(entries, entered);
        this.alignment = new ParentAlignment(parents);
    }

    public RelativePath(IPathAlignment alignment, String[] entries, boolean entered) {
        super(entries, entered);

        if (alignment == null)
            throw new NullPointerException("alignment");

        this.alignment = alignment;
    }

    @Override
    public String getProtocol() {
        return null;
    }

    @Override
    public IPathAlignment getAlignment() {
        return alignment;
    }

    @Override
    public RelativePath getRoot() {
        return new RelativePath(IPathAlignment.ROOT, new String[0], true);
    }

    @Override
    public IPath getParent(int n) {
        if (n == 0)
            return this;

        if (entries.length >= n) {
            String[] parentEntries = Arrays.copyOf(entries, entries.length - n);
            return createLocal(parentEntries, false); // with the same alignment.
        }

        if (getAlignment() == IPathAlignment.ROOT)
            return createLocal(new String[0], true);

        ParentAlignment alignment = (ParentAlignment) getAlignment();
        int moreParents = n - entries.length;
        int parents = alignment.getParents() + moreParents;
        return new RelativePath(parents, new String[0], false);
    }

    /**
     * @param entries
     *            The local entries. These entries should not start with "." or ".." entries.
     */
    @Override
    protected IPath createLocal(String[] entries, boolean entered)
            throws BadPathException {
        return new RelativePath(getAlignment(), entries, entered);
    }

    public static RelativePath parse(String pathstr) {
        return parse(0, pathstr);
    }

    public static RelativePath parse(int parents, String pathstr) {
        if (pathstr == null)
            throw new NullPointerException("pathstr");

        IPathAlignment alignment = null;

        boolean entered = pathstr.endsWith("/");

        if (pathstr.startsWith(SEPARATOR)) {
            alignment = IPathAlignment.ROOT;
            pathstr = pathstr.substring(SEPARATOR_LEN);
        }
        while (pathstr.endsWith(SEPARATOR))
            pathstr = pathstr.substring(0, pathstr.length() - SEPARATOR_LEN);

        int slash;
        L: while (!pathstr.isEmpty()) {
            slash = pathstr.indexOf(SEPARATOR_CHAR);
            switch (slash) {
            case -1:
                break L;
            case 0:
                break;
            case 1:
                if (pathstr.charAt(0) == '.') // "./", ignore
                    break;
                else
                    break L;
            case 2:
                if (pathstr.charAt(0) == '.' && pathstr.charAt(1) == '.') { // "../", parent+1
                    parents++;
                    break;
                } else
                    break L;
            default:
                break L;
            }
            pathstr = pathstr.substring(slash + 1);
        }

        if (alignment == null)
            alignment = new ParentAlignment(parents);

        String[] entries = StringArray.splitRaw(pathstr, SEPARATOR);
        return new RelativePath(alignment, entries, entered);
    }

    @Override
    protected String formatLocal(PathFormat format) {
        String localPath = StringArray.join(SEPARATOR, entries);
        String alignFormat = getAlignment().format(localPath);
        return alignFormat;
    }

}
