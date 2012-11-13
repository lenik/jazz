package net.bodz.bas.vfs.path;

import net.bodz.bas.c.string.StringArray;

public abstract class MultiEntryPath
        extends AbstractPath {

    private static final long serialVersionUID = 1L;

    String[] entries;

    public MultiEntryPath(String localPath) {
        setLocalPath(localPath);
    }

    public MultiEntryPath(String[] entries) {
        setLocalEntries(entries);
    }

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
        entries = localPath.split(SEPARATOR);
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

}