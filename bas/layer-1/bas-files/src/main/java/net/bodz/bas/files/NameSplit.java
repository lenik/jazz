package net.bodz.bas.files;

import java.io.Serializable;

import net.bodz.bas.lang.Nullables;

public class NameSplit
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private String base;
    private String extension;

    /**
     * @throws NullPointerException
     *             If <code>base</code> is <code>null</code>.
     */
    public NameSplit(String base, String extension) {
        if (base == null)
            throw new NullPointerException("base");
        this.base = base;
        this.extension = extension;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        if (base == null)
            throw new NullPointerException("base");
        this.base = base;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public boolean hasExtension() {
        return extension != null;
    }

    public String getName() {
        if (extension == null)
            return base;
        return base + "." + extension;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NameSplit))
            return false;
        NameSplit o = (NameSplit) obj;
        if (!base.equals(o.base))
            return false;
        if (!Nullables.equals(extension, o.extension))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = base.hashCode();
        if (extension != null)
            hash += extension.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return getName();
    }

}
