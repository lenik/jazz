package net.bodz.bas.vfs.path;

public class ProtocolPath
        extends MultiEntryPath {

    private static final long serialVersionUID = 1L;

    private final String protocol;

    public ProtocolPath(String protocol, String localPath) {
        super(localPath);
        this.protocol = protocol;
    }

    public ProtocolPath(String protocol, String[] entries) {
        super(entries);
        this.protocol = protocol;
    }

    @Override
    public String getProtocol() {
        return protocol;
    }

    @Override
    protected IPath createLocal(String[] entries)
            throws BadPathException {
        return new ProtocolPath(protocol, entries);
    }

}
