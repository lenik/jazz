package net.bodz.bas.vfs;

import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public abstract class MultiSessionVfsDriver
        extends AbstractVfsDriver {

    String SESSION_SEPARATOR = IPath.SEPARATOR;

    @Override
    protected IPath _parse(String protocol, String _path) {
        String session;
        String localPath;

        int slash = _path.indexOf(SESSION_SEPARATOR);
        if (slash == -1) {
            session = _path;
            localPath = "";
        } else {
            session = _path.substring(0, slash);
            localPath = _path.substring(slash + 1);
        }

        return parse(session, localPath);
    }

    protected abstract IPath parse(String session, String localPath)
            throws BadPathException;

}
