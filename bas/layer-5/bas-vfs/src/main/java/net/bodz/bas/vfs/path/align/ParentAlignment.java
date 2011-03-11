package net.bodz.bas.vfs.path.align;

import net.bodz.bas.vfs.path.IPath;

public class ParentAlignment
        extends TruncateOnlyAlignment {

    private final int parents;
    private final boolean keepInRoot;

    public ParentAlignment(int parents) {
        this(parents, false);
    }

    /**
     * @param keepInRoot
     *            If <code>true</code> and the result parent is <code>null</code>, the root is
     *            returned instead.
     */
    public ParentAlignment(int parents, boolean keepInRoot) {
        if (parents < 0)
            throw new IllegalArgumentException("parents is negative: " + parents);
        this.parents = parents;
        this.keepInRoot = keepInRoot;
    }

    @Override
    public IPath align(IPath context) {
        IPath parent = context.getParent(parents);
        if (parent == null)
            if (keepInRoot)
                return context.getRoot();
            else
                return null;
        else
            return parent;
    }

    @Override
    public String decorate(String localPath) {
        StringBuilder buf = new StringBuilder(parents * 3 + localPath.length());
        for (int i = 0; i < parents; i++)
            buf.append("../");
        buf.append(localPath);
        return buf.toString();
    }

}
