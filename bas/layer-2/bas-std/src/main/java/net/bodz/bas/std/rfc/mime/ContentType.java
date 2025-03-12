package net.bodz.bas.std.rfc.mime;

import java.io.File;
import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Nullable;

public class ContentType
        implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    public final String name;

    @NotNull
    public final String preferredExtension;

    @NotNull
    public final ContentCategory category;

    public ContentType(String name, String preferredExtension) {
        if (name == null)
            throw new NullPointerException("name");
        if (preferredExtension == null)
            throw new NullPointerException("preferredExtension");
        this.name = name.intern();
        this.preferredExtension = preferredExtension.intern();

        int slash = name.indexOf('/');
        String major = slash == -1 ? name : name.substring(0, slash);
        this.category = ContentCategory.parse(major);
    }

    @NotNull
    public String getName() {
        return name;
    }

    /**
     * The preferred extension name.
     *
     * @return The extension name, without the dot(.).
     */
    @NotNull
    public String getPreferredExtension() {
        return preferredExtension;
    }

    @NotNull
    public ContentCategory getCategory() {
        return category;
    }

    public boolean isText() {
        return category == ContentCategory.TEXT;
    }

    public boolean isImage() {
        return category == ContentCategory.IMAGE;
    }

    public boolean isAudio() {
        return category == ContentCategory.AUDIO;
    }

    public boolean isVideo() {
        return category == ContentCategory.VIDEO;
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
        result = prime * result + name.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof ContentType))
            return false;
        ContentType o = (ContentType) obj;
        if (!o.name.equals(name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    public static final ContentType DEFAULT = ContentTypes.application_octet_stream;

    @NotNull
    public static ContentType forFile(@NotNull File file) {
        return forName(file.getName());
    }

    @NotNull
    public static ContentType forPath(@NotNull String path) {
        String extension = FilePath.getExtension(path);
        if (extension != null) {
            return forExtension(extension);
        } else {
            String name = FilePath.getBaseName(path);
            return forName(name);
        }
    }

    @NotNull
    public static ContentType forName(@NotNull String name) {
        return forName(name, ContentType.DEFAULT);
    }

    @NotNull
    public static ContentType forName(@Nullable String name, @NotNull ContentType fallback) {
        if (name == null)
            return fallback;
        ContentType contentType = ContentTypes.nameMap.get(name);
        return contentType == null ? DEFAULT : contentType;
    }

    @NotNull
    public static ContentType forExtension(@NotNull String extension) {
        return forExtension(extension, ContentType.DEFAULT);
    }

    @NotNull
    public static ContentType forExtension(@Nullable String extension, @NotNull ContentType fallback) {
        if (extension == null)
            return fallback;
        extension = extension.toLowerCase();
        ContentType contentType = ContentTypes.extensionMap.get(extension);
        return contentType == null ? DEFAULT : contentType;
    }

}
