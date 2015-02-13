package net.bodz.bas.std.rfc.mime;

import java.io.File;
import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

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

    public void applyTo(HttpServletResponse response, ContentType... excludes) {
        for (ContentType exclude : excludes)
            if (this == exclude)
                return;
        response.setContentType(name);
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

    public static final ContentType DEFAULT = ContentTypes.application_octet_stream;

    public static ContentType forFile(File file) {
        return forName(file.getName());
    }

    public static ContentType forPath(String path) {
        if (path == null)
            throw new NullPointerException("path");
        String extension = FilePath.getExtension(path);
        if (extension != null) {
            return forExtension(extension);
        } else {
            String name = FilePath.getBaseName(path);
            return forName(name);
        }
    }

    public static ContentType forName(String name) {
        ContentType contentType = ContentTypes.nameMap.get(name);
        return contentType == null ? DEFAULT : contentType;
    }

    /**
     * @param extension
     *            Without dot.
     */
    public static ContentType forExtension(String extension) {
        if (extension == null)
            throw new NullPointerException("extension");
        extension = extension.toLowerCase();
        ContentType contentType = ContentTypes.extensionMap.get(extension);
        return contentType == null ? DEFAULT : contentType;
    }

}
