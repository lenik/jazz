package net.bodz.bas.vfs.path;

public abstract class ManagedPath<self_t extends ManagedPath<?>>
        extends AbstractPath {

    private static final long serialVersionUID = 1L;

    self_t parent;
    String baseName;

    public ManagedPath(self_t parent, String baseName) {
        if (baseName == null)
            throw new NullPointerException("baseName");
        this.parent = parent;
        this.baseName = baseName;
    }

    @Override
    public IPath getParent() {
        return parent;
    }

    @Override
    public int getLocalEntryCount() {
        int n = 0;
        IPath node = this;
        while (node != null) {
            node = node.getParent();
            n++;
        }
        return n;
    }

    @Override
    public String[] getLocalEntries() {
        int n = getLocalEntryCount();
        String[] entries = new String[n];
        IPath node = this;
        for (int i = 1; i <= n; i++) {
            entries[n - i] = node.getBaseName();
            node = node.getParent();
        }
        return entries;
    }

    @Override
    public String getLocalEntry(int index) {
        IPath node = this;
        for (int i = 0; i < index; i++)
            node = node.getParent();
        return node.getBaseName();
    }

    @Override
    public String getLocalPath() {
        int n = getLocalEntryCount();
        StringBuilder buf = null;
        IPath node = this;
        for (int i = 0; i < n; i++) {
            if (buf == null)
                buf = new StringBuilder(n * 30);
            else
                buf.append(SEPARATOR);
            buf.append(node.getBaseName());
            node = node.getParent();
        }
        return buf.toString();
    }

}
