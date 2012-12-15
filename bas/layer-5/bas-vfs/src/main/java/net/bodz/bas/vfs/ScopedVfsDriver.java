package net.bodz.bas.vfs;

import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public abstract class ScopedVfsDriver
        extends AbstractVfsDriver {

    String scopeSeparator = getScopeSeparator();
    int scopeSepLen = scopeSeparator.length();

    protected String getScopeSeparator() {
        return IPath.SEPARATOR;
    }

    @Override
    protected IPath _parse(String protocol, String _path) {
        String scope;
        String localPath;

        int scopeSepPos = _path.indexOf(scopeSeparator);
        if (scopeSepPos == -1) {
            scope = _path;
            localPath = "";
        } else {
            scope = _path.substring(0, scopeSepPos);
            localPath = _path.substring(scopeSepPos + scopeSepLen);
        }

        return parse(scope, localPath);
    }

    protected abstract IPath parse(String scope, String localPath)
            throws BadPathException;

}
