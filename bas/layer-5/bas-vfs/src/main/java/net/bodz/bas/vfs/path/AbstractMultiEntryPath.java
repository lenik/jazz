package net.bodz.bas.vfs.path;

import java.util.Arrays;

import net.bodz.bas.c.string.StringArray;

public abstract class AbstractMultiEntryPath
        extends AbstractPath {

    private static final long serialVersionUID = 1L;

    protected String[] entries;

    public AbstractMultiEntryPath(String localPath) {
        setLocalPath(localPath);
    }

    public AbstractMultiEntryPath(String[] entries, boolean entered) {
        super(entered);
        if (entries == null)
            throw new NullPointerException("entries");
        setLocalEntries(entries);
    }

    @Override
    protected IPath createLocal(String localPath)
            throws BadPathException {
        boolean entered = localPath.endsWith(SEPARATOR);

        while (localPath.startsWith(SEPARATOR))
            localPath = localPath.substring(SEPARATOR_LEN);

        if (entered)
            do {
                localPath = localPath.substring(0, localPath.length() - SEPARATOR_LEN);
            } while (localPath.endsWith(SEPARATOR));

        String[] entries = StringArray.splitRaw(localPath, SEPARATOR);
        return createLocal(entries, entered);
    }

    protected abstract IPath createLocal(String[] entries, boolean entered)
            throws BadPathException;

    @Override
    public String getLocalPath() {
        return StringArray.join(SEPARATOR, entries);
    }

    void setLocalPath(String localPath) {
        if (localPath == null)
            throw new NullPointerException("localPath");

        boolean entered = localPath.endsWith(SEPARATOR);

        while (localPath.endsWith(SEPARATOR))
            localPath = localPath.substring(0, localPath.length() - SEPARATOR_LEN);

        if (localPath.isEmpty()) {
            entries = new String[0];
            setEntered(true);
        } else {
            entries = StringArray.splitRaw(localPath, SEPARATOR);
            setEntered(entered);
        }
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
        return createLocal(parentEntries, false);
    }

}