package net.bodz.bas.servlet;

import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;

import java.nio.charset.Charset;

public abstract class AbstractBlob
        implements IMutableBlob {

    String filename;
    String description;
    Charset charset;
    ContentType contentType ;

    @Override
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Charset getCharset() {
        return charset;
    }

    @Override
    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    @Override
    public ContentType getContentType() {
        if (contentType == null) {
            String filename = getFilename();
            return ContentType.forName(filename);
        }
        else {
            return contentType;
        }
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

}
