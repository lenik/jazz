package net.bodz.bas.vfs.impl.filter;

import java.util.Arrays;
import java.util.List;

import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.vfs.IFileSystem;
import net.bodz.bas.vfs.VFS;
import net.bodz.bas.vfs.path.AbstractPath;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class FilterPath
        extends AbstractPath {

    private static final long serialVersionUID = 1L;
    public static final String SCOPE_SEPARATOR = ":";

    String protocol;
    String filterSpec;
    String encodedPath;

    public FilterPath(String protocol, String filterSpec, String encodedPath) {
        if (filterSpec == null)
            throw new NullPointerException("filterSpec");
        this.protocol = protocol;
        this.filterSpec = filterSpec;

        while (encodedPath.endsWith(SEPARATOR))
            encodedPath = encodedPath.substring(0, encodedPath.length() - SEPARATOR_LEN);
        this.encodedPath = encodedPath;
    }

    @Override
    public boolean isEntered() {
        return encodedPath.endsWith(SEPARATOR);
    }

    @Override
    public String getProtocol() {
        return protocol;
    }

    @Override
    public String getDeviceSpec() {
        return filterSpec;
    }

    @Override
    public String[] getEntryArray() {
        String esc = encodedPath;
        while (esc.endsWith(SEPARATOR))
            esc = esc.substring(0, esc.length() - 1);
        return StringArray.splitRaw(esc, "/");
    }

    @Override
    public List<String> getEntryList() {
        return Arrays.asList(getEntryArray());
    }

    @Override
    public int getEntryCount() {
        return getEntryArray().length;
    }

    @Override
    public String getEntry(int index) {
        String[] entries = getEntryArray();
        return entries[index];
    }

    @NotNull
    @Override
    public String joinEntries(String sep) {
        String s = encodedPath;
        int sepLen = sep.length();
        if (s.endsWith(sep))
            s = s.substring(0, s.length() - sepLen);
        return s;
    }

    @NotNull
    @Override
    public String getLocalPath() {
        return encodedPath;
    }

    @Override
    public IPath getParentLayer() {
        IFileSystem system = VFS.getFileSystem(); // parameter?
        FilterVfsDriver driver = (FilterVfsDriver) system.getDriver(protocol);
        FilterVfsDevice device = driver.getDevice(filterSpec);
        IVfsFilter filter = device.getFilter();
        IPath destPath = filter.decodePath(encodedPath);
        return destPath;
    }

    @NotNull
    @Override
    protected FilterPath createLocal(String localPath)
            throws BadPathException {
        return new FilterPath(protocol, filterSpec, localPath);
    }

    @NotNull
    @Override
    protected IPath createLocal(String[] localEntries, boolean entered) {
        String localPath = StringArray.join(SEPARATOR, localEntries);
        return createLocal(localPath);
    }

}
