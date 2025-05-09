package net.bodz.bas.vfs.path;

import net.bodz.bas.err.ParseException;
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

    public static MultiEntryPath parse(String s)
            throws ParseException {
        String protocol = null;
        String deviceSpec = null;
        int css = s.indexOf("://");
        if (css != -1) {
            protocol = s.substring(0, css);
            s = s.substring(css + 3);

            int slash = s.indexOf('/');
            if (slash != -1) {
                deviceSpec = s.substring(0, slash);
                s = s.substring(slash + 1);
            }
        }

        boolean entered = false;
        while (s.endsWith("/")) {
            s = s.substring(0, s.length() - 1);
            entered = true;
        }

        String[] entries = s.split("/");
        MultiEntryPath path = new MultiEntryPath(protocol, entries, entered);
        // path.setDeviceSpec();
        return path;
    }

}
