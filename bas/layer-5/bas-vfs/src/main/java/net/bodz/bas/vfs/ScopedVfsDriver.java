package net.bodz.bas.vfs;

import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public abstract class ScopedVfsDriver
        extends AbstractVfsDriver {

    String SCOPE_SEPARATOR = getScopeSeparator();

    protected String getScopeSeparator() {
        return IPath.SEPARATOR;
    }

    @Override
    protected IPath _parse(String protocol, String _path) {
        String scope;
        String localPath;

        int slash = _path.indexOf(SCOPE_SEPARATOR);
        if (slash == -1) {
            scope = _path;
            localPath = "";
        } else {
            scope = _path.substring(0, slash);
            localPath = _path.substring(slash + 1);
        }

        return parse(scope, localPath);
    }

    protected abstract IPath parse(String scope, String localPath)
            throws BadPathException;

}
