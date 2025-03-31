package net.bodz.bas.vfs.path;

import java.util.Arrays;
import java.util.List;

import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.meta.decl.NotNull;

public abstract class AbstractMultiEntryPath
        extends AbstractPath
        implements IMutablePathEntries {

    private static final long serialVersionUID = 1L;

    protected String[] entries;
    boolean entered;

    public AbstractMultiEntryPath(String localPath) {
        setLocalPath(localPath);
    }

    public AbstractMultiEntryPath(String[] entries, boolean entered) {
        if (entries == null)
            throw new NullPointerException("entries");
        this.entered = entered;
        this.entries = entries;
    }

    @Override
    public boolean isEntered() {
        return entered;
    }

    @NotNull
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

    @Override
    public String[] getEntryArray() {
        return entries;
    }

    @Override
    public List<String> getEntryList() {
        return Arrays.asList(entries);
    }

    @Override
    public int getEntryCount() {
        return entries.length;
    }

    @Override
    public String getEntry(int index) {
        return entries[index];
    }

    @Override
    public void setEntry(int index, String entry) {
        entries[index] = entry;
    }

    @NotNull
    @Override
    public String joinEntries(String sep) {
        return StringArray.join(sep, entries);
    }

    void setEntries(@NotNull String[] entries) {
        this.entries = entries;
    }

    @NotNull
    @Override
    public String getLocalPath() {
        String path = joinEntries();
        if (isEntered())
            path += SEPARATOR;
        return path;
    }

    void setLocalPath(String localPath) {
        if (localPath == null)
            throw new NullPointerException("localPath");

        boolean entered = localPath.endsWith(SEPARATOR);

        while (localPath.endsWith(SEPARATOR))
            localPath = localPath.substring(0, localPath.length() - SEPARATOR_LEN);

        if (localPath.isEmpty()) {
            entries = new String[0];
            this.entered = true;
        } else {
            entries = StringArray.splitRaw(localPath, SEPARATOR);
            this.entered = entered;
        }
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