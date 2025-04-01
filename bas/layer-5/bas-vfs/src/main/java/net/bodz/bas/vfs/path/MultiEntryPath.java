package net.bodz.bas.vfs.path;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.pojo.PathEntries;

/**
 * @TODO Replace {@link PathEntries}.
 */
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

    @NotNull
    @Override
    protected IPath createLocal(String[] entries, boolean entered)
            throws BadPathException {
        return new MultiEntryPath(protocol, entries, entered);
    }

}
