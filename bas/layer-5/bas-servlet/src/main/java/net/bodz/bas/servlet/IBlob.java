package net.bodz.bas.servlet;

import java.io.IOException;
import java.io.InputStream;

import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;

public interface IBlob {

    String getLocation();

    default String getPath() {
        String location = getLocation();
        return location;
    }

    default ContentType getContentType() {
        return ContentTypes.application_octet_stream;
    }

    default String getEncoding() {
        return null;
    }

    default Long getLength()
            throws IOException {
        return null;
    }

    default Long getLastModified()
            throws IOException {
        return null;
    }

    default String getFilename() {
        return null;
    }

    default String getDescription()
            throws IOException {
        return null;
    }

    InputStream openStream()
            throws IOException;

}
