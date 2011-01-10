package net.bodz.bas.vfs.path.align;

import net.bodz.bas.vfs.path.IPath;

public class RelativeAlignment
        extends TruncateOnlyAlignment {

    @Override
    public IPath align(IPath context) {
        return context;
    }

    @Override
    public String decorate(String localPath) {
        return localPath;
    }

}
