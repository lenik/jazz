package net.bodz.bas.vfs.path;

public abstract class MultiSessionPathParser
        extends AbstractPathParser {

    @Override
    public IPath parse(String path)
            throws BadPathException {
        String session;
        String localPath;

        int slash = path.indexOf('/');
        if (slash == -1) {
            session = path;
            localPath = "";
        } else {
            session = path.substring(0, slash);
            localPath = path.substring(slash + 1);
        }

        return parse(session, localPath);
    }

    public abstract IPath parse(String session, String path)
            throws BadPathException;

}
