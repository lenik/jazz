package net.bodz.bas.vfs;

import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public abstract class AbstractVfsProtocolHandler
        implements IVfsProtocolHandler {

    @Override
    public int getPriority() {
        return NORMAL_PRIORITY;
    }

    @Override
    public boolean accepts(String protocol) {
        return false;
    }

    @Override
    public IPath parse(String fqPath)
            throws BadPathException {
        String protocol;
        String qPath;
        int colon = fqPath.indexOf(':');

        if (colon == -1) {
            protocol = null;
            qPath = fqPath;
        } else {
            protocol = fqPath.substring(0, colon);
            qPath = fqPath.substring(colon + 1);
        }

        return _parse(protocol, qPath);
    }

    protected abstract IPath _parse(String protocol, String qPath)
            throws BadPathException;

}
