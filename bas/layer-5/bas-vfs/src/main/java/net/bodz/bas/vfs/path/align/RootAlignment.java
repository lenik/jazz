package net.bodz.bas.vfs.path.align;

import net.bodz.bas.vfs.path.IPath;

public class RootAlignment
        extends TruncateOnlyAlignment {

    private static final long serialVersionUID = 1L;

    @Override
    public IPath align(IPath context) {
        return context.getRoot();
    }

    @Override
    public String format(String barePath) {
        return "/" + barePath;
    }

}
