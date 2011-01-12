package net.bodz.bas.util.file;

import java.io.Serializable;

import net.bodz.bas.util.Nullables;

public class NameExtension
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String extension;

    /**
     * @throws NullPointerException
     *             If <code>name</code> is <code>null</code>.
     */
    public NameExtension(String name, String extension) {
        if (name == null)
            throw new NullPointerException("name");
        this.name = name;
        this.extension = extension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null)
            throw new NullPointerException("name");
        this.name = name;
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

    public String join() {
        if (extension == null)
            return name;
        return name + "." + extension;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NameExtension))
            return false;
        NameExtension o = (NameExtension) obj;
        if (!name.equals(o.name))
            return false;
        if (!Nullables.equals(extension, o.extension))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = name.hashCode();
        if (extension != null)
            hash += extension.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return join();
    }

}
