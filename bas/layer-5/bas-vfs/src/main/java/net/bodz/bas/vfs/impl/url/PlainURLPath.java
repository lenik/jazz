package net.bodz.bas.vfs.impl.url;

import net.bodz.bas.vfs.path.BadPathException;

public class PlainURLPath
        extends URLPath {

    private static final long serialVersionUID = 1L;

    public PlainURLPath(String scheme, String localPath) {
        super(scheme, localPath);
    }

    public PlainURLPath(String scheme, String[] entries, boolean entered) {
        super(scheme, entries, entered);
    }

    @Override
    public String getDeviceSpec() {
        return "";
    }

    @Override
    public String getDeviceSpecSeparator() {
        return "/";
    }

    @Override
    protected PlainURLPath createLocal(String[] entries, boolean entered)
            throws BadPathException {
        return new PlainURLPath(scheme, entries, entered);
    }

    @Override
    protected PlainURLPath createLocal(String localPath)
            throws BadPathException {
        return new PlainURLPath(scheme, localPath);
    }

}
