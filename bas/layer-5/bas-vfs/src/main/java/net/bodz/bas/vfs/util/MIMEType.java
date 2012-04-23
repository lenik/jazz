package net.bodz.bas.vfs.util;

import java.io.Serializable;

public class MIMEType
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String contentType;
    private final String preferredExtension;

    public MIMEType(String contentType, String preferredExtension) {
        if (contentType == null)
            throw new NullPointerException("contentType");
        if (preferredExtension == null)
            throw new NullPointerException("preferredExtension");
        this.contentType = contentType;
        this.preferredExtension = preferredExtension;
    }

    public String getContentType() {
        return contentType;
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
        result = prime * result + ((contentType == null) ? 0 : contentType.hashCode());
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

        MIMEType other = (MIMEType) obj;

        if (contentType == null) {
            if (other.contentType != null)
                return false;
        } else if (!contentType.equals(other.contentType))
            return false;

        if (preferredExtension == null) {
            if (other.preferredExtension != null)
                return false;
        } else if (!preferredExtension.equals(other.preferredExtension))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return contentType;
    }

    public static MIMEType getInstance(String contentType) {
        return MIMETypes.contentTypeMap.get(contentType);
    }

    public static MIMEType getInstanceByExtension(String extension) {
        if (extension == null)
            return null;
        extension = extension.toLowerCase();
        return MIMETypes.extensionMap.get(extension);
    }

}
