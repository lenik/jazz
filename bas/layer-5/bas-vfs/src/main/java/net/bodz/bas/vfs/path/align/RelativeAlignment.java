package net.bodz.bas.vfs.path.align;

import net.bodz.bas.vfs.path.IPath;

public class RelativeAlignment
        extends TruncateOnlyAlignment {

    private static final long serialVersionUID = 1L;

    public RelativeAlignment() {
    }

    @Override
    public IPath align(IPath context) {
        return context;
    }

    @Override
    public String format(String barePath) {
        return barePath;
    }

}
