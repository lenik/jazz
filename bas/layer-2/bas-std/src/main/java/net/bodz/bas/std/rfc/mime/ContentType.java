package net.bodz.bas.std.rfc.mime;

import java.io.Serializable;

import net.bodz.bas.c.java.io.FilePath;

public class ContentType
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;
    private final String preferredExtension;

    public ContentType(String name, String preferredExtension) {
        if (name == null)
            throw new NullPointerException("name");
        if (preferredExtension == null)
            throw new NullPointerException("preferredExtension");
        this.name = name.intern();
        this.preferredExtension = preferredExtension.intern();
    }

    public String getName() {
        return name;
    }

    /**
     * The preferred extension name.
     *
     * @return The extension name, without the dot(.).
     */
    public String getPreferredExtension() {
        return preferredExtension;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((preferredExtension == null) ? 0 : preferredExtension.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        ContentType o = (ContentType) obj;
        if (name != o.name)
            return false;
        if (preferredExtension != o.preferredExtension)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    public static ContentType forName(String name) {
        return ContentTypes.nameMap.get(name);
    }

    public static ContentType forPath(String path) {
        if (path == null)
            throw new NullPointerException("path");
        String extension = FilePath.getExtension(path);
        return forExtension(extension);
    }

    /**
     * @param extension
     *            Without dot.
     */
    public static ContentType forExtension(String extension) {
        if (extension == null)
            return null;
        extension = extension.toLowerCase();
        return ContentTypes.extensionMap.get(extension);
    }

}
