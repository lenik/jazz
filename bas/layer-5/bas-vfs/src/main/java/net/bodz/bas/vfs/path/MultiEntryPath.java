package net.bodz.bas.vfs.path;

public class MultiEntryPath
        extends AbstractMultiEntryPath {

    private static final long serialVersionUID = 1L;

    private final String protocol;

    public MultiEntryPath(String protocol, String localPath) {
        super(localPath);
        this.protocol = protocol;
    }

    public MultiEntryPath(String protocol, String[] entries, boolean entered) {
        super(entries, entered);
        this.protocol = protocol;
    }

    @Override
    public String getProtocol() {
        return protocol;
    }

    @Override
    protected IPath createLocal(String[] entries, boolean entered)
            throws BadPathException {
        return new MultiEntryPath(protocol, entries, entered);
    }

}
