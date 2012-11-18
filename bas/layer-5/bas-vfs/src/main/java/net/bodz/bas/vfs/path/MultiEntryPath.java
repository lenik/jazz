package net.bodz.bas.vfs.path;

import java.util.Arrays;

import net.bodz.bas.c.string.StringArray;

public abstract class MultiEntryPath
        extends AbstractPath {

    private static final long serialVersionUID = 1L;

    String[] entries;

    public MultiEntryPath(String localPath) {
        setLocalPath(localPath);
    }

    public MultiEntryPath(String[] entries) {
        if (entries == null)
            throw new NullPointerException("entries");
        setLocalEntries(entries);
    }

    @Override
    protected IPath createLocal(String localPath)
            throws BadPathException {
        String[] entries = StringArray.splitRaw(localPath, "/");
        return createLocal(entries);
    }

    protected abstract IPath createLocal(String[] entries)
            throws BadPathException;

    @Override
    public String getLocalPath() {
        return StringArray.join(SEPARATOR, entries);
    }

    /**
     * @TODO SEPARATOR should be escaped.
     */
    void setLocalPath(String localPath) {
        if (localPath == null)
            throw new NullPointerException("localPath");
        if (localPath.isEmpty())
            entries = new String[0];
        else
            entries = StringArray.splitRaw(localPath, SEPARATOR);
    }

    @Override
    public String[] getLocalEntries() {
        return entries;
    }

    void setLocalEntries(String... entries) {
        if (entries == null)
            throw new NullPointerException("entries");
        this.entries = entries;
    }

    @Override
    public final IPath getParent() {
        return getParent(1);
    }

    @Override
    public IPath getParent(int n) {
        if (entries.length < n)
            return null;
        String[] parentEntries = Arrays.copyOf(entries, entries.length - n);
        return createLocal(parentEntries);
    }

}