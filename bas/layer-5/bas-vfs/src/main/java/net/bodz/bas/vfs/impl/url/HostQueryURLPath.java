package net.bodz.bas.vfs.impl.url;

import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.PathFormat;

public class HostQueryURLPath
        extends URLPath {

    private static final long serialVersionUID = 1L;

    // private final String drive;
    private final String realPath;

    public HostQueryURLPath(String scheme, String localPath) {
        super(scheme, localPath);

        int quest = localPath.lastIndexOf('?');
        if (quest != -1) {
            query = localPath.substring(quest + 1);
            realPath = localPath.substring(0, quest);
        } else {
            realPath = localPath;
        }
    }

    public HostQueryURLPath(String scheme, String[] entries, boolean entered) {
        super(scheme, entries, entered);

        String localPath = getLocalPath();
        int quest = localPath.lastIndexOf('?');
        if (quest != -1) {
            query = localPath.substring(quest + 1);
            realPath = localPath.substring(0, quest);
        } else {
            realPath = localPath;
        }
    }

    @Override
    protected HostQueryURLPath createLocal(String[] entries, boolean entered)
            throws BadPathException {
        return new HostQueryURLPath(realPath, entries, entered);
    }

    @Override
    protected HostQueryURLPath createLocal(String localPath)
            throws BadPathException {
        return new HostQueryURLPath(realPath, localPath);
    }

    public String getRealBaseName() {
        int slash = realPath.lastIndexOf('/');
        if (slash != -1)
            return realPath.substring(slash + 1);
        else
            return realPath;
    }

    @Override
    protected String formatLocal(PathFormat format) {
        String result;
        if (format.isQuery()) {
            result = getLocalPath();
        } else {
            result = realPath;
        }
        if (format.getEncodeOptions() != 0) {
            // format.getEncodeCharset();
            // ...
        }
        return result;
    }

}
