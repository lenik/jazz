package net.bodz.bas.vfs.impl.url;

import net.bodz.bas.vfs.path.BadPathException;

public class PlainURLPath
        extends URLPath {

    private static final long serialVersionUID = 1L;

    public PlainURLPath(String scheme, String localPath) {
        super(scheme, localPath);
    }

    public PlainURLPath(String scheme, String[] entries) {
        super(scheme, entries);
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
    protected PlainURLPath createLocal(String[] entries)
            throws BadPathException {
        return new PlainURLPath(scheme, entries);
    }

    @Override
    protected PlainURLPath createLocal(String localPath)
            throws BadPathException {
        return new PlainURLPath(scheme, localPath);
    }

}
