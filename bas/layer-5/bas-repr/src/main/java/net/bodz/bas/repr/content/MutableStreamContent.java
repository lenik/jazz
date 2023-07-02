package net.bodz.bas.repr.content;

import net.bodz.bas.std.rfc.mime.ContentType;

public abstract class MutableStreamContent
        extends MutableContent
        implements
            IStreamContent {

    String fileName;
    ContentType contentType;
    String encoding = "utf-8";

    public MutableStreamContent(ContentType contentType) {
        this(null, contentType);
    }

    public MutableStreamContent(String fileName, ContentType contentType) {
        this.fileName = fileName;
        this.contentType = contentType;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    @Override
    public Long getContentLength() {
        return null;
    }

    @Override
    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

}
