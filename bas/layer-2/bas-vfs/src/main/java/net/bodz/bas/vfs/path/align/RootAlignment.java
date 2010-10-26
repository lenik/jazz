package net.bodz.bas.vfs.path.align;

import net.bodz.bas.vfs.path.IPath;

public class RootAlignment
        extends TruncateOnlyAlignment {

    @Override
    public IPath align(IPath context) {
        return context.getRoot();
    }

}
